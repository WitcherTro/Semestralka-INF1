import java.io.IOException;

public class Spustac {
    private static Okno okno;
    private static Hra hra;

    /**
     * metoda main vytvara novu instanciu okna a hry
     * pridava instanciu hry do instacie okna
     * a zapne Thread ktory ovlada celu hru
     */
    public static void main(String[] args) throws IOException {
        okno = new Okno();
        hra = new Hra();
        okno.add(hra);
        hra.startHernyThread();
        okno.pack();
    }
}