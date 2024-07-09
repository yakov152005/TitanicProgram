package main;

import entities.Name;
import entities.Passenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static utils.Constants.ManageScreen.*;
import static utils.Constants.Text.*;

public class ManageScreen extends JPanel {
    private static AtomicInteger csvCounter = new AtomicInteger();

    private JComboBox<String> classComboBox;
    private JComboBox<String> sexComboBox;
    private JComboBox<String> embarkedComboBox;

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
    private JButton crateStatisticFile;
    private JButton exitButton;


    private JLabel resultLabel;

    private List<Passenger> passengerList;
    private String titleLine;
    private int csvC = 0;

    public ManageScreen(int x, int y, int width, int height) {
        this.passengerList = new ArrayList<>();
        this.setLayout(null);
        this.setBounds(x, y + MARGIN_FROM_TOP, width, height);


        loadPassengerData();
        System.out.println(titleLine);

        JLabel classLabel = new JLabel(TEXT_2);
        classLabel.setBounds(x + MARGIN_FROM_LEFT, y, LABEL_WIDTH, LABEL_HEIGHT);
        this.add(classLabel);

        this.classComboBox = new JComboBox<>(PASSENGER_CLASS_OPTIONS);
        this.classComboBox.setBounds(classLabel.getX() + classLabel.getWidth() + 1, classLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.classComboBox);


        JLabel sexLabel = new JLabel(TEXT_3);
        sexLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 2), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(sexLabel);

        this.sexComboBox = new JComboBox<>(PASSENGER_SEX_OPTIONS);
        this.sexComboBox.setBounds(sexLabel.getX() + sexLabel.getWidth() + 1, sexLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.sexComboBox);

