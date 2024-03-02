-- Create One user As Admin
INSERT INTO user (id,first_name, last_name, user_email, contact, user_password, user_type,user_status)
VALUES (52,'admin', 's', 'admin@gmail.com', '1234567890', 'password', '1L','ADMIN');

-- If someone registered By Default Staus will be UU, Need to change the user_status to Admin
UPDATE user SET user_status='ADMIN' WHERE id=1;

 insert into user_roles values (1,'ADMIN');
 insert into user_roles values (2,'EXHIBITOR');
 insert into user_roles values (3,'USER');

-- Path: src/main/resources/data.sql