import javax.swing.JFrame;
import java.awt.*;

public class Okno extends JFrame{
    private static final int WIDTH = 800, HEIGTH = 800;

    Okno() {
        this.setTitle("Vtaaaak");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGTH);
        this.setResizable(false);
        this.setVisible(true);
        //jframe.add(renderer);
        //jframe.addMouseListener(this);
        //jframe.addKeyListener(this);


    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGTH() {
        return HEIGTH;
    }


}
