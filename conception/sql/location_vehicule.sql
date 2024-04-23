-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 14, 2024 at 10:09 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `location_vehicule`
--

-- --------------------------------------------------------

--
-- Table structure for table `adresse`
--

CREATE TABLE `adresse` (
  `adresse_id` bigint(20) NOT NULL,
  `cp` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `numero` int(11) NOT NULL,
  `ville_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `agence`
--

CREATE TABLE `agence` (
  `agence_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `adresse_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `agence_fourni_vehicule`
--

CREATE TABLE `agence_fourni_vehicule` (
  `quantite_fourni` int(11) NOT NULL,
  `agence_id` bigint(20) NOT NULL,
  `vehicule_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `client_reserve_vehicule`
--

CREATE TABLE `client_reserve_vehicule` (
  `date_fin` date NOT NULL,
  `date_debut` date NOT NULL,
  `client_user_id` bigint(20) NOT NULL,
  `date_reservation` date NOT NULL,
  `vehicule_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `client_user`
--

CREATE TABLE `client_user` (
  `client_user_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_booked` bit(1) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `adresse_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `commentaire_id` bigint(20) NOT NULL,
  `date_time` date NOT NULL,
  `description` varchar(500) NOT NULL,
  `client_user_id` bigint(20) NOT NULL,
  `vehicule_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `date_reservation`
--

CREATE TABLE `date_reservation` (
  `date_reservation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequences`
--

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
('default', 0);

-- --------------------------------------------------------

--
-- Table structure for table `scooter`
--

CREATE TABLE `scooter` (
  `id` bigint(20) NOT NULL,
  `couleur` varchar(255) NOT NULL,
  `is_booked` tinyint(1) DEFAULT 0,
  `photo` varchar(255) DEFAULT NULL,
  `poids` int(11) NOT NULL,
  `prix_journalier` int(11) NOT NULL,
  `client_user_id` bigint(20) DEFAULT NULL,
  `cylindre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `voiture`
--

CREATE TABLE `voiture` (
  `id` bigint(20) NOT NULL,
  `couleur` varchar(255) NOT NULL,
  `is_booked` tinyint(1) DEFAULT 0,
  `photo` varchar(255) DEFAULT NULL,
  `poids` int(11) NOT NULL,
  `prix_journalier` int(11) NOT NULL,
  `client_user_id` bigint(20) DEFAULT NULL,
  `nb_roues` int(11) NOT NULL,
  `nbr_porte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ville`
--

CREATE TABLE `ville` (
  `ville_id` bigint(20) NOT NULL,
  `nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`adresse_id`),
  ADD KEY `FKbqwghvq1aixqqe9ywocjj2irp` (`ville_id`);

--
-- Indexes for table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`agence_id`),
  ADD KEY `FKp92fb4t8wfnxo6wwuf59vrogi` (`adresse_id`);

--
-- Indexes for table `agence_fourni_vehicule`
--
ALTER TABLE `agence_fourni_vehicule`
  ADD PRIMARY KEY (`agence_id`,`vehicule_id`),
  ADD KEY `FK2nijfhufimtluqj2d1wwh3tfu` (`vehicule_id`);

--
-- Indexes for table `client_reserve_vehicule`
--
ALTER TABLE `client_reserve_vehicule`
  ADD PRIMARY KEY (`client_user_id`,`date_reservation`,`vehicule_id`),
  ADD KEY `FKo4bjww6sgk8qpfbxxoxrwom8h` (`date_reservation`),
  ADD KEY `FK485g4ltjn8645mwxsr2jlnqhk` (`vehicule_id`);

--
-- Indexes for table `client_user`
--
ALTER TABLE `client_user`
  ADD PRIMARY KEY (`client_user_id`),
  ADD KEY `FKp8bmn98xxie0ggv3aokywvfmb` (`adresse_id`);

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`commentaire_id`),
  ADD KEY `FKnjp9x63cll1bqnoil50n3ihvy` (`client_user_id`),
  ADD KEY `FKq94p1u0wubwxw3bu6jag5g67p` (`vehicule_id`);

--
-- Indexes for table `date_reservation`
--
ALTER TABLE `date_reservation`
  ADD PRIMARY KEY (`date_reservation`);

--
-- Indexes for table `hibernate_sequences`
--
ALTER TABLE `hibernate_sequences`
  ADD PRIMARY KEY (`sequence_name`);

--
-- Indexes for table `scooter`
--
ALTER TABLE `scooter`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_gsdnhyxkcncd5p2rh5xts1gd8` (`client_user_id`);

--
-- Indexes for table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_9efwas96pg6kqusqp734vijni` (`client_user_id`);

--
-- Indexes for table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`ville_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `adresse_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `agence`
--
ALTER TABLE `agence`
  MODIFY `agence_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_user`
--
ALTER TABLE `client_user`
  MODIFY `client_user_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `commentaire_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ville`
--
ALTER TABLE `ville`
  MODIFY `ville_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FKbqwghvq1aixqqe9ywocjj2irp` FOREIGN KEY (`ville_id`) REFERENCES `ville` (`ville_id`);

--
-- Constraints for table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `FKp92fb4t8wfnxo6wwuf59vrogi` FOREIGN KEY (`adresse_id`) REFERENCES `adresse` (`adresse_id`);

--
-- Constraints for table `agence_fourni_vehicule`
--
ALTER TABLE `agence_fourni_vehicule`
  ADD CONSTRAINT `FK2nijfhufimtluqj2d1wwh3tfu` FOREIGN KEY (`vehicule_id`) REFERENCES `voiture` (`id`),
  ADD CONSTRAINT `FKbb71t7qyrul43g3v65estag41` FOREIGN KEY (`agence_id`) REFERENCES `agence` (`agence_id`);

--
-- Constraints for table `client_reserve_vehicule`
--
ALTER TABLE `client_reserve_vehicule`
  ADD CONSTRAINT `FK485g4ltjn8645mwxsr2jlnqhk` FOREIGN KEY (`vehicule_id`) REFERENCES `voiture` (`id`),
  ADD CONSTRAINT `FK83wipqotmbv7tvijvh2efiw9s` FOREIGN KEY (`client_user_id`) REFERENCES `client_user` (`client_user_id`),
  ADD CONSTRAINT `FKo4bjww6sgk8qpfbxxoxrwom8h` FOREIGN KEY (`date_reservation`) REFERENCES `date_reservation` (`date_reservation`);

--
-- Constraints for table `client_user`
--
ALTER TABLE `client_user`
  ADD CONSTRAINT `FKp8bmn98xxie0ggv3aokywvfmb` FOREIGN KEY (`adresse_id`) REFERENCES `adresse` (`adresse_id`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FKnjp9x63cll1bqnoil50n3ihvy` FOREIGN KEY (`client_user_id`) REFERENCES `client_user` (`client_user_id`),
  ADD CONSTRAINT `FKq94p1u0wubwxw3bu6jag5g67p` FOREIGN KEY (`vehicule_id`) REFERENCES `voiture` (`id`);

--
-- Constraints for table `scooter`
--
ALTER TABLE `scooter`
  ADD CONSTRAINT `FK_gsdnhyxkcncd5p2rh5xts1gd8` FOREIGN KEY (`client_user_id`) REFERENCES `client_user` (`client_user_id`);

--
-- Constraints for table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `FK_9efwas96pg6kqusqp734vijni` FOREIGN KEY (`client_user_id`) REFERENCES `client_user` (`client_user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
