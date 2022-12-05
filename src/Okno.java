import javax.swing.*;

public class Okno extends JFrame {
    private static final int WIDTH = 800, HEIGTH = 800;

    Okno() {
        this.setTitle("Semestralka");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGTH);
        this.setResizable(false);
        this.setVisible(true);

    }
    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGTH() {
        return HEIGTH;
    }


}
