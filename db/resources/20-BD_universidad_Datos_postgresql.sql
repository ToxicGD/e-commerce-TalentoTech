-- PostgreSQL 17 version - Data insertion
-- SET search_path TO universidad;

INSERT INTO roles (rolename) VALUES ('Customer'), ('Admin');

INSERT INTO users (username, name, email, password, roleid) 
VALUES 
  ('donCharro', 'Ricardo Melo', 'evaristoguti68@une.net.co', '123456789riguez', 1),
  ('fuego_420', 'Julian Andres Rosero Zapata', 'molotovganja@yahoo.com', '09876543Garcia', 1),
  ('marraneoperfido24', 'Giovanni Rodríguez','gold_member_69@hotmail.com', 'ABCDEFGLopez', 2),
  ('anita_the_best99', 'Ana Ortiz', 'anita_the_Best99@gmail.com', 'LGRCSREZRuiz', 1);

INSERT INTO products (name, description, price) 
VALUES 
  ('2B', 'The amazing and powerful android girl from the famous anime.', 74400),
  ('Evelynn', 'The mysteryous and deadly demon from the rift will lurk your room until she has a chance...', 48700),
  ('Zelda', 'The eversearching princess from The Legend of Zelda, finally found.', 64400),
  ('Lilith Borderlands', 'Does she need an introduction?', 35700);

INSERT INTO delivery (userid, address, zipcode, phonenumber, description) 
VALUES 
  (1, 'Carrera Prueba 1 00 - 00', '00000', '6040000000', 'N/A'),
  (2, 'Carrera Prueba 2 00 - 00', '00000', '6040000001', 'N/A'),
  (3, 'Carrera Prueba 3 00 - 00', '00000', '6040000002', 'N/A'),
  (4, 'Carrera Prueba 4 00 - 00', '00000', '6040000003', 'N/A');