-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 17, 2021 at 10:13 PM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `inventario`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `codigosdebarras`
-- (See below for the actual view)
--
CREATE TABLE `codigosdebarras` (
`codigoBarras` varchar(45)
);

-- --------------------------------------------------------

--
-- Table structure for table `entradas`
--

CREATE TABLE `entradas` (
  `IdEntradas` int(11) NOT NULL,
  `noOrden` int(45) DEFAULT '0',
  `noPedido` int(45) DEFAULT '0',
  `fechaEntrada` date DEFAULT NULL,
  `horaEntrada` time DEFAULT NULL,
  `codigoBarras` varchar(45) DEFAULT NULL,
  `diseno` varchar(45) DEFAULT NULL,
  `codigoInterno` varchar(45) DEFAULT NULL,
  `cliente` varchar(45) DEFAULT NULL,
  `producto` varchar(45) DEFAULT NULL,
  `cantidadPallet` int(45) DEFAULT '0',
  `cantidadPorPallet` int(45) DEFAULT '0',
  `totalUnidades` int(45) DEFAULT '0',
  `condicion` varchar(45) DEFAULT NULL,
  `observaciones` text,
  `idTransporte` int(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `entradas`
--

INSERT INTO `entradas` (`IdEntradas`, `noOrden`, `noPedido`, `fechaEntrada`, `horaEntrada`, `codigoBarras`, `diseno`, `codigoInterno`, `cliente`, `producto`, `cantidadPallet`, `cantidadPorPallet`, `totalUnidades`, `condicion`, `observaciones`, `idTransporte`) VALUES
(1, 1111, 11112, '2021-01-17', '15:40:20', '1111', '2222', '312AS', 'Cliente de prueba uno', 'cajas de porcelana', 12, 200, 2400, 'Bueno', 'Es producto delicado', 1),
(2, 1111, 1111, '2021-01-17', '15:41:40', '1111', '2222', '312AS', 'Cliente de prueba uno', 'cajas de porcelana', 30, 2400, 72000, 'Bueno', 'Carga delicada', 2),
(3, 2123, 44332, '2021-01-17', '15:49:25', '22234', '1222', '123ABS', 'Cliente dos', 'Cajas de roble fino', 12, 20, 240, 'Bueno', 'Cajas resistentes pero tengan cuidado', 2);

--
-- Triggers `entradas`
--
DELIMITER $$
CREATE TRIGGER `addInventarioFromEntradas` AFTER INSERT ON `entradas` FOR EACH ROW UPDATE `inventario` SET
`entradasPallets` = (SELECT SUM(`cantidadPallet`) FROM `entradas` WHERE `codigoBarras`= NEW.`codigoBarras`),
`entradaPiezas` = (SELECT SUM(`totalUnidades`) FROM `entradas` WHERE `codigoBarras`= NEW.`codigoBarras`),
`stockPallets` = `entradasPallets`-`salidaPallets`,
`stockPiezas` = `entradaPiezas`-`salidaPiezas`
WHERE `codigoBarras` = NEW.`codigoBarras`
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `inventario`
--

CREATE TABLE `inventario` (
  `IdInventario` int(11) NOT NULL,
  `codigoBarras` varchar(45) DEFAULT NULL,
  `diseno` varchar(45) DEFAULT NULL,
  `codigoInterno` varchar(45) DEFAULT NULL,
  `cliente` varchar(45) DEFAULT NULL,
  `entradasPallets` int(45) NOT NULL DEFAULT '0',
  `entradaPiezas` int(45) NOT NULL DEFAULT '0',
  `salidaPallets` int(45) NOT NULL DEFAULT '0',
  `salidaPiezas` int(45) NOT NULL DEFAULT '0',
  `stockPallets` int(45) DEFAULT '0',
  `stockPiezas` int(45) DEFAULT '0',
  `producto` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inventario`
--

INSERT INTO `inventario` (`IdInventario`, `codigoBarras`, `diseno`, `codigoInterno`, `cliente`, `entradasPallets`, `entradaPiezas`, `salidaPallets`, `salidaPiezas`, `stockPallets`, `stockPiezas`, `producto`) VALUES
(1, '1111', '2222', '312AS', 'Cliente de prueba uno', 42, 74400, 6, 5400, 36, 69000, 'cajas de porcelana'),
(2, '22234', '1222', '123ABS', 'Cliente dos', 12, 240, 11, 220, 1, 20, 'Cajas de roble fino');

-- --------------------------------------------------------

--
-- Stand-in structure for view `productosenstock`
-- (See below for the actual view)
--
CREATE TABLE `productosenstock` (
`codigoBarras` varchar(45)
);

-- --------------------------------------------------------

--
-- Table structure for table `salidas`
--

CREATE TABLE `salidas` (
  `IdSalidas` int(11) NOT NULL,
  `noPedido` int(45) DEFAULT NULL,
  `codigoBarras` varchar(45) NOT NULL,
  `sellos` int(45) DEFAULT NULL,
  `cantidadPallet` int(45) DEFAULT NULL,
  `cantidadPorPallet` int(45) DEFAULT NULL,
  `totalUnidades` int(45) DEFAULT NULL,
  `fechaSalida` date DEFAULT NULL,
  `horaSalida` time NOT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `diseno` varchar(45) NOT NULL,
  `codigoInterno` varchar(45) NOT NULL,
  `producto` varchar(45) NOT NULL,
  `condicion` varchar(45) NOT NULL,
  `observaciones` text NOT NULL,
  `cliente` varchar(45) NOT NULL,
  `idTransporte` int(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salidas`
--

INSERT INTO `salidas` (`IdSalidas`, `noPedido`, `codigoBarras`, `sellos`, `cantidadPallet`, `cantidadPorPallet`, `totalUnidades`, `fechaSalida`, `horaSalida`, `fechaEntrega`, `diseno`, `codigoInterno`, `producto`, `condicion`, `observaciones`, `cliente`, `idTransporte`) VALUES
(1, 332, '1111', 42, 3, 800, 2400, '2021-01-17', '16:08:45', '2021-01-17', '2222', '312AS', 'cajas de porcelana', 'Bueno', 'con cuidado', '', 1),
(2, 4444, '1111', 3333, 3, 1000, 3000, '2021-01-17', '16:10:26', '2021-01-17', '2222', '312AS', 'cajas de porcelana', 'Bueno', 'con cuidado', '', 1),
(3, 22111, '22234', 9998, 11, 20, 220, '2021-01-17', '16:11:40', '2021-01-17', '1222', '123ABS', 'Cajas de roble fino', 'Bueno', 'nada', '', 2);

--
-- Triggers `salidas`
--
DELIMITER $$
CREATE TRIGGER `removeInventarioFromSalidas` AFTER INSERT ON `salidas` FOR EACH ROW UPDATE `inventario` SET
`salidaPallets` = (SELECT SUM(`cantidadPallet`) FROM `salidas` WHERE `codigoBarras`= NEW.`codigoBarras`),
`salidaPiezas` = (SELECT SUM(`totalUnidades`) FROM `salidas` WHERE `codigoBarras`= NEW.`codigoBarras`),
`stockPallets` = `entradasPallets`-`salidaPallets`,
`stockPiezas` = `entradaPiezas`-`salidaPiezas`
WHERE `codigoBarras` = NEW.`codigoBarras`
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `totalstock`
-- (See below for the actual view)
--
CREATE TABLE `totalstock` (
`totalPallets` decimal(65,0)
,`totalPiezas` decimal(65,0)
);

-- --------------------------------------------------------

--
-- Table structure for table `transporte`
--

CREATE TABLE `transporte` (
  `IdTransporte` int(11) NOT NULL,
  `chofer` varchar(45) DEFAULT NULL,
  `empresa` varchar(45) DEFAULT NULL,
  `placas` varchar(45) DEFAULT NULL,
  `tractoCamion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transporte`
--

INSERT INTO `transporte` (`IdTransporte`, `chofer`, `empresa`, `placas`, `tractoCamion`) VALUES
(1, 'Carlos Luna Ramirez', 'Plasti-Mundo', 'jwt-123-y99', 'a'),
(2, 'Agustin de inturbide', 'test', 'jwt-k12-12', 'b');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidoPaterno` varchar(45) DEFAULT NULL,
  `apellidoMaterno` varchar(45) DEFAULT NULL,
  `puesto` varchar(45) DEFAULT NULL,
  `correoElectronico` varchar(45) DEFAULT NULL,
  `contrasena` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`IdUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `puesto`, `correoElectronico`, `contrasena`) VALUES
(1, 'Carlos Gabriel', 'Luna', 'Montes', 'Desarrollador', 'carlosgabrieluna@gmail.com', 'Y2FybG9zZ2FicmllbDE='),
(2, 'a', 'a', 'a', 'a', 'a', 'YQ=='),
(3, 'Prueba', 'De', 'Usuario', 'el que sea', 'Prueba_test@gmail.com', 'aG9sYW11bmRv');

-- --------------------------------------------------------

--
-- Structure for view `codigosdebarras`
--
DROP TABLE IF EXISTS `codigosdebarras`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `codigosdebarras`  AS SELECT `codigoBarras` AS `codigoBarras` FROM `inventario` ;

-- --------------------------------------------------------

--
-- Structure for view `productosenstock`
--
DROP TABLE IF EXISTS `productosenstock`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `productosenstock`  AS SELECT `codigoBarras` AS `codigoBarras` FROM `inventario` WHERE ((`stockPallets` > 0) AND (`stockPiezas` > 0)) ;

-- --------------------------------------------------------

--
-- Structure for view `totalstock`
--
DROP TABLE IF EXISTS `totalstock`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalstock`  AS SELECT sum(`stockPallets`) AS `totalPallets`, sum(`stockPiezas`) AS `totalPiezas` FROM `inventario` WHERE 1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`IdEntradas`);

--
-- Indexes for table `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`IdInventario`),
  ADD UNIQUE KEY `CodigoBarras` (`codigoBarras`);

--
-- Indexes for table `salidas`
--
ALTER TABLE `salidas`
  ADD PRIMARY KEY (`IdSalidas`);

--
-- Indexes for table `transporte`
--
ALTER TABLE `transporte`
  ADD PRIMARY KEY (`IdTransporte`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IdUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `entradas`
--
ALTER TABLE `entradas`
  MODIFY `IdEntradas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `inventario`
--
ALTER TABLE `inventario`
  MODIFY `IdInventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `salidas`
--
ALTER TABLE `salidas`
  MODIFY `IdSalidas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transporte`
--
ALTER TABLE `transporte`
  MODIFY `IdTransporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
