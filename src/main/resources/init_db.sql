CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS parking_spots (
    id INT AUTO_INCREMENT PRIMARY KEY,
    spot_number INT NOT NULL,
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS vehicle(
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(50),
    model VARCHAR(50),
    release_year VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tickets (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    car_id INT,
    park_spot_id INT,
    parking_time_in_hours INT,
    start_of_parking DATETIME,
    end_of_parking DATETIME
);

INSERT INTO users (first_name, last_name, email) VALUES ('John', 'Doe', 'john_doe@example.com');
INSERT INTO parking_spots (spot_number, is_available) VALUES (1, true), (2, true), (3, false);
INSERT INTO vehicle (plate, model, release_year) VALUES ('h678ke', 'BMW', '2005');
INSERT INTO vehicle (plate, model, release_year) VALUES ('x324me', 'Mercedes', '2002');
INSERT INTO vehicle (plate, model, release_year) VALUES ('l277qw', 'Hyundai', '2014');
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (123, 321, 1, 41, NOW(), NOW() + 41);
INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (234, 432, 2, 22, NOW(), NOW() + 22);