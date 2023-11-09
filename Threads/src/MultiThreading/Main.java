/* ------------------------------------------
 * ---Erstellt von Thomas Tannenberg, 2023---
 *-------------------------------------------
 * Nachdem ihr den Start-Button gedrückt und eure Daten eingegeben habt, maximiert bitte das Fenster.
 * Ihr könnt auch scrollen, um mehr Inhalte zu sehen.
 *
 * Dieses Programm verwendet Swing und JavaFX für die grafische Benutzeroberfläche.
 * Es wurde mit Java 17 LTS erstellt.
 *
 * Um die Anwendung in der CMD zu starten, navigiert zu dem Verzeichnis, in dem die Threads.jar-Datei gespeichert ist,
 * (Threads\out\artifacts\Threads_jar)
 *
 * und verwendet folgenden Befehl, denn Ihr müsst auf die JAvaFX Library verweisen (Threads\javafx\lib):
 * java.exe --module-path "Pfad/zu/der/Threads/javafx/lib --add-modules=javafx.controls,javafx.fxml -jar Threads.jar
 *
 * VORSICHT: Programm Beenden, bevor ihr wieder auf START drückt!
 * Sonst startet ihr immer wieder neue Threads!
 *
 * -------------------------------------------
 * ----------------Anmerkungen----------------
 * -------------------------------------------
 *
 * Die Entwicklung dieses Programms hat mehrere Iterationen durchlaufen
 * und wurde insgesamt Drei mal von null neu geschrieben.
 * Es war schwierig eine finale und funktionierende Programmlogik zu erstellen die alle Threads
 * und Ausgaben der Ergebnisse korrekt ausführen kann.
 * Oft haben sich die Threads und Methoden gegenseitig blockiert!
 * Das Debug schreiben und herausfinden was überhaupt los ist war sehr anstrengend.
 * Jetzt da es aber funktioniert, bin ich selbst überrascht wie einfach es ist Programme für meine
 * kleine "API" zu schreiben
 *
 * -------------------------------------------
 * --------------Funktionsweise---------------
 * -------------------------------------------
 *
 * Beim Ausführen des Programms wird für jedes Unterprogramm ein eigener Thread erstellt.
 * Die Programme führen Berechnungen durch (wie die Ermittlung von Schaltjahren, Primzahl Faktoren etc.)
 * und aktualisieren die GUI mit ihren Ergebnissen in Echtzeit.
 * Dies demonstriert die Verwendung von Multithreading in Java, wobei jeder Thread unabhängig voneinander arbeitet
 * und die Ergebnisse über ein Callback-Interface (ErgebnisListener) an die GUI sendet.
 *
 * Benutzerinteraktionen wie das Starten und Beenden von Programmen werden über Schaltflächen in der GUI gemanagt.
 * Die GUI-Klasse aktualisiert dynamisch die Labels mit den Ergebnissen der laufenden Programme.
 *
 * -------------------------------------------
 * ------------ Arbeitsstunden: 30------------
 * -------------------------------------------
 *
 * !!!  internal scream !!!⠀⠀
 * still_screaming(loud)
 * cant_stop(still_screaming(very_loud))
 *
 * ⠀⠀⠀
 *      Und ja, die GUI Oberfläche ist nicht toll
 *      ⠀⠀⠀⠀⠀⠀⠀Ich mag kein Frontend⠀⠀
 * ⠀⠀⠀⠀
 */

package MultiThreading;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GUI());

    }
}
