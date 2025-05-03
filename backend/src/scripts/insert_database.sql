-- roles: only Student and Staff
INSERT INTO roles (role_id, name)
VALUES
    (1, 'Student'),
    (2, 'Staff');

-- persons: assign role_role_id = 1 (Student) or 2 (Staff)
INSERT INTO persons (person_id, age, birth_date, email, first_name, gender, join_date, last_name, middle_name, phone, role_role_id)
VALUES
    ('P001', 20, '2003-02-14 00:00:00.000000', 'alex.jordan@univ.edu',      'Alex',    1, '2022-08-20 09:00:00.000000', 'Jordan',   'Michael', '203-555-0101', 1),
    ('P002', 45, '1978-11-30 00:00:00.000000', 'emily.smith@univ.edu',     'Emily',   0, '2010-01-05 08:30:00.000000', 'Smith',    'Rose',    '203-555-0102', 2),
    ('P003', 22, '2001-07-09 00:00:00.000000', 'daniel.lee@univ.edu',      'Daniel',  1, '2023-01-15 10:15:00.000000', 'Lee',      'James',   '203-555-0103', 1),
    ('P004', 38, '1985-03-22 00:00:00.000000', 'sophia.brown@univ.edu',    'Sophia',  0, '2018-09-01 11:00:00.000000', 'Brown',    'Grace',   '203-555-0104', 2),
    ('P005', 21, '2002-12-01 00:00:00.000000', 'chris.davis@univ.edu',     'Chris',   1, '2023-08-25 14:45:00.000000', 'Davis',    'Andrew',  '203-555-0105', 1),
    ('P006', 29, '1995-07-20 00:00:00.000000', 'lisa.white@univ.edu',      'Lisa',    0, '2019-02-10 09:30:00.000000', 'White',    'Anne',    '203-555-0106', 1),
    ('P007', 32, '1991-01-11 00:00:00.000000', 'brian.hall@univ.edu',      'Brian',   1, '2015-07-15 13:20:00.000000', 'Hall',     'Edward',  '203-555-0107', 1),
    ('P008', 27, '1996-10-05 00:00:00.000000', 'rachel.evans@univ.edu',    'Rachel',  0, '2020-11-10 12:10:00.000000', 'Evans',    'Marie',   '203-555-0108', 1),
    ('P009', 50, '1973-04-18 00:00:00.000000', 'kevin.morris@univ.edu',    'Kevin',   1, '2000-06-01 08:00:00.000000', 'Morris',   'Samuel',  '203-555-0109', 2),
    ('P010', 19, '2004-08-09 00:00:00.000000', 'natalie.clark@univ.edu',  'Natalie', 0, '2023-09-01 09:15:00.000000', 'Clark',    'Elaine',  '203-555-0110', 1),
    ('P011', 23, '2000-12-25 00:00:00.000000', 'jason.king@univ.edu',      'Jason',   1, '2022-01-10 10:00:00.000000', 'King',     'Anthony', '203-555-0111', 1),
    ('P012', 28, '1995-06-30 00:00:00.000000', 'hannah.wright@univ.edu',   'Hannah',  0, '2019-09-05 11:30:00.000000', 'Wright',   'Joy',     '203-555-0112', 2),
    ('P013', 31, '1992-11-12 00:00:00.000000', 'aaron.green@univ.edu',     'Aaron',   1, '2016-05-20 13:45:00.000000', 'Green',    'Patrick', '203-555-0113', 1),
    ('P014', 40, '1983-03-08 00:00:00.000000', 'olivia.adams@univ.edu',    'Olivia',  0, '2008-12-01 08:20:00.000000', 'Adams',    'Faith',   '203-555-0114', 2),
    ('P015', 24, '2000-05-14 00:00:00.000000', 'ethan.baker@univ.edu',     'Ethan',   1, '2022-02-15 15:00:00.000000', 'Baker',    'Leon',    '203-555-0115', 1),
    ('P016', 35, '1988-02-02 00:00:00.000000', 'zoe.hughes@univ.edu',      'Zoe',     0, '2017-10-10 10:10:00.000000', 'Hughes',   'Dawn',    '203-555-0116', 2),
    ('P017', 26, '1997-09-19 00:00:00.000000', 'logan.price@univ.edu',     'Logan',   1, '2021-08-05 14:15:00.000000', 'Price',    'Victor',  '203-555-0117', 1),
    ('P018', 33, '1991-07-07 00:00:00.000000', 'mia.bennett@univ.edu',     'Mia',     0, '2014-05-20 16:20:00.000000', 'Bennett',  'Hope',    '203-555-0118', 1),
    ('P019', 21, '2002-01-29 00:00:00.000000', 'lucas.cooper@univ.edu',    'Lucas',   1, '2023-03-30 12:00:00.000000', 'Cooper',   'Nathan',  '203-555-0119', 1),
    ('P020', 29, '1995-10-16 00:00:00.000000', 'ava.morgan@univ.edu',      'Ava',     0, '2018-06-12 11:00:00.000000', 'Morgan',   'Skye',    '203-555-0120', 2);

