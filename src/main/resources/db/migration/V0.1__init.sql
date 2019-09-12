CREATE TABLE br (
	id BINARY(16) NOT NULL,
	url varchar(500) NOT NULL,
	address varchar(250),
	area varchar(250),
	fee int,
	price int,
	sqm decimal,
	rooms decimal,
	time_added DATETIME NOT NULL,
	time_updated DATETIME,
	time_removed DATETIME,
	broker_person_name varchar(250),
	broker_firm_name varchar(250),
	broker_firm_web_page varchar(500),
	coordinate varchar(250),
	municipality varchar(250),
	PRIMARY KEY (id),
	UNIQUE (url)
);

CREATE TABLE house (
	id BINARY(16) NOT NULL,
	url varchar(500) NOT NULL,
	address varchar(250),
	area varchar(250),
	type varchar(250),
	price int,
	sqm_living decimal,
	sqm_living_additional decimal,
	sqm_land decimal,
	rooms decimal,
	cost_year decimal,
	built_year int,
	time_added DATETIME NOT NULL,
	time_updated DATETIME,
	time_removed DATETIME,
	broker_person_name varchar(250),
	broker_firm_name varchar(250),
	broker_firm_web_page varchar(500),
	coordinate varchar(250),
	municipality varchar(250),
	PRIMARY KEY (id),
	UNIQUE (url)
);

CREATE TABLE image (
	id int NOT NULL AUTO_INCREMENT,
	object_id BINARY(16) NOT NULL,
	path varchar(250) NOT NULL,
	time_added DATETIME NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (path)
)
