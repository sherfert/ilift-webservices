package persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.gson.Gson;

import data.EqType;
import data.Equipment;
import data.Exercise;
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
	 */
	public static void insertSession(Session session) {
		// Set current time
		session.setDate(new Date());
		
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(session);
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

	public static void createDefaultData() {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			if(!isEmptyDB(em)) {
				return;
			}
			em.getTransaction().begin();
			
			// Exercises
			Exercise bicepsCurl = new Exercise("Biceps curl");
			Exercise shoulderPress = new Exercise("Shoulder curl");
			
			Exercise squat = new Exercise("Squat");
			Exercise tricepCurl = new Exercise("Tricep Curl");
			
			em.persist(bicepsCurl);
			em.persist(shoulderPress);
			em.persist(squat);
			em.persist(tricepCurl);
			
			// EqTypes
			EqType dumbbell = new EqType("Dumbbell");
			EqType kettlebell = new EqType("Kettlebell");
			
			dumbbell.addAvailableExercise(bicepsCurl);
			dumbbell.addAvailableExercise(shoulderPress);
			kettlebell.addAvailableExercise(squat);
			kettlebell.addAvailableExercise(tricepCurl);
			
			em.persist(dumbbell);
			em.persist(kettlebell);
			
			// TODO correct tags
			// Equipments
			Equipment dumbbell5kg = new Equipment(dumbbell, "aszjdguz", 5);
			Equipment dumbbell10kg = new Equipment(dumbbell, "buzjdguz", 10);
			Equipment kettlebell5kg = new Equipment(kettlebell, "zsuidguz", 5);
			
			em.persist(dumbbell5kg);
			em.persist(dumbbell10kg);
			em.persist(kettlebell5kg);
			
			// 2 users
			User user0 = new User();
			user0.setRfidReaderTag("218znciu217");
			User user1 = new User();
			user1.setRfidReaderTag("2z8zdskjhu7");
			
			em.persist(user0);
			em.persist(user1);			
			
			Session session = new Session(squat, kettlebell5kg, user0);
			Gson gson = new Gson();
			System.out.println(gson.toJson(session));
			
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * Tests wether DB is empty by checking the representative table EqType
	 * 
	 * @param em
	 *            the EntityManager
	 * @return {@code true} iff the DB is empty.
	 */
	private static boolean isEmptyDB(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EqType> cqE = cb.createQuery(EqType.class);
		Root<EqType> rootE = cqE.from(EqType.class);
		cqE.select(rootE);
		TypedQuery<EqType> qE = em.createQuery(cqE);
		return qE.getResultList().isEmpty();
	}

}
