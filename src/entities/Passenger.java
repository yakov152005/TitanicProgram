package entities;

import java.util.Comparator;

import static utils.Constants.Text.TEXT_25;

public class Passenger implements Comparator<Passenger> {
    private int passengerID;
    private int survived;
    private int pClass;
    private Name name;
    private String sex;
    private Double age;
    private int sibSp;
    private int parch;
    private String ticket;
    private Double fare;
    private String  cabin;
    private String embarked;


    public Passenger(int passengerID, int survived, int pClass, Name name, String sex, Double age, int sibSp, int parch, String ticket, Double fare, String cabin, String embarked) {
        this.passengerID = passengerID;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;

    }

    public int getPassengerID() {
        return passengerID;
    }

    public int getSurvived() {
        return survived;
    }

    public String getSurvivedString(){
        if (isSurvived()){
            return TEXT_25;
        }
        return "Not " + TEXT_25;
    }
    public boolean isSurvived(){
        if (getSurvived() == 1){
            return true;
        }
        return false;
    }

    public int getPClass() {
        return pClass;
    }

    public Name getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Double getAge() {
        return age;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public Double getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmbarked() {
        return embarked;
    }
    public String getFormattedName(){
        return this.name.getFormattedName();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("PassengerId: ").append(this.passengerID)
                .append(", Survived? ").append((this.survived == 1 ? "true" : "false"))
                .append(", Pclass: ").append(this.pClass)
                .append(", Name: ").append(getName())
                .append(", Sex: ").append(this.sex)
                .append(", Age: ").append(this.age)
                .append(", SibSp: ").append(sibSp)
                .append(", Parch: ").append(this.parch)
                .append(", Ticket: ").append(this.ticket)
                .append(", Fare: ").append(this.fare)
                .append(", Cabin: ").append(this.cabin)
                .append(", Embarked: ").append(this.embarked);
        return sb.toString();
    }

    @Override
    public int compare(Passenger passenger, Passenger other) {
        return passenger.name.getFormattedName().compareTo(other.name.getFormattedName());
    }
}
