package main;
import static utils.Constants.ManageScreen.*;
import static utils.Constants.Text.*;
import javax.swing.*;

class Titanic extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Titanic().setVisible(true);
        });

    }

    public Titanic() {
        this.setTitle(TEXT_1);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(new ManageScreen(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setVisible(true);
    }


}