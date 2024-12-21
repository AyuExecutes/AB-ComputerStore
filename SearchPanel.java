import javax.swing.*;
import java.awt.*;

/**
 * SearchPanel is to allow user to find a computer by category and types.
 */
public class SearchPanel extends JPanel{
    private final Database database;
    private final JComboBox<String> computerCategoryCombo;
    private final JComboBox<String> computerTypeCombo;

    /**
     * Constructor
     * @param database the database
     * @param state the state
     * @param callback the callback when selection changes
     */
    public SearchPanel(Database database, State state, SearchPanelCallback callback){
        this.database = database;
        // set the layout to grid layout of 2 x 2
        this.setLayout(new GridLayout(2, 2));
        // create a label for the first row and add it to the panel
        JLabel computerCategoryLabel = new JLabel("Computer Category");
        this.add(computerCategoryLabel);

        computerCategoryCombo = new JComboBox<>();
        this.add(computerCategoryCombo);

        // add an action listener to the combo box
        computerCategoryCombo.addActionListener(e -> {
            // get the selected item as a string which is the category they picked
            String category = (String) computerCategoryCombo.getSelectedItem();
            // populate the type combo box based on the category picked
            populateTypeCombo(category);
            // remember the selected category
            state.setSelectedCategory(category);
            // callback to the parent that the selection has changed
            callback.selectionChange();
        });

        // create a label for the second row and add it to the panel
        JLabel computerTypeLabel = new JLabel("Computer Type");
        this.add(computerTypeLabel);

        computerTypeCombo = new JComboBox<>();
        this.add(computerTypeCombo);
        // add an action listener to the combo box
        computerTypeCombo.addActionListener(e -> {
            // when user select the type, get it as a string
            String type = (String) computerTypeCombo.getSelectedItem();
            // if user picked the selected item, set type to null (no selection)
           if (type == null || type.startsWith("Select")){
               type = null;
           }
           // remember the selected type
           state.setSelectedType(type);
           // callback to the parent that the selection has changed
           callback.selectionChange();
        });
        // fills out all the options for the category combo box
        populateCategoryCombo();
    }

    /**
     * Method to fills out all the options for the category combo box
     */
    private void populateCategoryCombo(){
        computerCategoryCombo.addItem("All");
        for (String category : this.database.getCategories()) {
            computerCategoryCombo.addItem(category);
        }
    }

    /**
     * Method to populate the type combo box
     * @param category the category
     */
    private void populateTypeCombo(String category){
        // clear the previous items in the combo box
        computerTypeCombo.removeAllItems();

        if (category.equals("All")){
            return;
        }

        computerTypeCombo.addItem(String.format("Select %s type", category));
        // get all the types for this category and adding them to the box
        for (String type : this.database.getTypesByCategory(category)){
            computerTypeCombo.addItem(type);
        }
    }
}
