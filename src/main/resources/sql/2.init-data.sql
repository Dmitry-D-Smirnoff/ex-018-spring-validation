INSERT INTO EXAMPLE018.t_country(country_name) VALUES ('Austria');
INSERT INTO EXAMPLE018.t_country(country_name) VALUES ('Greece');
INSERT INTO EXAMPLE018.t_country(country_name) VALUES ('France');

INSERT INTO EXAMPLE018.t_person(person_name, country_id) VALUES ('Vienna', 1);
INSERT INTO EXAMPLE018.t_person(person_name, country_id) VALUES ('Athens', 2);
INSERT INTO EXAMPLE018.t_person(person_name, country_id) VALUES ('Paris', 3);
INSERT INTO EXAMPLE018.t_person(person_name, country_id) VALUES ('Marseille', 3);

INSERT INTO EXAMPLE018.t_role(role_name) VALUES ('ROLE_ADMIN');
INSERT INTO EXAMPLE018.t_role(role_name) VALUES ('ROLE_USER');
INSERT INTO EXAMPLE018.t_role(role_name) VALUES ('ROLE_UNUSED');
