import javax.swing.*;
import java.awt.*;

/**
 * BrowserProductPane is a container for the SearchPanel and ListPanel.
 */
public class BrowseProductPane extends JPanel {
    // ListPanel is a panel that displays the list of products.
    private final ListPanel listPanel;

    /**
     * constructor
     * @param database the database
     * @param state the state
     * @param callback  the callback for when user selects a computer
     */
    public BrowseProductPane(Database database, State state, BrowseProductPaneCallback callback){
        // set layout to border layout
        setLayout(new BorderLayout());
        // create a new list panel
        listPanel = new ListPanel(database, state, new ListPanelCallback() {
            @Override
            public void selectionChange() {
                // when user click on a computer inside a list panel, it will pass back up to the callback
                callback.listSelectionChange();
            }
        });
        // add list panel it to south border
        this.add(listPanel, BorderLayout.SOUTH);

        // create a search panel
        SearchPanel searchPanel = new SearchPanel(database, state, new SearchPanelCallback() {
            @Override
            public void selectionChange() {
                // when selection change, when user click a category/type, we update the table.
                listPanel.populateTable();
            }
        });
        // add search panel to north border
        this.add(searchPanel, BorderLayout.NORTH);
    }

    /**
     * method to populate the table with current selections
     */
    public void updateList(){
        listPanel.populateTable();
    }
}
