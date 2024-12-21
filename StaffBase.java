public abstract class StaffBase implements Staff {
    // Each staff member has a username and password.
    private final String username;
    private final String password;

    /**
     * Constructor
     * @param username the username
     * @param password the password
     */
    public StaffBase(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * abstract method to check if the staff member can maintain computer records.
     * @return true if the staff member can maintain computer records, false otherwise.
     */
    public abstract boolean canMaintainComputerRecords();

    /**
     * Method to get the username of the staff member.
     * @return the username of the staff member.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Method to check if the password is correct.
     * @param password the password to check.
     * @return true if the password is correct, false otherwise.
     */
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
