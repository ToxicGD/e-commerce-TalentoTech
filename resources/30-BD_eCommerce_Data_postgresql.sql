-- PostgreSQL 17 version - Data insertion
-- SET search_path TO universidad;

INSERT INTO Roles (role_Name)
VALUES
  ('Customer'),
  ('Admin');

INSERT INTO Users (username, email, password, RoleId) 
VALUES 
  ('donCharro', 'evaristoguti68@une.net.co', '123456789riguez', 1),
  ('fuego_420', 'molotovganja@yahoo.com', '09876543Garcia', 1),
  ('marraneoperfido24', 'gold_member_69@hotmail.com', 'ABCDEFGLopez', 2),
  ('anita_the_best99', 'anita_the_Best99@gmail.com', 'LGRCSREZRuiz', 1);

INSERT INTO Products (name, description, img, price) 
VALUES 
  ('2B', 'The amazing and powerful android girl from the famous anime.', 'pending', 74400),
  ('Evelynn', 'The mysteryous and deadly demon from the rift will lurk your room until she has a chance...', 'pending', 48700),
  ('Zelda', 'The eversearching princess from The Legend of Zelda, finally found.', 'pending', 64400),
  ('Lilith Borderlands', 'Does she need an introduction?', 'pending', 35700);

INSERT INTO DeliveryAddress (UserId, address, zipcode, phoneNumber, description) 
VALUES 
  (1, 'Carrera Prueba 1 00 - 00', '00000', '6040000000', 'N/A'),
  (2, 'Carrera Prueba 2 00 - 00', '00000', '6040000001', 'N/A'),
  (3, 'Carrera Prueba 3 00 - 00', '00000', '6040000002', 'N/A'),
  (4, 'Carrera Prueba 4 00 - 00', '00000', '6040000003', 'N/A');