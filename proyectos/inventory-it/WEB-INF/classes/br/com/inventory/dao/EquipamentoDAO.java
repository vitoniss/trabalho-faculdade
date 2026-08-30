package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.modelo.Equipamento;
import jakarta.persistence.EntityManager;

public class EquipamentoDAO {
    public void salvar(Equipamento equipamento) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(equipamento);
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

    public List<Equipamento> listarTodos() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            return entityManager.createQuery(
                    "select equipamento from Equipamento equipamento join fetch equipamento.laboratorio order by equipamento.numeroSerie",
                    Equipamento.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
