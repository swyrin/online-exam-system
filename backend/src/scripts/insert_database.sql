INSERT INTO persons (person_id, age, birth_date, email, first_name, gender, join_date, last_name, middle_name, phone)
VALUES
    ('P001', 28, '1997-06-15 00:00:00.000000', 'alex.jordan@example.com', 'Alex', 1, '2023-05-20 14:25:36.000000', 'Jordan', 'Michael', '555-1234'),
    ('P002', 34, '1990-02-28 00:00:00.000000', 'emily.smith@example.com', 'Emily', 0, '2022-11-13 09:45:12.000000', 'Smith', 'Rose', '555-5678'),
    ('P003', 25, '1999-09-09 00:00:00.000000', 'daniel.lee@example.com', 'Daniel', 1, '2024-01-05 18:30:50.000000', 'Lee', 'James', '555-9012'),
    ('P004', 30, '1994-03-22 00:00:00.000000', 'sophia.brown@example.com', 'Sophia', 0, '2021-08-17 11:15:22.000000', 'Brown', 'Grace', '555-3456'),
    ('P005', 22, '2002-12-01 00:00:00.000000', 'chris.davis@example.com', 'Chris', 1, '2024-04-10 16:05:42.000000', 'Davis', 'Andrew', '555-7890'),
    ('P006', 29, '1995-07-20 00:00:00.000000', 'lisa.white@example.com', 'Lisa', 0, '2023-02-15 10:00:00.000000', 'White', 'Anne', '555-1122'),
    ('P007', 31, '1993-01-11 00:00:00.000000', 'brian.hall@example.com', 'Brian', 1, '2022-07-30 15:40:20.000000', 'Hall', 'Edward', '555-3344'),
    ('P008', 27, '1997-10-05 00:00:00.000000', 'rachel.evans@example.com', 'Rachel', 0, '2023-11-22 13:12:45.000000', 'Evans', 'Marie', '555-5566'),
    ('P009', 35, '1989-04-18 00:00:00.000000', 'kevin.morris@example.com', 'Kevin', 1, '2021-06-19 08:33:00.000000', 'Morris', 'Samuel', '555-7788'),
    ('P010', 26, '1998-08-09 00:00:00.000000', 'natalie.clark@example.com', 'Natalie', 0, '2024-03-14 17:20:30.000000', 'Clark', 'Elaine', '555-9900'),
    ('P011', 24, '2000-12-25 00:00:00.000000', 'jason.king@example.com', 'Jason', 1, '2024-01-02 12:00:00.000000', 'King', 'Anthony', '555-1123'),
    ('P012', 32, '1992-06-30 00:00:00.000000', 'hannah.wright@example.com', 'Hannah', 0, '2022-09-25 09:10:15.000000', 'Wright', 'Joy', '555-2234'),
    ('P013', 29, '1995-11-12 00:00:00.000000', 'aaron.green@example.com', 'Aaron', 1, '2023-05-10 16:45:50.000000', 'Green', 'Patrick', '555-3345'),
    ('P014', 33, '1991-03-08 00:00:00.000000', 'olivia.adams@example.com', 'Olivia', 0, '2021-12-18 07:55:30.000000', 'Adams', 'Faith', '555-4456'),
    ('P015', 23, '2001-05-14 00:00:00.000000', 'ethan.baker@example.com', 'Ethan', 1, '2024-02-09 19:30:00.000000', 'Baker', 'Leon', '555-5567'),
    ('P016', 31, '1993-02-02 00:00:00.000000', 'zoe.hughes@example.com', 'Zoe', 0, '2022-10-01 11:11:11.000000', 'Hughes', 'Dawn', '555-6678'),
    ('P017', 27, '1997-09-19 00:00:00.000000', 'logan.price@example.com', 'Logan', 1, '2023-08-05 14:14:14.000000', 'Price', 'Victor', '555-7789'),
    ('P018', 36, '1988-07-07 00:00:00.000000', 'mia.bennett@example.com', 'Mia', 0, '2021-05-20 20:20:20.000000', 'Bennett', 'Hope', '555-8890'),
    ('P019', 25, '1999-01-29 00:00:00.000000', 'lucas.cooper@example.com', 'Lucas', 1, '2023-03-30 18:18:18.000000', 'Cooper', 'Nathan', '555-9901'),
    ('P020', 30, '1994-10-16 00:00:00.000000', 'ava.morgan@example.com', 'Ava', 0, '2022-06-12 12:34:56.000000', 'Morgan', 'Skye', '555-1011');

