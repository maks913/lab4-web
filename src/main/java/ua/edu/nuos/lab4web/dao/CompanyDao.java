package ua.edu.nuos.lab4web.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.edu.nuos.lab4web.data.Company;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CompanyDao {
    private final Logger logger = Logger.getLogger(CompanyDao.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<Company> findAll() {
        return em.createQuery("SELECT c FROM Company c", Company.class)
                .getResultList();
    }

    @Transactional
    public void createCompany(Company company) {
        logger.info("INFO: " + company.toString());
        em.persist(company);
    }

    @Transactional
    public void deleteById(Integer id) {
        em.remove(em.find(Company.class, id));
    }

    @Transactional
    public void updateCompany(Company company) {
        em.merge(company);
    }

}
