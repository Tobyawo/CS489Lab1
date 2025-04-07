-- Address Table
CREATE TABLE Address (
    addressId SERIAL PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    zipCode VARCHAR(10)
);

-- Patient Table
CREATE TABLE Patient (
    patientId SERIAL PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100),
    dob DATE,
    hasUnpaidBill BOOLEAN DEFAULT FALSE,
    addressId INT,
    FOREIGN KEY (addressId) REFERENCES Address(addressId)
);

-- Dentist Table
CREATE TABLE Dentist (
    dentistId SERIAL PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100),
    specialization VARCHAR(100)
);

-- Surgery Table
CREATE TABLE Surgery (
    surgeryId SERIAL PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    addressId INT,
    FOREIGN KEY (addressId) REFERENCES Address(addressId)
);

-- Appointment Table
CREATE TABLE Appointment (
    appointmentId SERIAL PRIMARY KEY,
    date DATE,
    time TIME,
    status VARCHAR(20),
    patientId INT,
    dentistId INT,
    surgeryId INT,
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (dentistId) REFERENCES Dentist(dentistId),
    FOREIGN KEY (surgeryId) REFERENCES Surgery(surgeryId)
);


CREATE TABLE AppUser (
    userId SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50) DEFAULT 'DENTIST',
    email VARCHAR(100)
);


-- Insert sample data into Address table
INSERT INTO Address (addressId, street, city, state, zipCode) VALUES
(1, '123 Main St', 'New York', 'NY', '10001'),
(2, '456 Oak St', 'Los Angeles', 'CA', '90001'),
(3, '789 Pine St', 'Chicago', 'IL', '60601');

-- Insert sample data into Patient table
INSERT INTO Patient (patientId, firstName, lastName, phone, email, dob, hasUnpaidBill, addressId) VALUES
(1, 'John', 'Doe', '123-456-7890', 'john.doe@example.com', '1980-01-01', TRUE, 1),
(2, 'Jane', 'Smith', '987-654-3210', 'jane.smith@example.com', '1990-05-15', FALSE, 2);

-- Insert sample data into Dentist table
INSERT INTO Dentist (dentistId, firstName, lastName, phone, email, specialization) VALUES
(1, 'Dr. Sarah', 'Jones', '111-222-3333', 'dr.sarah.jones@example.com', 'Orthodontics'),
(2, 'Dr. Mark', 'Taylor', '444-555-6666', 'dr.mark.taylor@example.com', 'General Dentistry');

-- Insert sample data into Surgery table
INSERT INTO Surgery (surgeryId, name, phone, addressId) VALUES
(1, 'Smile Surgery Center', '123-321-4321', 1),
(2, 'Bright Dental Clinic', '222-333-4444', 2);

-- Insert sample data into Appointment table
INSERT INTO Appointment (appointmentId, date, time, status, patientId, dentistId, surgeryId) VALUES
(1, '2025-04-10', '10:00:00', 'Scheduled', 1, 1, 1),
(2, '2025-04-11', '14:00:00', 'Scheduled', 2, 2, 2),
(3, '2025-04-12', '09:00:00', 'Scheduled', 1, 2, 1);

-- Insert sample data into SystemUser table
INSERT INTO AppUser (userId, username, password, role, email) VALUES
(1, 'office_manager', 'password123', 'OFFICE_MANAGER', 'manager@example.com');




--SQuery 1: Display the list of ALL Dentists registered in the system, sorted in ascending order of their lastNameELECT dentistId, firstName, lastName, phone, email, specialization
FROM Dentist
ORDER BY lastName ASC;



--Query 2: Display the list of ALL Appointments for a given Dentist by their dentistId. Include the Patient information.

SELECT a.appointmentId, a.date, a.time, a.status, p.firstName AS patientFirstName, p.lastName AS patientLastName, p.phone AS patientPhone, p.email AS patientEmail
FROM Appointment a
JOIN Patient p ON a.patientId = p.patientId
WHERE a.dentistId = 1; 



--Query 3: Display the list of ALL Appointments that have been scheduled at a Surgery Location
SELECT a.appointmentId, a.date, a.time, a.status, s.name AS surgeryName, s.phone AS surgeryPhone, p.firstName AS patientFirstName, p.lastName AS patientLastName
FROM Appointment a
JOIN Surgery s ON a.surgeryId = s.surgeryId
JOIN Patient p ON a.patientId = p.patientId
WHERE a.status = 'Scheduled'
and s.addressid=2;



--Query 4: Display the list of Appointments booked for a given Patient on a given Date

SELECT a.appointmentId, a.date, a.time, a.status, d.firstName AS dentistFirstName, d.lastName AS dentistLastName
FROM Appointment a
JOIN Dentist d ON a.dentistId = d.dentistId
WHERE a.patientId = 1
  AND a.date = '2025-04-10';


