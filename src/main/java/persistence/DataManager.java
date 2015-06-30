package persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import data.EqType;
import data.Equipment;
import data.Exercise;
import data.RepetitionObj;
import data.Session;
import data.StringLongTuple;
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
		} catch (NoResultException e) {
			return null;
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
	public static User getUserById(long id) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Gets the user by username
	 * 
	 * @param username
	 *            the username
	 * @return the user or null, if there is no user with that username
	 */
	public static User getUserByUsername(String username) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> cqU = cb.createQuery(User.class);
			Root<User> rootU = cqU.from(User.class);
			cqU.select(rootU).where(cb.equal(rootU.get("username"), username));
			TypedQuery<User> qU = em.createQuery(cqU);
			return qU.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Gets the user by rfidTag
	 * 
	 * @param rfidTag
	 *            the RFID tag
	 * @return the user
	 */
	public static User getUserByTag(String rfidTag) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> cqU = cb.createQuery(User.class);
			Root<User> rootU = cqU.from(User.class);
			cqU.select(rootU).where(cb.equal(rootU.get("rfidTag"), rfidTag));
			TypedQuery<User> qU = em.createQuery(cqU);
			return qU.getSingleResult();
		} catch (NoResultException e) {
			return null;
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
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Session> cqS = cb.createQuery(Session.class);
			Root<Session> rootS = cqS.from(Session.class);
			Join<Session, User> join = rootS.join("user");
			cqS.select(rootS).where(cb.equal(join.get("id"), id));
			TypedQuery<Session> qS = em.createQuery(cqS);
			return qS.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Get the count of all different exercises the user has done.
	 * 
	 * @param name
	 *            the username
	 * @return tuples (exercise name,count) of exercises.
	 */
	public static List<StringLongTuple> getSessionCountsOfUserByName(String name) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<StringLongTuple> cqS = cb
					.createQuery(StringLongTuple.class);

			Root<Session> rootS = cqS.from(Session.class);
			Join<Session, User> joinUser = rootS.join("user");
			Join<Session, Exercise> joinExercise = rootS.join("exercise");

			cqS.multiselect(joinExercise.get("name"), cb.count(rootS));
			cqS.where(cb.and(cb.equal(joinUser.get("username"), name)));
			cqS.groupBy(joinExercise.get("name"));

			TypedQuery<StringLongTuple> qS = em.createQuery(cqS);
			return qS.getResultList();
		} finally {
			em.close();
		}
	}
	
	/**
	 * @return a list of the names of all exercises
	 */
	public static List<String> getAllExercises() {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<String> cqS = cb.createQuery(String.class);
			Root<Exercise> rootE = cqS.from(Exercise.class);
			cqS.select(rootE.get("name"));
			TypedQuery<String> qE = em.createQuery(cqS);
			return qE.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Gets the list of repititions over the last sets for a certain exercise
	 * from a given user
	 * 
	 * @param name
	 *            the username
	 * @param exerciseName
	 *            the name of the exercise
	 * @param limit
	 *            the number of last sessions to include
	 * @return the repetitions in each session
	 */
	public static List<Integer> getRepetitions(String name,
			String exerciseName, int limit) {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> cqS = cb.createQuery(Integer.class);

			Root<Session> rootS = cqS.from(Session.class);
			Join<Session, User> joinUser = rootS.join("user");
			Join<Session, Exercise> joinExercise = rootS.join("exercise");

			cqS.select(rootS.get("repetitions"));
			cqS.where(cb.and(cb.equal(joinUser.get("username"), name),
					cb.equal(joinExercise.get("name"), exerciseName)));

			TypedQuery<Integer> qS = em.createQuery(cqS).setMaxResults(limit);
			return qS.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * This method creates some default data, because we don't have an interface
	 * to create users, add exercises or equipment.
	 */
	public static void createDefaultData() {
		EntityManager em = PersistenceManager.getNewEntityManager();

		try {
			if (!isEmptyDB(em)) {
				return;
			}
			em.getTransaction().begin();

			// Exercises
			Exercise bicepsCurl = new Exercise("Biceps curl");
			Exercise lateralRaise = new Exercise("Lateral raise");

			// Exercise squat = new Exercise("Squat");
			Exercise tricepCurl = new Exercise("Tricep extension");

			em.persist(bicepsCurl);
			em.persist(lateralRaise);
			// em.persist(squat);
			em.persist(tricepCurl);

			// EqTypes
			EqType dumbbell = new EqType("Dumbbell");
			EqType kettlebell = new EqType("Kettlebell");

			dumbbell.addAvailableExercise(bicepsCurl);
			dumbbell.addAvailableExercise(lateralRaise);

			// kettlebell.addAvailableExercise(squat);
			kettlebell.addAvailableExercise(tricepCurl);

			em.persist(dumbbell);
			em.persist(kettlebell);

			// Equipments
			Equipment dumbbell5kg = new Equipment(dumbbell, "4D0055BA45", 5);
			Equipment dumbbell10kg = new Equipment(dumbbell, "4D0055937C", 10);
			Equipment kettlebell5kg = new Equipment(kettlebell, "4D00558F46", 5);

			em.persist(dumbbell5kg);
			em.persist(dumbbell10kg);
			em.persist(kettlebell5kg);

			// 2 users
			User omar = new User("4D00558F46", "omrsin");
			User satia = new User("4D0055BA26", "satiaherfert");

			em.persist(omar);
			em.persist(satia);

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
