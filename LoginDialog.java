import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;

    /**
     * Constructor for LoginDialog
     * @param parent the parent frame
     * @param callback the callback for login
     */
    public LoginDialog(JFrame parent, LoginDialogCallback callback){

        // call JDialog super and set it to modal (cannot click on parent frame)
        super(parent, true);
        setResizable(false);
        setTitle("Employee Login");

        usernameTextField = new JTextField(15);
        passwordTextField = new JPasswordField(15);
        passwordTextField.setEchoChar('*');

        // create a log in button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check username is entered
                if (usernameTextField.getText().isEmpty()){
                    // show message of username cannot be empty
                    JOptionPane.showMessageDialog(LoginDialog.this, "Empty username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // check password is entered
                if (passwordTextField.getText().isEmpty()){
                    // show message of password cannot be empty
                    JOptionPane.showMessageDialog(LoginDialog.this, "Empty password", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // call the callback with the username and password
                if (callback.loginUser(usernameTextField.getText(), passwordTextField.getText())){
                    // if the login is successful then hide the dialog
                    setVisible(false);
                    return;
                }
                // otherwise, show an error message of invalid username or password
                JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // create a panel and set the layout to grid layout 3 x 2
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // add the labels and text fields to the panel
        // this would be the first row
        panel.add(new JLabel("Username:"));
        panel.add(usernameTextField);

        // this would be the second row
        panel.add(new JLabel("Password"));
        panel.add(passwordTextField);

        // add the buttons to the panel
        panel.add(loginButton);
        panel.add(cancelButton);

        // get the content panes and center the panel and pack it, then centre the window
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
