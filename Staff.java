public interface Staff {
    /**
     * Check if the staff member can maintain computer records.
     * @return true if the staff member can maintain computer records.
     */
    boolean canMaintainComputerRecords();

    /**
     * get the username of the staff member.
     * @return the username of the staff member.
     */
    String getUsername();

    /**
     * check password of the staff member.
     * @param password the password to check.
     * @return true if the password is correct.
     */
    boolean checkPassword(String password);
}
