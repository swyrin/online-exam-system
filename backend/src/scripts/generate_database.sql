create table courses
(
    course_id    varchar(255) not null
        primary key,
    abbreviation varchar(255) null,
    name         varchar(255) null
);

create table persons
(
    person_id   varchar(255) not null
        primary key,
    age         int          null,
    birth_date  datetime(6)  null,
    email       varchar(255) null,
    first_name  varchar(255) null,
    gender      tinyint      null
        check (`gender` between 0 and 1),
    join_date   datetime(6)  null,
    last_name   varchar(255) null,
    middle_name varchar(255) null,
    phone       varchar(255) null
);

create table course_person
(
    person_id varchar(255) not null,
    course_id varchar(255) not null,
    primary key (person_id, course_id),
    constraint FKa88c29pvhw6e7xrdcbr67krqg
        foreign key (person_id) references persons (person_id),
    constraint FKnp5u8xq9kjlfhsaax7o5o2ouf
        foreign key (course_id) references courses (course_id)
);

create table person_exam_report
(
    grade      int          null,
    issue_date datetime(6)  null,
    course_id  varchar(255) not null,
    person_id  varchar(255) not null,
    primary key (course_id, person_id),
    constraint FK7dj7k6wo992tbjbqhkcdxq4n9
        foreign key (person_id) references persons (person_id),
    constraint FKuev9qsnurv56g4dwupx2p63h
        foreign key (course_id) references courses (course_id)
);

create table roles
(
    role_id          bigint       not null
        primary key,
    name             varchar(255) null,
    person_person_id varchar(255) null,
    constraint FKllpefsje5wl1hjl7icf7w4qk9
        foreign key (person_person_id) references persons (person_id)
);

create table rooms
(
    room_id   varchar(255) not null
        primary key,
    headcount bigint       null
);

create table course_room
(
    room_id   varchar(255) not null,
    course_id varchar(255) not null,
    primary key (room_id, course_id),
    constraint FKdplytyuajc4htyion192ii01s
        foreign key (room_id) references rooms (room_id),
    constraint FKdyt03ca2c6ibqjtieoytt4oth
        foreign key (course_id) references courses (course_id)
);

create table person_room_attend
(
    room_id   varchar(255) not null,
    person_id varchar(255) not null,
    primary key (room_id, person_id),
    constraint FKau9b8s5qig0qhyh12yqlxjj6e
        foreign key (person_id) references persons (person_id),
    constraint FKgc2yaw61lqr2eevc2bep0688q
        foreign key (room_id) references rooms (room_id)
);

create table test_types
(
    type_id     bigint       not null
        primary key,
    description varchar(255) null,
    name        varchar(255) null
);

create table exams
(
    exam_id           bigint       not null
        primary key,
    bag_code          varchar(255) null,
    course_course_id  varchar(255) null,
    exam_type_type_id bigint       null,
    constraint FKgujq84gqhfqd96d1so4gmn8bu
        foreign key (exam_type_type_id) references test_types (type_id),
    constraint FKihnbtgx6sw5kyidarrf21cfq3
        foreign key (course_course_id) references courses (course_id)
);

create table exam_room
(
    room_id varchar(255) not null,
    exam_id bigint       not null,
    primary key (room_id, exam_id),
    constraint FKdg4594dtjjcb61168obpt03fq
        foreign key (exam_id) references exams (exam_id),
    constraint FKtfo6emql9pum6ciwftry40qg7
        foreign key (room_id) references rooms (room_id)
);

create table person_exam
(
    person_id varchar(255) not null,
    exam_id   bigint       not null,
    primary key (person_id, exam_id),
    constraint FK7qkp42jcnnybjqg65s9n70fno
        foreign key (person_id) references persons (person_id),
    constraint FKl26fpy2amha5oj47hdh9ev5s1
        foreign key (exam_id) references exams (exam_id)
);

