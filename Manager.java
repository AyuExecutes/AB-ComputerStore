public class Manager extends StaffBase {
    /**
     * constructor for Manager class.
     * @param username the username
     * @param password the password
     */
    public Manager(String username, String password){
        super(username, password);
    }

    /**
     * Method to check if the manager can maintain computer records, which they can.
     * @return true.
     */
    public boolean canMaintainComputerRecords(){
        return true;
    }
}
