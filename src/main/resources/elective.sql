CREATE DATABASE IF NOT EXISTS`elective` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE elective;

CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_role` enum('ADMIN','TEACHER','STUDENT') NOT NULL,
  `u_login` varchar(30) NOT NULL,
  `u_password` varchar(30) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

CREATE TABLE `common_info` (
  `u_id` int(11) NOT NULL,
  `ci_firstname` varchar(30) DEFAULT NULL,
  `ci_secondname` varchar(30) DEFAULT NULL,
  `ci_thirdname` varchar(30) DEFAULT NULL,
  `ci_university` varchar(45) DEFAULT NULL,
  `ci_faculty` varchar(100) DEFAULT NULL,
  `ci_department` varchar(100) DEFAULT NULL,
  `ci_birth` date DEFAULT NULL,
  `ci_email` varchar(30) DEFAULT NULL,
  `ci_phone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `name` (`ci_firstname`,`ci_secondname`,`ci_thirdname`),
  KEY `university` (`ci_university`),
  KEY `faculty` (`ci_faculty`),
  KEY `department` (`ci_department`),
  CONSTRAINT `common_info_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `teachers` (
  `t_id` int(11) NOT NULL,
  `t_position` enum('PROFESSOR','DOCENT','SENIOR_LECTURER','ASSISTANT') DEFAULT NULL,
  `t_experience` tinyint(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `experience` (`t_experience`),
  KEY `position` (`t_position`),
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `courses` (
  `crs_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` int(11) DEFAULT NULL,
  `crs_name` varchar(30) DEFAULT NULL,
  `crs_start_date` date DEFAULT NULL,
  `crs_end_date` date DEFAULT NULL,
  `crs_description` mediumtext,
  `crs_status` enum('OPEN_SET','STARTED','COMPLETED','NOT_STARTED') DEFAULT NULL,
  PRIMARY KEY (`crs_id`),
  KEY `t_id` (`t_id`),
  KEY `name` (`crs_name`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `teachers` (`t_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

CREATE TABLE `students` (
  `st_id` int(11) NOT NULL,
  `st_course` tinyint(4) DEFAULT NULL,
  `st_exp_date` year(4) DEFAULT NULL,
  PRIMARY KEY (`st_id`),
  KEY `exp_date` (`st_exp_date`),
  KEY `course` (`st_course`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`st_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `st_m2m_crs` (
  `st_id` int(11) NOT NULL,
  `crs_id` int(11) NOT NULL,
  `st_status` enum('APPLIED_FOR','TRAINING','NOT_ENROLLED','COMPLETED','NOT_STARTED') DEFAULT NULL,
  `st_mark` tinyint(4) DEFAULT NULL,
  `st_review` text,
  PRIMARY KEY (`st_id`,`crs_id`),
  KEY `crs_id` (`crs_id`),
  CONSTRAINT `st_m2m_crs_ibfk_1` FOREIGN KEY (`st_id`) REFERENCES `students` (`st_id`) ON DELETE CASCADE,
  CONSTRAINT `st_m2m_crs_ibfk_2` FOREIGN KEY (`crs_id`) REFERENCES `courses` (`crs_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_m2m_st` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` int(11) NOT NULL,
  `st_id` int(11) NOT NULL,
  `r_mark` tinyint(4) NOT NULL,
  `r_review` text NOT NULL,
  PRIMARY KEY (`r_id`,`t_id`,`st_id`),
  KEY `t_id` (`t_id`),
  KEY `st_id` (`st_id`),
  CONSTRAINT `t_m2m_st_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `teachers` (`t_id`) ON DELETE CASCADE,
  CONSTRAINT `t_m2m_st_ibfk_2` FOREIGN KEY (`st_id`) REFERENCES `students` (`st_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




