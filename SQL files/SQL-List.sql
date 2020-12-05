SELECT cus.Name, cus.Phone, prem.CardNumber, prem.RegistrationDate, prem.ExpirationDAte
FROM customer cus, premium prem
WHERE prem.CardNumber = '581 431 278'
AND cus.CustomerID = prem.CustomerID;

INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-LZ79KB', 'OCTOBER 26, 2020', '09:00 AM - 11:00 AM', 'Main Dining Hall', 'A1');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-IJCH6F', 'OCTOBER 26, 2020', '09:00 AM - 11:00 AM', 'Main Dining Hall', 'A2');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-680UHZ', 'OCTOBER 26, 2020', '09:00 AM - 11:00 AM', 'Main Dining Hall', 'A3');

INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-YAAKJB', 'OCTOBER 26, 2020', '11:00 AM - 01:00 PM', 'Main Dining Hall', 'B1');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-M9KCRH', 'OCTOBER 26, 2020', '11:00 AM - 01:00 PM', 'Main Dining Hall', 'B2');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-UKWXFH', 'OCTOBER 26, 2020', '11:00 AM - 01:00 PM', 'Main Dining Hall', 'B3');

INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-ZIPDJE', 'OCTOBER 26, 2020', '01:00 PM - 03:00 PM', 'Main Dining Hall', 'C1');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-0G16SS', 'OCTOBER 26, 2020', '01:00 PM - 03:00 PM', 'Main Dining Hall', 'C2');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-WWLQI8', 'OCTOBER 26, 2020', '01:00 PM - 03:00 PM', 'Main Dining Hall', 'C3');

DELETE FROM roomtable;

SELECT COUNT(TableName)
FROM roomtable
WHERE Date = 'OCTOBER 26, 2020'
AND Time = '09:00 AM - 11:00 AM';

SELECT TableName, RoomTableID
FROM roomtable
WHERE Date = 'OCTOBER 26, 2020'
AND Time = '09:00 AM - 11:00 AM';

SELECT RoomTableID, Date, Time, RoomName, TableName
FROM roomtable
WHERE Date = 'OCTOBER 26, 2020'
AND Time = '09:00 AM - 11:00 AM'
AND RoomName = 'Main Dining Hall'
AND TableName = 'A1';

UPDATE roomtable 
SET Status = 'Not Available'
WHERE RoomTableID ='';

INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-G3DW8G', 'OCTOBER 26, 2020', '09:00 AM - 11:00 AM', 'VVIP Room', 'D1');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-WJMNZ7', 'OCTOBER 26, 2020', '09:00 AM - 11:00 AM', 'VVIP Room', 'D2');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-9QEIEM', 'OCTOBER 26, 2020', '09:00 AM - 11:00 AM', 'VVIP Room', 'D3');

INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-OWVGGD', 'OCTOBER 26, 2020', '11:00 AM - 01:00 PM', 'VVIP Room', 'E1');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-MGW61G', 'OCTOBER 26, 2020', '11:00 AM - 01:00 PM', 'VVIP Room', 'E2');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-OAHH0A', 'OCTOBER 26, 2020', '11:00 AM - 01:00 PM', 'VVIP Room', 'E3');

INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-ZCJGFP', 'OCTOBER 26, 2020', '01:00 PM - 03:00 PM', 'VVIP Room', 'F1');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-X1798V', 'OCTOBER 26, 2020', '01:00 PM - 03:00 PM', 'VVIP Room', 'F2');
INSERT INTO roomtable (RoomTableID, Date, Time, RoomName, TableName)
VALUES ('RMTB-PO24PF', 'OCTOBER 26, 2020', '01:00 PM - 03:00 PM', 'VVIP Room', 'F3');

SELECT FoodName, UnitPrice
FROM food
WHERE Category = 'Sushi'

SELECT FoodID
FROM food
WHERE FoodName = 'California Maki'