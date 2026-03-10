Require4Testing - Prototyp für das Testmanagement<br>
Dies ist ein im Rahmen einer Fallstudie entwickelter Prototyp zur Verwaltung von Anforderungen, Testfällen und Testläufen. Das Ziel des Projekts ist es, die Workflows zwischen verschiedenen Rollen in einem Testprozess abzubilden.
<br><br>
Funktionen & Rollen<br>
Das System bietet für jeden Akteur eine eigene Ansicht:<br>

Requirements Engineer: Erstellen und Verwalten von Anforderungen.<br>

Testfallersteller: Anlegen von Testfällen und Zuordnung zu Anforderungen.<br>

Testmanager: Erstellen von Testläufen sowie Zuweisung von Testern und Testfällen.<br>

Tester: Einsehen der zugeordneten Testfälle und Erfassen der Testergebnisse.<br>

Verwendete Technologien<br>
Sprache: Java EE / Jakarta EE.<br>

Web-Framework: JSF (JavaServer Faces) mit PrimeFaces für die Benutzeroberfläche.<br>

Persistenz: JPA mit Hibernate als Persistenz-Provider.<br>

Datenbank: MySQL.<br>

Architektur: Model-View-Controller (MVC).<br>

Installation & Setup<br>
Datenbank: Erstelle eine leere MySQL-Datenbank mit dem Namen require4testing.<br>

Konfiguration: Die Zugangsdaten zur Datenbank befinden sich in der Datei persistence.xml.<br>

Automatisches Schema: Die Datenbanktabellen werden beim ersten Start automatisch durch Hibernate erstellt.<br>

Server: Das Projekt kann auf einem gängigen Application Server (z. B. WildFly oder GlassFish) als .war-Datei deployed werden.<br><br>

Bedienung des Prototyps<br>
Da es sich um einen Prototyp handelt, wurde auf ein Login-System verzichtet.<br>

Über das Dropdown-Menü "Ansicht wechseln" im Kopfbereich der Seite kann direkt zwischen den Rollen gewechselt werden, um die Funktionen zu testen.<br>

In den Tabellen können Daten direkt bearbeitet oder über die Schaltflächen hinzugefügt und gelöscht werden.<br>
