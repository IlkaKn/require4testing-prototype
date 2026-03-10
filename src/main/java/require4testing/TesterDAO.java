package require4testing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import java.util.List;

@ApplicationScoped
public class TesterDAO {

    public List<Tester> ladeAlle() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tester t", Tester.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Anforderung 4: Testlauf ein Tester zuordnen
    public Tester findeTester(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Tester.class, id);
        } finally {
            em.close();
        }
    }
}