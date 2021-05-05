package com.pankaj.jpa.university.service;

import com.pankaj.jpa.university.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CourseService {
    private EntityManager em;

    public CourseService(EntityManager em) {
        this.em = em;
    }

    public Course saveCourse(Course c){
        em.persist(c);
        return c;
    }

    public void removeCourse(Course c){
        em.remove(c);
    }

    public Course findById(Long id){
        return em.find(Course.class,id);
    }

    public Course findByName(String name){
        TypedQuery<Course> q = em.createQuery(
                "SELECT c from Course c where c.name = :name",Course.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    public int countStudentsInCourse(Long id){
        Query q =em.createQuery("SELECT SIZE(c.students) FROM Course c where c.id = :id");
        q.setParameter("id",id);
        return (int) q.getSingleResult();
    }
}
