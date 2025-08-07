-- PostgreSQL 17 version - Data insertion
-- SET search_path TO universidad;

INSERT INTO roles (rolename) VALUES ('Customer'), ('Admin');

INSERT INTO users (username, name, address, email, password, roleid) 
VALUES 
  ('donCharro', 'Ricardo Melo', 'Carrera Prueba 1 00 - 00', 'evaristoguti68@une.net.co', '123456789riguez', 1),
  ('fuego_420', 'Julian Andres Rosero Zapata', 'Carrera Prueba 1 00 - 00', 'molotovganja@yahoo.com', '09876543Garcia', 1),
  ('marraneoperfido24', 'Giovanni Rodríguez', 'Carrera Prueba 1 00 - 00','gold_member_69@hotmail.com', 'ABCDEFGLopez', 2),
  ('anita_the_best99', 'Ana Ortiz', 'Carrera Prueba 1 00 - 00', 'anita_the_Best99@gmail.com', 'LGRCSREZRuiz', 1);

INSERT INTO products (name, description, price) 
VALUES 
  ('2B', 'The amazing and powerful android girl from the famous anime.', 74400),
  ('Evelynn', 'The mysteryous and deadly demon from the rift will lurk your room until she has a chance...', 48700),
  ('Zelda', 'The eversearching princess from The Legend of Zelda, finally found.', 64400),
  ('Lilith Borderlands', 'Does she need an introduction?', 35700);

INSERT INTO delivery (userid, address, zipcode, phonenumber) 
VALUES 
  (1, 'Carrera Prueba 1 00 - 00', 000001, '6040000000'),
  (2, 'Carrera Prueba 2 00 - 00', 000002, '6040000001'),
  (3, 'Carrera Prueba 3 00 - 00', 000003, '6040000002'),
  (4, 'Carrera Prueba 4 00 - 00', 000004, '6040000003');