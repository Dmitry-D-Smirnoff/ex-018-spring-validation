DROP TABLE IF EXISTS t_country CASCADE;
CREATE TABLE IF NOT EXISTS t_country (
                                                  id SERIAL CONSTRAINT country_pk PRIMARY KEY,
                                                  country_name varchar(255) NOT NULL
);

DROP TABLE IF EXISTS t_city CASCADE;
CREATE TABLE IF NOT EXISTS t_city (
                                               id SERIAL CONSTRAINT city_pk PRIMARY KEY,
                                               city_name varchar(255) NOT NULL, -- UNIQUE,
                                               country_id integer NOT NULL
);

--DROP TABLE IF EXISTS example018.t_user CASCADE;
CREATE TABLE IF NOT EXISTS example018.t_user (
                                   id bigserial NOT NULL,
                                   user_password varchar(255) NULL,
                                   user_name varchar(255) NULL,
                                   CONSTRAINT t_user_pkey PRIMARY KEY (id)
);

--DROP TABLE IF EXISTS example018.t_role CASCADE;
CREATE TABLE IF NOT EXISTS example018.t_role (
                                   id bigserial NOT NULL,
                                   role_name varchar(255) NULL,
                                   CONSTRAINT t_role_pkey PRIMARY KEY (id)
);

--DROP TABLE IF EXISTS example018.t_user_roles CASCADE;
CREATE TABLE IF NOT EXISTS example018.t_user_roles (
                                         user_id int8 NOT NULL,
                                         roles_id int8 NOT NULL,
                                         CONSTRAINT t_user_roles_pkey PRIMARY KEY (user_id, roles_id),
                                         CONSTRAINT t_user_roles_t_role_fkey FOREIGN KEY (roles_id) REFERENCES example018.t_role(id),
                                         CONSTRAINT t_user_roles_t_user_fkey FOREIGN KEY (user_id) REFERENCES example018.t_user(id)
);

