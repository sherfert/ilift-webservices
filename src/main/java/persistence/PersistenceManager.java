package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import data.Exercise;
import data.Session;

public class PersistenceManager {
	
	/**
     * The name of the persistence unit. Specified in persistence.xml
     */
    public static final String PERSISTENCE_UNIT_NAME = "ilift-webservices";
    
    private static EntityManagerFactory entityManagerFactory;
    
    // TODO remove test main
    public static void main(String[] args) {
		connect();
		EntityManager em = getNewEntityManager();
		
		Exercise e0 = new Exercise("dumbell");
		em.getTransaction().begin();
		em.persist(e0);
		em.getTransaction().commit();
		
		// Attach file
		Exercise e1 = new Exercise();
		e1.setId(1);
		Session s1 = new Session();
		s1.setExercise(e1);
		
		em.getTransaction().begin();
		em.merge(s1);
		em.getTransaction().commit();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Exercise> cqE = cb.createQuery(Exercise.class);
		Root<Exercise> rootE = cqE.from(Exercise.class);
		cqE.select(rootE);
		TypedQuery<Exercise> qE = em.createQuery(cqE);
		List<Exercise> listE = qE.getResultList();
		
		CriteriaQuery<Session> cqS = cb.createQuery(Session.class);
		Root<Session> rootS = cqS.from(Session.class);
		cqS.select(rootS);
		TypedQuery<Session> qS = em.createQuery(cqS);
		List<Session> listS = qS.getResultList();
		
		System.out.println(listE.size());
		System.out.println(listE.get(0).getId());
		System.out.println(listE.get(0).getName());
		System.out.println(listS.size());
		System.out.println(listS.get(0).getId());
		
		em.close();
	}
    
    /**
     * Connect to DB
     */
    public static void connect() {
    	// Connect to database
        try {
        entityManagerFactory = Persistence.
                createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch(PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @return a new EntityManager that can be used for transactions or searches.
     */
    static EntityManager getNewEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    
    /**
     * Disconnects from the database.
     */
    public static void disconnect() {
        // Close everything
        entityManagerFactory.close();
    }

}
