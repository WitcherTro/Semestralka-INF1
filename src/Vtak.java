import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Trieda Vtak<br>
 * tato trieda ma na starosti vytvorenie, vykreslenie a poloha vtaka
 */
public class Vtak {
    private Rectangle vtak;
    private BufferedImage vtakfotka;

    /**
     * Konstruktor vytvara stvorec vtaka a importuje fotku vtaka
     * @throws IOException
     */
    public Vtak() throws IOException {
        this.vtak = new Rectangle(Okno.getWIDTH() / 2, Okno.getHEIGTH() / 2, 20, 20);
        this.vtakfotka = ImageIO.read(getClass().getResourceAsStream("images/vtak.png"));
    }

    /**
     * vykresluje fotku vtaka na stvorec
     */
    public void vykresliVtaka(Graphics2D g2d) {
        g2d.drawImage(this.vtakfotka, this.vtak.x, this.vtak.y, this.vtak.width, this.vtak.height, null);
    }

    /**
     * setter pre x poziciu stvorca
     * @param i hodnota na ktoru sa pozicia nastavi
     */
    public void setVtakX(int i) {
        this.vtak.x = i;
    }
    /**
     * setter pre y poziciu stvorca
     * @param i hodnota na ktoru sa pozicia nastavi
     */
    public void setVtakY(int i) {
        this.vtak.y = i;
    }

    /**
     * getter pre stvorec(Rectangle) vtaka
     * @return Rectangle vtaka
     */
    public Rectangle getVtak() {
        return this.vtak;
    }
}
