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
public class TestlaufController implements Serializable {

    @Inject
    private TestlaufDAO testlaufDAO;
    @Inject
    private TestfallDAO testfallDAO;
    @Inject
    private TestergebnisDAO testergebnisDAO;
    @Inject
    private TesterDAO testerDAO; 

    private List<Testlauf> alleTestlaeufe = new ArrayList<>();

    @PostConstruct
    public void init() {
        alleTestlaeufe = testlaufDAO.ladeAlle();
        if (alleTestlaeufe == null) {
            alleTestlaeufe = new ArrayList<>();
        }
        for (Testlauf tl : alleTestlaeufe) {
            List<Integer> ids = testergebnisDAO.ladeTestfallIdsFuerLauf(tl);
            tl.setAusgewaehlteTestfallIds(ids);
        }
    }

    public void neuenTestlaufHinzufuegen() {
        alleTestlaeufe.add(new Testlauf());
    }

    public String testlaeufeSpeichern() {
        try {
            for (Testlauf tl : alleTestlaeufe) {
                if (tl.getName() != null && !tl.getName().isEmpty()) {
                	if (tl.getTempTesterId() != 0) {
                        tl.setTester(testerDAO.findeTester(tl.getTempTesterId()));
                    }
                    testlaufDAO.speichern(tl);
                    testergebnisDAO.speicherZuordnung(tl, tl.getAusgewaehlteTestfallIds());
                }
            }
            addMessage("Erfolg", "Testläufe gespeichert.");
            init(); 
        } catch (Exception e) {
            addMessage("Fehler", "Fehler: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Testfall> getAlleTestfaelle() {
        return testfallDAO.ladeAlle();
    }
    public List<Tester> getAlleTester() {
        return testerDAO.ladeAlle();
    }

    public void testlaufLoeschen(Testlauf tl) {
        try {
            if (tl.getId() != 0) {
                testlaufDAO.loeschen(tl.getId());
            }
            alleTestlaeufe.remove(tl);
            addMessage("Info", "Testlauf gelöscht.");
        } catch (Exception e) {
            addMessage("Fehler", "Löschen fehlgeschlagen.");
        }
    }

    private void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }
    public List<Testlauf> getAlleTestlaeufe() { return alleTestlaeufe; }
    public void setAlleTestlaeufe(List<Testlauf> alleTestlaeufe) { this.alleTestlaeufe = alleTestlaeufe; }
}

