import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SuborHighScore {

    private File subor;

    public SuborHighScore() throws IOException {
        this.subor = new File("highscore.txt");

        if (this.subor.createNewFile()) {
            FileWriter zapisovac = new FileWriter("highscore.txt");
            zapisovac.write("0");
            zapisovac.close();
        }
    }

    public int getHighScore() throws FileNotFoundException {
        Scanner citac = new Scanner(this.subor);
        int highscore = Integer.parseInt(citac.nextLine());
        citac.close();
        return highscore;
    }

    public void setHighScore(int skore) throws IOException {
        String textskore = Integer.toString(skore);
        FileWriter zapisovac = new FileWriter("highscore.txt");
        zapisovac.write(textskore);
        zapisovac.close();
    }
}
