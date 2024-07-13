package main;

import audio.Music;
import entities.Name;
import entities.Passenger;
import helpclass.Generic;

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
import java.util.stream.Collectors;

import static utils.Constants.Audio.MUSIC;
import static utils.Constants.Files.*;
import static utils.Constants.FontForManage.*;
import static utils.Constants.Images.PATH_TO_IMAGE_1;
import static utils.Constants.ManageScreen.*;
import static utils.Constants.ReadFileCsv.*;
import static utils.Constants.Text.*;

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
        JLabel heading=new JLabel("The Titanic Project");
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

        JLabel maxPassengerIdLabel = new JLabel(TEXT_6);
        maxPassengerIdLabel.setFont(MY_FONT);
        maxPassengerIdLabel.setForeground(Color.GREEN);
        maxPassengerIdLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 8), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(maxPassengerIdLabel);

        this.maxPassengerIdField = new JTextField();
        this.maxPassengerIdField.setBounds(maxPassengerIdLabel.getX() + maxPassengerIdLabel.getWidth() + 1, maxPassengerIdLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.maxPassengerIdField);

        JLabel nameLabel = new JLabel(TEXT_7 + TEXT_27);
        nameLabel.setFont(MY_FONT);
        nameLabel.setForeground(Color.GREEN);
        nameLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 10), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(nameLabel);

        this.nameField = new JTextField();
        this.nameField.setBounds(nameLabel.getX() + nameLabel.getWidth() + 1, nameLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.nameField);

        JLabel sibSpLabel = new JLabel(TEXT_8 + TEXT_27);
        sibSpLabel.setFont(MY_FONT);
        sibSpLabel.setForeground(Color.GREEN);
        sibSpLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 12), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(sibSpLabel);

        this.sibSpField = new JTextField();
        this.sibSpField.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth() + 1, sibSpLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.sibSpField);

        JLabel parchLabel = new JLabel(TEXT_9 + TEXT_27);
        parchLabel.setFont(MY_FONT);
        parchLabel.setForeground(Color.GREEN);
        parchLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 14), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(parchLabel);

        this.parchField = new JTextField();
        this.parchField.setBounds(parchLabel.getX() + parchLabel.getWidth() + 1, parchLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.parchField);

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

        JLabel maxFareLabel = new JLabel(TEXT_12);
        maxFareLabel.setFont(MY_FONT);
        maxFareLabel.setForeground(Color.GREEN);
        maxFareLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 80), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(maxFareLabel);

        this.maxFareField = new JTextField();
        this.maxFareField.setBounds(maxFareLabel.getX() + maxFareLabel.getWidth() + 1, maxFareLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.maxFareField);

        JLabel cabinLabel = new JLabel(TEXT_13);
        cabinLabel.setFont(MY_FONT);
        cabinLabel.setForeground(Color.GREEN);
        cabinLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 40), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(cabinLabel);

        this.cabinField = new JTextField();
        this.cabinField.setBounds(cabinLabel.getX() + cabinLabel.getWidth() + 1, cabinLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.cabinField);

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
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.crateStatisticFileButton.addActionListener(e -> {
            try {
                performStatisticFileTxt();
            } catch (IOException ex) {throw new RuntimeException(ex);}
            this.crateStatisticFileButton.setText(TEXT_28);
            this.crateStatisticFileButton.addActionListener(e1 -> {
                try {
                    Desktop.getDesktop().open(new File(FULL_PATH_OF_STATISTIC));
                }catch (IOException ex){throw new RuntimeException(ex);}
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

                } catch (NumberFormatException e) {System.err.println(TEXT_16 + line);}
            }
        } catch (Exception e) {System.out.println(e.getMessage());}

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

            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, TEXT_20);});}
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

        int count1st = 0, count2nd = 0, count3rd = 0,
                countMale = 0, countFemale = 0,
                count10 = 0, count20 = 0, count30 = 0, count40 = 0, count50 = 0, count51PP = 0
                ,countWithClose = 0, countWithoutClose = 0
                ,countCostTicket_10 = 0, countCostTicket_30 = 0, countCostTicket_31PP = 0
                ,count_C = 0, count_Q = 0, count_S = 0;

        int sumSurvived1st = 0, sumSurvived2nd = 0, sumSurvived3rd = 0,
                sumMale = 0, sumFemale = 0 ,
                sum10 = 0, sum20 = 0, sum30 = 0, sum40 = 0, sum50 = 0, sum51PP = 0
                ,sumWithClose = 0, sumWithoutClose = 0
                ,sumCostTicket_10 = 0, sumCostTicket_30 = 0, sumCostTicket_31PP = 0
                ,sum_C = 0, sum_Q = 0, sum_S = 0;;


        for (Passenger passenger : statisticList){

            if (passenger.getPClass() == 1){count1st++;}
            else if (passenger.getPClass() == 2) {count2nd++;}
            else if (passenger.getPClass() == 3){count3rd++;}

            if (passenger.getSex().equalsIgnoreCase(MALE)){countMale++;}
            else if (passenger.getSex().equalsIgnoreCase(FEMALE)) {countFemale++;}

            try {
                if (passenger.getAge() <= 10){count10++;}
                else if (passenger.getAge() > 10 && passenger.getAge() <= 20) {count20++;}
                else if (passenger.getAge() > 20 && passenger.getAge() <= 30) {count30++;}
                else if (passenger.getAge() > 30 && passenger.getAge() <= 40) {count40++;}
                else if (passenger.getAge() > 40 && passenger.getAge() <= 50) {count50++;}
                else if (passenger.getAge() > 50) {count51PP++;}
            }catch (NullPointerException e){}

            if (passenger.getParch() >= 1 || passenger.getSibSp() >= 1){countWithClose++;}
            else if (passenger.getParch() == 0 && passenger.getSibSp() == 0){countWithoutClose++;}

            if (passenger.getFare() <= 10){countCostTicket_10++;}
            else if (passenger.getFare() > 10 && passenger.getFare() <= 30){countCostTicket_30++;}
            else if (passenger.getFare() > 30) {countCostTicket_31PP++;}

            if (passenger.getEmbarked().equalsIgnoreCase(C)){count_C++;}
            else if (passenger.getEmbarked().equalsIgnoreCase(Q)){count_Q++;}
            else if (passenger.getEmbarked().equalsIgnoreCase(S)){count_S++;}
        }

        for (Passenger passenger : statisticList){
            if (passenger.isSurvived()) {

                if (passenger.getPClass() == 1){sumSurvived1st++;}
                else if (passenger.getPClass() == 2){sumSurvived2nd++;}
                else if (passenger.getPClass() == 3){sumSurvived3rd++;}

                if (passenger.getSex().equalsIgnoreCase(MALE)){sumMale++;}
                else if (passenger.getSex().equalsIgnoreCase(FEMALE)){sumFemale++;}

                try {
                    if (passenger.getAge() <= 10){sum10++;}
                    else if (passenger.getAge() > 10 && passenger.getAge() <= 20) {sum20++;}
                    else if (passenger.getAge() > 20 && passenger.getAge() <= 30) {sum30++;}
                    else if (passenger.getAge() > 30 && passenger.getAge() <= 40) {sum40++;}
                    else if (passenger.getAge() > 40 && passenger.getAge() <= 50) {sum50++;}
                    else if (passenger.getAge() > 50) {sum51PP++;}
                }catch (NullPointerException e){}

                if (passenger.getParch() >= 1 || passenger.getSibSp() >= 1){sumWithClose++;}
                else if (passenger.getParch() == 0 && passenger.getSibSp() == 0){sumWithoutClose++;}

                if (passenger.getFare() <= 10){sumCostTicket_10++;}
                else if (passenger.getFare() > 10 && passenger.getFare() <= 30){sumCostTicket_30++;}
                else if (passenger.getFare() > 30) {sumCostTicket_31PP++;}

                if (passenger.getEmbarked().equalsIgnoreCase(C)){sum_C++;}
                else if (passenger.getEmbarked().equalsIgnoreCase(Q)){sum_Q++;}
                else if (passenger.getEmbarked().equalsIgnoreCase(S)){sum_S++;}

            }

        }

        float avg1st = (float) sumSurvived1st / count1st * 100 ;
        float avg2nd = (float) sumSurvived2nd / count2nd * 100;
        float avg3rd = (float) sumSurvived3rd / count3rd * 100;

        float avgMale = (float) sumMale / countMale * 100;
        float avgFemale = (float) sumFemale / countFemale * 100;

        float avg10 = (float) sum10 / count10 * 100;
        float avg20 = (float) sum20 / count20 * 100;
        float avg30 = (float) sum30 / count30 * 100;
        float avg40 = (float) sum40 / count40 * 100;
        float avg50 = (float) sum50 / count50 * 100;
        float avg51PP = (float) sum51PP / count51PP * 100;

        float avgWithClose = (float) sumWithClose / countWithClose * 100;
        float avgWithoutClose = (float) sumWithoutClose / countWithoutClose * 100;

        float avgCostTicket10 = (float) sumCostTicket_10 / countCostTicket_10 * 100;
        float avgCostTicket30 = (float) sumCostTicket_30 / countCostTicket_30 * 100;
        float avgCostTicket31PP = (float) sumCostTicket_31PP / countCostTicket_31PP * 100;

        float avg_C = (float) sum_C / count_C * 100;
        float avg_Q = (float) sum_Q / count_Q * 100;
        float avg_S = (float) sum_S / count_S * 100;

        FileWriter fw = new FileWriter(FULL_PATH_OF_STATISTIC);
        PrintWriter pw = new PrintWriter(fw);

        pw.println("אחוזי הישרדות לפי -->>");
        pw.println("מחלקה:");
        pw.print("1st --> " + avg1st + N);
        pw.print("2nd --> " + avg2nd + N);
        pw.print("3rd --> " + avg3rd + N);

        pw.println(" ");

        pw.println("מין:");
        pw.print(MALE + " --> " + avgMale + N);
        pw.print(FEMALE + " --> " + avgFemale + N);

        pw.println(" ");

        pw.println("קבוצת גיל:");
        pw.print("0-10 --> " + avg10 + N );
        pw.print("11-20 --> " + avg20 + N );
        pw.print("21-30 --> " + avg30 + N );
        pw.print("31-40 --> " + avg40 + N );
        pw.print("41-50 --> " + avg50 + N );
        pw.print("51+ --> " + avg51PP + N );

        pw.println(" ");

        pw.println("האם יש בני משפחה על הסיפון:");
        pw.print("With relatives --> " + avgWithClose + N);
        pw.print("Without relatives --> " + avgWithoutClose + N);

        pw.println(" ");

        pw.println("עלות כרטיס:");
        pw.print("0-10 --> " + avgCostTicket10 + N );
        pw.print("11-30 --> " + avgCostTicket30 + N );
        pw.print("31+ --> " + avgCostTicket31PP + N );

        pw.println(" ");

        pw.println("הנמל שממנו העפילו לספינה:");
        pw.print("C – צ'רבורג --> " + avg_C + N );
        pw.print("Q – קווינסטאון --> " + avg_Q + N );
        pw.print("S – סאות'המפטון --> " + avg_S + N );


        JOptionPane.showMessageDialog(null, TEXT_19);
        pw.close();
    }

    private void performDataGrouping(){
        Map<String, Float> dataGroupMap = new HashMap<>();
        String selectedClass = dataGroupingComboBox.getSelectedItem().toString();
        float percentage = 0.0f;


        if (selectedClass.equals(TEXT_24)) {
            Map<Integer, Integer> classCountMap = new HashMap<>();

            for (Passenger passenger : passengerList) {
                classCountMap.put(passenger.getPClass(), classCountMap.getOrDefault(passenger.getPClass(), 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : classCountMap.entrySet()) {
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey()  + "|", percentage);
            }
        }

        if (selectedClass.equals(TEXT_3)) {
            Generic<String, Integer> genericSexCount = new Generic<>();

            for (Passenger passenger : passengerList) {
                genericSexCount.put(passenger.getSex(),genericSexCount.getGenericMap().getOrDefault(passenger.getSex(),0) + 1);
            }

            for (Map.Entry<String, Integer> entry : genericSexCount.getGenericMap().entrySet()) {
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey() + "|", percentage);
            }
        }

        if (selectedClass.equals(TEXT_4)){
            Generic<String,Integer> genericEmbarkedCount = new Generic<>();

            for (Passenger passenger : passengerList){
                genericEmbarkedCount.put(passenger.getEmbarked(),genericEmbarkedCount.getGenericMap().getOrDefault(passenger.getEmbarked(), 0) + 1);
            }

            for (Map.Entry<String,Integer> entry : genericEmbarkedCount.getGenericMap().entrySet()){
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey() + "|" ,percentage);
            }
        }

        if (selectedClass.equals(TEXT_8)){
            Generic<Integer,Integer> genericSibSpCount = new Generic<>();

            for (Passenger passenger : passengerList){
                genericSibSpCount.put(passenger.getSibSp(),genericSibSpCount.getGenericMap().getOrDefault(passenger.getSibSp(),0) + 1);
            }

            for (Map.Entry<Integer,Integer> entry : genericSibSpCount.getGenericMap().entrySet()){
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey() + "|", percentage);
            }
        }

        if (selectedClass.equals(TEXT_25)){
            Generic<String,Integer> genericSurvivedCount = new Generic<>();

            for (Passenger passenger : passengerList){
                genericSurvivedCount.put(passenger.getSurvivedString(),genericSurvivedCount.getGenericMap().getOrDefault(passenger.getSurvivedString(),0) + 1);
            }

            for (Map.Entry<String,Integer> entry : genericSurvivedCount.getGenericMap().entrySet()){
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey() + "|",percentage);
            }
        }

        if (selectedClass.equals(TEXT_9)){
            Generic<Integer,Integer> genericParchCount = new Generic<>();

            for (Passenger passenger : passengerList){
                genericParchCount.put(passenger.getParch(),genericParchCount.getGenericMap().getOrDefault(passenger.getParch(),0) + 1);
            }

            for (Map.Entry<Integer,Integer> entry : genericParchCount.getGenericMap().entrySet()){
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey() + "|", percentage);
            }
        }

        if (selectedClass.equals(TEXT_26)){
            Generic<Double,Integer> genericAgeCount = new Generic<>();

            for (Passenger passenger : passengerList){
                genericAgeCount.put(passenger.getAge(),genericAgeCount.getGenericMap().getOrDefault(passenger.getAge(),0) + 1);
            }

            for (Map.Entry<Double,Integer> entry : genericAgeCount.getGenericMap().entrySet()){
                percentage = percentage(entry.getValue());
                dataGroupMap.put("|" + entry.getKey() + "|" ,percentage);
            }
        }



        Map<String, Float> sortedDataGroupMap = dataGroupMap.entrySet().stream()
                .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        StringBuilder resultText = new StringBuilder(TOTAL).append(N);
        for (Map.Entry<String, Float> entry : sortedDataGroupMap.entrySet()) {
            resultText.append(entry.getKey()).append(" ").append(entry.getValue()).append("% " + N);
        }

        /**
         * print to console
         */
        System.out.println(resultText);
        /**
         * print to GUI
         */
        resultDataGroupLabel.setFont(MY_FONT_2);
        resultDataGroupLabel.setForeground(Color.GREEN);
        resultDataGroupLabel.setText(resultText.toString());
        /**
         * show result on the screen
         */
        JOptionPane.showMessageDialog(null,resultText);
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
     * change the PATH_TO_IMAGE_1
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