INSERT INTO courses (course_id, abbreviation, name)
VALUES
    ('C001', 'CS101', 'Introduction to Computer Science'),
    ('C002', 'MATH201', 'Calculus I'),
    ('C003', 'ENG105', 'English Literature'),
    ('C004', 'PHY111', 'General Physics I'),
    ('C005', 'CHEM101', 'Basic Chemistry'),
    ('C006', 'BIO120', 'Introduction to Biology'),
    ('C007', 'HIST210', 'World History'),
    ('C008', 'ECON101', 'Principles of Economics'),
    ('C009', 'PSY101', 'Introduction to Psychology'),
    ('C010', 'SOC150', 'Sociology Basics'),
    ('C011', 'CS202', 'Data Structures and Algorithms'),
    ('C012', 'MATH301', 'Linear Algebra'),
    ('C013', 'ART100', 'Fundamentals of Art'),
    ('C014', 'PHIL110', 'Introduction to Philosophy'),
    ('C015', 'STAT200', 'Statistics for Beginners');

INSERT INTO course_person (person_id, course_id)
VALUES
    ('P001', 'C001'),
    ('P001', 'C002'),
    ('P002', 'C003'),
    ('P003', 'C004'),
    ('P003', 'C005'),
    ('P004', 'C006'),
    ('P005', 'C007'),
    ('P005', 'C008'),
    ('P006', 'C009'),
    ('P007', 'C010'),
    ('P008', 'C011'),
    ('P009', 'C012'),
    ('P010', 'C013'),
    ('P011', 'C014'),
    ('P012', 'C015'),
    ('P013', 'C001'),
    ('P014', 'C003'),
    ('P015', 'C006'),
    ('P016', 'C009'),
    ('P017', 'C005'),
    ('P018', 'C007'),
    ('P019', 'C010'),
    ('P020', 'C011');


INSERT INTO person_exam_report (person_id, course_id, grade, issue_date)
VALUES
    ('P001', 'C001', 85, '2024-06-15 10:00:00'),
    ('P001', 'C002', 78, '2024-06-16 10:00:00'),
    ('P002', 'C003', 92, '2024-06-15 11:00:00'),
    ('P003', 'C004', 66, '2024-06-17 09:00:00'),
    ('P003', 'C005', 74, '2024-06-18 09:30:00'),
    ('P004', 'C006', 88, '2024-06-15 14:00:00'),
    ('P005', 'C007', 81, '2024-06-16 13:00:00'),
    ('P005', 'C008', 79, '2024-06-17 12:00:00'),
    ('P006', 'C009', 91, '2024-06-15 10:30:00'),
    ('P007', 'C010', 75, '2024-06-16 11:30:00'),
    ('P008', 'C011', 84, '2024-06-15 15:00:00'),
    ('P009', 'C012', 69, '2024-06-17 16:00:00'),
    ('P010', 'C013', 73, '2024-06-18 17:00:00'),
    ('P011', 'C014', 77, '2024-06-19 10:00:00'),
    ('P012', 'C015', 82, '2024-06-20 10:00:00');


INSERT INTO rooms (room_id, headcount) VALUES
                                           ('A101', 12),
                                           ('A102', 15),
                                           ('A103', 8),
                                           ('A104', 20),
                                           ('A105', 5),
                                           ('B201', 18),
                                           ('B202', 10),
                                           ('B203', 14),
                                           ('B204', 9),
                                           ('B205', 11),
                                           ('C301', 7),
                                           ('C302', 16),
                                           ('C303', 13),
                                           ('C304', 6),
                                           ('C305', 17),
                                           ('D401', 22),
                                           ('D402', 19),
                                           ('D403', 4),
                                           ('D404', 23),
                                           ('D405', 21);


INSERT INTO test_types (type_id, description, name) VALUES
                                                        (1, 'Basic reading and comprehension test', 'Reading Test'),
                                                        (2, 'Fundamental math skills assessment', 'Math Test'),
                                                        (3, 'General science knowledge evaluation', 'Science Test'),
                                                        (4, 'Writing skills and grammar test', 'Writing Test'),
                                                        (5, 'Computer literacy and typing speed test', 'Computer Skills Test'),
                                                        (6, 'Problem-solving and critical thinking assessment', 'Logic Test'),
                                                        (7, 'Physical endurance and strength evaluation', 'Fitness Test'),
                                                        (8, 'Emotional intelligence and resilience survey', 'EQ Test'),
                                                        (9, 'Teamwork and leadership abilities assessment', 'Leadership Test'),
                                                        (10, 'Creativity and innovation aptitude test', 'Creativity Test');

INSERT INTO roles (role_id, name, person_person_id) VALUES
                                                        (1, 'Manager', 'P001'),
                                                        (2, 'Developer', 'P003'),
                                                        (3, 'Designer', 'P004'),
                                                        (4, 'QA Engineer', 'P002'),
                                                        (5, 'Data Analyst', 'P006'),
                                                        (6, 'HR Specialist', 'P005'),
                                                        (7, 'Marketing Lead', 'P008'),
                                                        (8, 'Sales Executive', 'P007'),
                                                        (9, 'Product Owner', 'P009'),
                                                        (10, 'Business Analyst', 'P010'),
                                                        (11, 'Scrum Master', 'P011'),
                                                        (12, 'Technical Writer', 'P012'),
                                                        (13, 'Support Engineer', 'P014'),
                                                        (14, 'Finance Officer', 'P013'),
                                                        (15, 'IT Administrator', 'P015');

