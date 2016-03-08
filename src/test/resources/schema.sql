DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS student(stu_id int PRIMARY KEY auto_increment,stu_name varchar(20),stu_email varchar(30));

DROP TABLE IF EXISTS subject;

CREATE TABLE IF NOT EXISTS subject(sub_id int PRIMARY KEY auto_increment,sub_name varchar(20));

DROP TABLE IF EXISTS stu_sub;

CREATE TABLE IF NOT EXISTS stu_sub(stu_sub_id int PRIMARY KEY auto_increment, stu_id int, sub_id int);

