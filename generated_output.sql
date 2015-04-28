-- PostgreSQL Script
-- Wed Mar 25 16:01:21 2015

/* SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0; */
/* SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0; */
/* SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'; */

-- -----------------------------------------------------
-- Schema public
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema public
-- -----------------------------------------------------
--CREATE SCHEMA public DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
--USE public ;

-- -----------------------------------------------------
-- Table `public`.`Countries`
-- -----------------------------------------------------
CREATE SEQUENCE countries_seq;

CREATE TABLE Countries (
  country_id INT CHECK (country_id > 0) NOT NULL DEFAULT NEXTVAL ('countries_seq'),
  country_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (country_id));

-- -----------------------------------------------------
-- Table `public`.`Schools`
-- -----------------------------------------------------
CREATE SEQUENCE schools_seq;

CREATE TABLE Schools (
  school_id INT CHECK (school_id > 0) NOT NULL DEFAULT NEXTVAL ('schools_seq'),
  country_id INT CHECK (country_id > 0) NOT NULL REFERENCES Countries (country_id) ON DELETE CASCADE ON UPDATE CASCADE,
  school_name VARCHAR(45) NOT NULL,
  school_desc TEXT NULL,
  PRIMARY KEY (school_id));


-- -----------------------------------------------------
-- Table `public`.`Groups`
-- -----------------------------------------------------
CREATE SEQUENCE groups_seq;

CREATE TABLE Groups (
  group_id INT CHECK (group_id > 0) NOT NULL DEFAULT NEXTVAL ('groups_seq'),
  school_id INT CHECK (school_id > 0) NOT NULL REFERENCES Schools (school_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (group_id));

-- -----------------------------------------------------
-- Table `public`.`Users`
-- -----------------------------------------------------
CREATE SEQUENCE users_seq;

CREATE TABLE Users (
  user_id INT CHECK (user_id > 0) NOT NULL DEFAULT NEXTVAL ('users_seq'),
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_id));

