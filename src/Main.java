import javax.swing.JPanel;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        //Menu menu = new Menu();
        Okno okno = new Okno();
        Hra hra = new Hra();
        okno.add(hra);
        okno.pack();
        hra.startHernyThread();

        //okno.add(menu);
    }
}