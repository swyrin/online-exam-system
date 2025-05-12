from datetime import datetime, timedelta, time
import random

# Constants
num_students = 20
num_courses = 10
num_rooms = 10
num_roles = 3
num_exams = 15
course_ids = [f"IT-{101 + i}" for i in range(num_courses)]
student_ids = [f"ITITIU21{str(i).zfill(3)}" for i in range(num_students)]
room_ids = [str(100 + i) for i in range(num_rooms)]
role_ids = list(range(1, num_roles + 1))
first_names = ["Nguyen", "Tran", "Le", "Pham", "Hoang", "Dang", "Bui", "Do", "Ngo", "Vo"]
middle_names = ["Van", "Thi", "Duc", "Minh", "Ngoc", "Thanh", "Quoc", "Huu", "Hong", "Khanh"]
last_names = ["An", "Binh", "Chau", "Dung", "Giang", "Hang", "Khoa", "Linh", "Nam", "Quyen"]
course_names = [
    ("PRF", "Programming Fundamentals"),
    ("DSA", "Data Structures and Algorithms"),
    ("DBI", "Database Systems"),
    ("OS", "Operating Systems"),
    ("CN", "Computer Networks"),
    ("SE", "Software Engineering"),
    ("AI", "Artificial Intelligence"),
    ("ML", "Machine Learning"),
    ("CS", "Cybersecurity"),
    ("WD", "Web Development")
]
exam_types = [(1, "Final exam", "Final"), (2, "Midterm", "Midterm"), (3, "Quiz", "Quiz")]

# Date range
start_date = datetime(2024, 1, 1)
end_date = datetime.now()

# Helper functions
def random_date(start, end):
    delta = end - start
    random_days = random.randint(0, delta.days)
    return start + timedelta(days=random_days)

def random_time():
    hour = random.randint(8, 13)
    return time(hour, 0, 0)

# Start building SQL
sql = ["-- Roles"]
for rid in role_ids:
    sql.append(f"INSERT INTO roles (role_id, name) VALUES ({rid}, 'Role {rid}');")

sql.append("\n-- Courses")
for i, (abbr, name) in enumerate(course_names):
    sql.append(f"INSERT INTO courses (course_id, abbreviation, name) VALUES ('{course_ids[i]}', '{abbr}', '{name}');")

sql.append("\n-- Rooms")
for rid in room_ids:
    headcount = random.randint(20, 50)
    sql.append(f"INSERT INTO rooms (room_id, headcount) VALUES ('{rid}', {headcount});")

sql.append("\n-- Test Types")
for tid, desc, name in exam_types:
    sql.append(f"INSERT INTO test_types (type_id, description, name) VALUES ({tid}, '{desc}', '{name}');")

sql.append("\n-- Persons")
for i, pid in enumerate(student_ids):
    age = 21
    birth_date = datetime(2004, random.randint(1, 12), random.randint(1, 28))
    email = f"{last_names[i % len(last_names)].lower()}{i}@student.edu.vn"
    fname = first_names[i % len(first_names)]
    mname = middle_names[i % len(middle_names)]
    lname = last_names[i % len(last_names)]
    gender = random.randint(0, 1)
    join_date = random_date(start_date, end_date)
    phone = f"09{random.randint(10000000, 99999999)}"
    role = random.choice(role_ids)
    sql.append(f"INSERT INTO persons (person_id, age, birth_date, email, first_name, gender, join_date, last_name, middle_name, phone, role_role_id) "
               f"VALUES ('{pid}', {age}, '{birth_date.strftime('%Y-%m-%d %H:%M:%S.%f')}', '{email}', '{fname}', {gender}, '{join_date.strftime('%Y-%m-%d %H:%M:%S.%f')}', "
               f"'{lname}', '{mname}', '{phone}', {role});")

sql.append("\n-- Course_Person")
for pid in student_ids:
    courses_sample = random.sample(course_ids, 3)
    for cid in courses_sample:
        sql.append(f"INSERT INTO course_person (person_id, course_id) VALUES ('{pid}', '{cid}');")

sql.append("\n-- Course_Room")
for cid in course_ids:
    rooms_sample = random.sample(room_ids, 2)
    for rid in rooms_sample:
        sql.append(f"INSERT INTO course_room (room_id, course_id) VALUES ('{rid}', '{cid}');")

sql.append("\n-- Person_Room_Attend")
for pid in student_ids:
    room_sample = random.sample(room_ids, 2)
    for rid in room_sample:
        sql.append(f"INSERT INTO person_room_attend (room_id, person_id) VALUES ('{rid}', '{pid}');")

sql.append("\n-- Exams")
exam_ids = list(range(1, num_exams + 1))
for eid in exam_ids:
    bag_code = f"BC{random.randint(1000,9999)}"
    course = random.choice(course_ids)
    exam_type = random.choice([1, 2, 3])
    edate = random_date(start_date, end_date)
    etime = random_time()
    diff = random.randint(1, 5)
    sql.append(f"INSERT INTO exams (exam_id, bag_code, course_course_id, exam_type_type_id, date, time, difficulty) VALUES "
               f"({eid}, '{bag_code}', '{course}', {exam_type}, '{edate.date()}', '{etime.strftime('%H:%M:%S.%f')}', {diff});")

sql.append("\n-- Exam_Room")
for eid in exam_ids:
    room = random.choice(room_ids)
    sql.append(f"INSERT INTO exam_room (room_id, exam_id) VALUES ('{room}', {eid});")

sql.append("\n-- Person_Exam")
for pid in student_ids:
    for eid in random.sample(exam_ids, 3):
        sql.append(f"INSERT INTO person_exam (person_id, exam_id) VALUES ('{pid}', {eid});")

sql.append("\n-- Person_Exam_Report")
for pid in student_ids:
    for cid in random.sample(course_ids, 2):
        grade = random.randint(50, 100)
        idate = random_date(start_date, end_date)
        sql.append(f"INSERT INTO person_exam_report (grade, issue_date, course_id, person_id) "
                   f"VALUES ({grade}, '{idate.strftime('%Y-%m-%d %H:%M:%S.%f')}', '{cid}', '{pid}');")

# Write to file
with open("insert_database.sql", "w") as f:
    f.write("\n".join(sql))
