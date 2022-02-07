package hibernate;

import hibernate.entities.Athlete;
import hibernate.entities.Team;
import hibernate.entities.Team_Athlete;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        DBConfig dbConfig = new DBConfig();

//        removeTeam(dbConfig.getEntityManager(), 4);
//        removeAthlete(dbConfig.getEntityManager(), 3);
    }

    static void addTeam(EntityManager entityManager, String name) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Team team = new Team();
        team.setName(name);
        entityManager.persist(team);

        transaction.commit();
    }

    static void addAthlete(EntityManager entityManager, String name, Team team) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        if (team == null) {
            transaction.rollback();
            return;
        }

        Athlete athlete = new Athlete();
        athlete.setName(name);
        athlete.setTeam(team);
        entityManager.persist(athlete);

        team.getAthletes().add(athlete);
        entityManager.persist(team);

        transaction.commit();
    }

    static void addTeam_Athlete(EntityManager entityManager, Team team, Athlete athlete) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        if (team == null || athlete == null) {
            transaction.rollback();
            return;
        }

        Team_Athlete team_athlete = new Team_Athlete();
        team_athlete.setTeam(team);
        team_athlete.setAthlete(athlete);
        entityManager.persist(team_athlete);

        transaction.commit();
    }

    static Object getEntity(EntityManager entityManager, String table, long id) {
        return entityManager.createQuery("SELECT e FROM " + table + " e WHERE e.id="+id).getSingleResult();
    }

    static List<Object> getEntities(EntityManager entityManager, String table) {
        return entityManager.createQuery("SELECT e FROM " + table + " e").getResultList();
    }

    static void removeTeam(EntityManager entityManager, long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createQuery("DELETE FROM Team t WHERE t.id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM Athlete a WHERE a.team.id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM Team_Athlete t WHERE t.team.id="+id).executeUpdate();

        transaction.commit();
    }

    static void removeAthlete(EntityManager entityManager, long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createQuery("DELETE FROM Athlete a WHERE a.id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM Team_Athlete t WHERE t.athlete.id="+id).executeUpdate();

        transaction.commit();
    }
}
