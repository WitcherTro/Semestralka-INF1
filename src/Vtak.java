import java.awt.*;

public class Vtak {
    private Rectangle vtak;

    public Vtak() {
        this.vtak = new Rectangle(Okno.getWIDTH()/2, Okno.getHEIGTH()/2, 20, 20);
    }

    public void vykresliVtaka(Graphics2D g2d) {
        g2d.setPaint(Color.red);
        g2d.fillRect(this.vtak.x, this.vtak.y, this.vtak.width, this.vtak.height);
    }
    public void setVtakX(int i) {
        this.vtak.x = i;
    }

    public void setVtakY(int i) {
        this.vtak.y = i;
    }
    public Rectangle getVtak() {
        return this.vtak;
    }
}
