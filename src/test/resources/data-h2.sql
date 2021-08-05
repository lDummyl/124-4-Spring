--ROLE
INSERT INTO u_roles (id, name) VALUES (1, 'ROLE_GUEST');

--USER
INSERT INTO users (id, firstname, lastname, username, password, user_status, age) VALUES ('1','Test','Testov','test22','test',0, 19);
INSERT INTO users (id, firstname, lastname, username, password, user_status, age) VALUES ('2','Sergey','Zuk','serj','serj',0, 20);

--USER_ROLE
INSERT INTO users_roles (user_id,roles_id) VALUES (1,1);

--DRIVER
INSERT INTO driver (id, first_name, last_name, driver_license) VALUES (1, 'Vin', 'Diesel', 777777);

--CAR
INSERT INTO car (id, brand, model, driver_id, category_, user_id) VALUES (1, 'BMW', '5-series', 1, 'B', 1);
INSERT INTO car (id, brand, model, driver_id, category_, user_id) VALUES (2, 'LADA', 'GRANTA', 1, 'B', 2);

COMMIT;

