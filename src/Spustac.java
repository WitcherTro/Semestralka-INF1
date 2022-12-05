public class Spustac {
    private static Okno okno;
    private static Hra hra;
    public static void main(String[] args) {
        okno = new Okno();
        hra = new Hra();
        okno.add(hra);
        hra.startHernyThread();
        okno.pack();
    }
}