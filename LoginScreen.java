package simplyRugby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * LoginScreen class: <br>
 * The login screen class controls how the data is displayed in the corresponding LoginScreen. <br>
 * Subclass of {@link JFrame}.
 * @author Conor King
 * @date 10/05/2021
 */
public class LoginScreen extends JFrame{

    // Attribute.
    private JPanel panelLogin;
    private JButton btnLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblTitleLogin;

    /**
     * The constructor of the login screen class. <br>
     * Instantiates a new Login screen.
     * @param controller Controller instance controller
     */
    public LoginScreen(Controller controller){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700,370,600,400);
        setContentPane(panelLogin);
        setTitle("Login");

        // Setting font size for each label on the LoginView. Resizing the font for the button and textboxes.
        lblTitleLogin.setFont(lblTitleLogin.getFont().deriveFont(Font.BOLD , (float)50.0));
        lblUsername.setFont(lblUsername.getFont().deriveFont(Font.PLAIN, (float)20.0));
        lblPassword.setFont(lblPassword.getFont().deriveFont(Font.PLAIN, (float)20.0));
        txtUsername.setFont(txtUsername.getFont().deriveFont(Font.PLAIN, (float) 20.0));
        txtPassword.setFont(txtPassword.getFont().deriveFont(Font.PLAIN, (float) 20.0));
        btnLogin.setFont(btnLogin.getFont().deriveFont(Font.PLAIN, (float) 20.0));

        btnLogin.addActionListener(new ActionListener() {
            /**
             * Invoked when login button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean result = controller.verifyLogin(txtUsername.getText(), txtPassword.getPassword());

                if (!result) {
                    JOptionPane.showMessageDialog(null, "You have entered an incorrect username or password please try again.");
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             * @param e the event to be processed
             */
            @Override
            public void windowClosing(WindowEvent e) {

                controller.serializePlayers();
                controller.serializeSkills();

                super.windowClosing(e);
            }
        });
    }
}
