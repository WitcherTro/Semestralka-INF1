import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Vtak {
    private Rectangle vtak;
    private BufferedImage vtakfotka;

    public Vtak() throws IOException {
        this.vtak = new Rectangle(Okno.getWIDTH() / 2, Okno.getHEIGTH() / 2, 20, 20);
        this.vtakfotka = ImageIO.read(getClass().getResourceAsStream("images/vtak.png"));
    }

    public void vykresliVtaka(Graphics2D g2d) {
        //g2d.setPaint(Color.red);
        //g2d.fillRect(this.vtak.x, this.vtak.y, this.vtak.width, this.vtak.height);
        g2d.drawImage(this.vtakfotka, this.vtak.x, this.vtak.y, this.vtak.width, this.vtak.height, null);
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
