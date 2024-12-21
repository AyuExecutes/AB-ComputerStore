import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckUpdateProductDetailsPane  extends JPanel  {
    // set up all the necessary member fields
    private final Database database;
    private final State state;

    private final JTextField modelIdTextField;
    private final JComboBox<String> categoryCombo;
    private final JComboBox<String> typeCombo;
    private final JTextField brandTextField;
    private final JTextField cpuFamilyTextField;
    private final JTextField memorySizeTextField;
    private final JTextField ssdCapacityTextField;
    private final JTextField screenSizeTextField;
    private final JTextField priceTextField;

    private final JLabel memorySizeLabel;
    private final JLabel ssdCapacityLabel;
    private final JLabel screenSizeLabel;

    private final JButton addButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    private final JButton clearButton;

    /**
     * constructor
     * @param database the database
     * @param state the state
     * @param callback the callback to the tell parent computer has been edited
     */
    public CheckUpdateProductDetailsPane(Database database, State state, CheckUpdateProductDetailsPaneCallback callback){
        this.database = database;
        this.state = state;

        // set grid layout of 11 x 2
        this.setLayout(new GridLayout(11, 2));

        // row 0 would be model id
        JLabel modelIdLabel = new JLabel("Model ID");
        this.add(modelIdLabel);

        modelIdTextField = new JTextField();
        this.add(modelIdTextField);

        // row 1 would be category
        JLabel categoryLabel = new JLabel("Category");
        this.add(categoryLabel);

        categoryCombo = new JComboBox<>();
        this.add(categoryCombo);

        // add action listener to category combo box
        categoryCombo.addActionListener(e -> {
            String category = (String) categoryCombo.getSelectedItem();
            populateTypeCombo(category);
        });

        // row 2 would be type
        JLabel typeLabel = new JLabel("Type");
        this.add(typeLabel);

        typeCombo = new JComboBox<>();
        this.add(typeCombo);

        // row 3 would be the brand
        JLabel brandLabel = new JLabel("Brand");
        this.add(brandLabel);

        brandTextField = new JTextField();
        this.add(brandTextField);

        // row 4 would be cpu family
        JLabel cpuFamilyLabel = new JLabel("CPU Family");
        this.add(cpuFamilyLabel);

        cpuFamilyTextField = new JTextField();
        this.add(cpuFamilyTextField);

        // row 5 would be memory size
        memorySizeLabel = new JLabel("Memory Size");
        this.add(memorySizeLabel);

        memorySizeTextField = new JTextField();
        this.add(memorySizeTextField);

        // row 6 would be ssd capacity
        ssdCapacityLabel = new JLabel("SSD Capacity");
        this.add(ssdCapacityLabel);

        ssdCapacityTextField = new JTextField();
        this.add(ssdCapacityTextField);

        // row 7 would be screen size
        screenSizeLabel = new JLabel("Screen Size");
        this.add(screenSizeLabel);

        screenSizeTextField = new JTextField();
        this.add(screenSizeTextField);

        // row 8 would be price
        JLabel priceLabel = new JLabel("Price");
        this.add(priceLabel);

        priceTextField = new JTextField();
        this.add(priceTextField);

        // row 9 would be buttons for add and update
        this.addButton = new JButton("Add");
        this.add(addButton);

        // add action listener to add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewComputer();
                    JOptionPane.showMessageDialog(CheckUpdateProductDetailsPane.this, "The record for the computer is added successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    callback.editCompleted();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CheckUpdateProductDetailsPane.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.updateButton = new JButton("Update");
        this.add(updateButton);

        // add action listener to update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComputerFromFields();
                JOptionPane.showMessageDialog(CheckUpdateProductDetailsPane.this, "The record for the computer is updated successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                callback.editCompleted();
            }
        });

        // row 10 would be buttons for delete and clear
        this.deleteButton = new JButton("Delete");
        this.add(deleteButton);

        // add action listener to delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedComputer();
                clearFields();
                JOptionPane.showMessageDialog(CheckUpdateProductDetailsPane.this, "The record for the computer is deleted successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                callback.editCompleted();
            }
        });

        // add action listener to clear button
        this.clearButton = new JButton("Clear");
        this.add(clearButton);

        // add action listener to clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    /**
     * method is to check the permissions of user and enable or disable the fields depending on the permissions
     */
    public void checkPermissions() {
        // check the logged-in user to see if they are logged in which state is remembered
        Staff loggedInUser = this.state.getLoginUser();
        // if the logged-in user is null, then return
        if (loggedInUser == null) {
            return;
        }

        // if the logged-in user is a salesperson, then they can only view the computer records and not edit them.
        // if the logged-in user is a manager, then they can view and edit the computer records.

        // fields
        modelIdTextField.setEnabled(loggedInUser.canMaintainComputerRecords());
        categoryCombo.setEnabled(loggedInUser.canMaintainComputerRecords());
        typeCombo.setEnabled(loggedInUser.canMaintainComputerRecords());
        brandTextField.setEnabled(loggedInUser.canMaintainComputerRecords());
        cpuFamilyTextField.setEnabled(loggedInUser.canMaintainComputerRecords());
        memorySizeTextField.setEnabled(loggedInUser.canMaintainComputerRecords());
        ssdCapacityTextField.setEnabled(loggedInUser.canMaintainComputerRecords());
        screenSizeTextField.setEnabled(loggedInUser.canMaintainComputerRecords());
        priceTextField.setEnabled(loggedInUser.canMaintainComputerRecords());

        // buttons
        addButton.setEnabled(loggedInUser.canMaintainComputerRecords());
        updateButton.setEnabled(loggedInUser.canMaintainComputerRecords());
        deleteButton.setEnabled(loggedInUser.canMaintainComputerRecords());
        clearButton.setEnabled(loggedInUser.canMaintainComputerRecords());
    }

    /**
     * method is to populate the category combo box
     */
    private void populateCategoryCombo(){
        // in case calling this multiple times, clear it first to avoid duplicates
        categoryCombo.removeAllItems();
        for (String category : this.database.getCategories()) {
            categoryCombo.addItem(category);
        }
    }

    /**
     * method to populate the type combo box
     * @param category the category
     */
    private void populateTypeCombo(String category){
        // in case calling this multiple times, clear it first to avoid duplicates
        typeCombo.removeAllItems();
        // if category is null, then return
        if (category == null){
            return;
        }

        for (String type : this.database.getTypesByCategory(category)){
            typeCombo.addItem(type);
        }
    }

    /**
     * method to display the selected computer
     */
    public void displaySelectedComputer(){
        // get the selected computer from the state
        Computer computer = state.getSelectedComputer();
        // if null/nothing selected, we clear the fields
        if (computer == null){
            modelIdTextField.setText("");
            categoryCombo.setSelectedItem(null);
            typeCombo.setSelectedItem(null);
            brandTextField.setText("");
            cpuFamilyTextField.setText("");
            memorySizeTextField.setText("");
            ssdCapacityTextField.setText("");
            screenSizeTextField.setText("");
            priceTextField.setText("");

            memorySizeLabel.setVisible(false);
            memorySizeTextField.setVisible(false);
            ssdCapacityLabel.setVisible(false);
            ssdCapacityTextField.setVisible(false);
            screenSizeLabel.setVisible(false);
            screenSizeTextField.setVisible(false);

            return;
        }

        // update the combos
        populateCategoryCombo();
        populateTypeCombo(computer.getCategory());

        // otherwise, populate the fields with the selected computer's details
        modelIdTextField.setText(computer.getId());
        categoryCombo.setSelectedItem(computer.getCategory());
        typeCombo.setSelectedItem(computer.getType());
        brandTextField.setText(computer.getBrand());
        cpuFamilyTextField.setText(computer.getCpuFamily());
        priceTextField.setText(String.valueOf(computer.getPrice()));

        // for the additional fields which are special to each category, disabled them by default first.
        memorySizeLabel.setEnabled(false);
        ssdCapacityLabel.setEnabled(false);
        screenSizeLabel.setEnabled(false);

        memorySizeTextField.setEnabled(false);
        ssdCapacityTextField.setEnabled(false);
        screenSizeTextField.setEnabled(false);

        memorySizeTextField.setText("");
        ssdCapacityTextField.setText("");
        screenSizeTextField.setText("");


         // check one by one either DesktopPC, Laptop or Tablet then enable the additional fields accordingly.
         // then populate the additional fields with the selected computer's details.


        // use instanceof to check the type of computer
        if (computer instanceof Laptop){
            // cast the computer to Laptop
            Laptop laptop = (Laptop) computer;
            memorySizeLabel.setEnabled(true);
            ssdCapacityLabel.setEnabled(true);
            screenSizeLabel.setEnabled(true);

            memorySizeTextField.setEnabled(true);
            ssdCapacityTextField.setEnabled(true);
            screenSizeTextField.setEnabled(true);

            memorySizeTextField.setText(String.valueOf(laptop.getMemorySize()));
            ssdCapacityTextField.setText(String.valueOf(laptop.getSsdCapacity()));
            screenSizeTextField.setText(String.valueOf(laptop.getScreenSize()));
        }

        if (computer instanceof DesktopPC){
            // cast the computer to DesktopPC
            DesktopPC desktopPC = (DesktopPC) computer;
            memorySizeTextField.setText(String.valueOf(desktopPC.getMemorySize()));
            ssdCapacityTextField.setText(String.valueOf(desktopPC.getSsdCapacity()));

            memorySizeLabel.setEnabled(true);
            ssdCapacityLabel.setEnabled(true);

            memorySizeTextField.setEnabled(true);
            ssdCapacityTextField.setEnabled(true);
        }

        if (computer instanceof Tablet) {
            // cast the computer to Tablet
            Tablet tablet = (Tablet) computer;
            screenSizeTextField.setText(String.valueOf(tablet.getScreenSize()));
            screenSizeLabel.setEnabled(true);
            screenSizeTextField.setEnabled(true);
        }
    }

    /**
     * method to clear and disabled fields and labels
     */
    public void clearFields(){
        modelIdTextField.setText("");
        categoryCombo.setSelectedItem(null);
        typeCombo.setSelectedItem(null);
        brandTextField.setText("");
        cpuFamilyTextField.setText("");
        memorySizeTextField.setText("");
        ssdCapacityTextField.setText("");
        screenSizeTextField.setText("");
        priceTextField.setText("");

        memorySizeLabel.setEnabled(false);
        ssdCapacityLabel.setEnabled(false);
        screenSizeLabel.setEnabled(false);

        memorySizeTextField.setEnabled(false);
        ssdCapacityTextField.setEnabled(false);
        screenSizeTextField.setEnabled(false);
    }

    /**
     * method to delete the selected computer from database
     */
    public void deleteSelectedComputer(){
        // get the computer that is selected from the state
        Computer computer = state.getSelectedComputer();
        // if null/nothing selected, return
        if (computer == null){
            return;
        }
        // otherwise, delete the computer from the database
        database.deleteComputer(computer);
        // clear the state's selected computer
        state.setSelectedComputer(null);
    }

    /**
     * method to add a new computer to the database
     * @throws Exception when the category and type are not selected or the model ID already exists
     */
    public void addNewComputer() throws Exception {
        // if the category combo has nothing selected or the type also has nothing selected, then throw an exception
        if (categoryCombo.getSelectedItem() == null || typeCombo.getSelectedItem() == null) {
            throw new Exception("Category and Type must be selected");
        }
        // ensure id is unique and not already exists
        if (database.getComputerById(modelIdTextField.getText()) != null) {
            throw new Exception("Model ID already exists");
        }

        // declare new computer to add to the database
        Computer computer;
        // depending on which category, create the respective computer object
        switch ((String) categoryCombo.getSelectedItem()) {
            case "Desktop PC":
                computer = new DesktopPC(
                    categoryCombo.getSelectedItem().toString(),
                    typeCombo.getSelectedItem().toString(),
                    modelIdTextField.getText(),
                    brandTextField.getText(),
                    cpuFamilyTextField.getText(),
                    Double.parseDouble(priceTextField.getText()),
                    Integer.parseInt(memorySizeTextField.getText()),
                    Integer.parseInt(ssdCapacityTextField.getText())
                );
                break;

            case "Laptop":
                computer = new Laptop(
                    categoryCombo.getSelectedItem().toString(),
                    typeCombo.getSelectedItem().toString(),
                    modelIdTextField.getText(),
                    brandTextField.getText(),
                    cpuFamilyTextField.getText(),
                    Double.parseDouble(priceTextField.getText()),
                    Integer.parseInt(memorySizeTextField.getText()),
                    Integer.parseInt(ssdCapacityTextField.getText()),
                    Double.parseDouble(screenSizeTextField.getText())
                );
                break;

            case "Tablet":
                computer = new Tablet(
                    categoryCombo.getSelectedItem().toString(),
                    typeCombo.getSelectedItem().toString(),
                    modelIdTextField.getText(),
                    brandTextField.getText(),
                    cpuFamilyTextField.getText(),
                    Double.parseDouble(priceTextField.getText()),
                    Double.parseDouble(screenSizeTextField.getText())
                );
                break;

                // if the category is not one of the above, throw an exception
            default:
                throw new Exception("Invalid Category");
        }

        // then add the new computer to the database
        database.addComputer(computer);
    }

    /**
     * method to update the selected computer from the fields
     */
    public void updateComputerFromFields(){
        // get the selected computer from the state
        Computer computer = state.getSelectedComputer();
        // if null, just return
        if (computer == null){
            return;
        }

        // otherwise, set the base fields of the computer (common fields)
        computer.setId(modelIdTextField.getText());
        computer.setCategory((String) categoryCombo.getSelectedItem());
        computer.setType((String) typeCombo.getSelectedItem());
        computer.setBrand(brandTextField.getText());
        computer.setCpuFamily(cpuFamilyTextField.getText());
        computer.setPrice(Double.parseDouble(priceTextField.getText()));

        // depending on the type, set the additional fields
        if (computer instanceof Laptop){
            Laptop laptop = (Laptop) computer;
            laptop.setMemorySize(Integer.parseInt(memorySizeTextField.getText()));
            laptop.setSsdCapacity(Integer.parseInt(ssdCapacityTextField.getText()));
            laptop.setScreenSize(Double.parseDouble(screenSizeTextField.getText()));
        }

        if (computer instanceof DesktopPC){
            DesktopPC desktopPC = (DesktopPC) computer;
            desktopPC.setMemorySize(Integer.parseInt(memorySizeTextField.getText()));
            desktopPC.setSsdCapacity(Integer.parseInt(ssdCapacityTextField.getText()));
        }

        if (computer instanceof Tablet){
            Tablet tablet = (Tablet) computer;
            tablet.setScreenSize(Double.parseDouble(screenSizeTextField.getText()));
        }
    }
}
