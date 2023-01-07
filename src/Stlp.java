import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.TexturePaint;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda Stlp
 * tato trieda ma na starosti vytvorenie a vykreslenie stlpov
 */
public class Stlp {

    private int medzera;
    private final int sirka;
    private final ArrayList<Rectangle> stlpy;

    private final BufferedImage fotkastlp;

    /**
     * Konstruktor triedy Stlp<br>
     * nacitava obrazok stlpu
     * @throws IOException
     */
    public Stlp() throws IOException {

        this.medzera = 400;
        this.sirka = 100;
        this.stlpy = new ArrayList<Rectangle>();
        this.fotkastlp = ImageIO.read(getClass().getResourceAsStream("images/stlp.png"));
    }

    /**
     * pridava stlpy do ArrayListu<br>
     * pomocou booleanu naZaciatku sa odlisuje to<br>
     * ci su to stlpy pridavane na zaciatku hry alebo neskor pocas hrania<br>
     * @param naZaciatku boolean ktory meni poziciu vytvorenia stlpov
     */
    public void pridajStlp(boolean naZaciatku) {
        Random rand = new Random();
        int vyska = 50 + rand.nextInt(200);

        if (naZaciatku) {
            this.stlpy.add(new Rectangle(Okno.getWIDTH() + this.sirka + this.stlpy.size() * 300, Okno.getHEIGTH() - vyska - 150, this.sirka, vyska));
            this.stlpy.add(new Rectangle(Okno.getWIDTH() + this.sirka + (this.stlpy.size() - 1) * 300, 0, this.sirka, Okno.getHEIGTH() - vyska - this.medzera));
        } else {
            this.stlpy.add(new Rectangle(this.stlpy.get(this.stlpy.size() - 1).x + 600, Okno.getHEIGTH() - vyska - 150, this.sirka, vyska));
            this.stlpy.add(new Rectangle(this.stlpy.get(this.stlpy.size() - 1).x, 0, this.sirka, Okno.getHEIGTH() - vyska - this.medzera));
        }
    }

    /**
     * Vykresluje parameter stlpy do okna<br>
     * vyuziva sa tu "TexturePaint" na nekonecne opakovanie maleho obrazku vo vacsom objekte<br>
     * obrazok je ohraniceny zelenym okrajom
     */
    public void vykresliStlp(Graphics2D g2d, Rectangle stlpy) {
        TexturePaint tp = new TexturePaint(this.fotkastlp, new Rectangle(0, 0, 24, 24));
        g2d.setPaint(tp);
        g2d.fillRect(stlpy.x, stlpy.y, stlpy.width, stlpy.height);
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.green.darker());
        g2d.drawRect(stlpy.x, stlpy.y, stlpy.width, stlpy.height);
    }

    /**
     * setter na medzeru medzi dvojicou stlpov
     * @param hodnota na ktoru sa nastavi medzera
     */
    public void setMedzera(int hodnota) {
        this.medzera = hodnota;
    }

    /**
     * getter na medzeru medzi dvojicou stlpov
     * @return int this.medzera
     */
    public int getMedzera() {
        return this.medzera;
    }

    /**
     * getter na ArrayList stlpov
     * @return Arraylist
     */
    public ArrayList<Rectangle> getStlpy() {
        return this.stlpy;
    }

}
