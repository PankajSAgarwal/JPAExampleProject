package com.pankaj.jpa.university.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name=Student.QUERY_STUDENT_BY_FIRSTNAME_AND_LASTNAME,
            query = "SELECT s from Student s where s.firstName = :" + Student.PARAM_FIRSTNAME
                    + " AND s.lastName = :"+ Student.PARAM_LASTNAME)

public class Student {
    public static final String QUERY_STUDENT_BY_FIRSTNAME_AND_LASTNAME
            = "query.StudentByFirstNameAndLastName";

    public static final String PARAM_FIRSTNAME = "firstName";
    public static final String PARAM_LASTNAME = "lastName";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="student_generator")
    @SequenceGenerator(name = "student_generator",sequenceName = "student_seq")
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    private StudentState state;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentState getState() {
        return state;
    }

    public void setState(StudentState state) {
        this.state = state;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }
}