INSERT INTO course_room (room_id, course_id) VALUES
                                                 ('A101', 'C001'),
                                                 ('A102', 'C002'),
                                                 ('A103', 'C003'),
                                                 ('A104', 'C004'),
                                                 ('A105', 'C005'),
                                                 ('B201', 'C006'),
                                                 ('B202', 'C007'),
                                                 ('B203', 'C008'),
                                                 ('B204', 'C009'),
                                                 ('B205', 'C010'),
                                                 ('C301', 'C011'),
                                                 ('C302', 'C012'),
                                                 ('C303', 'C013'),
                                                 ('C304', 'C014'),
                                                 ('C305', 'C015'),
                                                 ('D401', 'C001'),
                                                 ('D402', 'C003'),
                                                 ('D403', 'C005'),
                                                 ('D404', 'C007'),
                                                 ('D405', 'C009');

INSERT INTO person_room_attend (person_id, room_id) VALUES
                                                        ('P001', 'A101'),
                                                        ('P002', 'A102'),
                                                        ('P003', 'A103'),
                                                        ('P004', 'A104'),
                                                        ('P005', 'A105'),
                                                        ('P006', 'B201'),
                                                        ('P007', 'B202'),
                                                        ('P008', 'B203'),
                                                        ('P009', 'B204'),
                                                        ('P010', 'B205'),
                                                        ('P011', 'C301'),
                                                        ('P012', 'C302'),
                                                        ('P013', 'C303'),
                                                        ('P014', 'C304'),
                                                        ('P015', 'C305'),
                                                        ('P016', 'D401'),
                                                        ('P017', 'D402'),
                                                        ('P018', 'D403'),
                                                        ('P019', 'D404'),
                                                        ('P020', 'D405');


INSERT INTO exams (exam_id, bag_code, course_course_id, exam_type_type_id) VALUES
                                                                               (1, 'BAG001', 'C001', 1),
                                                                               (2, 'BAG002', 'C002', 2),
                                                                               (3, 'BAG003', 'C003', 3),
                                                                               (4, 'BAG004', 'C004', 4),
                                                                               (5, 'BAG005', 'C005', 5),
                                                                               (6, 'BAG006', 'C006', 6),
                                                                               (7, 'BAG007', 'C007', 7),
                                                                               (8, 'BAG008', 'C008', 8),
                                                                               (9, 'BAG009', 'C009', 9),
                                                                               (10, 'BAG010', 'C010', 10),
                                                                               (11, 'BAG011', 'C011', 1),
                                                                               (12, 'BAG012', 'C012', 2),
                                                                               (13, 'BAG013', 'C013', 3),
                                                                               (14, 'BAG014', 'C014', 4),
                                                                               (15, 'BAG015', 'C015', 5),
                                                                               (16, 'BAG016', 'C001', 6),
                                                                               (17, 'BAG017', 'C003', 7),
                                                                               (18, 'BAG018', 'C005', 8),
                                                                               (19, 'BAG019', 'C007', 9),
                                                                               (20, 'BAG020', 'C009', 10);

INSERT INTO person_exam (person_id, exam_id) VALUES
                                                 ('P001', 1),
                                                 ('P001', 5),
                                                 ('P002', 2),
                                                 ('P002', 6),
                                                 ('P003', 3),
                                                 ('P003', 7),
                                                 ('P003', 10),
                                                 ('P004', 4),
                                                 ('P005', 8),
                                                 ('P005', 11),
                                                 ('P006', 9),
                                                 ('P006', 12),
                                                 ('P007', 13),
                                                 ('P008', 14),
                                                 ('P008', 15),
                                                 ('P009', 16),
                                                 ('P010', 17),
                                                 ('P010', 18),
                                                 ('P011', 19),
                                                 ('P011', 20),
                                                 ('P012', 1),
                                                 ('P013', 2),
                                                 ('P014', 3),
                                                 ('P015', 4),
                                                 ('P016', 5);

INSERT INTO exam_room (room_id, exam_id) VALUES
                                             ('A101', 3),
                                             ('A101', 7),
                                             ('A102', 1),
                                             ('A103', 5),
                                             ('A104', 2),
                                             ('A104', 8),
                                             ('A105', 9),
                                             ('B201', 4),
                                             ('B202', 6),
                                             ('B202', 10),
                                             ('B203', 11),
                                             ('B204', 13),
                                             ('B205', 12),
                                             ('C301', 14),
                                             ('C302', 16),
                                             ('C303', 15),
                                             ('C303', 18),
                                             ('C304', 17),
                                             ('C305', 19),
                                             ('D401', 20),
                                             ('D402', 1),
                                             ('D403', 2),
                                             ('D404', 5),
                                             ('D405', 7),
                                             ('D405', 9);

























