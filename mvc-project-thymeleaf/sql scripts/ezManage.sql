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
      `user_role` ENUM('ROLE_EMPLOYEE', 'ROLE_MANAGER', 'ROLE_ADMIN') NOT NULL,
      PRIMARY KEY (`username`, `user_role`),
      FOREIGN KEY (`username`) REFERENCES `member` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    CREATE TABLE `employee` (
      `username` VARCHAR(50) PRIMARY KEY,
      `first_name` VARCHAR(45) DEFAULT NULL,
      `last_name` VARCHAR(45) DEFAULT NULL,
      `email` VARCHAR(45) DEFAULT NULL,
      `user_role` ENUM('ROLE_EMPLOYEE', 'ROLE_MANAGER', 'ROLE_ADMIN') DEFAULT NULL,
      FOREIGN KEY (`username`) REFERENCES `member` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    INSERT INTO `member` (`username`, `password`, `active`) VALUES
      ('abdul', '{bcrypt}$your_hashed_password_here', 1),
      ('anusha', '{bcrypt}$your_hashed_password_here', 1),
      ('pratibha', '{bcrypt}$your_hashed_password_here', 1),
      ('yash', '{bcrypt}$your_hashed_password_here', 1),
      ('vikas', '{bcrypt}$your_hashed_password_here', 1),
      ('avani', '{bcrypt}$your_hashed_password_here', 1);

    INSERT INTO `role` (`username`, `user_role`) VALUES
          ('abdul', 'ROLE_EMPLOYEE'),
          ('yash', 'ROLE_EMPLOYEE'),
          ('pratibha', 'ROLE_EMPLOYEE'),
          ('avani', 'ROLE_EMPLOYEE'),
          ('avani', 'ROLE_MANAGER'),
          ('anusha', 'ROLE_EMPLOYEE'),
          ('anusha', 'ROLE_MANAGER'),
          ('vikas', 'ROLE_EMPLOYEE'),
          ('vikas', 'ROLE_MANAGER'),
          ('vikas', 'ROLE_ADMIN');

    INSERT INTO `employee` (`username`, `first_name`, `last_name`, `email`, `user_role`) VALUES
      ('abdul', 'Abdul', 'Ahad', 'abdul@ahad', 'ROLE_EMPLOYEE'),
      ('yash', 'Yash', 'Prakash', 'yash@prakash', 'ROLE_EMPLOYEE'),
      ('pratibha', 'Pratibha', 'Sharma', 'pratibha@sharma', 'ROLE_EMPLOYEE'),
      ('avani', 'Avani', 'Gupta', 'avani@gupta', 'ROLE_MANAGER'),
      ('anusha', 'Anusha', 'Reddy', 'anusha@reddy', 'ROLE_MANAGER'),
      ('vikas', 'Vikas', 'Seervi', 'vikas@seervi', 'ROLE_ADMIN');