package require4testing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import java.util.List;

@ApplicationScoped
public class TestergebnisDAO {
	public void speicherZuordnung(Testlauf tl, List<Integer> testfallIds) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.createQuery("DELETE FROM Testergebnis te WHERE te.testlauf.id = :laufId")
              .setParameter("laufId", tl.getId())
              .executeUpdate();
            
            if (testfallIds != null) {
                for (Integer tfId : testfallIds) {
                    Testfall tf = em.find(Testfall.class, tfId);
                    
                    if (tf != null) {
                        Testergebnis te = new Testergebnis(tf, tl);
                        em.persist(te);
                    }
                }
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
	 
    public List<Integer> ladeTestfallIdsFuerLauf(Testlauf tl) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String query = "SELECT te.testfall.id FROM Testergebnis te WHERE te.testlauf.id = :laufId";
            return em.createQuery(query, Integer.class).setParameter("laufId", tl.getId()).getResultList();
        } finally {
            em.close();
        }
    }
    
    public void speichern(Testergebnis te) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.merge(te); 
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
  
    public List<Testergebnis> ladeAlleFuerTester(int testerId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String query = "SELECT te FROM Testergebnis te " +
                           "JOIN FETCH te.testlauf tl " +
                           "JOIN FETCH te.testfall tf " +
                           "WHERE tl.tester.id = :tid";
            return em.createQuery(query, Testergebnis.class).setParameter("tid", testerId).getResultList();
        } finally {
            em.close();
        }
    } 
}

