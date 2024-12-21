public interface LoginAction {
    /**
     * method that is called when the login state changes
     * @param loggedIn true if logged in, false if logged out
     */
    void LoginStateChanged(boolean loggedIn);
}