-- courses: university course catalog
INSERT INTO courses (course_id, abbreviation, name)
VALUES
    ('C001', 'CSCI101', 'Introduction to Computer Science'),
    ('C002', 'MATH201', 'Calculus I'),
    ('C003', 'ENGL150', 'World Literature'),
    ('C004', 'PHYS110', 'General Physics I'),
    ('C005', 'CHEM101', 'Principles of Chemistry'),
    ('C006', 'BIOL120', 'Foundations of Biology'),
    ('C007', 'HIST210', 'Modern World History'),
    ('C008', 'ECON101', 'Microeconomics'),
    ('C009', 'PSYC100', 'Introduction to Psychology'),
    ('C010', 'SOC200', 'Sociology Theory'),
    ('C011', 'CSCI202', 'Data Structures'),
    ('C012', 'MATH301', 'Linear Algebra'),
    ('C013', 'ART105', 'Art History'),
    ('C014', 'PHIL110', 'Introduction to Philosophy'),
    ('C015', 'STAT250', 'Statistical Methods');


-- enrollments: which students are registered
INSERT INTO course_person (person_id, course_id)
VALUES
    ('P001','C001'), ('P001','C002'),
    ('P002','C003'),
    ('P003','C004'), ('P003','C005'),
    ('P004','C006'),
    ('P005','C007'), ('P005','C008'),
    ('P006','C009'),
    ('P007','C010'),
    ('P008','C011'),
    ('P009','C012'),
    ('P010','C013'),
    ('P011','C014'),
    ('P012','C015'),
    ('P013','C001'),
    ('P014','C003'),
    ('P015','C006'),
    ('P016','C009'),
    ('P017','C005'),
    ('P018','C007'),
    ('P019','C010'),
    ('P020','C011');

-- exam reports: final grades for each student-course
INSERT INTO person_exam_report (person_id, course_id, grade, issue_date)
VALUES
    ('P001','C001',  88, '2024-05-15 10:00:00'),
    ('P001','C002',  79, '2024-05-16 10:30:00'),
    ('P002','C003',  92, '2024-05-15 11:00:00'),
    ('P003','C004',  67, '2024-05-17 09:00:00'),
    ('P003','C005',  74, '2024-05-18 09:30:00'),
    ('P004','C006',  85, '2024-05-15 14:00:00'),
    ('P005','C007',  81, '2024-05-16 13:00:00'),
    ('P005','C008',  78, '2024-05-17 12:00:00'),
    ('P006','C009',  90, '2024-05-15 10:30:00'),
    ('P007','C010',  75, '2024-05-16 11:30:00'),
    ('P008','C011',  84, '2024-05-15 15:00:00'),
    ('P009','C012',  69, '2024-05-17 16:00:00'),
    ('P010','C013',  73, '2024-05-18 17:00:00'),
    ('P011','C014',  77, '2024-05-19 10:00:00'),
    ('P012','C015',  82, '2024-05-20 10:00:00');

-- rooms: lecture halls & labs
INSERT INTO rooms (room_id, headcount)
VALUES
    ('A101', 120), ('A102', 80),  ('A103', 60),  ('A104', 100), ('A105', 40),
    ('B201', 90),  ('B202', 50),  ('B203', 70),  ('B204', 30),  ('B205', 110),
    ('C301', 25),  ('C302', 35),  ('C303', 45),  ('C304', 20),  ('C305', 55),
    ('D401', 200), ('D402', 150), ('D403', 10),  ('D404', 180), ('D405', 160);

