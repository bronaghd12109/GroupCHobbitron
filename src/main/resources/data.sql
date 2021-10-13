-- data.sql / schema.sql - Spring Boot will find it and use it

INSERT INTO Driver_Details (Id, prefix, first_Name, last_Name, telephone_Number, address_Line1, address_Line2, city, postcode, vehicle_Body_Type, engine_Size, additional_Drivers, commercial_Use, outside_State_Use, current_Value, date_Registered)
VALUES (1, 'Mrs', 'Bronagh', 'Doherty', '0123456789', '123 Main Street', 'Main Road', 'Magherafelt', 'BT45 5GW', 'Coupe', '1600','2', 'Yes', 'Yes', '50000', '12/10/2010'),
(2, 'Mr', 'Dermot', 'Clenaghan', '0123456789', '124 Main Street', 'Main Road', 'Lisburn', 'BT9 5GW', 'Cabriolet', '200','2', 'Yes', 'No', '30000', '10/10/2010'),
(3, 'Mr', 'Conor', 'Hawkins', '0123456789', '125 Main Street', 'Main Road', 'Belfast', 'BT8 5GW', 'Hatchback', '1400','4', 'No', 'Yes', '10000', '08/10/2002'),
(4, 'Mr', 'Alex', 'ONeill', '0123456789', '126 Main Street', 'Main Road', 'Armagh', 'BT61 5GW', 'Estate', '2000','1', 'No', 'No', '35000', '05/12/2008');
