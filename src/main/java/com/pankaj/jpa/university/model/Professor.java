package com.pankaj.jpa.university.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "professor_generator")
    @SequenceGenerator(name = "professor_generator",sequenceName = "professor_seq")
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "professor")
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

    public Long getId() {
        return id;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
