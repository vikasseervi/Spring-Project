CREATE DATABASE IF NOT EXISTS `ezManage`;
USE `ezManage`;

-- Drop tables in reverse order to resolve foreign key constraint issue
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `members`;

-- Table structure for table `members`
CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- Inserting data for table `members`
-- NOTE: The passwords are encrypted using BCrypt
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
-- Default passwords here are: fun123
INSERT INTO `members`
VALUES
  ('abdul','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
  ('anusha','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
  ('pratibha','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
  ('yash','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
  ('vikas','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

-- Table structure for table `authorities`
CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`, `role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `roles` VALUES
  ('abdul','ROLE_EMPLOYEE'),
  ('yash','ROLE_EMPLOYEE'),
  ('pratibha','ROLE_EMPLOYEE'),
  ('avani','ROLE_EMPLOYEE'),
  ('avani','ROLE_MANAGER'),
  ('anusha','ROLE_EMPLOYEE'),
  ('anusha','ROLE_MANAGER'),
  ('vikas','ROLE_EMPLOYEE'),
  ('vikas','ROLE_MANAGER'),
  ('vikas','ROLE_ADMIN');

-- Table structure for table `employee`
CREATE TABLE `employee` (
  `user_id` varchar(50) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `employee` VALUES
  ('abdul','Abdul','Ahad','abdul@ahad','ROLE_EMPLOYEE'),
  ('yash','Yash','Prakash','yash@prakash','ROLE_EMPLOYEE'),
  ('pratibha','Pratibha','Sharma','pratibha@sharma','ROLE_EMPLOYEE'),
  ('avani','Avani','Gupta','avani@gupta','ROLE_MANAGER'),
  ('anusha','Anusha','Reddy','anusha@reddy','ROLE_MANAGER'),
  ('vikas','Vikas','Seervi','vikas@seervi','ROLE_ADMIN');