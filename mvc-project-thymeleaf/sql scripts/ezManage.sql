    CREATE DATABASE IF NOT EXISTS `ezManage`;
    USE `ezManage`;

    -- Drop tables in reverse order to resolve foreign key constraint issue
    DROP TABLE IF EXISTS `role`;
    DROP TABLE IF EXISTS `employee`;
    DROP TABLE IF EXISTS `member`;


    CREATE TABLE `member` (
      `id` INT AUTO_INCREMENT PRIMARY KEY,
      `username` VARCHAR(50) NOT NULL UNIQUE,
      `password` CHAR(68) DEFAULT NULL,
      `active` TINYINT DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    CREATE TABLE `role` (
      `username` VARCHAR(50) NOT NULL,
      `user_role` ENUM('EMPLOYEE', 'MANAGER', 'ADMIN') NOT NULL,
      UNIQUE KEY (`username`, `user_role`),
      FOREIGN KEY (`username`) REFERENCES `member` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    CREATE TABLE `employee` (
      `username` VARCHAR(50) PRIMARY KEY,
      `first_name` VARCHAR(45) DEFAULT NULL,
      `last_name` VARCHAR(45) DEFAULT NULL,
      `email` VARCHAR(45) DEFAULT NULL,
      `user_role` ENUM('EMPLOYEE', 'MANAGER', 'ADMIN') DEFAULT NULL,
      FOREIGN KEY (`username`) REFERENCES `member` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--    password is vikas

    INSERT INTO `member` (`username`, `password`, `active`) VALUES
      ('abdul', '{bcrypt}$2a$12$pBdU.51Jmo2YKoN3ASkTyeXGRxoC5d6kirz7JHPcWwfHrSwiyIqTK', 1),
      ('anusha', '{bcrypt}$2a$12$pBdU.51Jmo2YKoN3ASkTyeXGRxoC5d6kirz7JHPcWwfHrSwiyIqTK', 1),
      ('pratibha', '{bcrypt}$2a$12$pBdU.51Jmo2YKoN3ASkTyeXGRxoC5d6kirz7JHPcWwfHrSwiyIqTK', 1),
      ('yash', '{bcrypt}$2a$12$pBdU.51Jmo2YKoN3ASkTyeXGRxoC5d6kirz7JHPcWwfHrSwiyIqTK', 1),
      ('vikas', '{bcrypt}$2a$12$pBdU.51Jmo2YKoN3ASkTyeXGRxoC5d6kirz7JHPcWwfHrSwiyIqTK', 1),
      ('avani', '{bcrypt}$2a$12$pBdU.51Jmo2YKoN3ASkTyeXGRxoC5d6kirz7JHPcWwfHrSwiyIqTK', 1);

    INSERT INTO `role` (`username`, `user_role`) VALUES
          ('abdul', 'EMPLOYEE'),
          ('yash', 'EMPLOYEE'),
          ('pratibha', 'EMPLOYEE'),
          ('avani', 'EMPLOYEE'),
          ('avani', 'MANAGER'),
          ('anusha', 'EMPLOYEE'),
          ('anusha', 'MANAGER'),
          ('vikas', 'EMPLOYEE'),
          ('vikas', 'MANAGER'),
          ('vikas', 'ADMIN');

    INSERT INTO `employee` (`username`, `first_name`, `last_name`, `email`, `user_role`) VALUES
      ('abdul', 'Abdul', 'Ahad', 'abdul@ahad', 'EMPLOYEE'),
      ('yash', 'Yash', 'Prakash', 'yash@prakash', 'EMPLOYEE'),
      ('pratibha', 'Pratibha', 'Sharma', 'pratibha@sharma', 'EMPLOYEE'),
      ('avani', 'Avani', 'Gupta', 'avani@gupta', 'MANAGER'),
      ('anusha', 'Anusha', 'Reddy', 'anusha@reddy', 'MANAGER'),
      ('vikas', 'Vikas', 'Seervi', 'vikas@seervi', 'ADMIN');