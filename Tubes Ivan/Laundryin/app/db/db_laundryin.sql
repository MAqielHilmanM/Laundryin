-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 02, 2019 at 05:20 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_laundryin`
--
CREATE DATABASE IF NOT EXISTS `db_laundryin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_laundryin`;

-- --------------------------------------------------------

--
-- Table structure for table `tb_delivery`
--

DROP TABLE IF EXISTS `tb_delivery`;
CREATE TABLE `tb_delivery` (
  `id_delivery` int(11) NOT NULL,
  `kode_transaksi` int(11) NOT NULL,
  `id_kurir` int(11) NOT NULL,
  `tgl_delivery` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_delivery`
--

INSERT INTO `tb_delivery` (`id_delivery`, `kode_transaksi`, `id_kurir`, `tgl_delivery`) VALUES
(1, 1, 1, '2019-12-01 09:08:31'),
(2, 1, 1, '2019-12-02 02:46:22'),
(3, 1, 1, '2019-12-02 02:46:28'),
(4, 1, 1, '2019-12-02 02:47:50');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kurir`
--

DROP TABLE IF EXISTS `tb_kurir`;
CREATE TABLE `tb_kurir` (
  `id_kurir` int(11) NOT NULL,
  `nama_kurir` varchar(40) NOT NULL,
  `no_telp` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kurir`
--

INSERT INTO `tb_kurir` (`id_kurir`, `nama_kurir`, `no_telp`) VALUES
(1, 'Asep', '082117896960');

-- --------------------------------------------------------

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id_order` int(11) NOT NULL,
  `tgl_selesai` datetime NOT NULL,
  `tgl_order` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(40) NOT NULL,
  `id_paket` int(11) NOT NULL,
  `catatan` text,
  `address` text NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_order`
--

