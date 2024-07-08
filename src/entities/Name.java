package entities;

public class Name {
    private String firstName;
    private String lastName;
    private String degree;

    public Name(String name) {
        String[] parts = name.split(" ");
        this.lastName = parts[0];

        this.firstName ="";
        String[] nameParts = parts[1].split("'.'");
        this.degree = nameParts[0];
        for (int i = 2; i <= parts.length -1; i++) {
            if (parts[i] != null){
                this.firstName += parts[i] + " ";
            }
        }

    }

    public String getFormattedName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFullName(){
        return this.lastName + ", " + this.degree + " " + this.firstName;
    }


    @Override
    public String toString() {
        return this.lastName + ", " + this.degree + " " + this.firstName;
    }
}