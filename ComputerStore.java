import javax.swing.*;
import static java.lang.System.exit;

public class ComputerStore {
    public static void main(String[] args){
        try {
            Database database = new Database();
            database.loadFromFile("computers.txt"); // load the data from the .txt file

            /*
             * Add some staff members to the database
             * 3 salespersons and 2 managers.
             */
            for (int i = 1; i < 4; i++) {
                String usernameAndPassword = String.format("sp%d", i);       // sp1, sp2, sp3
                Staff salesperson = new Salesperson(usernameAndPassword, usernameAndPassword);
                database.addStaffMember(salesperson);
            }

            for (int i = 1; i < 3; i++) {
                String usernameAndPassword = String.format("m%d", i);      // m1, m2
                Staff manager = new Manager(usernameAndPassword, usernameAndPassword);
                database.addStaffMember(manager);
            }

            /*
             * Create the main window and pass the database to it.
             */
            SwingUtilities.invokeLater(() -> {
                new MainWindow(database);
            });

        } catch (RuntimeException e) {
            exit(1);
        }
    }
}
