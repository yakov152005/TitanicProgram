package utils;

import java.awt.*;

public class Constants {

    public static class TitanicWindow{

        public static final int WINDOW_WIDTH_CENTER = 800;
        public static final int WINDOW_HEIGHT_CENTER = 100;
    }

    public static class ManageScreen{

        public static final int WINDOW_WIDTH = 1000;
        public static final int WINDOW_HEIGHT = 800;
        public static final int MARGIN_FROM_TOP = 20;
        public static final int MARGIN_FROM_LEFT = 20;
        public static final int MARGIN_FROM_RIGHT = 480;
        public static final int LABEL_WIDTH = 150;
        public static final int LABEL_HEIGHT = 25;
        public static final int COMBO_BOX_WIDTH = 100;
        public static final int COMBO_BOX_HEIGHT = 25;
        public static final int BUTTON_WIDTH = 150;
        public static final int BUTTON_HEIGHT = 25;
        public static final int RESULT_WIDTH = 300;
        public static final int RESULT_HEIGHT = 25;
        public static final int DEF_1 = 50;
    }

    public static class Text{

        public static final String TEXT_1 = "Titanic Passengers Data";
        public static final String TEXT_2 = "Passenger Class: ";
        public static final String TEXT_3 = "Sex";
        public static final String TEXT_4 = "Embarked";
        public static final String TEXT_5 = "Min Passenger ID: ";
        public static final String TEXT_6 = "Max Passenger ID: ";
        public static final String TEXT_7 = "Name";
        public static final String TEXT_8 = "SibSp";
        public static final String TEXT_9 = "Parch";
        public static final String TEXT_10 = "Ticket: ";
        public static final String TEXT_11 = "Min Fare: ";
        public static final String TEXT_12 = "Max Fare: ";
        public static final String TEXT_13 = "Cabin: ";
        public static final String TEXT_14 = "Filter";
        public static final String TEXT_15 = "";
        public static final String TEXT_16 = "Error parsing line: ";
        public static final String TEXT_17 = "All";
        public static final String TEXT_18 = "Exit";
        public static final String TEXT_19 = "File saved successfully!";
        public static final String TEXT_20 = "Failed to save file!";
        public static final String TEXT_21 = "Create statistic file";
        public static final String TEXT_22 = "Data Grouping: ";
        public static final String TEXT_23 = "Select";
        public static final String TEXT_24 = "Pclass";
        public static final String TEXT_25 = "Survived";
        public static final String TEXT_26 = "Age";
        public static final String TEXT_27 = ": ";
        public static final String TEXT_28 = "Open File";
        public static final String TOTAL = "Total: ";
        public static final String NULL = "null";
        public static final String N_0 = "0";
        public static final String MALE = "male";
        public static final String FEMALE = "female";
        public static final String N = "\n";
        public static final String C = "C";
        public static final String Q = "Q";
        public static final String S = "S";
        public static final String[] PASSENGER_CLASS_OPTIONS = { TEXT_17, "1", "2", "3"};
        public static final String[] PASSENGER_SEX_OPTIONS = { TEXT_17, MALE, FEMALE};
        public static final String[] PASSENGER_EMBARKED_OPTIONS = { TEXT_17, C, Q, S};
        public static final String[] PASSENGER_DATA_GROUPING =
                {TEXT_23,TEXT_26,TEXT_24,TEXT_25,TEXT_3,TEXT_4,TEXT_8,TEXT_9,TEXT_26};

    }

    public static class Images{

        public static final String PATH_TO_IMAGE = "src/image/";
        public static final String PATH_TO_ICON = PATH_TO_IMAGE + "titanicIcon.jpg";
        public static final String PATH_TO_IMAGE_1 = PATH_TO_IMAGE + "img.png";
        public static final String PATH_TO_IMAGE_2 = PATH_TO_IMAGE + "img_2.png";
    }

    public static class Files{

        public static final String PATH = "src/data/";
        public static final String CSV = ".csv";
        public static final String TXT = ".txt";
        public static final String PATH_TO_DATA_FILE = PATH + "titanic" + CSV;
        public static final String PATH_TO_STATISTIC = "Statistic" + TXT;
        public static final String FULL_PATH_OF_STATISTIC = PATH + PATH_TO_STATISTIC;

    }

    public static class FontForManage{

        public static final Font MY_FONT = new Font("Ink Free", Font.BOLD, 15);
        public static final Font MY_FONT_2 = new Font("Free style",Font.BOLD,13);
    }
}
