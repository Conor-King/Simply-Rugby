package simplyRugby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * MenuScreen class: <br>
 * The menu screen class controls how the data is displayed in the corresponding MenuScreen. <br>
 * Subclass of {@link JFrame}.
 * @author Conor King
 * @date 10/05/2021
 */
public class MenuScreen extends JFrame{
    private Controller myController;
    private Coach currentCoach;
    private JLabel lblTitleMenu;
    private JButton btnSquadList;
    private JButton btnLogout;
    private JButton btnAddSkill;
    private JButton btnPlayerList;
    private JPanel panelMenu;
    private JLabel lblCoach;

    /**
     * The constructor for the login screen class. <br>
     * Instantiates a new Menu screen.
     * @param cont  Controller instance cont
     * @param coach Coach instance coach
     */
    public MenuScreen(Controller cont, Coach coach) {
        myController = cont;
        currentCoach = coach;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700,370,600,400);
        setContentPane(panelMenu);
        setTitle("Menu");

        lblCoach.setText("Coach: " + coach.getFirstName() + " " + coach.getLastName());

        // Setting font size for each label on the LoginView. Resizing the font for the button and textboxes.
        lblTitleMenu.setFont(lblTitleMenu.getFont().deriveFont(Font.BOLD , (float)40.0));
        lblCoach.setFont(lblCoach.getFont().deriveFont(Font.PLAIN, (float)20.0));
        btnAddSkill.setFont(btnAddSkill.getFont().deriveFont(Font.PLAIN, (float)30.0));
        btnLogout.setFont(btnLogout.getFont().deriveFont(Font.PLAIN, (float)30.0));
        btnPlayerList.setFont(btnPlayerList.getFont().deriveFont(Font.PLAIN, (float)30.0));
        btnSquadList.setFont(btnSquadList.getFont().deriveFont(Font.PLAIN, (float)30.0));



        btnPlayerList.addActionListener(new ActionListener() {
            /**
             * Invoked when player list button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("PlayerList", currentCoach);
            }
        });

        btnSquadList.addActionListener(new ActionListener() {
            /**
             * Invoked when squad list button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("SquadList", currentCoach);
            }
        });

        btnAddSkill.addActionListener(new ActionListener() {
            /**
             * Invoked when add skill button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("AddSkill", currentCoach);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            /**
             * Invoked when logout button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window has been closed.
             *
             * @param e the event to be processed.
             */
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                myController.handleWindowClosed("Logout");
            }
        });
    }
}