-- test types: academic exams & assessments
INSERT INTO test_types (type_id, description, name)
VALUES
    (1, 'Comprehensive midterm exam',         'Midterm'),
    (2, 'End-of-term final examination',      'Final');

-- course-room assignments
INSERT INTO course_room (room_id, course_id)
VALUES
    ('A101','C001'), ('A101','C011'),
    ('A102','C002'),
    ('A103','C003'),
    ('A104','C004'),
    ('A105','C005'),
    ('B201','C006'),
    ('B202','C007'),
    ('B203','C008'),
    ('B204','C009'),
    ('B205','C010'),
    ('C301','C012'),
    ('C302','C013'),
    ('C303','C014'),
    ('C304','C015'),
    ('D401','C001'),
    ('D402','C003'),
    ('D403','C005'),
    ('D404','C007'),
    ('D405','C009');

-- attendance: who shows up in which room
INSERT INTO person_room_attend (person_id, room_id)
VALUES
    ('P001','A101'), ('P002','A102'), ('P003','A103'), ('P004','A104'), ('P005','A105'),
    ('P006','B201'), ('P007','B202'), ('P008','B203'), ('P009','B204'), ('P010','B205'),
    ('P011','C301'), ('P012','C302'), ('P013','C303'), ('P014','C304'), ('P015','C305'),
    ('P016','D401'), ('P017','D402'), ('P018','D403'), ('P019','D404'), ('P020','D405');

-- scheduled exams per course
INSERT INTO exams (exam_id, bag_code, course_course_id, exam_type_type_id)
VALUES
    ( 1, 'MT1-CSCI101','C001', 1),
    ( 2, 'FN1-MATH201','C002', 2),
    ( 3, 'MT1-WORLDLIT','C003', 1),
    ( 4, 'FN1-PHYS110','C004', 2),
    ( 5, 'MT1-CHEM101','C005', 1),
    ( 6, 'FN1-BIOL120','C006', 2),
    ( 7, 'MT1-HIST210','C007', 1),
    ( 8, 'FN1-ECON101','C008', 2),
    ( 9, 'MT1-PSYC100','C009', 1),
    (10, 'FN1-SOC200','C010', 2),
    (11, 'MT2-CSCI202','C011', 1),
    (12, 'FN2-MATH301','C012', 2),
    (13, 'MT2-ARTH105','C013', 1),
    (14, 'FN2-PHIL110','C014', 2),
    (15, 'MT2-STAT250','C015', 1),
    (16, 'FN2-CSCI101','C001', 2),
    (17, 'MT2-WORLDLIT','C003', 1),
    (18, 'FN2-CHEM101','C005', 2),
    (19, 'MT2-HIST210','C007', 1),
    (20, 'FN2-PSYC100','C009', 2);

-- exam-room scheduling
INSERT INTO exam_room (room_id, exam_id)
VALUES
    ('A101',  1), ('A101', 11),
    ('A102',  2),
    ('A103',  3),
    ('A104',  4),
    ('A105',  5),
    ('B201',  6),
    ('B202',  7), ('B202', 10),
    ('B203',  8),
    ('B204',  9),
    ('C301', 12),
    ('C302', 13),
    ('C303', 14), ('C303', 18),
    ('C304', 15),
    ('C305', 16),
    ('D401', 17),
    ('D402', 19),
    ('D403', 20);

-- exam attendance: who sat which exam
INSERT INTO person_exam (person_id, exam_id)
VALUES
    ('P001',  1), ('P001', 16),
    ('P002',  2),
    ('P003',  3), ('P003', 17),
    ('P004',  4),
    ('P005',  5),
    ('P006',  6),
    ('P007',  7),
    ('P008',  8), ('P008', 10),
    ('P009',  9), ('P009', 19),
    ('P010', 11), ('P010', 12),
    ('P011', 13),
    ('P012', 14), ('P012', 15),
    ('P013', 18),
    ('P014', 20),
    ('P015',  1),
    ('P016',  2);
