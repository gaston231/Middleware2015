CREATE DATABASE  IF NOT EXISTS `db_lealtad`;
USE `db_lealtad`;

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idusuario` double NOT NULL,
  `puntos` int(11) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE USER 'middleware'@'localhost' IDENTIFIED BY 'middleware';

GRANT SELECT ON db_lealtad.* TO 'middleware'@'localhost';
GRANT UPDATE ON db_lealtad.* TO 'middleware'@'localhost';
GRANT INSERT ON db_lealtad.* TO 'middleware'@'localhost';

