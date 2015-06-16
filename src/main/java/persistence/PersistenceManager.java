package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * Manages access to the Database 
 * 
 * @author satia
 */
public class PersistenceManager {
	
	/**
     * The name of the persistence unit. Specified in persistence.xml
     */
    public static final String PERSISTENCE_UNIT_NAME = "ilift-webservices";
    
    /**
     * The entity manager factory
     */
    private static EntityManagerFactory entityManagerFactory;
    
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
