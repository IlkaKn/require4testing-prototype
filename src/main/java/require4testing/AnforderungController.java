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
public class AnforderungController implements Serializable {

    @Inject
    private AnforderungDAO anforderungDAO;
    
    private List<Anforderung> alleAnforderungen;

    @PostConstruct
    public void init() {
        alleAnforderungen = anforderungDAO.ladeAlle();
        
        if (alleAnforderungen == null) {
            alleAnforderungen = new ArrayList<>();
        }
    }

    public void neueAnforderungHinzufügen() {
        alleAnforderungen.add(new Anforderung());
    }

    public String anforderungenSpeichern() {
        try {
            for (Anforderung a : alleAnforderungen) {
                if (a.getName() != null && !a.getName().isEmpty()) {
                    anforderungDAO.speichern(a);
                }
            }
            addMessage("Erfolg", "Anforderungen gespeichert.");
            init(); 
        } catch (Exception e) {
            addMessage("Fehler", "Speichern fehlgeschlagen: " + e.getMessage());
        }
        return null;
    }
    
    public void anforderungLoeschen(Anforderung a) {
        try {
            if (a.getId() != 0) {
                anforderungDAO.loeschen(a.getId());
            }
            alleAnforderungen.remove(a);
            addMessage("Info", "Anforderung gelöscht.");
        } catch (Exception e) {
            addMessage("Fehler", "Löschen fehlgeschlagen.");
        }
    }
    
    private void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    public List<Anforderung> getAlleAnforderungen() { return alleAnforderungen; }
    public void setAlleAnforderungen(List<Anforderung> alleAnforderungen) {this.alleAnforderungen = alleAnforderungen;}
}

