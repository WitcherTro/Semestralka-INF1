import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Hra extends JPanel implements Runnable {
    private final Stlp stlp;
    private Vtak vtak;
    private final Pozadie pozadie;
    private boolean gameOver;
    private boolean started;
    private boolean paused;
    private int fpscounter;
    private int skore;
    private int highskore;
    private double yPohyb;
    private static final int FPS = 60;
    private Thread hernyThread;
    private final Action skok;
    private final Action navratDoMenu;
    private final Action pauza;
    private final Action resetSkore;
    private final Action zmenaObtiaznosti;
    private SuborHighScore suborSkore;
    private int moznostObtiaznosti;

    public Hra() throws IOException {
        this.setPreferredSize(new Dimension(Okno.getWIDTH(), Okno.getHEIGTH()));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.skok = new Skok();
        this.navratDoMenu = new NavratDoMenu();
        this.pauza = new Pauza();
        this.suborSkore = new SuborHighScore();
        this.resetSkore = new ResetSkore();
        this.highskore = this.suborSkore.getHighScore();
        this.zmenaObtiaznosti = new ZmenaObtiaznosti();



        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "skok");
        this.getActionMap().put("skok", this.skok);
        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), "Pauza");
        this.getActionMap().put("Pauza", this.pauza);
        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "navratDoMenu");
        this.getActionMap().put("navratDoMenu", this.navratDoMenu);
        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "resetSkore");
        this.getActionMap().put("resetSkore", this.resetSkore);
        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "zmenaObtiaznosti");
        this.getActionMap().put("zmenaObtiaznosti", this.zmenaObtiaznosti);


        this.pozadie = new Pozadie();
        this.vtak = new Vtak();
        this.stlp = new Stlp();

        this.stlp.pridajStlp(true);
        this.stlp.pridajStlp(true);
        this.stlp.pridajStlp(true);
        this.stlp.pridajStlp(true);

    }
    public void jump() throws IOException {

        if (this.gameOver) {
            this.vtak = new Vtak();
            this.stlp.getStlpy().clear();
            this.yPohyb = 0;
            this.skore = 0;
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.gameOver = false;
        }
        if (!this.started) {
            this.started = true;
            this.paused = false;
        } else if (!this.gameOver) {
            if (this.yPohyb > 0) {
                this.yPohyb = 0;
            }
            this.yPohyb -= 10;
        }
    }

    public void pause() {
        if (this.started && !this.gameOver) {
            this.started = false;
            this.paused = true;
        } else if (this.paused && !this.gameOver) {
            this.started = true;
            this.paused = false;
        }
    }
    public void menu() throws IOException {
        if (this.gameOver && !this.paused) {
            this.started = false;
            this.vtak = new Vtak();
            this.stlp.getStlpy().clear();
            this.yPohyb = 0;
            this.skore = 0;
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.gameOver = false;
        }
    }
    public void resetskore() throws IOException {
        this.highskore = 0;
        this.suborSkore.setHighScore(this.highskore);
    }

    public void startHernyThread() {
        this.hernyThread = new Thread(this);
        this.hernyThread.start();
    }
    public void zmenObtiaznost() {
        if (!this.started) {
            this.stlp.getStlpy().clear();
            switch (this.stlp.getMedzera()) {
                case 400:
                    this.stlp.setMedzera(320);
                    break;
                case 320:
                    this.stlp.setMedzera(280);
                    break;
                case 280:
                    this.stlp.setMedzera(400);
                    break;
            }
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
        }
    }

    @Override
    public void run() {

        double vykreslovaciInterval = 1000000000 / FPS;
        double delta = 0;
        long minulyCas = System.nanoTime();
        long terajsiCas;
        long casovac = 0;
        int pocetvykresleni = 0;

        while (this.hernyThread != null) {
            terajsiCas = System.nanoTime();

            delta += (terajsiCas - minulyCas) / vykreslovaciInterval;
            casovac += (terajsiCas - minulyCas);
            minulyCas = terajsiCas;

            if (delta >= 1) {
                try {
                    this.update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
                pocetvykresleni++;
            }

            if (casovac >= 1000000000) {
                this.fpscounter = pocetvykresleni;
                pocetvykresleni = 0;
                casovac = 0;
            }
        }
    }


    public void update() throws IOException {
        int rychlost = 6;
        if (this.started) {
            //pohyb stlpov
            for (int i = 0; i < this.stlp.getStlpy().size(); i++) {
                Rectangle stlpS = this.stlp.getStlpy().get(i);
                stlpS.x -= rychlost;
            }
            //yPohyb
            if (this.yPohyb < 15) {
                this.yPohyb += 0.7;
            }
            //vymazanie stlpu ked zmizne za obrazovku
            for (int i = 0; i < this.stlp.getStlpy().size(); i++) {
                Rectangle stlpS = this.stlp.getStlpy().get(i);

                if (stlpS.x + stlpS.width < 0) {
                    this.stlp.getStlpy().remove(stlpS);
                    if (stlpS.y == 0) {
                        this.stlp.pridajStlp(false);
                    }
                }
            }
            this.vtak.setVtakY((int)(this.vtak.getVtak().y + this.yPohyb));

            //akcie so stlpmi
            for (Rectangle stlpS : this.stlp.getStlpy()) {
                int i = 0;
                //prida skore ak vtak preleti cez stlpy
                if (stlpS.y == 0 && this.vtak.getVtak().x + this.vtak.getVtak().width / 2 > stlpS.x + stlpS.width / 2 - 1
                        && this.vtak.getVtak().x + this.vtak.getVtak().width / 2 < stlpS.x + stlpS.width / 2 + 1) {
                    this.skore++;


                }
                //gameover ak vtak sa trafi so stlpom
                if (stlpS.intersects(this.vtak.getVtak())) {
                    this.gameOver = true;
                    //stlp zoberie vtaka za obrazovku
                    if (this.vtak.getVtak().x <= stlpS.x) {
                        this.vtak.setVtakX(stlpS.x - this.vtak.getVtak().width);
                    } else {
                        //kod aby vtak nevyletel do dolneho stlpu
                        if (stlpS.y != 0) {
                            this.vtak.setVtakY(this.vtak.getVtak().y - this.vtak.getVtak().height + 10);
                            //kod aby vtak nespadol do horneho stlpu
                        } else if (this.vtak.getVtak().y <= stlpS.height) {
                            this.vtak.setVtakY(stlpS.height);
                        }
                    }
                }
            }
            if (!this.gameOver && this.highskore < this.skore) {
                this.highskore = this.skore;
                this.suborSkore.setHighScore(this.highskore);
            }
            //gamover ak vtak trafi zem alebo vyleti nad hernu plochu
            if (this.vtak.getVtak().y > Okno.getHEIGTH() - this.vtak.getVtak().height - 170 || this.vtak.getVtak().y < 0) {
                this.gameOver = true;
                this.paused = false;
            }
            //vtak nespadne pod hernu plochu
            if (this.vtak.getVtak().y + this.yPohyb >= Okno.getHEIGTH() - 150) {
                this.vtak.getVtak().y = Okno.getHEIGTH() - 150 - this.vtak.getVtak().height;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        this.pozadie.vykresliPozadie(g2d);
        for (Rectangle stlpy : this.stlp.getStlpy()) {
            this.stlp.vykresliStlp(g2d, stlpy);
        }
        this.vtak.vykresliVtaka(g2d);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 0, 100));

        if (!this.started && !this.paused) {
            g.drawString("Začni hrať!", 150, Okno.getHEIGTH() / 2 - 50);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač medzerník.", 300, Okno.getHEIGTH() / 2 - 10);
            g.drawString("Stlač R pre vynulovanie najvyššieho skóre", 100, Okno.getHEIGTH() - 25);
            g.drawString("Stlač P pre zmenenie obtiažnosti", 120, Okno.getHEIGTH() - 70);
            g.setFont(new Font("Arial", 0, 15));
            g.drawString("Verzia 0.8.1", Okno.getWIDTH() - 80, Okno.getHEIGTH() - 5);
            g.setFont(new Font("Arial", 0, 20));
            switch (this.stlp.getMedzera()) {
                case 400:
                    g.drawString("Obtiažnosť: Ľahká", Okno.getWIDTH() - 230, Okno.getHEIGTH() - 70);
                    break;
                case 320:
                    g.drawString("Obtiažnosť: Stredná", Okno.getWIDTH() - 230, Okno.getHEIGTH() - 70);
                    break;
                case 280:
                    g.drawString("Obtiažnosť: Ťažká", Okno.getWIDTH() - 230, Okno.getHEIGTH() - 70);
                    break;
            }
        }

        if (this.gameOver) {
            g.drawString("Koniec hry!", 150, Okno.getHEIGTH() / 2 - 60);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač medzerník aby si znova začal hrat", 140, Okno.getHEIGTH() / 2 - 10);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač ESC pre vratenie na uvodnu obrazovku", 100, Okno.getHEIGTH() - 60);
            g.drawString("Stlač R pre vynulovanie najvyššieho skóre", 100, Okno.getHEIGTH() - 25);


        }
        if (!this.gameOver && this.started) {
            if (this.skore < 10) {
                g.drawString(String.valueOf(this.skore), Okno.getWIDTH() / 2 - 25, 150);
            } else {
                g.drawString(String.valueOf(this.skore), Okno.getWIDTH() / 2 - 50, 150);
            }
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač T pre pozastavenie hry", 200, Okno.getHEIGTH() - 60);
            g.drawString("Stlač R pre vynulovanie najvyššieho skóre", 100, Okno.getHEIGTH() - 25);
        }
        if (this.gameOver || this.started || !this.started) {
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Najvyššie skore: " + String.valueOf(this.highskore), Okno.getWIDTH() / 2 - 130, 30);
            g.setFont(new Font("Arial", 0, 10));
            g.setColor(Color.black);
            g.drawString("FPS: " + String.valueOf(this.fpscounter), Okno.getWIDTH() - 38, 10);
        }
        if (this.paused && !this.started && !this.gameOver) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 0, 80));
            g.drawString("Pozastavená hra", 100, Okno.getHEIGTH() / 2);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Pre pokračovanie stlač T alebo medzernik", 125, Okno.getHEIGTH() / 2 + 30);
            g.drawString("Stlač R pre vynulovanie najvyššieho skóre", 100, Okno.getHEIGTH() - 25);
            g.setFont(new Font("Arial", 0, 100));
            if (this.skore < 10) {
                g.drawString(String.valueOf(this.skore), Okno.getWIDTH() / 2 - 25, 150);
            } else {
                g.drawString(String.valueOf(this.skore), Okno.getWIDTH() / 2 - 50, 150);
            }
        }
    }

    public class Skok extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Hra.this.jump();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class NavratDoMenu extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Hra.this.menu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class Pauza extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Hra.this.pause();
        }
    }
    public class ResetSkore extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Hra.this.resetskore();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class ZmenaObtiaznosti extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Hra.this.zmenObtiaznost();
        }
    }
}

