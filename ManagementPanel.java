import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * ManagementPanel is the base for BrowseProductPane and CheckUpdateProductDetailsPane.
 */
public class ManagementPanel extends JPanel {
    private final State state;
    private final JTabbedPane tabbedPane;

    /**
     * This is the constructor for ManagementPanel.
     * @param database the database
     * @param state the state
     */
    public ManagementPanel(Database database, State state){
        this.state = state;

        // management panel has border layout
        this.setLayout(new BorderLayout());
        // create a tabbed pane and add it to the management panel
        this.tabbedPane = new JTabbedPane();
        this.add(tabbedPane);

        // to create the check/update product details pane
        CheckUpdateProductDetailsPane checkUpdateProductDetailsPane = new CheckUpdateProductDetailsPane(database, state, new CheckUpdateProductDetailsPaneCallback() {
            @Override
            public void editCompleted() {
                // when the edit is completed, set the tabbed pane to the first tab
                tabbedPane.setSelectedIndex(0);
            }
        });

        // to create the browse product pane
        BrowseProductPane browseProductPane = new BrowseProductPane(database, state, new BrowseProductPaneCallback() {
            @Override
            public void listSelectionChange() {
                // if the user has not selected a computer, return
                if (state.getSelectedComputer() == null){
                    return;
                }
                // otherwise when user clicked a computer, set the tabbed pane to the second tab
                tabbedPane.setSelectedIndex(1);
                // then update the details on the second tab
                checkUpdateProductDetailsPane.displaySelectedComputer();
                // then check the user's permissions
                checkUpdateProductDetailsPane.checkPermissions();
            }
        });

        // create two tabs for the two panes
        tabbedPane.addTab("Browse Product", browseProductPane);
        tabbedPane.addTab("Check/Update Product Details", checkUpdateProductDetailsPane);
        // adding a change listener to the tabbed pane
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // if the selected component is the browse product pane
                if (tabbedPane.getSelectedComponent() == browseProductPane){
                    // update the list every time making sure any changes user selected on the second tab are reflected
                    browseProductPane.updateList();
                }
            }
        });
    }

    /**
     * method enables/disables the second tab depending on the logged-in user's permissions.
     */
    public void checkUserPermissions() {
        // first, get the user that is logged in
        Staff loggedInUser = this.state.getLoginUser();
        // if they are not logged in, return
        if (loggedInUser == null) {
            return;
        }
        // otherwise enable/disable the second tab depending on the user's permissions
        tabbedPane.setEnabledAt(1, loggedInUser.canMaintainComputerRecords());
    }

    /**
     * This method is to reset the tabbed pane to the first tab.
     */
    public void reset() {
        tabbedPane.setSelectedIndex(0);
    }
}
