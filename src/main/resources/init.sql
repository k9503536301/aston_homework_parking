CREATE SCHEMA IF NOT EXISTS tickets;
USE tickets;

--DROP TABLE IF EXISTS tickets;
CREATE TABLE tickets (ID INT PRIMARY KEY AUTO_INCREMENT, user_id INT, car_id INT, park_spot_id INT, parking_time_in_hours INT, start_of_parking DATETIME, end_of_parking  DATETIME);


INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (123, 321, 1, 41, NOW(), NOW() + 41);
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (234, 432, 2, 45, NOW(), NOW());
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (345, 543, 3, 23, NOW(), NOW());
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (456, 654, 4, 12, NOW(), NOW());
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (567, 765, 5, 32, NOW(), NOW());
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (678, 876, 6, 56, NOW(), NOW());
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (789, 987, 7, 3, NOW(), NOW());