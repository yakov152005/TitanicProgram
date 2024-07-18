package main;

import audio.Music;
import entities.Name;
import entities.Passenger;
import exception.ExceptionHandler;
import helpclass.Generic;
import userinput.KeyInputs;
import utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static utils.Constants.Audio.MUSIC;
import static utils.Constants.Exceptions.ERROR_3;
import static utils.Constants.Exceptions.ERROR_6;
import static utils.Constants.Files.*;
import static utils.Constants.FontForManage.*;
import static utils.Constants.Images.PATH_TO_IMAGE_1;
import static utils.Constants.Images.PATH_TO_IMAGE_2;
import static utils.Constants.ManageScreen.*;
import static utils.Constants.ReadFileCsv.*;
import static utils.Constants.Text.*;
import static utils.Constants.TitanicWindow.TITLE_PROJECT;

public class ManageScreen extends JPanel {
    private static final AtomicInteger csvCounter = new AtomicInteger();

    private BufferedImage backGround;
    private static Music music;

    private JComboBox<String> classComboBox;
    private JComboBox<String> sexComboBox;
    private JComboBox<String> embarkedComboBox;
    private JComboBox<String> dataGroupingComboBox;

    private JTextField minPassengerIdField;
    private JTextField maxPassengerIdField;
    private JTextField nameField;
    private JTextField sibSpField;
    private JTextField parchField;
    private JTextField ticketField;
    private JTextField minFareField;
    private JTextField maxFareField;
    private JTextField cabinField;

    private JButton filterButton;
    private JButton crateStatisticFileButton;
    private JButton musicButton;
    private JButton exitButton;

    private JLabel forDataGroupLabel;
    private JLabel resultFilterLabel;
    private JLabel resultDataGroupLabel;

    private List<Passenger> passengerList;
    private String titleLine;
    private int csvC = 0;

    public ManageScreen(int x, int y, int width, int height){
        this.passengerList = new ArrayList<>();

        this.setLayout(null);
        this.setBounds(x, y + MARGIN_FROM_TOP, width, height);
        this.setFocusable(true);
        this.requestFocus(true);

        playMusic();
        createTitle();
        drawBackGroundImg();
        loadPassengerData();
        addJButton(x,y);
        addJLabel(x,y);
        addComboBox(x,y);
        playActionListener();
    }

    private void createTitle(){
        JLabel heading=new JLabel(TITLE_PROJECT);
        heading.setBounds(245,685,WINDOW_WIDTH / 2, 100);
        heading.setFont(MY_FONT_3);
        heading.setForeground(Color.white);
        this.add(heading);
    }

    public void addJButton(int x,int y){

        this.filterButton = new JButton(TEXT_14);
        this.filterButton.setBounds(x + MARGIN_FROM_LEFT, y + MARGIN_FROM_RIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(this.filterButton);

        this.crateStatisticFileButton = new JButton(TEXT_21);
        this.crateStatisticFileButton.setBounds(x + (MARGIN_FROM_LEFT * 9) + 10, y + MARGIN_FROM_RIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.add(crateStatisticFileButton);

        this.musicButton = new JButton(MUSIC);
        this.musicButton.setBounds(x + (MARGIN_FROM_LEFT * 18), y + MARGIN_FROM_RIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.add(musicButton);

        this.exitButton = new JButton(TEXT_18);
        this.exitButton.setBounds(x + (MARGIN_FROM_LEFT * 26) + 10, y + MARGIN_FROM_RIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.add(exitButton);
    }

    public void addJLabel(int x, int y){

        JLabel minPassengerIdLabel = new JLabel(TEXT_5);
        minPassengerIdLabel.setFont(MY_FONT);
        minPassengerIdLabel.setForeground(Color.GREEN);
        minPassengerIdLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 6), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(minPassengerIdLabel);

        this.minPassengerIdField = new JTextField();
        this.minPassengerIdField.setBounds(minPassengerIdLabel.getX() + minPassengerIdLabel.getWidth() + 1, minPassengerIdLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.minPassengerIdField);
        this.minPassengerIdField.addKeyListener(new KeyInputs(this.minPassengerIdField ,DIGITS));


        JLabel maxPassengerIdLabel = new JLabel(TEXT_6);
        maxPassengerIdLabel.setFont(MY_FONT);
        maxPassengerIdLabel.setForeground(Color.GREEN);
        maxPassengerIdLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 8), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(maxPassengerIdLabel);

        this.maxPassengerIdField = new JTextField();
        this.maxPassengerIdField.setBounds(maxPassengerIdLabel.getX() + maxPassengerIdLabel.getWidth() + 1, maxPassengerIdLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.maxPassengerIdField);
        this.maxPassengerIdField.addKeyListener(new KeyInputs(this.maxPassengerIdField ,DIGITS));

        JLabel nameLabel = new JLabel(TEXT_7 + TEXT_27);
        nameLabel.setFont(MY_FONT);
        nameLabel.setForeground(Color.GREEN);
        nameLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 10), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(nameLabel);

        this.nameField = new JTextField();
        this.nameField.setBounds(nameLabel.getX() + nameLabel.getWidth() + 1, nameLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.nameField);
        this.nameField.addKeyListener(new KeyInputs(this.nameField,ONLY_LETTER));

        JLabel sibSpLabel = new JLabel(TEXT_8 + TEXT_27);
        sibSpLabel.setFont(MY_FONT);
        sibSpLabel.setForeground(Color.GREEN);
        sibSpLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 12), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(sibSpLabel);

        this.sibSpField = new JTextField();
        this.sibSpField.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth() + 1, sibSpLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.sibSpField);
        this.sibSpField.addKeyListener(new KeyInputs(this.sibSpField,ONLY_DIGIT));

