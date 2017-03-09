<?php
/*
Consultar estado actual del usuario

Entrada
usuario: mín=3 chars, máx=30 chars
clave: mín=5 chars, máx=30 chars

Respuesta
estado: ok | no
mensaje: esta | no_esta.
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
    $conexion = mysqli_connect($_SERVIDOR, $_USUARIO, $_CLAVE, $_BD);
    if($conexion->connect_error){
        $estado = "no";
        $mensaje = "Error al conectar a la base de datos";
    }else{
        $consulta = "SELECT id FROM usuarios WHERE nombre='$usuario' AND clave=AES_ENCRYPT('$clave', '_io_laboratorio_')";
        $datos = mysqli_query($conexion, $consulta);
        if ($datos) {
            $fila = mysqli_fetch_array($datos);
            if($fila){
                $id_usuario = $fila['id'];
                if(ya_ingreso($id_usuario, $conexion)){
                    $estado = "ok";
                    $mensaje = "esta";
                }else{
                    $estado = "ok";
                    $mensaje = "no_esta";
                }
            }else{
                $estado = "no";
                $mensaje = "Usuario y/o clave incorrecta";
            }
        }else{
            $estado = "no";
            $mensaje = "Error al consultar el usuario";
        }
    }
}else{
    $estado = "no";
    $mensaje = "Parámetros incorrectos";
}

$a = array("estado" => $estado, "mensaje" => $mensaje);

echo json_encode($a);
