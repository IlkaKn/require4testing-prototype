package require4testing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import java.util.List;

@ApplicationScoped
public class TestlaufDAO {

    public void speichern(Testlauf tl) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            if (tl.getId() == 0) {
                em.persist(tl);
            } else {
                em.merge(tl);
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
            Testlauf tl = em.find(Testlauf.class, id);
            
            if (tl != null) {
               
                em.createQuery("DELETE FROM Testergebnis te WHERE te.testlauf.id = :id")
                  .setParameter("id", id)
                  .executeUpdate();
                
                em.remove(tl);
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Testlauf> ladeAlle() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT tl FROM Testlauf tl", Testlauf.class).getResultList();
        } finally {
            em.close();
        }
    }
}