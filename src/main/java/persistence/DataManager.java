package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import data.Equipment;
import data.Session;
import data.User;

/**
 * Manages the data interface to the database.
 * 
 * @author satia
 */
public class DataManager {

	/**
	 * Gets the equipment by its RFID tag
	 * 
	 * @param tag
	 *            the tag
	 * @return the equipment
	 */
	public static Equipment getEquipmentByTag(String tag) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Equipment> cqE = cb.createQuery(Equipment.class);
			Root<Equipment> rootE = cqE.from(Equipment.class);
			cqE.select(rootE).where(cb.equal(rootE.get("rfidTag"), tag));
			TypedQuery<Equipment> qE = em.createQuery(cqE);
			return qE.getSingleResult();
		} finally {
			em.close();
		}
	}

	/**
	 * Inserts a session for a user
	 * 
	 * @param session
	 *            the session
	 * @param userId
	 *            the user id
	 */
	public static void insertSession(Session session, long userId) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			User user = em.find(User.class, userId);
			
			em.getTransaction().begin();
			user.addSession(session);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * Gets the user by id
	 * 
	 * @param id
	 *            the id
	 * @return the user
	 */
	public static User getUserbyId(long id) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Get all sessions of a user
	 * 
	 * @param id
	 *            the user id
	 * @return the users sessions
	 */
	public static List<Session> getSessionOfUserById(long id) {
		User user = getUserbyId(id);
		return user.getSessions();
	}

}
