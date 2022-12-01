import java.awt.*;

public class Pozadie {

    public Pozadie(){}
    public void vykresliPozadie(Graphics2D g2d) {
        g2d.setPaint(Color.cyan);
        g2d.fillRect(0,0,800,800);
        g2d.setPaint(Color.orange.darker());
        g2d.fillRect(0,800-110,800,110);
        g2d.setPaint(Color.green);
        g2d.fillRect(0,800-150,800,40);
    }
}
