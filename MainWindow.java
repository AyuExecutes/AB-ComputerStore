import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame mainFrame;

    /**
     * Constructor for the MainWindow class.
     * @param database the Database object to be passed to the MainWindow
     */
    public MainWindow(Database database){
        State state = new State();

        // create a new JFrame object and set its properties for the main window
        mainFrame = new JFrame("AB Computer Inventory Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        // MainWindow has a ManagementPanel on North Layout, and initially set to invisible (when user is not logged in)
        ManagementPanel managementPanel = new ManagementPanel(database, state);
        mainFrame.add(managementPanel, BorderLayout.NORTH);
        managementPanel.setVisible(false);

        // MainWindow also has a LoginLogoutPanel on South Layout
        LoginLogoutPanel loginLogoutPanel = new LoginLogoutPanel(mainFrame, database, state, (loggedIn) -> {
            // if the user is logged in, show the management panel and check the user permissions
            if (loggedIn) {
                managementPanel.setVisible(true);
                managementPanel.checkUserPermissions();
            } else {
                // otherwise, hide the management panel and reset it.
                managementPanel.setVisible(false);
                // next time management panel is opened, it will show the first tabbed pane (fresh start)
                managementPanel.reset();
            }
            // reorganise the window and move it to the centre after each mode
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
        });

        // LoginLogoutPanel is initially set to LOGIN mode
        loginLogoutPanel.show(LoginLogoutMode.LOGIN);

        // add the loginLogoutPanel to the main frame
        mainFrame.add(loginLogoutPanel, BorderLayout.SOUTH);

        // pack the main frame and set its location to the centre of the screen
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
