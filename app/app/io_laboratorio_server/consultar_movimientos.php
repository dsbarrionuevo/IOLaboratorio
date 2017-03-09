<?php
/*
Consulta el estado de los movimientos del día

Entradas
Ninguna

Respuesta
estado: ok | no
mensaje: datos de los movimientos en formato json.
*/

require_once ('config.php');

$consulta = "SELECT u.nombre AS nombre, h.fecha_hora_ingreso AS fecha_hora_ingreso,
            h.fecha_hora_egreso AS fecha_hora_egreso
            FROM historial AS h INNER JOIN usuarios AS u ON (h.id_usuario=u.id)
            WHERE DAY(fecha_hora_ingreso)=DAY(NOW())
            AND MONTH(fecha_hora_ingreso)=MONTH(NOW()) AND YEAR(fecha_hora_ingreso)=YEAR(NOW())
            ";

$conexion = mysqli_connect($_SERVIDOR, $_USUARIO, $_CLAVE, $_BD);
$datos = mysqli_query($conexion, $consulta);
if($datos){
    $filas = array();
    while($fila = mysqli_fetch_array($datos)){
        $filas[] = $fila;
    }
    $r = array();
    for($i = 0; $i < count($filas); $i++){
        $r[$i] = array();
        $r[$i]["nombre"] = $filas[$i]["nombre"];
        $r[$i]["fecha_hora_ingreso"] = $filas[$i]["fecha_hora_ingreso"];
        $r[$i]["fecha_hora_egreso"] = $filas[$i]["fecha_hora_egreso"];
    }
}

$a = array("estado" => "ok", "mensaje" => $r);

echo json_encode($a);
