import javax.swing.JFrame;
public class Okno extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGTH = 800;

    /**
     * Kontruktor vytvara okno a nastavuje jeho moznosti
     */
    public Okno() {
        this.setTitle("Semestralka");
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
