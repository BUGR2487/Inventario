-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 07, 2021 at 06:22 AM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `inventario`
--

-- --------------------------------------------------------

--
-- Table structure for table `entradas`
--

CREATE TABLE `entradas` (
  `IdEntradas` int(11) NOT NULL,
  `NoOrden` int(45) DEFAULT '0',
  `NoPedido` int(45) DEFAULT '0',
  `FechaEntrada` date DEFAULT NULL,
  `HoraEntrada` time DEFAULT NULL,
  `CodigoBarras` varchar(45) DEFAULT NULL,
  `Diseno` varchar(45) DEFAULT NULL,
  `CodigoInterno` varchar(45) DEFAULT NULL,
  `Cliente` varchar(45) DEFAULT NULL,
  `Producto` varchar(45) DEFAULT NULL,
  `CantidadPallet` int(45) DEFAULT '0',
  `CantidadPorPallet` int(45) DEFAULT '0',
  `TotalUnidades` int(45) DEFAULT '0',
  `Condicion` varchar(45) DEFAULT NULL,
  `Observaciones` varchar(45) DEFAULT NULL,
  `idTransporte` int(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `entradas`
--

INSERT INTO `entradas` (`IdEntradas`, `NoOrden`, `NoPedido`, `FechaEntrada`, `HoraEntrada`, `CodigoBarras`, `Diseno`, `CodigoInterno`, `Cliente`, `Producto`, `CantidadPallet`, `CantidadPorPallet`, `TotalUnidades`, `Condicion`, `Observaciones`, `idTransporte`) VALUES
(1, 123213, 123121, '2020-09-03', '13:57:53', '213123', '213212', 'adsd1213', 'Dulces vero', 'Paletas sabor a maruchan dulce', 2, 100, 400, 'Bueno', 'nada', 1),
(5, 1312321, 3243242, '2020-09-08', '12:00:00', 'dasdsasd12', 'qwewqwq', '21321321', 'cliente x', 'producto x', 12, 100, 1200, 'bien', 'nada', 1),
(6, 234809, 76564, '2020-11-03', '15:50:43', '213123', '213212', 'adsd1213', 'Dulces vero', 'Paletas sabor a maruchan dulce', 12, 200, 2400, 'Bueno', 'nada', 1),
(7, 534545, 43543, '2020-11-03', '20:18:59', '432540980', '12367588', 'das123', 'Juan camanei', 'cajas de pan de quesidogo', 12, 300, 3600, 'Bueno', 'nada', 1),
(8, 324324, 756567, '2020-11-03', '20:24:11', '432540980', '12367588', 'das123', 'Juan camanei', 'cajas de pan de quesidogo', 12, 300, 3600, 'Bueno', 'nada', 1),
(9, 2222, 121212, '2021-01-06', '20:27:32', '213123', '213212', 'adsd1213', 'Dulces vero', 'Paletas sabor a maruchan dulce', 12, 200, 2400, 'Bueno', 'birns', 1),
(10, 222, 2121, '2021-01-06', '20:29:36', '432540980', '12367588', 'das123', 'Juan camanei', 'cajas de pan de quesidogo', 21, 300, 6300, 'Bueno', 'correcto', 1);

-- --------------------------------------------------------

--
-- Table structure for table `inventario`
--

CREATE TABLE `inventario` (
  `IdInventario` int(11) NOT NULL,
  `CodigoBarras` varchar(45) DEFAULT NULL,
  `Diseno` varchar(45) DEFAULT NULL,
  `CodigoInterno` varchar(45) DEFAULT NULL,
  `Cliente` varchar(45) DEFAULT NULL,
  `StockPallets` int(45) DEFAULT NULL,
  `StockPiezas` int(45) DEFAULT NULL,
  `Producto` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inventario`
--

INSERT INTO `inventario` (`IdInventario`, `CodigoBarras`, `Diseno`, `CodigoInterno`, `Cliente`, `StockPallets`, `StockPiezas`, `Producto`) VALUES
(1, '213123', '213212', 'adsd1213', 'Dulces vero', 23, 200, 'Paletas sabor a maruchan dulce'),
(2, '432540980', '12367588', 'das123', 'Juan camanei', 24, 300, 'cajas de pan de quesidogo'),
(3, '3322111', '774993', '12222', 'Prueba', 2000, 12, 'Caja de prueba');

-- --------------------------------------------------------

--
-- Table structure for table `salidas`
--

CREATE TABLE `salidas` (
  `IdSalidas` int(11) NOT NULL,
  `NoPedido` varchar(45) DEFAULT NULL,
  `Sellos` int(45) DEFAULT NULL,
  `CantidadPallet` int(45) DEFAULT NULL,
  `CantidadPorPallet` int(45) DEFAULT NULL,
  `TotalUnidades` int(45) DEFAULT NULL,
  `FechaSalida` date DEFAULT NULL,
  `HoraSalida` time NOT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `idTransporte` int(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salidas`
--

INSERT INTO `salidas` (`IdSalidas`, `NoPedido`, `Sellos`, `CantidadPallet`, `CantidadPorPallet`, `TotalUnidades`, `FechaSalida`, `HoraSalida`, `fechaEntrega`, `idTransporte`) VALUES
(1, '3243242', 89123, 12, 100, 1200, '2020-10-09', '14:58:13', '2020-10-30', 1),
(2, '123121', 90887, 2, 100, 400, '2020-10-09', '15:01:36', '2020-10-31', 1),
(3, '3243242', 1234354, 12, 100, 1200, '2020-11-03', '15:55:07', '2020-11-03', 1),
(4, '123121', 12221, 2, 100, 400, '2021-01-06', '20:31:51', '2021-01-06', 1),
(5, '121212', 2233333, 12, 200, 2400, '2021-01-06', '20:32:13', '2021-01-22', 1),
(6, '2121', 3333, 21, 300, 6300, '2021-01-06', '20:33:24', '2021-01-22', 1),
(7, '76564', 444444, 12, 200, 2400, '2021-01-06', '20:33:35', '2021-01-22', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transporte`
--

CREATE TABLE `transporte` (
  `IdTransporte` int(11) NOT NULL,
  `Chofer` varchar(45) DEFAULT NULL,
  `Empresa` varchar(45) DEFAULT NULL,
  `Placas` varchar(45) DEFAULT NULL,
  `TractoCamion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transporte`
--

INSERT INTO `transporte` (`IdTransporte`, `Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES
(1, 'Carlos Luna Ramirez', 'Plasti-Mundo', 'jwt-123-y99', 'a'),
(2, 'Agustin de inturbide', 'test', 'jwt-k12-12', 'b');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `ApellidoPaterno` varchar(45) DEFAULT NULL,
  `ApellidoMaterno` varchar(45) DEFAULT NULL,
  `Puesto` varchar(45) DEFAULT NULL,
  `CorreoElectronico` varchar(45) DEFAULT NULL,
  `Contrasena` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`IdUsuario`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Puesto`, `CorreoElectronico`, `Contrasena`) VALUES
(1, 'Carlos Gabriel', 'Luna', 'Montes', 'Desarrollador', 'carlosgabrieluna@gmail.com', 'Y2FybG9zZ2FicmllbDE='),
(2, 'a', 'a', 'a', 'a', 'a', 'YQ=='),
(3, 'Prueba', 'De', 'Usuario', 'el que sea', 'Prueba_test@gmail.com', 'aG9sYW11bmRv');

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
  ADD PRIMARY KEY (`IdInventario`);

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
  MODIFY `IdEntradas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `inventario`
--
ALTER TABLE `inventario`
  MODIFY `IdInventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `salidas`
--
ALTER TABLE `salidas`
  MODIFY `IdSalidas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
