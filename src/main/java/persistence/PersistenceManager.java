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
		
		DataManager.createDefaultData();
		
		disconnect();
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
