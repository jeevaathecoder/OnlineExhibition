INSERT INTO user (firstname, lastname, email, mobile, password, user_type_id)
VALUES ('admin','admin','admin@gmail.com,''1234567890','passoword',1);

alter user set user_status ='ADMIN' WHERE user_id = 1;

 insert into user_roles values (1,'ADMIN');
 insert into user_roles values (2,'EXHIBITOR');
 insert into user_roles values (3,'USER');

-- Path: src/main/resources/data.sql