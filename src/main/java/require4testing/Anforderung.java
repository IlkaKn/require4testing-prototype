package require4testing;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Anforderung implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    
    public Anforderung() {}
   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}


