package com.pankaj.jpa.university.service;

import com.pankaj.jpa.university.model.Professor;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProfessorServiceTest {
    Logger log = Logger.getLogger(this.getClass().getName());
    private EntityManagerFactory emf;

    @Before
    public void init(){
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @Test
    public void persistProfessor(){
        log.info("...persistProfessor...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Professor professor = new Professor();
        professor.setFirstName("Jane");
        professor.setLastName("Doe");
        em.persist(professor);
        em.getTransaction().commit();
        em.close();

        Assert.assertNotNull(professor.getId());
    }
    @After
    public void close(){
        emf.close();
    }
}