        JLabel parchLabel = new JLabel(TEXT_9 + TEXT_27);
        parchLabel.setFont(MY_FONT);
        parchLabel.setForeground(Color.GREEN);
        parchLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 14), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(parchLabel);

        this.parchField = new JTextField();
        this.parchField.setBounds(parchLabel.getX() + parchLabel.getWidth() + 1, parchLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.parchField);
        this.parchField.addKeyListener(new KeyInputs(this.parchField,ONLY_DIGIT));

        JLabel ticketLabel = new JLabel(TEXT_10);
        ticketLabel.setFont(MY_FONT);
        ticketLabel.setForeground(Color.GREEN);
        ticketLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 16), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(ticketLabel);

        this.ticketField = new JTextField();
        this.ticketField.setBounds(ticketLabel.getX() + ticketLabel.getWidth() + 1, ticketLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.ticketField);

        JLabel minFareLabel = new JLabel(TEXT_11);
        minFareLabel.setFont(MY_FONT);
        minFareLabel.setForeground(Color.GREEN);
        minFareLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 120), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(minFareLabel);

        this.minFareField = new JTextField();
        this.minFareField.setBounds(minFareLabel.getX() + minFareLabel.getWidth() + 1, minFareLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.minFareField);
        this.minFareField.addKeyListener(new KeyInputs(this.minFareField,DIGITS));

        JLabel maxFareLabel = new JLabel(TEXT_12);
        maxFareLabel.setFont(MY_FONT);
        maxFareLabel.setForeground(Color.GREEN);
        maxFareLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 80), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(maxFareLabel);

        this.maxFareField = new JTextField();
        this.maxFareField.setBounds(maxFareLabel.getX() + maxFareLabel.getWidth() + 1, maxFareLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.maxFareField);
        this.maxFareField.addKeyListener(new KeyInputs(this.maxFareField,DIGITS));

        JLabel cabinLabel = new JLabel(TEXT_13);
        cabinLabel.setFont(MY_FONT);
        cabinLabel.setForeground(Color.GREEN);
        cabinLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 40), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(cabinLabel);

        this.cabinField = new JTextField();
        this.cabinField.setBounds(cabinLabel.getX() + cabinLabel.getWidth() + 1, cabinLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.cabinField);
        this.cabinField.addKeyListener(new KeyInputs(this.cabinField,ONLY_DIGIT));

        this.forDataGroupLabel = new JLabel(TEXT_22);
        this.forDataGroupLabel.setFont(MY_FONT);
        this.forDataGroupLabel.setForeground(Color.GREEN);
        this.forDataGroupLabel.setBounds(x + MARGIN_FROM_LEFT, y + MARGIN_FROM_RIGHT + DEF_1 * 2, LABEL_WIDTH, LABEL_HEIGHT);
        this.add(forDataGroupLabel);

        this.resultDataGroupLabel = new JLabel(TEXT_15);
        this.resultDataGroupLabel.setBounds(x + MARGIN_FROM_LEFT * 15, y + MARGIN_FROM_RIGHT + DEF_1 * 2, RESULT_WIDTH, RESULT_HEIGHT);
        this.add(this.resultDataGroupLabel);

        this.resultFilterLabel = new JLabel(TEXT_15);
        this.resultFilterLabel.setBounds(x + MARGIN_FROM_LEFT, y + MARGIN_FROM_RIGHT + (MARGIN_FROM_LEFT * 2), RESULT_WIDTH, RESULT_HEIGHT);
        this.add(this.resultFilterLabel);
    }
    public void addComboBox(int x, int y){

        JLabel classLabel = new JLabel(TEXT_2);
        classLabel.setFont(MY_FONT);
        classLabel.setForeground(Color.GREEN);
        classLabel.setBounds(x + MARGIN_FROM_LEFT, y, LABEL_WIDTH, LABEL_HEIGHT);
        this.add(classLabel);

        this.classComboBox = new JComboBox<>(PASSENGER_CLASS_OPTIONS);
        this.classComboBox.setBounds(classLabel.getX() + classLabel.getWidth() + 1, classLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.classComboBox);

        JLabel sexLabel = new JLabel(TEXT_3 + TEXT_27);
        sexLabel.setFont(MY_FONT);
        sexLabel.setForeground(Color.GREEN);
        sexLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 2), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(sexLabel);

        this.sexComboBox = new JComboBox<>(PASSENGER_SEX_OPTIONS);
        this.sexComboBox.setBounds(sexLabel.getX() + sexLabel.getWidth() + 1, sexLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.sexComboBox);

        JLabel embarkedLabel = new JLabel(TEXT_4 + TEXT_27);
        embarkedLabel.setFont(MY_FONT);
        embarkedLabel.setForeground(Color.GREEN);
        embarkedLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 4), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(embarkedLabel);

        this.embarkedComboBox = new JComboBox<>(PASSENGER_EMBARKED_OPTIONS);
        this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth() + 1, embarkedLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.embarkedComboBox);

        this.dataGroupingComboBox = new JComboBox<>(PASSENGER_DATA_GROUPING);
        this.dataGroupingComboBox.setBounds(classLabel.getX() + classLabel.getWidth() ,classLabel.getY() + MARGIN_FROM_RIGHT + DEF_1 * 2 ,COMBO_BOX_WIDTH,COMBO_BOX_HEIGHT);
        this.add(dataGroupingComboBox);
    }

    public void playActionListener(){

        this.filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performFiltering();
                } catch (IOException ex) {ExceptionHandler.handle(ex);}
            }
        });

        this.crateStatisticFileButton.addActionListener(e -> {
            try {
                performStatisticFileTxt();
            } catch (IOException ex) {ExceptionHandler.handle(ex);}
            this.crateStatisticFileButton.setText(TEXT_28);
            System.out.println(TEXT_29);
            this.crateStatisticFileButton.addActionListener(e1 -> {
                try {
                    Desktop.getDesktop().open(new File(FULL_PATH_OF_STATISTIC));
                }catch (IOException ex){ExceptionHandler.handle(ex);}
            });
        });

        this.dataGroupingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDataGrouping();
            }
        });

        this.musicButton.addActionListener(e ->{
            music.toggleSound();
            musicButton.setText(music.isIsSoundOn() ? MUSIC + " Off" : MUSIC + " On" );
        });

        this.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private void loadPassengerData() {

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_DATA_FILE))) {
            String line;
            String title = br.readLine();
            this.titleLine = title;
            while ((line = br.readLine()) != null) {

                try {

                    String[] values = line.split(",");
                    int passengerID = Integer.parseInt(values[P_D].isEmpty() ? N_0 :values[P_D].trim());
                    int survived = Integer.parseInt(values[S_V].isEmpty() ? N_0 :values[S_V].trim());
                    int pClass = Integer.parseInt(values[P_C].isEmpty() ? N_0 :values[P_C].trim());
                    String nameStr1 = values[N_1].replace("\"", "");
                    String nameStr2 = values[N_2].replace("\"", "");
                    Name name = new Name(nameStr1 + nameStr2);
                    String sex = values[S_X].isEmpty() ? NULL : values[S_X].trim();
                    Double age = values[A_E].isEmpty() ? null : Double.parseDouble(values[A_E].trim());
                    int sibSp = Integer.parseInt(values[S_P].isEmpty() ? N_0 :values[S_P].trim());
                    int parch = Integer.parseInt(values[P_H].isEmpty() ? N_0 :values[P_H].trim());
                    String ticket = values[T_T].isEmpty() ? NULL : values[T_T].trim();
                    Double fare = values[F_E].isEmpty() ? null : Double.parseDouble(values[F_E].trim());
                    String cabin = values[C_N].isEmpty() ? NULL : values[C_N].trim();
                    String embarked = values[E_D].isEmpty() ? NULL : values[E_D].trim();

                    Passenger passenger = new Passenger(passengerID, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked);

                    this.passengerList.add(passenger);

                } catch (NumberFormatException ex) {ExceptionHandler.handleNumberFormatException(ex, line);}
            }
        } catch (Exception ex) {ExceptionHandler.handle(ex);}

        System.out.println(titleLine);
    }

    private void performFiltering() throws IOException {

        List<Passenger> filteredList = passengerList;

        String selectedClass = classComboBox.getSelectedItem().toString();
        if (!selectedClass.equals(TEXT_17)) {
            int selectedClassInt = Integer.parseInt(selectedClass);
            filteredList = filteredList.stream()
                    .filter(p -> p.getPClass() == selectedClassInt)
                    .collect(Collectors.toList());
        }

        String selectedSex = sexComboBox.getSelectedItem().toString();
        if (!selectedSex.equals(TEXT_17)) {
            filteredList = filteredList.stream()
                    .filter(p -> p.getSex().equals(selectedSex))
                    .collect(Collectors.toList());
        }

        String selectedEmbarked = embarkedComboBox.getSelectedItem().toString();
        if (!selectedEmbarked.equals(TEXT_17)) {
            filteredList = filteredList.stream()
                    .filter(p -> p.getEmbarked().equals(selectedEmbarked))
                    .collect(Collectors.toList());
        }

        String minPassengerIdStr = minPassengerIdField.getText().trim();
        String maxPassengerIdStr = maxPassengerIdField.getText().trim();
        if (!minPassengerIdStr.isEmpty()) {
            int minPassengerId = Integer.parseInt(minPassengerIdStr);
            filteredList = filteredList.stream()
                    .filter(p -> p.getPassengerID() >= minPassengerId)
                    .collect(Collectors.toList());
        }

        if (!maxPassengerIdStr.isEmpty()) {
            int maxPassengerId = Integer.parseInt(maxPassengerIdStr);
            filteredList = filteredList.stream()
                    .filter(p -> p.getPassengerID() <= maxPassengerId)
                    .collect(Collectors.toList());
        }

        String nameFilter = nameField.getText().trim();
        if (!nameFilter.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(p -> p.getName().toString().contains(nameFilter))
                    .collect(Collectors.toList());
        }

        String sibSpFilter = sibSpField.getText().trim();
        if (!sibSpFilter.isEmpty()) {
            int sibSp = Integer.parseInt(sibSpFilter);
            filteredList = filteredList.stream()
                    .filter(p -> p.getSibSp() == sibSp)
                    .collect(Collectors.toList());
        }

        String parchFilter = parchField.getText().trim();
        if (!parchFilter.isEmpty()) {
            int parch = Integer.parseInt(parchFilter);
            filteredList = filteredList.stream()
                    .filter(p -> p.getParch() == parch)
                    .collect(Collectors.toList());
        }

        String ticketFilter = ticketField.getText().trim();
        if (!ticketFilter.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(p -> p.getTicket().contains(ticketFilter))
                    .collect(Collectors.toList());
        }

        String minFareStr = minFareField.getText().trim();
        String maxFareStr = maxFareField.getText().trim();
        if (!minFareStr.isEmpty()) {
            double minFare = Double.parseDouble(minFareStr);
            filteredList = filteredList.stream()
                    .filter(p -> p.getFare() >= minFare)
                    .collect(Collectors.toList());
        }
        if (!maxFareStr.isEmpty()) {
            double maxFare = Double.parseDouble(maxFareStr);
            filteredList = filteredList.stream()
                    .filter(p -> p.getFare() <= maxFare)
                    .collect(Collectors.toList());
        }

        String cabinFilter = cabinField.getText().trim();
        if (!cabinFilter.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(p -> p.getCabin().contains(cabinFilter))
                    .collect(Collectors.toList());
        }

        this.csvC = csvCounter.incrementAndGet();
        String numberForFile = csvC + CSV;
        Set<Passenger> savedHashSet = filteredList.stream()
                .sorted(Comparator.comparing(p -> {
                    return p.getName().getFormattedName();
                }))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        new Thread(() -> {
            try {
                saveCsvFile(savedHashSet, numberForFile);

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, TEXT_19);});

            } catch (IOException ex) {
                SwingUtilities.invokeLater(() -> {
                    ExceptionHandler.handleIoExceptionWithGuiMessage(ex, ERROR_3);});}
        }).start();


        filteredList.forEach(System.out::println);
        int totalPassengers = filteredList.size();
        long survivedCount = filteredList.stream().filter(Passenger::isSurvived).count();
        long notSurvivedCount = totalPassengers - survivedCount;

        resultFilterLabel.setFont(MY_FONT_2);
        resultFilterLabel.setForeground(Color.GREEN);
        resultFilterLabel.setText(TOTAL + totalPassengers +
                ", Survived: " + survivedCount + ", Not Survived: " + notSurvivedCount);
    }

    private void performStatisticFileTxt() throws IOException {
        List<Passenger> statisticList = passengerList;

        Generic<Integer, Integer> classCount = new Generic<>();
        Generic<String, Integer> genderCount = new Generic<>();
        Generic<Integer, Integer> ageGroupCount = new Generic<>();
        Generic<Boolean, Integer> closeRelativesCount = new Generic<>();
        Generic<Integer, Integer> fareGroupCount = new Generic<>();
        Generic<String, Integer> embarkedCount = new Generic<>();

        Generic<Integer, Integer> survivedClassCount = new Generic<>();
        Generic<String, Integer> survivedGenderCount = new Generic<>();
        Generic<Integer, Integer> survivedAgeGroupCount = new Generic<>();
        Generic<Boolean, Integer> survivedCloseRelativesCount = new Generic<>();
        Generic<Integer, Integer> survivedFareGroupCount = new Generic<>();
        Generic<String, Integer> survivedEmbarkedCount = new Generic<>();

        for (Passenger passenger : statisticList) {
            incrementCount(classCount, passenger.getPClass());
            incrementCount(genderCount, passenger.getSex());
            incrementCount(ageGroupCount, getAgeGroup(passenger.getAge()));
            incrementCount(closeRelativesCount, hasCloseRelatives(passenger));
            incrementCount(fareGroupCount, getFareGroup(passenger.getFare()));
            incrementCount(embarkedCount, passenger.getEmbarked());

            if (passenger.isSurvived()) {
                incrementCount(survivedClassCount, passenger.getPClass());
                incrementCount(survivedGenderCount, passenger.getSex());
                incrementCount(survivedAgeGroupCount, getAgeGroup(passenger.getAge()));
                incrementCount(survivedCloseRelativesCount, hasCloseRelatives(passenger));
                incrementCount(survivedFareGroupCount, getFareGroup(passenger.getFare()));
                incrementCount(survivedEmbarkedCount, passenger.getEmbarked());
            }
        }

        float avg1st = calculatePercentage(survivedClassCount.getGenericMap().get(ST_1), classCount.getGenericMap().get(ST_1));
        float avg2nd = calculatePercentage(survivedClassCount.getGenericMap().get(ND_2), classCount.getGenericMap().get(ND_2));
        float avg3rd = calculatePercentage(survivedClassCount.getGenericMap().get(RD_3), classCount.getGenericMap().get(RD_3));

        float avgMale = calculatePercentage(survivedGenderCount.getGenericMap().get(MALE), genderCount.getGenericMap().get(MALE));
        float avgFemale = calculatePercentage(survivedGenderCount.getGenericMap().get(FEMALE), genderCount.getGenericMap().get(FEMALE));

        float avg10 = calculatePercentage(survivedAgeGroupCount.getGenericMap().get(10), ageGroupCount.getGenericMap().get(10));
        float avg20 = calculatePercentage(survivedAgeGroupCount.getGenericMap().get(20), ageGroupCount.getGenericMap().get(20));
        float avg30 = calculatePercentage(survivedAgeGroupCount.getGenericMap().get(30), ageGroupCount.getGenericMap().get(30));
        float avg40 = calculatePercentage(survivedAgeGroupCount.getGenericMap().get(40), ageGroupCount.getGenericMap().get(40));
        float avg50 = calculatePercentage(survivedAgeGroupCount.getGenericMap().get(50), ageGroupCount.getGenericMap().get(50));
        float avg51PP = calculatePercentage(survivedAgeGroupCount.getGenericMap().get(51), ageGroupCount.getGenericMap().get(51));

        float avgWithClose = calculatePercentage(survivedCloseRelativesCount.getGenericMap().get(true), closeRelativesCount.getGenericMap().get(true));
        float avgWithoutClose = calculatePercentage(survivedCloseRelativesCount.getGenericMap().get(false), closeRelativesCount.getGenericMap().get(false));

        float avgCostTicket10 = calculatePercentage(survivedFareGroupCount.getGenericMap().get(10), fareGroupCount.getGenericMap().get(10));
        float avgCostTicket30 = calculatePercentage(survivedFareGroupCount.getGenericMap().get(30), fareGroupCount.getGenericMap().get(30));
        float avgCostTicket31PP = calculatePercentage(survivedFareGroupCount.getGenericMap().get(31), fareGroupCount.getGenericMap().get(31));

        float avg_C = calculatePercentage(survivedEmbarkedCount.getGenericMap().get(C), embarkedCount.getGenericMap().get(C));
        float avg_Q = calculatePercentage(survivedEmbarkedCount.getGenericMap().get(Q), embarkedCount.getGenericMap().get(Q));
        float avg_S = calculatePercentage(survivedEmbarkedCount.getGenericMap().get(S), embarkedCount.getGenericMap().get(S));

        try (PrintWriter pw = new PrintWriter(new FileWriter(FULL_PATH_OF_STATISTIC))) {
            pw.println("אחוזי הישרדות לפי -->>");
            pw.println("מחלקה:");
            pw.printf("1st --> %.2f%%%n", avg1st);
            pw.printf("2nd --> %.2f%%%n", avg2nd);
            pw.printf("3rd --> %.2f%%%n", avg3rd);

            pw.print(N);

            pw.println("מין:");
            pw.printf(MALE + " --> %.2f%%%n", avgMale);
            pw.printf(FEMALE + " --> %.2f%%%n", avgFemale);

            pw.print(N);

            pw.println("קבוצת גיל:");
            pw.printf("0-10 --> %.2f%%%n", avg10);
            pw.printf("11-20 --> %.2f%%%n", avg20);
            pw.printf("21-30 --> %.2f%%%n", avg30);
            pw.printf("31-40 --> %.2f%%%n", avg40);
            pw.printf("41-50 --> %.2f%%%n", avg50);
            pw.printf("51+ --> %.2f%%%n", avg51PP);

            pw.print(N);

            pw.println("האם יש בני משפחה על הסיפון:");
            pw.printf("With relatives --> %.2f%%%n", avgWithClose);
            pw.printf("Without relatives --> %.2f%%%n", avgWithoutClose);

            pw.print(N);

            pw.println("עלות כרטיס:");
            pw.printf("0-10 --> %.2f%%%n", avgCostTicket10);
            pw.printf("11-30 --> %.2f%%%n", avgCostTicket30);
            pw.printf("31+ --> %.2f%%%n", avgCostTicket31PP);

            pw.print(N);

            pw.println("הנמל שממנו העפילו לספינה:");
            pw.printf("C – צ'רבורג --> %.2f%%%n", avg_C);
            pw.printf("Q – קווינסטאון --> %.2f%%%n", avg_Q);
            pw.printf("S – סאות'המפטון --> %.2f%%%n", avg_S);
        }

        JOptionPane.showMessageDialog(null, TEXT_19);
    }

    private void incrementCount(Generic<String, Integer> countMap, String key) {
        countMap.put(key, countMap.getGenericMap().getOrDefault(key, 0) + 1);
    }

    private void incrementCount(Generic<Integer, Integer> countMap, Integer key) {
        countMap.put(key, countMap.getGenericMap().getOrDefault(key, 0) + 1);
    }

    private void incrementCount(Generic<Boolean, Integer> countMap, Boolean key) {
        countMap.put(key, countMap.getGenericMap().getOrDefault(key, 0) + 1);
    }

    private int getAgeGroup(Double age) {
        if (age == null) return -1;
        if (age <= 10) return 10;
        if (age <= 20) return 20;
        if (age <= 30) return 30;
        if (age <= 40) return 40;
        if (age <= 50) return 50;
        return 51;
    }

    private boolean hasCloseRelatives(Passenger passenger) {
        return passenger.getParch() >= 1 || passenger.getSibSp() >= 1;
    }

    private int getFareGroup(Double fare) {
        if (fare == null) return -1;
        if (fare <= 10) return 10;
        if (fare <= 30) return 30;
        return 31;
    }

    private float calculatePercentage(Integer survivedCount, Integer totalCount) {
        if (totalCount == null || totalCount == 0) return 0;
        return (float) survivedCount / totalCount * 100;
    }


    private void performDataGrouping(){
        Map<String, Float> dataGroupMap = new HashMap<>();
        String selectedClassText = dataGroupingComboBox.getSelectedItem().toString();
        Constants.GroupingOption selectedClass = Constants.GroupingOption.fromText(selectedClassText);

        Generic<String, Integer> genericCountMap = switch (selectedClass) {
            case PCLASS -> countByField(passenger -> String.valueOf(passenger.getPClass()));
            case SEX -> countByField(Passenger::getSex);
            case EMBARKED -> countByField(Passenger::getEmbarked);
            case SIBSP -> countByField(passenger -> String.valueOf(passenger.getSibSp()));
            case SURVIVED -> countByField(Passenger::getSurvivedString);
            case PARCH -> countByField(passenger -> String.valueOf(passenger.getParch()));
            case AGE -> countByField(passenger -> String.valueOf(passenger.getAge()));
            default -> throw new IllegalArgumentException(ERROR_6);
        };

        genericCountMap.getGenericMap().forEach((key, value) -> {
            float percentage = percentage(value);
            dataGroupMap.put("|" + key + "|", percentage);
        });

        Map<String, Float> sortedDataGroupMap = dataGroupMap.entrySet().stream()
                .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        StringBuilder resultText = new StringBuilder(TOTAL).append(N);
        sortedDataGroupMap.forEach((key, value) -> resultText.append(key).append(" ").append(value).append("% ").append(N));

        /**
         *  print to console
         */
        System.out.println(resultText);

        /**
         * show result on the screen
         */
        JOptionPane.showMessageDialog(null, resultText);
    }

    private Generic<String, Integer> countByField(Function<Passenger, String> fieldExtractor) {
        Generic<String, Integer> genericCount = new Generic<>();
        for (Passenger passenger : passengerList) {
            String key = fieldExtractor.apply(passenger);
            genericCount.put(key, genericCount.getGenericMap().getOrDefault(key, 0) + 1);
        }
        return genericCount;
    }


    private float percentage (int value){
        return (float) value / passengerList.size() * 100;
    }


    private void saveCsvFile(Set<Passenger> savedHashSet, String numberForFile) throws IOException {

        FileWriter f = new FileWriter(PATH + numberForFile);
        PrintWriter printWriter = new PrintWriter(f);

        for (Passenger passenger : savedHashSet) {
            StringBuilder sb = new StringBuilder();
            sb.append(passenger.getPassengerID()).append(",");
            sb.append(passenger.getSurvived()).append(",");
            sb.append(passenger.getPClass()).append(",");
            sb.append("\"").append(passenger.getName().getFormattedName()).append("\"").append(",");
            sb.append(passenger.getSex()).append(",");
            sb.append(passenger.getAge()).append(",");
            sb.append(passenger.getSibSp()).append(",");
            sb.append(passenger.getParch()).append(",");
            sb.append(passenger.getTicket()).append(",");
            sb.append(passenger.getFare()).append(",");
            sb.append(passenger.getCabin()).append(",");
            sb.append(passenger.getEmbarked()).append("\n");

            printWriter.print(sb.toString());
        }
        printWriter.close();
    }

    /**
     * You can change the background image if u want
     * change the PATH_TO_IMAGE_2
     */
    private void drawBackGroundImg(){
        try {
            this.backGround = ImageIO.read(new File(PATH_TO_IMAGE_1));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround,0,0,
                WINDOW_WIDTH, WINDOW_HEIGHT, null);
    }

    public void playMusic() {
        music = new Music();
    }
}