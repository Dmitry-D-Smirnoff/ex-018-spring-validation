DROP TABLE example018.t_city;
DROP TABLE example018.t_country;
DROP TABLE example018.t_user_roles;
DROP TABLE example018.t_user;
DROP TABLE example018.t_role;

CREATE TABLE example018.t_country (
                                                  id NUMBER CONSTRAINT country_pk PRIMARY KEY,
                                                  country_name varchar(255) NOT NULL
);
GRANT SELECT, INSERT, DELETE ON example018.t_country TO example018;

CREATE TABLE example018.t_city (
                                               id NUMBER CONSTRAINT city_pk PRIMARY KEY,
                                               city_name varchar(255) NOT NULL, -- UNIQUE,
                                               country_id integer NOT NULL
);
GRANT SELECT, INSERT, DELETE ON example018.t_city TO example018;

CREATE TABLE example018.t_user (
                                   id NUMBER NOT NULL,
                                   user_password varchar(255) NULL,
                                   user_name varchar(255) NULL,
                                   CONSTRAINT t_user_pkey PRIMARY KEY (id)
);
GRANT SELECT, INSERT, DELETE ON example018.t_user TO example018;

CREATE TABLE example018.t_role (
                                   id NUMBER NOT NULL,
                                   role_name varchar(255) NULL,
                                   CONSTRAINT t_role_pkey PRIMARY KEY (id)
);
GRANT SELECT, INSERT, DELETE ON example018.t_role TO example018;

CREATE TABLE example018.t_user_roles (
                                         user_id NUMBER NOT NULL,
                                         roles_id NUMBER NOT NULL,
                                         CONSTRAINT t_user_roles_pkey PRIMARY KEY (user_id, roles_id),
                                         CONSTRAINT t_user_roles_t_role_fkey FOREIGN KEY (roles_id) REFERENCES example018.t_role(id),
                                         CONSTRAINT t_user_roles_t_user_fkey FOREIGN KEY (user_id) REFERENCES example018.t_user(id)
);
GRANT SELECT, INSERT, DELETE ON example018.t_user_roles TO example018;

INSERT INTO example018.t_country(country_name) VALUES ('Austria');
INSERT INTO example018.t_country(country_name) VALUES ('Greece');
INSERT INTO example018.t_country(country_name) VALUES ('France');

INSERT INTO example018.t_city(city_name, country_id) VALUES ('Vienna', 1);
INSERT INTO example018.t_city(city_name, country_id) VALUES ('Athens', 2);
INSERT INTO example018.t_city(city_name, country_id) VALUES ('Paris', 3);
INSERT INTO example018.t_city(city_name, country_id) VALUES ('Marseille', 3);

INSERT INTO example018.t_role(role_name) VALUES ('ROLE_ADMIN');
INSERT INTO example018.t_role(role_name) VALUES ('ROLE_USER');
INSERT INTO example018.t_role(role_name) VALUES ('ROLE_UNUSED');
