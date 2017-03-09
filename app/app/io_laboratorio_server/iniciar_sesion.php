<?php

/*
Verifica si existe un usuario con nombre de usuario y clave.

Entradas
usuario: mín=3 chars, máx=30 chars
clave: mín=5 chars, máx=30 chars

Respuesta
estado: ok | no
mensaje: Mensaje indicando la situación.
datos: {id, nombre}
*/

require_once ('config.php');

define('MIN_CAR_US', 3);
define('MIN_CAR_CL', 5);
define('MAX_CAR_US', 30);
define('MAX_CAR_CL', 30);

$usuario = filter_input(INPUT_GET, 'usuario');
$clave = filter_input(INPUT_GET, 'clave');

$mensaje = "";
$estado = "";
$datos = array();
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
        $consulta = "SELECT id, nombre, clave FROM usuarios WHERE nombre = '$usuario' AND clave = AES_ENCRYPT('$clave', '_io_laboratorio_')";
		$resultados = mysqli_query($conexion, $consulta);
        if ($resultados == false) {
			$estado = "no";
			$mensaje = "Error: usuario inexistente";
        }else{
            $estado = "ok";
			$resultado = mysqli_fetch_assoc($resultados);
			$datos = array("id" => $resultado["id"], "nombre" => $resultado["nombre"]); //, "clave" => $resultado["clave"]);
            $mensaje = "Verificado con éxito";
        }
        mysqli_close($conexion);
    }
}else{
    $estado = "no";
    $mensaje = "Parámetros incorrectos";
}

$a = array("estado" => $estado, "mensaje" => $mensaje, "datos" => $datos);

echo json_encode($a);
