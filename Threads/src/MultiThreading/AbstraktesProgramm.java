
package MultiThreading;

// Deklaration einer abstrakten Klasse, die das Runnable Interface implementiert.
public abstract class AbstraktesProgramm implements Runnable {
    // Geschützte Variablen, die für die Identifizierung und Kontrolle des Algorithmus verwendet werden.
    protected int programmNummer;
    private Thread workerThread; // Thread-Instanz, in dem der Algorithmus ausgeführt wird.
    protected volatile boolean running = false; // Volatile für die korrekte Handhabung in Multithreading-Kontexten.
    protected String lastResult = ""; // Letztes Ergebnis des Algorithmus.

    // Listener für Ergebnisse, um Updates an das GUI zu senden.
    private final ErgebnisListener ergebnisListener;

    // Konstruktor der Klasse.
    public AbstraktesProgramm(int programmNummer, ErgebnisListener ergebnisListener) {
        this.programmNummer = programmNummer; // Zuweisung der Programnummer.
        this.ergebnisListener = ergebnisListener; // Zuweisung des ErgebnisListeners.
    }

    // Methode zum Starten des Algorithmus.
    public void starteAlgorithmus() {
        synchronized (this) {
            if (running) return; // Verhindert das mehrfache Starten des Threads.
            running = true; // Setzt den Status auf laufend.
        }
        workerThread = new Thread(this, "Algorithmus-Worker-" + programmNummer); // Thread mit benutzerdefiniertem Namen.
        workerThread.start(); // Startet den Thread und somit die Ausführung des Algorithmus.
    }

    // Methode zum Stoppen des Algorithmus.
    public void stoppeAlgorithmus() {
        synchronized (this) {
            if (!running) return; // Verhindert den Stopp, wenn der Algorithmus nicht läuft.
            running = false; // Setzt den Status auf nicht laufend.
        }
        workerThread.interrupt(); // Unterbricht den Thread.
    }

    // Abstrakte Methode, die den Algorithmus definiert und von Unterklassen implementiert werden muss.
    protected abstract String berechneAlgorithmus();

    // Die run-Methode des Runnable Interfaces, die in einem neuen Thread ausgeführt wird.
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) { // Führt eine Schleife aus, bis der Thread unterbrochen wird.
                String ergebnis = berechneAlgorithmus(); // Aufruf der abstrakten Methode.
                synchronized (this) {
                    if (!running) break; // Beendet die Schleife, wenn der Algorithmus gestoppt wurde.
                    lastResult = formatiereErgebnis(ergebnis); // Speichert das formatierte Ergebnis.
                }
                zeigeErgebnisAn(lastResult); // Anzeigen des Ergebnisses über den Listener.

                try {
                    Thread.sleep(250); // Verzögerung von 250 ms, ist notwendig sonst passieren schlimme Sachen.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Setzt das Unterbrechungsflag wieder, falls eine Unterbrechung eintritt.
                    break; // Beendet die Schleife, wenn eine Unterbrechung stattgefunden hat.
                }
            }
        } finally {
            // Abschlussnachricht, wenn der Thread endet.
            System.out.println("Tschüss Programm " + programmNummer + "!");
        }
    }

    // Methode zur Formatierung des Ergebnisses.
    protected String formatiereErgebnis(String ergebnis) {
        return String.format("Programm %d - Ergebnis: %s", programmNummer, ergebnis);
    }

    // Methode zum Anzeigen des Ergebnisses, ruft den ErgebnisListener auf, falls vorhanden.
    protected void zeigeErgebnisAn(String formatiertesErgebnis) {
        if (ergebnisListener != null) {
            ergebnisListener.updateAusgabe(programmNummer, formatiertesErgebnis);
        }
    }

    // Getter für die Programnummer.
    public int getProgrammNummer() {
        return programmNummer;
    }

    // Methode, um den laufenden Status des Programms zu überprüfen.
    public boolean isRunning() {
        return running;
    }

}

