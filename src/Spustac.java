import java.io.IOException;

/**
 * Trieda Spustac
 * tato trieda ma na starosti spustenie kodu okna a hry
 */
public class Spustac {
    private static Okno okno;
    private static Hra hra;

    /**
     * metoda main vytvara novu instanciu okna a hry<br>
     * pridava instanciu hry do instacie okna<br>
     * a zapne Thread ktory ovlada celu hru
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        okno = new Okno();
        hra = new Hra();
        okno.add(hra);
        hra.startHernyThread();
        okno.pack();
    }
}