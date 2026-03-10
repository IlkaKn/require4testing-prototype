package require4testing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestergebnisController implements Serializable {

    @Inject
    private TestergebnisDAO testergebnisDAO;
    
    @Inject
    private TesterDAO testerDAO; 
    
    private List<Testergebnis> alleTestergebnisse = new ArrayList<>();
    
    private List<Tester> alleTester = new ArrayList<>(); 
    private int ausgewaehlterTesterId; 
    

    @PostConstruct
    public void init() {
    	try {
            alleTester = testerDAO.ladeAlle();
            if (ausgewaehlterTesterId != 0) {
                alleTestergebnisse = testergebnisDAO.ladeAlleFuerTester(ausgewaehlterTesterId);
                
                if (alleTestergebnisse.isEmpty()) {
                    addMessage("Info", "Keine offenen Tests gefunden.");
               
                }
            } 
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Tests: " + e.getMessage());
        }
    }

    public String ergebnisseSpeichern() {
        try {
            if (!alleTestergebnisse.isEmpty()) {
            	 for (Testergebnis te : alleTestergebnisse) {
                     testergebnisDAO.speichern(te);
                 }
                 
                 addMessage("Erfolg", "Alle Änderungen gespeichert.");
                 
   
                 init();
            }
        	 
           
            
        } catch (Exception e) {
            addMessage("Fehler", "Speichern fehlgeschlagen: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
  
    private void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    // --- Getter & Setter ---
    public List<Testergebnis> getZugewieseneTests() { return alleTestergebnisse; }
    public void setZugewieseneTests(List<Testergebnis> zugewieseneTests) { this.alleTestergebnisse = zugewieseneTests; }
    
    public List<Tester> getAlleTester() { return alleTester; }
    
    public int getAusgewaehlterTesterId() { return ausgewaehlterTesterId; }
    public void setAusgewaehlterTesterId(int ausgewaehlterTesterId) { this.ausgewaehlterTesterId = ausgewaehlterTesterId; }
}