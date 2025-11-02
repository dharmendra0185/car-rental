--valid customer: license issued 2 years ago
INSERT INTO CUSTOMER (id, driving_license_number, name, license_issue_date)
values (1, 'DL-042025-2002', 'David', DATEADD('YEAR', -2, CURRENT_DATE()));

--Invalid (license issues only 6 months ago)
INSERT INTO CUSTOMER (id, driving_license_number, name, license_issue_date)
values (2, 'DL-NEW-0008', 'John', DATEADD('MONTH', -6, CURRENT_DATE()));

--Driving License (Owner & Expiry)
INSERT INTO DRIVING_LICENSE(id, license_number, owner_name, expiry_date)
values (1, 'DL-NEW-0001', 'Amaan', '2027-03-31');

--INSERT INTO DRIVING_LICENSE(id, license_number, owner_name, expiry_date)
--values (1, 'DL-NEW-0001', 'Amaan', '2027-03-31');

--Booking 
INSERT INTO BOOKING (id, reservation_start_date, reservation_end_date, car_segment, age, customer_id)
values (1003, '2025-11-01', '2025-11-10', 'MEDIUM', 34,1);


INSERT INTO BOOKING (id, reservation_start_date, reservation_end_date, car_segment, age, customer_id)
values (1004, '2025-12-01', '2025-12-07', 'EXTRALARGE', 26,2);