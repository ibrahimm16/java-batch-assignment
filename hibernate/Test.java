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
        return entityManager.createQuery("SELECT e FROM " + table + " e WHERE " + table.toLowerCase() + "_id="+id).getSingleResult();
    }

    static List<Object> getEntities(EntityManager entityManager, String table) {
        return entityManager.createQuery("SELECT e FROM " + table + " e").getResultList();
    }

    static void removeTeam(EntityManager entityManager, long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createQuery("DELETE FROM Team t WHERE team_id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM Team_Athlete t WHERE team_id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM Athlete a WHERE team_id="+id).executeUpdate();

        transaction.commit();
    }

    static void removeAthlete(EntityManager entityManager, long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createQuery("DELETE FROM Athlete a WHERE athlete_id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM Team_Athlete t WHERE athlete_id="+id).executeUpdate();

        transaction.commit();
    }

    static void removeTeam_Athlete(EntityManager entityManager, long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createQuery("DELETE FROM Team_Athlete t WHERE team_athlete_id="+id).executeUpdate();

        transaction.commit();
    }
}
