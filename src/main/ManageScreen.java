package main;

import entities.Name;
import entities.Passenger;
import utils.Constants;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ManageScreen extends JPanel {
    private JComboBox<String> classComboBox;
    private List<Passenger> passengerList;
    private String titleLine;

    /**
     *  מגדיר את המסך של התפריט הראשי
     * @param x
     * @param y
     * @param width רוחב
     * @param height גובה
     */
    public ManageScreen(int x, int y, int width, int height) {

        this.passengerList = new ArrayList<>();
        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);

        // Load passenger data from CSV file
        loadPassengerData();
        System.out.println(titleLine);



        JLabel classLabel = new JLabel("entities.Passenger Class: ");
        classLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(classLabel);

        this.classComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
        this.classComboBox.setBounds(classLabel.getX() + classLabel.getWidth() + 1, classLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.classComboBox);


        this.classComboBox.addActionListener((e) -> {
            filterPassengers();
        });
    }

    private void loadPassengerData() {
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.PATH_TO_DATA_FILE))) {
            String line;
            String title = br.readLine();
            this.titleLine = title;
            while ((line = br.readLine()) != null) {
                try {

                    String[] values = line.split(",");
                    int passengerID = Integer.parseInt(values[0].trim());
                    int survived = Integer.parseInt(values[1].trim());
                    int pClass = Integer.parseInt(values[2].trim());
                    String nameStr1 = values[3].replace("\"", "");
                    String nameStr2 = values[4].replace("\"", "");
                    Name name = new Name(nameStr1 + nameStr2);
                    String sex = values[5].trim();
                    Double age = values[6].isEmpty() ? null : Double.parseDouble(values[6].trim());
                    int sibSp = Integer.parseInt(values[7].trim());
                    int parch = Integer.parseInt(values[8].trim());
                    String ticket = values[9].isEmpty() ? null : values[9].trim();
                    Double fare = values[10].isEmpty() ? null : Double.parseDouble(values[10].trim());
                    String cabin = values[11].isEmpty() ? null : values[11].trim();
                    String embarked = values[12].isEmpty() ? null : values[12].trim();


                    Passenger passenger = new Passenger(passengerID, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked);
                    this.passengerList.add(passenger);

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void filterPassengers(){
        String selectedClass = (String) this.classComboBox.getSelectedItem();
        List<Passenger> filteredAll = new ArrayList<>();
        List<Passenger> filtered1St = new ArrayList<>();
        List<Passenger> filtered2nd = new ArrayList<>();
        List<Passenger> filtered3rd = new ArrayList<>();
        List<Passenger> passengersID = new ArrayList<>();
        List<Passenger> nameList = new ArrayList<>();
        List<Passenger> sibSpList = new ArrayList<>();
        List<Passenger> parchList = new ArrayList<>();
        List<Passenger> fareList = new ArrayList<>();
        List<String> clear = new ArrayList<>();

        int number = 0;
        String nameSearch = "";
        int sibSpNumber = 0;
        int parchNumber = 0;
        double fareNumber = 0.0;

        if (selectedClass.equals("PassengerId") ){
            String numberStr = JOptionPane.showInputDialog("Enter the maximum PID range you want to see: ");
            number = Integer.parseInt(numberStr);
        }

        if (selectedClass.equals("entities.Name")) {
            String name = JOptionPane.showInputDialog("Enter the name: ");
            nameSearch = name;
            nameList = passengerList.stream()
                    .filter(passenger -> passenger.getFormattedName().contains(name))
                    .toList();
        }

        if (selectedClass.equals("SibSp")){
            String sibSpStr = JOptionPane.showInputDialog("Enter number of siblings/spouses: ");
            sibSpNumber = Integer.parseInt(sibSpStr);
        }

        if (selectedClass.equals("Parch")){
            String parchStr = JOptionPane.showInputDialog("Enter number of parch: ");
            parchNumber = Integer.parseInt(parchStr);
        }

        if (selectedClass.equals("Fare")){
            String fareStr = JOptionPane.showInputDialog("Enter the max cost ticket: ");
            fareNumber = Double.parseDouble(fareStr);
        }

        if (selectedClass.equals("Clear")){
            for (int i = 0; i <200 ; i++) {
                clear.add(" " + "\n");
            }
            clear.forEach(System.out::println);
        }


        for (Passenger passenger : this.passengerList) {
            if (selectedClass.equals("All")) {
                filteredAll.add(passenger);
            }
            if (selectedClass.equals("1st") && passenger.getpClass() == 1){
                filtered1St.add(passenger);
            }
            if (selectedClass.equals("2nd") && passenger.getpClass() == 2){
                filtered2nd.add(passenger);
            }
            if (selectedClass.equals("3rd") && passenger.getpClass() == 3){
                filtered3rd.add(passenger);
            }
            if (passenger.getPassengerID() <= number){
                passengersID.add(passenger);
            }
            if (passenger.getSibSp() == sibSpNumber){
                sibSpList.add(passenger);
            }
            if (passenger.getParch() == parchNumber){
                parchList.add(passenger);
            }
            if (passenger.getFare() <= fareNumber){
                fareList.add(passenger);
            }
        }
        if (selectedClass.equals("All")) {
            filteredAll.forEach(System.out::println);
        }
        if (selectedClass.equals("1st") ){
            filtered1St.forEach(System.out::println);
        }
        if (selectedClass.equals("2nd")){
            filtered2nd.forEach(System.out::println);
        }
        if (selectedClass.equals("3rd") ){
            filtered3rd.forEach(System.out::println);
        }
        if (selectedClass.equals("PassengerId") ){
            passengersID.forEach(System.out::println);
        }
        if (selectedClass.equals("entities.Name") ){
            nameList.forEach(System.out::println);
        }

        if (selectedClass.equals("SibSp") ){
            sibSpList.forEach(System.out::println);
        }
        if (selectedClass.equals("Parch")){
            parchList.forEach(System.out::println);
        }
        if (selectedClass.equals("Fare")){
            fareList.forEach(System.out::println);
        }


    }

}

