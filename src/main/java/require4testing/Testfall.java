package require4testing;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Testfall implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    
    @ManyToOne
    private Anforderung anforderung;
    
    @Transient
    private int tempAnforderungId;
  
    public Testfall() {}
    
    public Anforderung getAnforderung() { return anforderung; }
    public void setAnforderung(Anforderung anforderung) { this.anforderung = anforderung; }
    public int getTempAnforderungId() {
        if (this.anforderung != null) {
            return this.anforderung.getId();
        }
        return tempAnforderungId;
    }
    public void setTempAnforderungId(int tempAnforderungId) { this.tempAnforderungId = tempAnforderungId; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
