package main;
import static utils.Constants.Images.PATH_TO_ICON;
import static utils.Constants.ManageScreen.*;
import static utils.Constants.Text.*;
import static utils.Constants.TitanicWindow.WINDOW_HEIGHT_CENTER;
import static utils.Constants.TitanicWindow.WINDOW_WIDTH_CENTER;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Titanic extends JFrame {

    public Titanic()throws IOException {
        this.setTitle(TEXT_1);

        Image icon = ImageIO.read(new File(PATH_TO_ICON));
        this.setIconImage(icon);

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(WINDOW_WIDTH_CENTER,WINDOW_HEIGHT_CENTER);

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(new ManageScreen(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT));

        this.setResizable(false);
        this.setVisible(true);
    }

}