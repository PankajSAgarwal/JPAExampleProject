CREATE TABLE public.course
(
  id bigint NOT NULL,
  enddate date,
  name character varying(255),
  startdate date,
  professor_id bigint
);

CREATE SEQUENCE public.course_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.course_student
(
  courses_id bigint NOT NULL,
  students_id bigint NOT NULL
);

CREATE TABLE public.curriculum
(
  id bigint NOT NULL,
  description character varying(255)
);

CREATE TABLE public.professor
(
  id bigint NOT NULL,
  firstname character varying(255),
  lastname character varying(255)
);

CREATE SEQUENCE public.professor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.student
(
  id bigint NOT NULL,
  firstname character varying(255),
  lastname character varying(255),
  state integer
);

CREATE SEQUENCE public.student_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY public.course
ADD CONSTRAINT course_pkey PRIMARY KEY
(id);

ALTER TABLE ONLY public.course_student
ADD CONSTRAINT course_student_pkey PRIMARY KEY
(courses_id, students_id);

ALTER TABLE ONLY public.curriculum
ADD CONSTRAINT curriculum_pkey PRIMARY KEY
(id);

ALTER TABLE ONLY public.professor
ADD CONSTRAINT professor_pkey PRIMARY KEY
(id);

ALTER TABLE ONLY public.student
ADD CONSTRAINT student_pkey PRIMARY KEY
(id);

ALTER TABLE ONLY public.course
ADD CONSTRAINT fk_course_professor FOREIGN KEY
(professor_id) REFERENCES public.professor
(id);

ALTER TABLE ONLY public.course_student
ADD CONSTRAINT fk_coursestudent_course FOREIGN KEY
(courses_id) REFERENCES public.course
(id);

ALTER TABLE ONLY public.course_student
ADD CONSTRAINT fk_coursestudent_student FOREIGN KEY
(students_id) REFERENCES public.student
(id);

ALTER TABLE ONLY public.curriculum
ADD CONSTRAINT fk_curriculum_course FOREIGN KEY
(id) REFERENCES public.course
(id);