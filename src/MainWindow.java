import javax.swing.*;
import java.awt.*;

/**
 * Created by daniel on 4.10.16.
 */
public class MainWindow extends JFrame{
    public MainWindow() {
        JPanel basePanel = new JPanel();
        BorderLayout basePanelLayout = new BorderLayout();
        basePanel.setLayout(basePanelLayout);

        JPanel leftPanel = new JPanel();
        BorderLayout l = new BorderLayout();
        leftPanel.setLayout(l);

        JTextArea outputText = new JTextArea(25,50);
        Font font = new Font("Monospaced", Font.PLAIN, 16);
        outputText.setFont(font);
        outputText.setBackground(new Color( 32,  32,  32));
        outputText.setForeground(new Color( 32, 255,  32));

        JTextArea inputText = new JTextArea("inputText");
        leftPanel.add(outputText, BorderLayout.NORTH);
        leftPanel.add(inputText, BorderLayout.SOUTH);
        inputText.setFont(font);
        inputText.setBackground(new Color( 32,  32,  32));
        inputText.setForeground(new Color( 32, 255,  32));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(  0,   0,   0));
        rightPanel.setPreferredSize(new Dimension(500, 500));

        add(basePanel);
        basePanel.add(leftPanel, BorderLayout.WEST);
        basePanel.add(rightPanel, BorderLayout.CENTER);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pri zmacknuti krizku zavri celou aplikaci (tj. pozabijej vsechna vlakna)
        pack();
        setVisible(true);

    }
}
