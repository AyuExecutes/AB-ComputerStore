/**
 * State class is used to store the current state of the application.
 */
public class State {
    // private attributes including the current logged-in user, selected category, type and computer
    private Staff loginUser;
    private String selectedCategory;
    private String selectedType;
    private Computer selectedComputer;

    /**
     * method to set the current logged-in user
     * @param user the user to be set as the current logged-in user
     */
    public void setLoginUser(Staff user){
        this.loginUser = user;
    }

    /**
     * method to get the current logged-in user
     * @return the current logged-in user
     */
    public Staff getLoginUser(){
        return this.loginUser;
    }

    /**
     * method to set the selected category
     * @param category the category to be set as the selected category
     */
    public void setSelectedCategory(String category) {
        this.selectedCategory = category;
    }

    /**
     * method to set the selected type
     * @param type the type to be set as the selected type
     */
    public void setSelectedType(String type) {
        this.selectedType = type;
    }

    /**
     * method to set the selected computer
     * @param computer the computer to be set as the selected computer
     */
    public void setSelectedComputer(Computer computer) {
        this.selectedComputer = computer;
    }

    /**
     * method to get the selected category
     * @return the selected category
     */
    public String getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * method to get the selected type
     * @return the selected type
     */
    public String getSelectedType() {
        return selectedType;
    }

    /**
     * method to get the selected computer
     * @return the selected computer
     */
    public Computer getSelectedComputer() {
        return selectedComputer;
    }
}
