package require4testing;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Testergebnis implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private boolean ergebnis; 
    
    @ManyToOne
    private Testfall testfall;

    @ManyToOne
    private Testlauf testlauf;
    
    public Testergebnis() {}
    public Testergebnis(Testfall tf, Testlauf tl) {
    	this.testfall = tf;
    	this.testlauf = tl;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public boolean getErgebnis() { return ergebnis; }
    public void setErgebnis(boolean ergebnis) { this.ergebnis = ergebnis; }
    
    public Testfall getTestfall() { return testfall; }
    public void setTestfall(Testfall testfall) { this.testfall = testfall; }
    public Testlauf getTestlauf() { return testlauf; }
    public void setTestlauf(Testlauf testlauf) { this.testlauf = testlauf; }
   
}