INSERT INTO `tb_order` (`id_order`, `tgl_selesai`, `tgl_order`, `username`, `id_paket`, `catatan`, `address`, `status`) VALUES
(2, '2019-12-02 00:00:00', '2019-12-01 02:11:33', 'admin', 1, NULL, 'Jln', 2),
(3, '2019-12-02 00:00:00', '2019-12-01 02:27:35', 'admin', 1, NULL, 'Jln', 0),
(6, '2019-12-03 20:43:48', '2019-12-01 02:43:48', 'a', 1, NULL, 'Jln ', 3),
(7, '2019-12-04 20:40:49', '2019-12-02 02:40:49', 'a', 1, NULL, '', 1),
(8, '2019-12-05 07:41:30', '2019-12-02 13:41:30', 'a', 1, 'Lorem Ipsum', 'Lorem block 1', 0),
(13, '2019-12-05 09:45:17', '2019-12-02 15:45:17', 'a', 1, 'Lorem Ipsum', 'Lorem block 1', 0),
(14, '2019-12-05 09:47:55', '2019-12-02 15:47:55', 's', 1, '', '', 0),
(15, '2019-12-05 12:30:25', '2019-12-02 18:30:25', 'a', 1, '', 'jl haji umar', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_paketlaundry`
--

DROP TABLE IF EXISTS `tb_paketlaundry`;
CREATE TABLE `tb_paketlaundry` (
  `id_paket` int(11) NOT NULL,
  `nama_paket` varchar(40) NOT NULL,
  `harga_paket` int(11) NOT NULL,
  `estimasi_paket` int(11) NOT NULL DEFAULT '3',
  `keterangan` text NOT NULL,
  `url_picture` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_paketlaundry`
--

INSERT INTO `tb_paketlaundry` (`id_paket`, `nama_paket`, `harga_paket`, `estimasi_paket`, `keterangan`, `url_picture`) VALUES
(1, 'Satu Kilo', 1000, 3, 'Paket ini menawarkan 4 Hari kerja dijamin cucian anda akan selesai. Di paket ini tersedia beberapa layanan yang sudah termasuk ke dalam paket 4 Hari ini di antaranya yaitu : <br>1. Full ( Setrika + Cuci ) <br>2. Setrika ( Parfum ) <br>3. Cuci ( Parfum ) ', NULL),
(2, 'Kiloan', 1000, 4, 'dododo', 'nothing');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pelanggan`
--

DROP TABLE IF EXISTS `tb_pelanggan`;
CREATE TABLE `tb_pelanggan` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `alamat` text NOT NULL,
  `phone` varchar(40) NOT NULL,
  `pakaian` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pelanggan`
--

INSERT INTO `tb_pelanggan` (`username`, `password`, `nama`, `alamat`, `phone`, `pakaian`) VALUES
('a', 'a', 'a', 'a', '0812123123', ''),
('admin', 'admin', 'admin', 'admin', '0812123123', 'admin'),
('b', 'b', 'b', 'b', '0812123123', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_transaksi`
--

DROP TABLE IF EXISTS `tb_transaksi`;
CREATE TABLE `tb_transaksi` (
  `kode_transaksi` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  `total_bayar` double NOT NULL,
  `tgl_transaksi` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_transaksi`
--

INSERT INTO `tb_transaksi` (`kode_transaksi`, `id_order`, `total_bayar`, `tgl_transaksi`) VALUES
(1, 2, 1000, '2019-12-01 02:12:44'),
(2, 2, 100, '2019-12-01 09:01:37'),
(3, 2, 100, '2019-12-02 02:45:17');

-- --------------------------------------------------------

--
-- Table structure for table `tb_ulasan`
--

DROP TABLE IF EXISTS `tb_ulasan`;
CREATE TABLE `tb_ulasan` (
  `id` int(11) NOT NULL,
  `id_paket` int(11) NOT NULL,
  `username` varchar(40) NOT NULL,
  `ulasan` text NOT NULL,
  `entry_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_ulasan`
--

INSERT INTO `tb_ulasan` (`id`, `id_paket`, `username`, `ulasan`, `entry_date`) VALUES
(1, 1, 'admin', 'satu kilo emang terbaek', '2019-11-30 18:50:20'),
(2, 0, 'admin', 'Bacod Santuy', '2019-12-02 04:03:14'),
(3, 1, 'admin', 'Bacod Santuy', '2019-12-02 04:03:41'),
(4, 1, 'admin', 'Bacod Santuy', '2019-12-02 13:11:15'),
(5, 6, 'a', 'mangstap', '2019-12-02 13:43:58'),
(6, 1, 'a', '', '2019-12-02 13:46:05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_delivery`
--
ALTER TABLE `tb_delivery`
  ADD PRIMARY KEY (`id_delivery`),
  ADD KEY `kode_transaksi` (`kode_transaksi`),
  ADD KEY `id_kurir` (`id_kurir`);

--
-- Indexes for table `tb_kurir`
--
ALTER TABLE `tb_kurir`
  ADD PRIMARY KEY (`id_kurir`);

--
-- Indexes for table `tb_order`
--
ALTER TABLE `tb_order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `username` (`username`),
  ADD KEY `tb_order_ibfk_2` (`id_paket`);

--
-- Indexes for table `tb_paketlaundry`
--
ALTER TABLE `tb_paketlaundry`
  ADD PRIMARY KEY (`id_paket`);

--
-- Indexes for table `tb_pelanggan`
--
ALTER TABLE `tb_pelanggan`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`kode_transaksi`),
  ADD KEY `id_order` (`id_order`);

--
-- Indexes for table `tb_ulasan`
--
ALTER TABLE `tb_ulasan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_delivery`
--
ALTER TABLE `tb_delivery`
  MODIFY `id_delivery` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_kurir`
--
ALTER TABLE `tb_kurir`
  MODIFY `id_kurir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_order`
--
ALTER TABLE `tb_order`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tb_paketlaundry`
--
ALTER TABLE `tb_paketlaundry`
  MODIFY `id_paket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `kode_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_ulasan`
--
ALTER TABLE `tb_ulasan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_delivery`
--
ALTER TABLE `tb_delivery`
  ADD CONSTRAINT `tb_delivery_ibfk_1` FOREIGN KEY (`kode_transaksi`) REFERENCES `tb_transaksi` (`kode_transaksi`),
  ADD CONSTRAINT `tb_delivery_ibfk_2` FOREIGN KEY (`id_kurir`) REFERENCES `tb_kurir` (`id_kurir`);

--
-- Constraints for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD CONSTRAINT `tb_transaksi_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `tb_order` (`id_order`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