-- -----------------------------------------------------
-- Table `public`.`Students`
-- -----------------------------------------------------
CREATE TABLE Students (
  user_id INT CHECK (user_id > 0) NULL REFERENCES Users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  student_from TIMESTAMP NOT NULL,
  student_to VARCHAR(45) NULL,
  group_id INT CHECK (group_id > 0) NOT NULL REFERENCES Groups (group_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (user_id));

-- -----------------------------------------------------
-- Table `public`.`Coordinators`
-- -----------------------------------------------------
CREATE TABLE Coordinators (
  user_id INT CHECK (user_id > 0) NOT NULL REFERENCES Users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
  coordinator_to TIMESTAMP NULL,
  coordinator_from TIMESTAMP NOT NULL,
  school_id INT CHECK (school_id > 0) NOT NULL REFERENCES Schools (school_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (user_id));

-- -----------------------------------------------------
-- Table `public`.`Mobilities`
-- -----------------------------------------------------
CREATE SEQUENCE mobilities_seq;

CREATE TABLE Mobilities (
  mobility_id INT CHECK (mobility_id > 0) NOT NULL DEFAULT NEXTVAL ('mobilities_seq'),
  host_school_id INT CHECK (host_school_id > 0) NOT NULL REFERENCES Schools (school_id) ON DELETE SET NULL ON UPDATE CASCADE,
  start TIMESTAMP NOT NULL,
  end TIMESTAMP NOT NULL,
  mobility_description TEXT NOT NULL,
  PRIMARY KEY (mobility_id));

-- -----------------------------------------------------
-- Table `public`.`Projects`
-- -----------------------------------------------------
CREATE SEQUENCE projects_seq;

CREATE TABLE Projects (
  project_id INT CHECK (project_id > 0) NULL DEFAULT NEXTVAL ('projects_seq'),
  project_name VARCHAR(45) NOT NULL,
  project_desc TEXT NULL,
  PRIMARY KEY (project_id));

-- -----------------------------------------------------
-- Table `public`.`Outputs`
-- -----------------------------------------------------
CREATE SEQUENCE outputs_seq;

CREATE TABLE Outputs (
  output_id INT CHECK (output_id > 0) NULL DEFAULT NEXTVAL ('outputs_seq'),
  output_name VARCHAR(45) NOT NULL,
  output_desc TEXT NULL,
  project_id INT CHECK (project_id > 0) NOT NULL REFERENCES Projects (project_id) ON DELETE SET NULL ON UPDATE CASCADE,
  PRIMARY KEY (output_id));

-- -----------------------------------------------------
-- Table `public`.`Articles`
-- -----------------------------------------------------
CREATE SEQUENCE articles_seq;

CREATE TABLE Articles (
  article_id INT CHECK (article_id > 0) NOT NULL DEFAULT NEXTVAL ('articles_seq'),
  content TEXT NOT NULL,
  name VARCHAR(45) NOT NULL,
  rating SMALLINT NOT NULL,
  PRIMARY KEY (article_id));

-- -----------------------------------------------------
-- Table `public`.`Externist`
-- -----------------------------------------------------
CREATE SEQUENCE externist_seq;

CREATE TABLE Externist (
  user_id INT CHECK (user_id > 0) NOT NULL DEFAULT NEXTVAL ('externist_seq') REFERENCES Users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
  schoold_id INT CHECK (schoold_id > 0) NOT NULL REFERENCES Schools (school_id) ON DELETE CASCADE ON UPDATE CASCADE,
  active SMALLINT NOT NULL,
  PRIMARY KEY (user_id),);

-- -----------------------------------------------------
-- Table `public`.`Output_comments`
-- -----------------------------------------------------
CREATE SEQUENCE output_comments_seq;

CREATE TABLE Output_comments (
  comment_id INT CHECK (comment_id > 0) NOT NULL DEFAULT NEXTVAL ('output_comments_seq') REFERENCES Output_comments (comment_id) ON DELETE CASCADE ON UPDATE CASCADE,
  user_id INT CHECK (user_id > 0) NOT NULL REFERENCES Users (user_id) ON DELETE SET NULL ON UPDATE CASCADE,
  output_id INT CHECK (output_id > 0) NOT NULL REFERENCES Outputs (output_id) ON DELETE CASCADE ON UPDATE CASCADE,
  content TEXT NOT NULL,
  parrent_comment INT CHECK (parrent_comment > 0) NOT NULL,
  rating SMALLINT NOT NULL,
  PRIMARY KEY (comment_id));

-- -----------------------------------------------------
-- Table `public`.`Attending_schools`
-- -----------------------------------------------------
CREATE TABLE Attending_schools (
  project_id INT CHECK (project_id > 0) NOT NULL REFERENCES Projects (project_id) ON DELETE CASCADE ON UPDATE CASCADE,
  school_id INT CHECK (school_id > 0) NOT NULL REFERENCES Schools (school_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (project_id, school_id));

-- -----------------------------------------------------
-- Table `public`.`Article_authors`
-- -----------------------------------------------------
CREATE SEQUENCE article_authors_seq;

CREATE TABLE Article_authors (
  user_id INT CHECK (user_id > 0) NULL REFERENCES Students (user_id) ON DELETE SET NULL ON UPDATE CASCADE,
  article_id INT CHECK (article_id > 0) NOT NULL REFERENCES Articles (article_id) ON DELETE CASCADE ON UPDATE CASCADE,
  article_authors_id INT CHECK (article_authors_id > 0) NOT NULL DEFAULT NEXTVAL ('article_authors_seq'),
  PRIMARY KEY (article_authors_id));

-- -----------------------------------------------------
-- Table `public`.`Attending_groups`
-- -----------------------------------------------------
CREATE TABLE Attending_groups (
  mobility_id INT CHECK (mobility_id > 0) NOT NULL REFERENCES Mobilities (mobility_id) ON DELETE CASCADE ON UPDATE CASCADE,
  group_id INT CHECK (group_id > 0) NOT NULL REFERENCES Groups (group_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (group_id, mobility_id));

-- -----------------------------------------------------
-- Table `public`.`Article_comments`
-- -----------------------------------------------------
CREATE SEQUENCE article_comments_seq;

CREATE TABLE Article_comments (
  comment_id INT CHECK (comment_id > 0) NOT NULL DEFAULT NEXTVAL ('article_comments_seq') REFERENCES Article_comments (comment_id) ON DELETE CASCADE ON UPDATE CASCADE,
  user_id INT CHECK (user_id > 0) NULL REFERENCES Users (user_id) ON DELETE SET NULL ON UPDATE CASCADE,
  article_id INT CHECK (article_id > 0) NOT NULL REFERENCES Articles (article_id) ON DELETE CASCADE ON UPDATE CASCADE,
  content TEXT NOT NULL,
  parrent_comment INT CHECK (parrent_comment > 0) NULL,
  rating SMALLINT NOT NULL,
  PRIMARY KEY (comment_id));

/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */
