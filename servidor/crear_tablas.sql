CREATE DATABASE io_laboratorio;
USE io_laboratorio;

CREATE TABLE usuarios(
	id INT AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL,
    clave BLOB NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nombre)
)ENGINE MyISAM DEFAULT CHARACTER SET=utf8;

CREATE TABLE historial(
	id INT AUTO_INCREMENT,
	id_usuario INT NOT NULL,
    fecha_hora_ingreso DATETIME NOT NULL,
    fecha_hora_egreso DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
)ENGINE MyISAM DEFAULT CHARACTER SET=utf8;
