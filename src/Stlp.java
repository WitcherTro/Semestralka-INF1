import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Stlp {

    private Random rand;
    private int medzera,sirka,vyska;
    private ArrayList<Rectangle> stlpy;

    public Stlp() {

        this.medzera = 300;
        this.sirka = 100;
        this.stlpy = new ArrayList<Rectangle>();
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
        g2d.setColor(Color.GREEN.darker());
        g2d.fillRect(stlpy.x, stlpy.y, stlpy.width, stlpy.height);
    }

    public ArrayList<Rectangle> getStlpy() {
        return this.stlpy;
    }

}
