package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.modelo.Laboratorio;
import jakarta.persistence.EntityManager;

public class LaboratorioDAO {
    public void salvar(Laboratorio laboratorio) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(laboratorio);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    public List<Laboratorio> listarTodos() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            return entityManager.createQuery("select laboratorio from Laboratorio laboratorio order by laboratorio.nome", Laboratorio.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
