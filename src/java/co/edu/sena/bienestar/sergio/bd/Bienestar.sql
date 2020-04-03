-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 04, 2020 at 01:21 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Bienestar`
--
CREATE DATABASE IF NOT EXISTS `Bienestar` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `Bienestar`;

-- --------------------------------------------------------

--
-- Table structure for table `Actividades`
--

CREATE TABLE `Actividades` (
  `Id_actividad` int(11) NOT NULL,
  `Nombre_actividad` varchar(500) NOT NULL,
  `Tipo_actividad` varchar(255) NOT NULL,
  `Fecha_inicio` date NOT NULL,
  `Fecha_fin` date NOT NULL,
  `responsable` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Actividades_Aprendiz`
--

CREATE TABLE `Actividades_Aprendiz` (
  `Cod_actividad` int(11) NOT NULL,
  `Cod_aprendiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Aprendiz`
--

CREATE TABLE `Aprendiz` (
  `idAprendiz` int(11) NOT NULL,
  `Documento_aprendiz` varchar(255) NOT NULL,
  `Tipo_documento` varchar(10) NOT NULL,
  `Nombres_aprendiz` varchar(300) NOT NULL,
  `Email_aprendiz` varchar(255) NOT NULL,
  `Municipio_aprendiz` varchar(255) NOT NULL,
  `Genero` varchar(2) NOT NULL,
  `FechaNacimiento_Aprendiz` date NOT NULL,
  `Tipo_poblacion` varchar(255) NOT NULL,
  `Eps` varchar(255) DEFAULT NULL,
  `Estrato` varchar(1) NOT NULL,
  `Ficha` varchar(255) NOT NULL,
  `NombrePrograma` varchar(300) NOT NULL,
  `Nivel_formacion` varchar(255) NOT NULL,
  `Coordinacion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Responsables`
--

CREATE TABLE `Responsables` (
  `idResponsable` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `year` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Responsables`
--

INSERT INTO `Responsables` (`idResponsable`, `nombre`, `codigo`, `year`) VALUES
(1, 'VIVIANA PARDO', 'P01', '2020'),
(2, 'VIVIANA SIERRA', 'P02', '2020'),
(3, 'ELIZABETH DÍAZ', 'P03', '2020'),
(5, 'ANGIE ESTEPA', 'P04', '2020'),
(6, 'TEÓDULO QUINTERO', 'P05', '2020'),
(7, 'GINA DUARTE', 'P06', '2020'),
(8, 'SOLANYHI RODRIGUEZ', 'P07', '2020'),
(9, 'SEBASTIÁN URREA', 'P08', '2020'),
(10, 'CESAR DÍAZ', 'P09', '2020'),
(11, 'DANIEL GALEANO', 'P10', '2020'),
(12, 'EDILMA SILVA', 'P11', '2020'),
(13, 'FLORALBA RAMOS', 'P12', '2020'),
(14, 'PILAR DUARTE', 'P13', '2020'),
(15, 'CHRISTIAN RIVEROS', 'P14', '2020'),
(16, 'ESMERALDA CLAVIJO', 'P15', '2020'),
(17, 'ALEJANDRA TORRES', 'P16', '2020'),
(18, 'MASIVO', 'P17', '2020');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Actividades`
--
ALTER TABLE `Actividades`
  ADD PRIMARY KEY (`Id_actividad`);

--
-- Indexes for table `Actividades_Aprendiz`
--
ALTER TABLE `Actividades_Aprendiz`
  ADD KEY `fkAprendiz` (`Cod_aprendiz`),
  ADD KEY `fkActividad` (`Cod_actividad`);

--
-- Indexes for table `Aprendiz`
--
ALTER TABLE `Aprendiz`
  ADD PRIMARY KEY (`idAprendiz`);

--
-- Indexes for table `Responsables`
--
ALTER TABLE `Responsables`
  ADD PRIMARY KEY (`idResponsable`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Actividades`
--
ALTER TABLE `Actividades`
  MODIFY `Id_actividad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Aprendiz`
--
ALTER TABLE `Aprendiz`
  MODIFY `idAprendiz` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Responsables`
--
ALTER TABLE `Responsables`
  MODIFY `idResponsable` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Actividades_Aprendiz`
--
ALTER TABLE `Actividades_Aprendiz`
  ADD CONSTRAINT `fkActividad` FOREIGN KEY (`Cod_actividad`) REFERENCES `Actividades` (`Id_actividad`),
  ADD CONSTRAINT `fkAprendiz` FOREIGN KEY (`Cod_aprendiz`) REFERENCES `Aprendiz` (`idAprendiz`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;