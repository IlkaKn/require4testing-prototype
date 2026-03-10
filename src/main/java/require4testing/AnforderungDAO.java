package require4testing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import java.util.List;

@ApplicationScoped
public class AnforderungDAO {

	public void speichern(Anforderung a) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction t = em.getTransaction();
      
        try {
            t.begin();
            if (a.getId() == 0) {
                em.persist(a); 
            } else {
                em.merge(a);   
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
            Anforderung a = em.find(Anforderung.class, id);
            
            if (a != null) {
                em.createQuery("UPDATE Testfall t SET t.anforderung = null WHERE t.anforderung.id = :id")
                  .setParameter("id", id)
                  .executeUpdate();
                em.remove(a);
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) t.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Anforderung> ladeAlle() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Anforderung a", Anforderung.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Anforderung findeAnforderung(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Anforderung.class, id);
        } finally {
            em.close();
        }
    }
}
