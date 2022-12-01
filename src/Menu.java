import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
public class Menu extends JPanel {
    private JButton tlacidlo;
    private JButton tlacidlo2;
    public Menu(){
        /*tlacidlo = new JButton();
        tlacidlo.setBounds(300,250,200,100);
        tlacidlo.addActionListener(e -> this.setBackground(Color.red));
        tlacidlo.setText("Wiiiii");
        tlacidlo.setFocusable(false);
        tlacidlo.setFont(new Font("Minecraft", Font.PLAIN, 40));
        tlacidlo2 = new JButton();
        tlacidlo2.setBounds(300,450,200,100);
        tlacidlo2.addActionListener(e -> this.setBackground(Color.cyan));
        tlacidlo2.setText("Juuuuuu");
        tlacidlo2.setFont(new Font("Minecraft", Font.PLAIN, 40));
        */
        this.setBackground(Color.cyan);
        this.setBounds(0,0,800,800);
        this.add(tlacidlo);
        this.add(tlacidlo2);


    }
}
