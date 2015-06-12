package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import data.Exercise;

public class PersistenceManager {
	
	/**
     * The name of the persistence unit. Specified in persistence.xml
     */
    public static final String PERSISTENCE_UNIT_NAME = "ilift-webservices";
    
    
    private EntityManagerFactory entityManagerFactory;
    
    // TODO remove test main
    public static void main(String[] args) {
		PersistenceManager pm = new PersistenceManager();
		pm.connect();
		EntityManager em = pm.getNewEntityManager();
		
		Exercise e0 = new Exercise("dumbell");
		em.getTransaction().begin();
		em.persist(e0);
		em.getTransaction().commit();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Exercise> cq = cb.createQuery(Exercise.class);
		Root<Exercise> root = cq.from(Exercise.class);
		cq.select(root);
		TypedQuery<Exercise> q = em.createQuery(cq);
		List<Exercise> list = q.getResultList();
		
		System.out.println(list.get(0).getName()); 
		
		em.close();
	}
    
    /**
     * Connect to DB
     */
    public void connect() {
    	// Connect to database
        try {
        this.entityManagerFactory = Persistence.
                createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch(PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @return a new EntityManager that can be used for transactions or searches.
     */
    EntityManager getNewEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    
    /**
     * Disconnects from the database.
     */
    public void disconnect() {
        // Close everything
        entityManagerFactory.close();
    }

}
