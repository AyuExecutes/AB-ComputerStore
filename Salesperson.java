public class Salesperson extends StaffBase {

    /**
     * Constructor for Salesperson class.
     * @param username the username
     * @param password the password
     */
    public Salesperson(String username, String password){
        super(username, password);
    }

    /**
     * Method to check if the salesperson can maintain computer records, which they cannot.
     * @return false.
     */
    public boolean canMaintainComputerRecords(){
        return false;
    }
}
