<?php

/*
Registra un usuario con nombre de usuario y clave.

Entradas
usuario: mín=3 chars, máx=30 chars
clave: mín=5 chars, máx=30 chars

Respuesta
estado: ok | no
mensaje: Mensaje indicando la situación.
datos: id_usuario
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
$datos = "";
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
        $insercion = "INSERT INTO usuarios (nombre, clave) VALUES ('$usuario', AES_ENCRYPT('$clave', '_io_laboratorio_'))";
        if (!mysqli_query($conexion, $insercion)) {
            if($conexion->errno == 1062){
                $estado = "no";
                $mensaje = "Usuario ya existente";
            }else{
                $estado = "no";
                $mensaje = "Error " . $conexion->errno;
            }
        }else{
            $estado = "ok";
			$datos = mysqli_insert_id($conexion);//id de usuario
            $mensaje = "Registrado con éxito";
        }
        mysqli_close($conexion);
    }
}else{
    $estado = "no";
    $mensaje = "Parámetros incorrectos";
}

$a = array("estado" => $estado, "mensaje" => $mensaje, "datos" => $datos);

echo json_encode($a);
