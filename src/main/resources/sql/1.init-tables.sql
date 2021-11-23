DROP TABLE EXAMPLE018.t_country CASCADE CONSTRAINTS;
CREATE TABLE EXAMPLE018.t_country (
                                      id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                      country_name varchar(255) NOT NULL
);
GRANT SELECT, INSERT, DELETE ON EXAMPLE018.t_country TO EXAMPLE018;

DROP TABLE EXAMPLE018.t_city CASCADE CONSTRAINTS;
CREATE TABLE EXAMPLE018.t_city (
                                   id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                   city_name varchar(255) NOT NULL, -- UNIQUE,
                                   country_id integer NOT NULL
);
GRANT SELECT, INSERT, DELETE ON EXAMPLE018.t_city TO EXAMPLE018;

DROP TABLE EXAMPLE018.t_user CASCADE CONSTRAINTS;
CREATE TABLE EXAMPLE018.t_user (
                                   id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                   user_password varchar(255) NULL,
                                   user_name varchar(255) NULL
);
GRANT SELECT, INSERT, DELETE ON EXAMPLE018.t_user TO EXAMPLE018;

DROP TABLE EXAMPLE018.t_role CASCADE CONSTRAINTS;
CREATE TABLE EXAMPLE018.t_role (
                                   id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                   role_name varchar(255) NULL
);
GRANT SELECT, INSERT, DELETE ON EXAMPLE018.t_role TO EXAMPLE018;

DROP TABLE EXAMPLE018.t_user_roles CASCADE CONSTRAINTS;
CREATE TABLE EXAMPLE018.t_user_roles (
                                         user_id NUMBER NOT NULL,
                                         roles_id NUMBER NOT NULL,
                                         CONSTRAINT t_user_roles_pkey PRIMARY KEY (user_id, roles_id),
                                         CONSTRAINT t_user_roles_t_role_fkey FOREIGN KEY (roles_id) REFERENCES EXAMPLE018.t_role(id),
                                         CONSTRAINT t_user_roles_t_user_fkey FOREIGN KEY (user_id) REFERENCES EXAMPLE018.t_user(id)
);
GRANT SELECT, INSERT, DELETE ON EXAMPLE018.t_user_roles TO EXAMPLE018;

