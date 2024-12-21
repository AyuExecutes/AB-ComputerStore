public interface LoginDialogCallback {
    /**
     * method is called when the user attempts to log in
     * @param username the username
     * @param password the password
     * @return true if the login is successful, false otherwise
     */
    boolean loginUser(String username, String password);
}
