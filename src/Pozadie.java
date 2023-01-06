import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pozadie {
    private BufferedImage pozadiefotka;
    private BufferedImage zemfotka;
    private BufferedImage travafotka;

    /**
     * Konstruktor triedy Pozadie
     * nacitava obrazky vyuzite v pozadi
     * @throws IOException
     */
    public Pozadie() throws IOException {
        this.pozadiefotka = ImageIO.read(getClass().getResourceAsStream("images/pozadie.png"));
        this.zemfotka = ImageIO.read(getClass().getResourceAsStream("images/zem.png"));
        this.travafotka = ImageIO.read(getClass().getResourceAsStream("images/trava.png"));
    }

    /**
     * vykresluje pozadie na herne okno
     * vyuziva sa tu "TexturePaint" na nekonecne opakovanie maleho obrazku vo vacsom objekte
     */
    public void vykresliPozadie(Graphics2D g2d) {
        g2d.setPaint(Color.cyan);
        g2d.fillRect(0, 0, 800, 800);
        g2d.drawImage(this.pozadiefotka, 0, 0, 800, 300, null);
        TexturePaint zem = new TexturePaint(this.zemfotka, new Rectangle(0, 0, 64, 64));
        g2d.setPaint(zem);
        g2d.fillRect(0, Okno.getHEIGTH() - 110, 800, 110);
        TexturePaint trava = new TexturePaint(this.travafotka, new Rectangle(0, 0, 64, 64));
        g2d.setPaint(trava);
        g2d.fillRect(0, Okno.getHEIGTH() - 150, 800, 40);
    }
}
