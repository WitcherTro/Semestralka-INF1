import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Trieda SuborHighScore
 * tato trieda ma na starosti tvorbu textoveho suboru, zapisovanie a citanie z neho int hodnotu HighScore
 */
public class SuborHighScore {

    private File subor;

    /**
     * Konstruktor nacitava subor "highscore.txt"<br>
     * v pripade ze subor neexistuje tak ho vytvori a zapise do neho "0"
     * @throws IOException
     */
    public SuborHighScore() throws IOException {
        this.subor = new File("highscore.txt");

        if (this.subor.createNewFile()) {
            FileWriter zapisovac = new FileWriter("highscore.txt");
            zapisovac.write("0");
            zapisovac.close();
        }
    }

    /**
     * getter ziskava hodnotu zo suboru
     * @return int hodnota v subore
     */
    public int getHighScore() throws FileNotFoundException {
        Scanner citac = new Scanner(this.subor);
        int highscore = Integer.parseInt(citac.nextLine());
        citac.close();
        return highscore;
    }

    /**
     * setter zapisuje hodnotu do suboru
     * @param skore hodnota ktora sa ma zapisat do suboru
     * @throws IOException
     */
    public void setHighScore(int skore) throws IOException {
        String textskore = Integer.toString(skore);
        FileWriter zapisovac = new FileWriter("highscore.txt");
        zapisovac.write(textskore);
        zapisovac.close();
    }
}