        JLabel embarkedLabel = new JLabel(TEXT_4);
        embarkedLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 4), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(embarkedLabel);

        this.embarkedComboBox = new JComboBox<>(PASSENGER_EMBARKED_OPTIONS);
        this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth() + 1, embarkedLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.embarkedComboBox);

        JLabel minPassengerIdLabel = new JLabel(TEXT_5);
        minPassengerIdLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 6), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(minPassengerIdLabel);

        this.minPassengerIdField = new JTextField();
        this.minPassengerIdField.setBounds(minPassengerIdLabel.getX() + minPassengerIdLabel.getWidth() + 1, minPassengerIdLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.minPassengerIdField);

        JLabel maxPassengerIdLabel = new JLabel(TEXT_6);
        maxPassengerIdLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 8), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(maxPassengerIdLabel);

        this.maxPassengerIdField = new JTextField();
        this.maxPassengerIdField.setBounds(maxPassengerIdLabel.getX() + maxPassengerIdLabel.getWidth() + 1, maxPassengerIdLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.maxPassengerIdField);

        JLabel nameLabel = new JLabel(TEXT_7);
        nameLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 10), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(nameLabel);

        this.nameField = new JTextField();
        this.nameField.setBounds(nameLabel.getX() + nameLabel.getWidth() + 1, nameLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.nameField);

        JLabel sibSpLabel = new JLabel(TEXT_8);
        sibSpLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 12), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(sibSpLabel);

        this.sibSpField = new JTextField();
        this.sibSpField.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth() + 1, sibSpLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.sibSpField);

        JLabel parchLabel = new JLabel(TEXT_9);
        parchLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 14), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(parchLabel);

        this.parchField = new JTextField();
        this.parchField.setBounds(parchLabel.getX() + parchLabel.getWidth() + 1, parchLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.parchField);

        JLabel ticketLabel = new JLabel(TEXT_10);
        ticketLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_LEFT * 16), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(ticketLabel);

        this.ticketField = new JTextField();
        this.ticketField.setBounds(ticketLabel.getX() + ticketLabel.getWidth() + 1, ticketLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.ticketField);

        JLabel minFareLabel = new JLabel(TEXT_11);
        minFareLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 120), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(minFareLabel);

        this.minFareField = new JTextField();
        this.minFareField.setBounds(minFareLabel.getX() + minFareLabel.getWidth() + 1, minFareLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.minFareField);

        JLabel maxFareLabel = new JLabel(TEXT_12);
        maxFareLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 80), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(maxFareLabel);

        this.maxFareField = new JTextField();
        this.maxFareField.setBounds(maxFareLabel.getX() + maxFareLabel.getWidth() + 1, maxFareLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.maxFareField);

        JLabel cabinLabel = new JLabel(TEXT_13);
        cabinLabel.setBounds(x + MARGIN_FROM_LEFT, y + (MARGIN_FROM_RIGHT - 40), LABEL_WIDTH, LABEL_HEIGHT);
        this.add(cabinLabel);

        this.cabinField = new JTextField();
        this.cabinField.setBounds(cabinLabel.getX() + cabinLabel.getWidth() + 1, cabinLabel.getY(), COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
        this.add(this.cabinField);

        this.filterButton = new JButton(TEXT_14);
        this.filterButton.setBounds(x + MARGIN_FROM_LEFT, y + MARGIN_FROM_RIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(this.filterButton);

        this.crateStatisticFile = new JButton(TEXT_21);
        this.crateStatisticFile.setBounds(x + (MARGIN_FROM_LEFT * 9) + 10, y + MARGIN_FROM_RIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.add(crateStatisticFile);

        this.exitButton = new JButton(TEXT_18);
        this.exitButton.setBounds(x + (MARGIN_FROM_LEFT * 18), y + MARGIN_FROM_RIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.add(exitButton);


        this.resultLabel = new JLabel(TEXT_15);
        this.resultLabel.setBounds(x + MARGIN_FROM_LEFT, y + MARGIN_FROM_RIGHT + (MARGIN_FROM_LEFT * 2), RESULT_WIDTH, RESULT_HEIGHT);
        this.add(this.resultLabel);

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

        this.crateStatisticFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performStatisticFileTxt();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
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
                    int passengerID = Integer.parseInt(values[0].isEmpty() ? N_0 :values[0].trim());
                    int survived = Integer.parseInt(values[1].isEmpty() ? N_0 :values[1].trim());
                    int pClass = Integer.parseInt(values[2].isEmpty() ? N_0 :values[2].trim());
                    String nameStr1 = values[3].replace("\"", "");
                    String nameStr2 = values[4].replace("\"", "");
                    Name name = new Name(nameStr1 + nameStr2);
                    String sex = values[5].isEmpty() ? NULL : values[5].trim();
                    Double age = values[6].isEmpty() ? null : Double.parseDouble(values[6].trim());
                    int sibSp = Integer.parseInt(values[7].isEmpty() ? N_0 :values[7].trim());
                    int parch = Integer.parseInt(values[8].trim());
                    String ticket = values[9].isEmpty() ? NULL : values[9].trim();
                    Double fare = values[10].isEmpty() ? null : Double.parseDouble(values[10].trim());
                    String cabin = values[11].isEmpty() ? NULL : values[11].trim();
                    String embarked = values[12].isEmpty() ? NULL : values[12].trim();

                    Passenger passenger = new Passenger(passengerID, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked);

                    this.passengerList.add(passenger);

                } catch (NumberFormatException e) {System.err.println(TEXT_16 + line);}
            }
        } catch (Exception e) {System.out.println(e.getMessage());}
    }

    private void performStatisticFileTxt() throws IOException {
        List<Passenger> statisticList = passengerList;

        int count1st = 0, count2nd = 0, count3rd = 0;
        int sumSurvived1st = 0, sumSurvived2nd = 0, sumSurvived3rd = 0;

        for (Passenger passenger : statisticList){
            if (passenger.getPClass() == 1){
                count1st++;
            } else if (passenger.getPClass() == 2) {
                count2nd++;
            }else if (passenger.getPClass() == 3){
                count3rd++;
            }
        }

        for (Passenger passenger : statisticList){
            if (passenger.isSurvived() && passenger.getPClass() == 1){
                sumSurvived1st++;
            } else if (passenger.isSurvived() && passenger.getPClass() == 2){
                sumSurvived2nd++;
            }else if (passenger.isSurvived() && passenger.getPClass() == 3){
                sumSurvived3rd++;
            }
        }

        float avg1st = (float) sumSurvived1st / count1st * 100 ;
        float avg2nd = (float) sumSurvived2nd / count2nd * 100;
        float avg3rd = (float) sumSurvived3rd / count3rd * 100;
        System.out.println("1 --->" + sumSurvived1st +" count: " + count1st);
        System.out.println("2--->" + sumSurvived2nd +" count: " + count2nd);
        System.out.println("3--->" + sumSurvived3rd +" count: " + count3rd);
        FileWriter fw = new FileWriter(PATH + "Statistic.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.println("אחוזי הישרדות לפי מחלקה: ");
        pw.print("1st -- >" + avg1st + "\n");
        pw.print("2nd --> " + avg2nd + "\n");
        pw.print("3rd --> " + avg3rd + "\n");
        pw.close();
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

        resultLabel.setText("Total: " + totalPassengers + ", Survived: " + survivedCount + ", Not Survived: " + notSurvivedCount);
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
}



