Require4Testing - Prototyp für das Testmanagement
Dies ist ein im Rahmen einer Fallstudie entwickelter Prototyp zur Verwaltung von Anforderungen, Testfällen und Testläufen. Das Ziel des Projekts ist es, die Workflows zwischen verschiedenen Rollen in einem Testprozess abzubilden.

Funktionen & Rollen
Das System bietet für jeden Akteur eine eigene Ansicht:

Requirements Engineer: Erstellen und Verwalten von Anforderungen.

Testfallersteller: Anlegen von Testfällen und Zuordnung zu Anforderungen.

Testmanager: Erstellen von Testläufen sowie Zuweisung von Testern und Testfällen.

Tester: Einsehen der zugeordneten Testfälle und Erfassen der Testergebnisse.

Verwendete Technologien
Sprache: Java EE / Jakarta EE.

Web-Framework: JSF (JavaServer Faces) mit PrimeFaces für die Benutzeroberfläche.

Persistenz: JPA mit Hibernate als Persistenz-Provider.

Datenbank: MySQL.

Architektur: Model-View-Controller (MVC).

Installation & Setup
Datenbank: Erstelle eine leere MySQL-Datenbank mit dem Namen require4testing.

Konfiguration: Die Zugangsdaten zur Datenbank befinden sich in der Datei persistence.xml.

Automatisches Schema: Die Datenbanktabellen werden beim ersten Start automatisch durch Hibernate erstellt (Einstellung: hbm2ddl.auto).

Server: Das Projekt kann auf einem gängigen Application Server (z. B. WildFly oder GlassFish) als .war-Datei deployed werden.

Bedienung des Prototyps
Da es sich um einen Prototyp handelt, wurde auf ein Login-System verzichtet.

Über das Dropdown-Menü "Ansicht wechseln" im Kopfbereich der Seite kann direkt zwischen den Rollen gewechselt werden, um die Funktionen zu testen.

In den Tabellen können Daten direkt bearbeitet oder über die Schaltflächen hinzugefügt und gelöscht werden.
