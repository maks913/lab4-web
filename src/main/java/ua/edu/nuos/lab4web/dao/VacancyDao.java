package ua.edu.nuos.lab4web.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.edu.nuos.lab4web.data.Vacancy;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class VacancyDao {
    private static final Logger LOGGER = Logger.getLogger(VacancyDao.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<Vacancy> findAll() {
       return em.createQuery("SELECT v FROM Vacancy v", Vacancy.class).getResultList();
    }

    @Transactional
    public void createVacancy(Vacancy vacancy) {
        em.persist(vacancy);
    }

    @Transactional
    public void deleteById(Integer id) {
        em.remove(em.find(Vacancy.class, id));
    }

    public Vacancy findById(Integer id) {
        return em.find(Vacancy.class, id);
    }

    @Transactional
    public void updateVacancy(Vacancy vacancy) {
        em.merge(vacancy);
    }
}
