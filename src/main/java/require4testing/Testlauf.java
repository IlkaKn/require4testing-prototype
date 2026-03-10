package require4testing;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List; 
import java.util.ArrayList;

@Entity
public class Testlauf implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    
    @ManyToOne
    private Tester tester; 
    
    @Transient 
    private int tempTesterId;
   
    @Transient
    private List<Integer> ausgewaehlteTestfallIds = new ArrayList<>();

    public Testlauf() {}
    
    public Tester getTester() { return tester; }
    public void setTester(Tester tester) { this.tester = tester; }
    public int getTempTesterId() {
        if (this.tester != null) {
            return this.tester.getId();
        }
        return tempTesterId;
    }
    public void setTempTesterId(int tempTesterId) { this.tempTesterId = tempTesterId; }
    public List<Integer> getAusgewaehlteTestfallIds() { return ausgewaehlteTestfallIds; }
    public void setAusgewaehlteTestfallIds(List<Integer> ausgewaehlteTestfallIds) { 
    	this.ausgewaehlteTestfallIds = ausgewaehlteTestfallIds; 
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

