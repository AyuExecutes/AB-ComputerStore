import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * LoginLogoutPanel is the panel for displaying logo and login/logout button.
 */
public class LoginLogoutPanel extends JPanel {
    private final JButton button;
    private LoginLogoutMode currentMode;

    /**
     * Constructor for LoginLogoutPanel
     * @param mainFrame the main frame
     * @param database the database
     * @param state the state
     * @param loginAction is a callback when login state changes
     */
    public LoginLogoutPanel(JFrame mainFrame, Database database, State state, LoginAction loginAction){
        this.setLayout(new BorderLayout());
        BufferedImage image = null;

        try {
            // read image from file
            image = ImageIO.read(new File("AB_ComputerStore.png"));
            // scale the image to fit the panel
            Image scaledImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            // create an ImageIcon from the scaled image
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            // create a JLabel to display the image
            JLabel imageLabel = new JLabel(imageIcon);
            // add the image to the panel and set it to the left
            this.add(imageLabel, BorderLayout.WEST);

            // create a new button
            this.button = new JButton("Click to login");
            // create a specific font for the button and size
            Font buttonFont = new Font("Arial", Font.BOLD, 20);
            this.button.setFont(buttonFont);

            // add an action listener to the button
            this.button.addActionListener(e -> {
                // firstly, check the current mode which is either login or logout
                switch (this.currentMode) {
                    // when the current mode is LOGIN (meaning when user try to log in)
                    case LOGIN:
                        // create a new login dialog
                        LoginDialog loginDialog = new LoginDialog(mainFrame, ((username, password) -> {
                            // go to database and find the user by username
                            Staff user = database.getUserByUsername(username);
                            // if user is not found, return false
                            if (user == null) {
                                // false means cannot close the dialog (failed login)
                                return false;
                            }

                            // if user is found, check the password
                            if (user.checkPassword(password)) {
                                // if correct, update the state to remember the logged-in user
                                state.setLoginUser(user);
                                // show log out mode (click to log out text)
                                this.show(LoginLogoutMode.LOGOUT);
                                // then call back to the main window informing user is logged in
                                loginAction.LoginStateChanged(true);
                                // true means close the dialog (successful login)
                                return true;
                            }

                            // false means cannot close the dialog (failed login)
                            return false;
                        }));

                        // make the login dialog visible
                        loginDialog.setVisible(true);
                        break;

                    // otherwise, when the mode is LOGOUT (meaning user wants to log out)
                    case LOGOUT:
                        // clear all the states (who the user is, what computer is selected, what category is selected, and what type is selected)
                        state.setLoginUser(null);
                        state.setSelectedComputer(null);
                        state.setSelectedCategory(null);
                        state.setSelectedType(null);

                        // then set the mode back to LOG IN.
                        this.show(LoginLogoutMode.LOGIN);
                        // then callback to the main window user is logged out
                        loginAction.LoginStateChanged(false);
                        break;
                }
            });
            // add the button to the panel and set it to the right
            this.add(this.button, BorderLayout.EAST);

        } catch (IOException e){
            // if the image cannot be read, print an error message
            System.out.println("Error reading image file");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method is used to show either the login or logout button
     * @param mode the mode to show
     */
    public void show(LoginLogoutMode mode){
        this.currentMode = mode;

        switch (mode){

            case LOGIN:
                button.setText("Click to login");
                break;

            case LOGOUT:
                button.setText("Click to logout");
                break;
        }
    }
}
