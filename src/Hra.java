import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Hra extends JPanel implements Runnable {
    private Stlp stlp;
    private Vtak vtak;
    private Pozadie pozadie;
    private boolean gameOver, started, paused;
    private int fpscounter, skore, highskore;
    private double yPohyb;
    private static final int FPS = 60;
    Thread hernyThread;
    Action skok;
    Action navratDoMenu;
    Action pauza;
    public Hra() {
        this.setPreferredSize(new Dimension(Okno.getWIDTH(), Okno.getHEIGTH()));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        skok = new skok();
        navratDoMenu = new navratDoMenu();
        pauza = new pauza();


        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),"skok");
        this.getActionMap().put("skok",skok);
        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0),"Pauza");
        this.getActionMap().put("Pauza",pauza);
        this.getInputMap(this.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"navratDoMenu");
        this.getActionMap().put("navratDoMenu", navratDoMenu);

        this.pozadie = new Pozadie();
        this.vtak = new Vtak();
        this.stlp = new Stlp();
        this.fpscounter = fpscounter;

        this.stlp.pridajStlp(true);
        this.stlp.pridajStlp(true);
        this.stlp.pridajStlp(true);
        this.stlp.pridajStlp(true);
    }
    public void jump() {

        if (gameOver) {
            this.vtak = new Vtak();
            this.stlp.getStlpy().clear();
            yPohyb = 0;
            skore = 0;
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            gameOver = false;
        }
        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yPohyb > 0) {
                yPohyb = 0;
            }
            yPohyb -= 10;
        }
    }

    public void pause() {
        if (started && !gameOver) {
            started = false;
            paused = true;
        } else if (paused && !gameOver) {
            started = true;
        }
    }
    public void menu() {
        if (gameOver) {
            started = false;
            this.vtak = new Vtak();
            this.stlp.getStlpy().clear();
            yPohyb = 0;
            skore = 0;
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            this.stlp.pridajStlp(true);
            gameOver = false;
        }
    }

    public void startHernyThread() {
        hernyThread = new Thread(this);
        hernyThread.start();
    }

    @Override
    public void run() {

        double vykreslovaciInterval = 1000000000 / FPS;
        double delta = 0;
        long minulyCas = System.nanoTime();
        long terajsiCas;
        long casovac = 0;
        int pocetvykresleni = 0;

        while (hernyThread != null) {
            terajsiCas = System.nanoTime();

            delta += (terajsiCas - minulyCas) / vykreslovaciInterval;
            casovac += (terajsiCas - minulyCas);
            minulyCas = terajsiCas;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                pocetvykresleni++;
            }

            if (casovac >= 1000000000) {
                //System.out.println("FPS: " + pocetvykresleni);
                fpscounter = pocetvykresleni;
                pocetvykresleni = 0;
                casovac = 0;
            }
        }
    }


    public void update() {
        int rychlost = 6;
        if (started) {
            //pohyb stlpov
            for (int i = 0; i < this.stlp.getStlpy().size(); i++) {
                Rectangle stlp = this.stlp.getStlpy().get(i);
                stlp.x -= rychlost;
            }
            //yPohyb
            if (yPohyb < 15) {
                yPohyb += 0.7;
            }
            //vymazanie stlpu ked zmizne za obrazovku
            for (int i = 0; i < this.stlp.getStlpy().size(); i++) {
                Rectangle stlp = this.stlp.getStlpy().get(i);

                if (stlp.x + stlp.width < 0) {
                    this.stlp.getStlpy().remove(stlp);
                    if (stlp.y == 0) {
                        this.stlp.pridajStlp(false);
                    }
                }
            }
            this.vtak.setVtakY((int) (this.vtak.getVtak().y + yPohyb));

            //akcie so stlpmi
            for (Rectangle stlp : this.stlp.getStlpy()) {
                int i = 0;
                //prida skore ak vtak preleti cez stlpy
                if (stlp.y == 0 && this.vtak.getVtak().x + this.vtak.getVtak().width / 2 > stlp.x + stlp.width / 2 - 1
                        && this.vtak.getVtak().x + this.vtak.getVtak().width / 2 < stlp.x + stlp.width / 2 + 1) {
                    skore++;


                }
                //gameover ak vtak sa trafi so stlpom
                if (stlp.intersects(this.vtak.getVtak())) {
                    gameOver = true;
                    //stlp zoberie vtaka za obrazovku
                    if (this.vtak.getVtak().x <= stlp.x) {
                        this.vtak.setVtakX(stlp.x - this.vtak.getVtak().width);
                    } else {
                        //kod aby vtak nevyletel do horneho stlpu
                        if (stlp.y != 0) {
                            this.vtak.setVtakY(this.vtak.getVtak().y - this.vtak.getVtak().height);
                            //kod aby vtak nespadol do spodneho stlpu
                        } else if (this.vtak.getVtak().y < stlp.height) {
                            this.vtak.setVtakY(stlp.height);
                        }
                    }
                }
            }
            if (!gameOver && highskore < skore) {
                highskore = skore;
            }
            //gamover ak vtak trafi zem alebo vyleti nad hernu plochu
            if (this.vtak.getVtak().y > Okno.getHEIGTH() - this.vtak.getVtak().height - 170 || this.vtak.getVtak().y < 0) {
                gameOver = true;
            }
            //vtak nespadne pod hernu plochu
            if (this.vtak.getVtak().y + yPohyb >= Okno.getHEIGTH() - 150) {
                this.vtak.getVtak().y = Okno.getHEIGTH() - 150 - this.vtak.getVtak().height;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        this.pozadie.vykresliPozadie(g2d);

        for (Rectangle stlpy : this.stlp.getStlpy()) {
            this.stlp.vykresliStlp(g2d, stlpy);
        }

        this.vtak.vykresliVtaka(g2d);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 0, 100));

        if (!started && !paused) {
            g.drawString("Začni hrať!", 150, Okno.getHEIGTH()/2 - 50);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač medzerník.", 300, Okno.getHEIGTH()/2 - 10);
            //g.drawString("Stlač R aby si vymazal najvyššie skore.", 125, Okno.getHEIGTH() - 60);

            g.setFont(new Font("Arial", 0, 15));
            g.drawString("Verzia 0.6", Okno.getWIDTH() - 100, Okno.getHEIGTH() - 50);

        }

        if (gameOver) {
            g.drawString("Koniec hry!", 150, Okno.getHEIGTH()/2 - 60);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač medzerník aby si znova začal hrat", 140, Okno.getHEIGTH()/2 - 10);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač ESC pre vratenie na uvodnu obrazovku", 120, Okno.getHEIGTH() - 60);


        }
        if (!gameOver && started) {
            if (skore < 10) {
                g.drawString(String.valueOf(skore), Okno.getWIDTH() / 2 - 25, 150);
            } else {
                g.drawString(String.valueOf(skore), Okno.getWIDTH() / 2 - 50, 150);
            }
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Stlač T pre pozastavenie hry", 200, Okno.getHEIGTH() - 60);
        }
        if (gameOver || started || !started) {
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Najvyššie skore: " + String.valueOf(highskore), Okno.getWIDTH() / 2 - 130, 30);
            g.setFont(new Font("Arial", 0, 10));
            g.setColor(Color.black);
            g.drawString("FPS: " + String.valueOf(fpscounter), Okno.getWIDTH() - 38, 10);
        }
        if (paused && !started) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 0, 80));
            g.drawString("Pozastavená hra", 100, Okno.getHEIGTH()/2);
            g.setFont(new Font("Arial", 0, 30));
            g.drawString("Pre pokračovanie stlač ESC alebo medzernik", 105, Okno.getHEIGTH()/2 + 30);
            g.setFont(new Font("Arial", 0, 100));
            if (skore < 10) {
                g.drawString(String.valueOf(skore), Okno.getWIDTH() / 2 - 25, 150);
            } else {
                g.drawString(String.valueOf(skore), Okno.getWIDTH() / 2 - 50, 150);
            }

        }
        g2d.dispose();
    }

    public class skok extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            jump();
            System.out.println("skok");
        }
    }
    public class navratDoMenu extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            menu();
        }
    }
    public class pauza extends AbstractAction{
    @Override
        public void actionPerformed(ActionEvent e) {
        pause();
        }
    }
}

