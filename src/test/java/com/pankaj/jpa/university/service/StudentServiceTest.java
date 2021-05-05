package com.pankaj.jpa.university.service;

import com.pankaj.jpa.university.model.Course;
import com.pankaj.jpa.university.model.Student;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StudentServiceTest {
    Logger log = Logger.getLogger(this.getClass().getName());
    private EntityManagerFactory emf;
    @Before
    public void init(){
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @After
    public void close(){
        emf.close();
    }

    @Test
    public void persistStudent(){

        log.info("...persistStudent...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student s = new Student();
        s.setFirstName("Peter");
        s.setLastName("Doe");
        StudentService ss = new StudentService(em);
        ss.saveStudent(s);

        em.getTransaction().commit();
        em.close();

        Assert.assertNotNull(s.getId());
    }

    @Test
    public void updateStudent(){
        log.info("...updateStudent...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // persist a new student
        Student s = createStudent(em);
        // Load and update the Student
        StudentService ss = new StudentService(em);
        s.setFirstName("Martin");

        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void removeStudent() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // persist a new Student
        Student s = createStudent(em);

        // Load and update the Student
        StudentService ss = new StudentService(em);
        ss.removeStudent(s);

        em.getTransaction().commit();
        em.close();

        // Validate update
        s = getStudent(s.getId());
        Assert.assertNull(s);
    }

    @Test
    public void enrollStudentToCourse() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //persist a new Student
        Student s = createStudent(em);
        Course c = createCourse(em);

        CourseService cs = new CourseService(em);
        c = cs.findByName("Software Development 2019");

        StudentService ss = new StudentService(em);
        List<Student> students = ss.findByFirstNameAndLastName("Jane","Doe");

        for (Student student : students) {
            c.getStudents().add(student);
            student.getCourses().add(c);
        }


        int numOfStudents = cs.countStudentsInCourse(c.getId());
        Assert.assertTrue(numOfStudents>0);

        em.getTransaction().commit();
        em.close();
    }

    private Course createCourse(EntityManager em) {
        Course c = new Course();
        c.setName("Software Development 2019");

        CourseService cs = new CourseService(em);
        cs.saveCourse(c);

        return c;
    }

    private Student createStudent(EntityManager em) {
        Student s = new Student();
        s.setFirstName("Jane");
        s.setLastName("Doe");

        StudentService ss = new StudentService(em);
        ss.saveStudent(s);

        return s;
    }

    private Student getStudent(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        StudentService ss = new StudentService(em);
        Student s = ss.findById(id);

        em.getTransaction().commit();
        em.close();

        return s;
    }
}
