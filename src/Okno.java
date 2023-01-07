import javax.swing.JFrame;

/**
 * Trieda Okno<br>
 * tato trieda vytvara okno v ktorom sa bude cela hra zobrazovat<br><br>
 *
 * vyuzite videa:
 * https://www.youtube.com/watch?v=Kmgo00avvEw
 */
public class Okno extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGTH = 800;

    /**
     * Kontruktor vytvara okno a nastavuje jeho moznosti
     */
    public Okno() {
        this.setTitle("Leť a netraf");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGTH);
        this.setResizable(false);
        this.setVisible(true);

    }

    /**
     * Getter pre sirku okna
     * @return int sirka okna
     */
    public static int getWIDTH() {
        return WIDTH;
    }
    /**
     * Getter pre vysku okna
     * @return int vyska okna
     */
    public static int getHEIGTH() {
        return HEIGTH;
    }


}
