import java.io.IOException;

public class Spustac {
    private static Okno okno;
    private static Hra hra;
    public static void main(String[] args) throws IOException {
        okno = new Okno();
        hra = new Hra();
        okno.add(hra);
        hra.startHernyThread();
        okno.pack();
    }
}