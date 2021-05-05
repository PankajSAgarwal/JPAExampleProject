package com.pankaj.jpa.university.model;

import javax.persistence.*;

@Entity
public class Curriculum {
    @Id
    private Long id;

    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private Course course;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
