import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Stlp {

    private Random rand;
    private int medzera;
    private int sirka;
    private int vyska;
    private ArrayList<Rectangle> stlpy;

    private BufferedImage fotkastlp;

    public Stlp() throws IOException {

        this.medzera = 320;
        this.sirka = 100;
        this.stlpy = new ArrayList<Rectangle>();
        this.fotkastlp = ImageIO.read(getClass().getResourceAsStream("images/stlp.png"));
    }

    public void pridajStlp(boolean start) {
        this.rand = new Random();
        this.vyska = 50 + this.rand.nextInt(200);

        if (start) {
            this.stlpy.add(new Rectangle(Okno.getWIDTH() + this.sirka + this.stlpy.size() * 300, Okno.getHEIGTH() - this.vyska - 150, this.sirka, this.vyska));
            this.stlpy.add(new Rectangle(Okno.getWIDTH() + this.sirka + (this.stlpy.size() - 1) * 300, 0, this.sirka, Okno.getHEIGTH() - this.vyska - this.medzera));
        } else {
            this.stlpy.add(new Rectangle(this.stlpy.get(this.stlpy.size() - 1).x + 600, Okno.getHEIGTH() - this.vyska - 150, this.sirka, this.vyska));
            this.stlpy.add(new Rectangle(this.stlpy.get(this.stlpy.size() - 1).x, 0, this.sirka, Okno.getHEIGTH() - this.vyska - this.medzera));
        }
    }

    public void vykresliStlp(Graphics2D g2d, Rectangle stlpy) {

        TexturePaint tp = new TexturePaint(this.fotkastlp, new Rectangle(0, 0, 24, 24));
        g2d.setPaint(tp);
        //g2d.setColor(Color.green);
        g2d.fillRect(stlpy.x, stlpy.y, stlpy.width, stlpy.height);
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.green.darker());
        g2d.drawRect(stlpy.x, stlpy.y, stlpy.width, stlpy.height);
    }
    public void setMedzera(int hodnota) {
        this.medzera = hodnota;
    }

    public int getMedzera() {
        return this.medzera;
    }

    public ArrayList<Rectangle> getStlpy() {
        return this.stlpy;
    }

}
