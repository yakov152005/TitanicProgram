import entities.Name;
import entities.Passenger;

/**
 * for check i delete this main after all
 */
public class Main {
    public static void main(String[] args) {

        String name1 = "Frauenthal, Dr. Henry William";
        String name2 = "Soholt, Mr. Peter Andreas Lauritz Andersen";

        Name formattedName1 = new Name(name1);
        Name formattedName2 = new Name(name2);

        System.out.println(formattedName1.getFormattedName()); // Output: William Alfred Brocklebank
        System.out.println(formattedName2.getFormattedName()); // Output: Peter Andreas Lauritz Andersen Soholt

        Passenger passenger = new Passenger(1,1,3,formattedName1,"male",22.2,1,0,"A/5 21171"
        ,7.25,"E43","S");

        System.out.println(passenger);

        clearConsole();



    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
