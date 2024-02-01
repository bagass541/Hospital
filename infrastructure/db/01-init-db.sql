DROP TABLE IF EXISTS filials;
CREATE TABLE filials (
	id SERIAL PRIMARY KEY,
	name varchar not null
);

DROP TABLE IF EXISTS polyclinics;
CREATE TABLE polyclinics (
	id SERIAL PRIMARY KEY,
	name varchar not null
);

DROP TABLE IF EXISTS hospitals;
CREATE TABLE hospitals (
	id SERIAL PRIMARY KEY,
	name varchar not null
);

DROP TABLE IF EXISTS child_institutions;
CREATE TABLE child_institutions (
	id SERIAL PRIMARY KEY,
	name varchar not null
);

DROP TABLE IF EXISTS stomatologies;
CREATE TABLE stomatologies (
	id SERIAL PRIMARY KEY,
	name varchar not null
);

DROP TABLE IF EXISTS pharmacies;
CREATE TABLE pharmacies (
	id SERIAL PRIMARY KEY,
	name varchar not null
);

DROP TABLE IF EXISTS users_info;
create table users_info (
	id SERIAL primary key, 
	fio varchar not null, 
	number varchar not null
);

DROP TABLE IF EXISTS users;
create table users (
	id SERIAL PRIMARY KEY,
	username varchar not null,
	password varchar not null,
	user_info_id bigint REFERENCES users_info(id),
	is_account_non_expired boolean not null,
	is_account_non_locked boolean not null,
	is_credentials_non_expired boolean not null,
	is_enabled boolean not null
);

DROP TABLE IF EXISTS roles;
create table roles (
	id SERIAL PRIMARY KEY,
	authority varchar not null
);

DROP TABLE IF EXISTS users_authorities;
create table users_authorities (
	user_id bigint REFERENCES users(id),
	authorities_id bigint REFERENCES roles(id),
	primary key(user_id, authorities_id)
);


create type doctor_type as enum ('Хирург', 'Терапевт', 'Лор', 'Окулист',
 'Дерматолог', 'Ортопед', 'Невропатолог', 'Кардиолог', 'Психиатр');

DROP TABLE IF EXISTS doctors;
create table doctors (
	id SERIAL primary key,
	doctor_type doctor_type not null,
	fio varchar not null,
	start_work time not null,
	end_work time not null
);

DROP TABLE IF EXISTS appointments;
create table appointments (
	id SERIAL primary key,
	doctor_id bigint not null references doctors(id) on delete cascade,
	user_id bigint references users(id) on delete cascade,
	time timestamp not null
);

DROP TABLE IF EXISTS procedures;
create table procedures (
	id SERIAL primary key,
	name varchar not null,
	minutes int not null,
	price int not null
);








