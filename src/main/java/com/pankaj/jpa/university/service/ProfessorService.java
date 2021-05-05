package com.pankaj.jpa.university.service;

import com.pankaj.jpa.university.model.Professor;

import javax.persistence.EntityManager;

public class ProfessorService {
    private EntityManager em;

    public ProfessorService(EntityManager em) {
        this.em = em;
    }

    public Professor saveProfessor(Professor p){
        em.persist(p);
        return p;
    }

    public void removeProfessor(Professor p){
        em.remove(p);
    }

    public Professor findById(Long id){
        return em.find(Professor.class,id);
    }
}
