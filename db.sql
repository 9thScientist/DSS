-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Apartamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Apartamento` (
  `Id` INT NOT NULL,
  `Saldo` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Morador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Morador` (
  `Id` INT NOT NULL,
  `Apartamento` INT NOT NULL,
  `Nome` VARCHAR(50) NOT NULL,
  `Contacto` VARCHAR(50) NOT NULL,
  `Saldo` DECIMAL(8,2) NOT NULL,
  `Ativo` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Morador_Apartamento1_idx` (`Apartamento` ASC),
  CONSTRAINT `fk_Morador_Apartamento1`
    FOREIGN KEY (`Apartamento`)
    REFERENCES `mydb`.`Apartamento` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `Id` INT NOT NULL,
  `Categoria` VARCHAR(50) NOT NULL,
  `Recorrente` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Movimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Movimento` (
  `Id` INT NOT NULL,
  `Apartamento` INT NOT NULL,
  `Morador` INT NOT NULL,
  `Valor` DECIMAL(8,2) NOT NULL,
  `Data` DATE NOT NULL,
  `Transacao` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Movimento_Apartamento1_idx` (`Apartamento` ASC),
  INDEX `fk_Movimento_Morador1_idx` (`Morador` ASC),
  CONSTRAINT `fk_Movimento_Apartamento1`
    FOREIGN KEY (`Apartamento`)
    REFERENCES `mydb`.`Apartamento` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movimento_Morador1`
    FOREIGN KEY (`Morador`)
    REFERENCES `mydb`.`Morador` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Despesa` (
  `Id` INT NOT NULL,
  `Categoria` INT NOT NULL,
  `Pago` TINYINT(1) NOT NULL,
  `Descrição` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Despesa_Categoria1_idx` (`Categoria` ASC),
  INDEX `fk_Despesa_Movimento1_idx` (`Id` ASC),
  CONSTRAINT `fk_Despesa_Categoria1`
    FOREIGN KEY (`Categoria`)
    REFERENCES `mydb`.`Categoria` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Despesa_Movimento1`
    FOREIGN KEY (`Id`)
    REFERENCES `mydb`.`Movimento` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Racio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Racio` (
  `Morador` INT NOT NULL,
  `Despesa` INT NOT NULL,
  `Racio` DECIMAL(3,2) NOT NULL,
  PRIMARY KEY (`Morador`, `Despesa`),
  INDEX `fk_Morador_has_Despesa_Despesa1_idx` (`Despesa` ASC),
  INDEX `fk_Morador_has_Despesa_Morador1_idx` (`Morador` ASC),
  CONSTRAINT `fk_Morador_has_Despesa_Morador1`
    FOREIGN KEY (`Morador`)
    REFERENCES `mydb`.`Morador` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Morador_has_Despesa_Despesa1`
    FOREIGN KEY (`Despesa`)
    REFERENCES `mydb`.`Despesa` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
