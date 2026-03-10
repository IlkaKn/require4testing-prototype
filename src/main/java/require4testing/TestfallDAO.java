package require4testing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import java.util.List;

@ApplicationScoped
public class TestfallDAO {

    public void speichern(Testfall tf) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            if (tf.getId() == 0) {
                em.persist(tf);
            } else {
                em.merge(tf);
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void loeschen(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Testfall tf = em.find(Testfall.class, id);
            
            if (tf != null) {
                // SCHRITT 1: Aufräumen der Abhängigkeiten
                // "Lösche alle Ergebnisse, die diesen Testfall beinhalten"
                em.createQuery("DELETE FROM Testergebnis te WHERE te.testfall.id = :id")
                  .setParameter("id", id)
                  .executeUpdate();
                  
                // SCHRITT 2: Testfall löschen
                em.remove(tf);
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Testfall> ladeAlle() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT tf FROM Testfall tf", Testfall.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    // Anforderung 4: Testlauf beliebige Testfälle zuordnen
    public Testfall findeTestfall(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Testfall.class, id);
        } finally {
            em.close();
        }
    }
}