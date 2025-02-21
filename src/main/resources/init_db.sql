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

CREATE TABLE IF NOT EXISTS vehicles(
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(50),
    model VARCHAR(50),
    owner_id INT,
    FOREIGN KEY (owner_id)  REFERENCES users (Id)
);

CREATE TABLE IF NOT EXISTS tickets (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    parking_spot_id INT,
    parking_time_in_hours INT,
    start_of_parking TIMESTAMP,
    end_of_parking TIMESTAMP,
    FOREIGN KEY (vehicle_id)  REFERENCES vehicles (Id),
    FOREIGN KEY (parking_spot_id)  REFERENCES parking_spots (Id)
);

INSERT INTO users (first_name, last_name, email) VALUES ('John', 'Doe', 'john_doe@example.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Adam', 'Smith', 'adam_smith@example.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Jane', 'Smith', 'jane_smith@example.com');
INSERT INTO parking_spots (spot_number, is_available) VALUES (1, true);
INSERT INTO parking_spots (spot_number, is_available) VALUES (2, true);
INSERT INTO parking_spots (spot_number, is_available) VALUES (3, false);
INSERT INTO vehicles (plate, model, owner_id) VALUES ('h678ke', 'BMW', 1);
INSERT INTO vehicles (plate, model, owner_id) VALUES ('x324me', 'Mercedes',2);
INSERT INTO vehicles (plate, model, owner_id) VALUES ('l277qw', 'Hyundai',1);
INSERT INTO tickets(vehicle_id, parking_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (1, 1, 41, NOW(), NOW() + 41);
INSERT INTO tickets(vehicle_id, parking_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (2, 2, 22, NOW(), NOW() + 22);