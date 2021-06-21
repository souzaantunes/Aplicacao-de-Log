create database log;

CREATE TABLE Log (
  id serial NOT null  ,
  data timestamp(6) with time zone DEFAULT NULL,
  ip varchar(255) DEFAULT NULL,
  metodo varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  userAgent varchar(255) DEFAULT NULL,
  PRIMARY KEY (id));
  