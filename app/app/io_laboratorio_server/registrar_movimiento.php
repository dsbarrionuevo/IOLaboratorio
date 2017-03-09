<?php
/*
Registrar un movimiento de un usuario.

Casos
Si el usuario ya ha ingresado entonces lo da de baja.
Si el usuario no ha ingresado entonces lo da de alta.

Entradas
usuario: mín=3 chars, máx=30 chars
clave: mín=5 chars, máx=30 chars

Respuesta
estado: ok | no
mensaje: Mensaje indicando la situación.
*/


require_once ('config.php');

define('MIN_CAR_US', 3);
define('MIN_CAR_CL', 5);
define('MAX_CAR_US', 30);
define('MAX_CAR_CL', 30);

$usuario = filter_input(INPUT_GET, 'usuario');
$clave = filter_input(INPUT_GET, 'clave');

function ya_ingreso($id_usuario, $conexion){
    $ingreso = false;
    $consulta = "SELECT COUNT(*) AS cantidad FROM historial WHERE id_usuario=$id_usuario AND fecha_hora_egreso IS NULL";
    $datos = mysqli_query($conexion, $consulta);
    if($datos){
        $fila = mysqli_fetch_array($datos);
        if($fila){
            $cantidad = $fila['cantidad'];
            if($cantidad == 1){
                $ingreso = true;
            }
        }
    }
    return $ingreso;
}

$mensaje = "";
$estado = "";
if(isset($usuario) && isset($clave) && 
    strlen($usuario) >= MIN_CAR_US && strlen($usuario) <= 30 &&
    strlen($clave) >= MIN_CAR_CL && strlen($clave) <= 30){
    $usuario = addslashes($usuario);
    $clave = addslashes($clave);
    
    $conexion = mysqli_connect($_SERVIDOR, $_USUARIO, $_CLAVE, $_BD);
    if($conexion->connect_error){
        $estado = "no";
        $mensaje = "Error al conectar a la base de datos";
    }else{
        // Verifico usuario.
        $consulta = "SELECT id FROM usuarios WHERE nombre='$usuario' AND clave=AES_ENCRYPT('$clave', '_io_laboratorio_')";
        $datos = mysqli_query($conexion, $consulta);
        if ($datos) {
            $fila = mysqli_fetch_array($datos);
            if($fila){
                $id_usuario = $fila['id'];
                if(ya_ingreso($id_usuario, $conexion)){
                    $actualizacion = "UPDATE historial SET fecha_hora_egreso=NOW() WHERE id_usuario=$id_usuario AND fecha_hora_egreso IS NULL";
                    mysqli_query($conexion, $actualizacion);
                    $estado = "ok";
                    $mensaje = "Salida registrada con éxito";
                }else{
                    $insercion = "INSERT INTO historial (id_usuario, fecha_hora_ingreso, fecha_hora_egreso) VALUES ($id_usuario, NOW(), null)";
                    mysqli_query($conexion, $insercion);
                    $estado = "ok";
                    $mensaje = "Ingreso registrado con éxito";
                }
            }else{
                $estado = "no";
                $mensaje = "Usuario y/o clave incorrecta";
            }
        }else{
            $estado = "no";
            $mensaje = "Error al consultar el usuario";
        }
        mysqli_close($conexion);
    }
}else{
    $estado = "no";
    $mensaje = "Parámetros incorrectos";
}

$a = array("estado" => $estado, "mensaje" => $mensaje);

echo json_encode($a);
