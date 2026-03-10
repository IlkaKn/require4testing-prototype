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
public class TestfallController implements Serializable {

    @Inject
    private TestfallDAO testfallDAO;
    @Inject
    private AnforderungDAO anforderungDAO;

    private List<Testfall> alleTestfaelle;

    @PostConstruct
    public void init() {
        alleTestfaelle = testfallDAO.ladeAlle();
        if (alleTestfaelle == null) {
            alleTestfaelle = new ArrayList<>();
        }
    }

    public void neuenTestfallHinzufuegen() {
        alleTestfaelle.add(new Testfall());
    }

    public String testfaelleSpeichern() {
        try {
            for (Testfall tf : alleTestfaelle) {
                if (tf.getName() != null && !tf.getName().isEmpty()) {
                	if (tf.getTempAnforderungId() != 0) {
                        tf.setAnforderung(anforderungDAO.findeAnforderung(tf.getTempAnforderungId()));
                	}
                	testfallDAO.speichern(tf);
                }
            }
            addMessage("Erfolg", "Testfälle gespeichert.");
            init(); 
        } catch (Exception e) {
            addMessage("Fehler", "Fehler: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public List<Anforderung> getAlleAnforderungen() {
        return anforderungDAO.ladeAlle();
    }

    public void testfallLoeschen(Testfall tf) {
        try {
            if (tf.getId() != 0) {
                testfallDAO.loeschen(tf.getId());
            }
            alleTestfaelle.remove(tf);
            addMessage("Info", "Testfall gelöscht.");
        } catch (Exception e) {
            addMessage("Fehler", "Löschen fehlgeschlagen.");
        }
    }

    private void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }
   
    public List<Testfall> getAlleTestfaelle() { return alleTestfaelle; }
    public void setAlleTestfaelle(List<Testfall> alleTestfaelle) { this.alleTestfaelle = alleTestfaelle; }
}



