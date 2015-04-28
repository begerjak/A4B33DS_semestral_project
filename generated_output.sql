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
--CREATE SCHEMA IF NOT EXISTS public DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
--USE public ;

-- -----------------------------------------------------
-- Table `public`.`Countries`
-- -----------------------------------------------------
CREATE SEQUENCE mobility_description;

CREATE TABLE IF NOT EXISTS public.Countries (
  country_id INT CHECK (country_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Countries_seq'),
  country_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (country_id));

-- -----------------------------------------------------
-- Table `public`.`Schools`
-- -----------------------------------------------------
CREATE SEQUENCE country_id;

CREATE TABLE IF NOT EXISTS public.Schools (
  school_id INT CHECK (school_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Schools_seq'),
  country_id INT CHECK (country_id > 0) NOT NULL REFERENCES public.Countries (country_id) ON DELETE CASCADE ON UPDATE CASCADE,
  school_name VARCHAR(45) NOT NULL,
  school_desc TEXT NULL,
  PRIMARY KEY (school_id));


-- -----------------------------------------------------
-- Table `public`.`Groups`
-- -----------------------------------------------------
CREATE SEQUENCE fk_Groups_1;

CREATE TABLE IF NOT EXISTS public.Groups (
  group_id INT CHECK (group_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Groups_seq'),
  school_id INT CHECK (school_id > 0) NOT NULL REFERENCES public.Schools (school_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (group_id));

-- -----------------------------------------------------
-- Table `public`.`Users`
-- -----------------------------------------------------
CREATE SEQUENCE student_from;

CREATE TABLE IF NOT EXISTS public.Users (
  user_id INT CHECK (user_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Users_seq'),
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_id));

-- -----------------------------------------------------
-- Table `public`.`Students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.Students (
  user_id INT CHECK (user_id > 0) NULL REFERENCES public.Users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  student_from TIMESTAMP(0) NOT NULL,
  student_to VARCHAR(45) NULL,
  group_id INT CHECK (group_id > 0) NOT NULL REFERENCES public.Groups (group_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (user_id));

-- -----------------------------------------------------
-- Table `public`.`Coordinators`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.Coordinators (
  user_id INT CHECK (user_id > 0) NOT NULL REFERENCES public.Users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
  coordinator_to TIMESTAMP(0) NULL,
  coordinator_from TIMESTAMP(0) NOT NULL,
  school_id INT CHECK (school_id > 0) NOT NULL REFERENCES public.Schools (school_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  PRIMARY KEY (user_id));

-- -----------------------------------------------------
-- Table `public`.`Mobilities`
-- -----------------------------------------------------
CREATE SEQUENCE mobility_description;

CREATE TABLE IF NOT EXISTS public.Mobilities (
  mobility_id INT CHECK (mobility_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Mobilities_seq'),
  host_school_id INT CHECK (host_school_id > 0) NOT NULL REFERENCES public.Schools (school_id) ON DELETE SET NULL ON UPDATE CASCADE,
  start TIMESTAMP(0) NOT NULL,
  end TIMESTAMP(0) NOT NULL,
  mobility_description TEXT NOT NULL,
  PRIMARY KEY (mobility_id));

-- -----------------------------------------------------
-- Table `public`.`Projects`
-- -----------------------------------------------------
CREATE SEQUENCE project_name;

CREATE TABLE IF NOT EXISTS public.Projects (
  project_id INT CHECK (project_id > 0) NULL DEFAULT NEXTVAL ('public.Projects_seq'),
  project_name VARCHAR(45) NOT NULL,
  project_desc TEXT NULL,
  PRIMARY KEY (project_id));

-- -----------------------------------------------------
-- Table `public`.`Outputs`
-- -----------------------------------------------------
CREATE SEQUENCE output_name;

CREATE TABLE IF NOT EXISTS public.Outputs (
  output_id INT CHECK (output_id > 0) NULL DEFAULT NEXTVAL ('public.Outputs_seq'),
  output_name VARCHAR(45) NOT NULL,
  output_desc TEXT NULL,
  project_id INT CHECK (project_id > 0) NOT NULL REFERENCES public.Projects (project_id) ON DELETE SET NULL ON UPDATE CASCADE,
  PRIMARY KEY (output_id));

-- -----------------------------------------------------
-- Table `public`.`Articles`
-- -----------------------------------------------------
CREATE SEQUENCE article_id;

CREATE TABLE IF NOT EXISTS public.Articles (
  article_id INT CHECK (article_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Articles_seq'),
  content TEXT NOT NULL,
  name VARCHAR(45) NOT NULL,
  rating SMALLINT NOT NULL,
  PRIMARY KEY (article_id));

-- -----------------------------------------------------
-- Table `public`.`Externist`
-- -----------------------------------------------------
CREATE SEQUENCE public.Output_commen;

CREATE TABLE IF NOT EXISTS public.Externist (
  user_id INT CHECK (user_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Externist_seq') REFERENCES public.Users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
  schoold_id INT CHECK (schoold_id > 0) NOT NULL REFERENCES public.Schools (school_id) ON DELETE CASCADE ON UPDATE CASCADE,
  active SMALLINT NOT NULL,
  PRIMARY KEY (user_id),);

-- -----------------------------------------------------
-- Table `public`.`Output_comments`
-- -----------------------------------------------------
CREATE SEQUENCE public.Attending_schools;

CREATE TABLE IF NOT EXISTS public.Output_comments (
  comment_id INT CHECK (comment_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Output_comments_seq') REFERENCES public.Output_comments (comment_id) ON DELETE CASCADE ON UPDATE CASCADE,
  user_id INT CHECK (user_id > 0) NOT NULL REFERENCES public.Users (user_id) ON DELETE SET NULL ON UPDATE CASCADE,
  output_id INT CHECK (output_id > 0) NOT NULL REFERENCES public.Outputs (output_id) ON DELETE CASCADE ON UPDATE CASCADE,
  content TEXT NOT NULL,
  parrent_comment INT CHECK (parrent_comment > 0) NOT NULL,
  rating SMALLINT NOT NULL,
  PRIMARY KEY (comment_id));

-- -----------------------------------------------------
-- Table `public`.`Attending_schools`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.Attending_schools (
  project_id INT CHECK (project_id > 0) NOT NULL REFERENCES public.Projects (project_id) ON DELETE CASCADE ON UPDATE CASCADE,
  school_id INT CHECK (school_id > 0) NOT NULL REFERENCES public.Schools (school_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (project_id, school_id));

-- -----------------------------------------------------
-- Table `public`.`Article_authors`
-- -----------------------------------------------------
CREATE SEQUENCE article_authors_id;

CREATE TABLE IF NOT EXISTS public.Article_authors (
  user_id INT CHECK (user_id > 0) NULL REFERENCES public.Students (user_id) ON DELETE SET NULL ON UPDATE CASCADE,
  article_id INT CHECK (article_id > 0) NOT NULL REFERENCES public.Articles (article_id) ON DELETE CASCADE ON UPDATE CASCADE,
  article_authors_id INT CHECK (article_authors_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Article_authors_seq'),
  PRIMARY KEY (article_authors_id));

-- -----------------------------------------------------
-- Table `public`.`Attending_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS public.Attending_groups (
  mobility_id INT CHECK (mobility_id > 0) NOT NULL REFERENCES public.Mobilities (mobility_id) ON DELETE CASCADE ON UPDATE CASCADE,
  group_id INT CHECK (group_id > 0) NOT NULL REFERENCES public.Groups (group_id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (group_id, mobility_id));

-- -----------------------------------------------------
-- Table `public`.`Article_comments`
-- -----------------------------------------------------
CREATE SEQUENCE fk_Article_comments_1;

CREATE TABLE IF NOT EXISTS public.Article_comments (
  comment_id INT CHECK (comment_id > 0) NOT NULL DEFAULT NEXTVAL ('public.Article_comments_seq') REFERENCES public.Article_comments (comment_id) ON DELETE CASCADE ON UPDATE CASCADE,
  user_id INT CHECK (user_id > 0) NULL REFERENCES public.Users (user_id) ON DELETE SET NULL ON UPDATE CASCADE,
  article_id INT CHECK (article_id > 0) NOT NULL REFERENCES public.Articles (article_id) ON DELETE CASCADE ON UPDATE CASCADE,
  content TEXT NOT NULL,
  parrent_comment INT CHECK (parrent_comment > 0) NULL,
  rating SMALLINT NOT NULL,
  PRIMARY KEY (comment_id));

/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */
