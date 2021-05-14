package simplyRugby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;

/**
 * SquadList class: <br>
 * The add a skill screen class controls how the data is displayed in the corresponding SquadList screen. <br>
 * Subclass of {@link JFrame}.
 * @author Conor King
 * @date 10/05/2021
 */
public class SquadList extends JFrame implements ActionListener{

    // Attributes.
    private Controller myController;
    private Coach currentCoach;
    private JPanel panelSquadList;
    private JButton btnBack;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JLabel lblTitle;
    private JPanel panelList;
    private JLabel lblSearchHelp;

    /**
     * The constructor for the squad list class. <br>
     * Instantiates a new Squad list.
     * @param cont         Controller instance cont
     * @param currentCoach Coach instance current coach
     * @param search       A boolean search
     * @param searchResult A string search result
     */
    public SquadList(Controller cont, Coach currentCoach, boolean search, String searchResult) {

        myController = cont;
        this.currentCoach = currentCoach;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700,370,600,400);
        setContentPane(panelSquadList);
        setTitle("Squad List");

        // Setting the button layout in the panelList.
        panelList.setLayout(new GridLayout(0, 1));

        // Setting font size for each label on the PlayerList view. Resizing the font for the button and textboxes.
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, (float)40));
        btnBack.setFont(btnBack.getFont().deriveFont(Font.PLAIN, (float)18));
        btnSearch.setFont(btnSearch.getFont().deriveFont(Font.PLAIN, (float)18));
        txtSearch.setFont(txtSearch.getFont().deriveFont(Font.PLAIN, (float)20));
        lblSearchHelp.setFont(lblSearchHelp.getFont().deriveFont(Font.PLAIN, (float)14));

        // Populating the squad list.
        if (searchResult != null && searchResult.equals("all") || Objects.equals(searchResult, "All")) {

            ArrayList<JButton> buttons = myController.populateSquadList();
            int count = 0;
            for (JButton button : buttons) {
                button.setName("button" + count);
                button.addActionListener(this);
                button.setFont(button.getFont().deriveFont(Font.PLAIN, (float)20));
                button.setMargin(new Insets(10, 10, 10, 10));
                panelList.add(button);
                count++;
            }

        } else if (search) {

            ArrayList<JButton> buttons = myController.populateSquadList();
            int count = 0;

            for (JButton button: buttons) {
                String name = button.getText();
                String nameNoSpaces = name.replaceAll("\\s","");
                String[] nameArray = nameNoSpaces.split(":");

                assert searchResult != null;
                String searchString = searchResult.replaceAll("\\s","");
                searchString = searchString.toLowerCase();

                if (nameArray[0].toLowerCase().matches(searchString)) {
                    button.setName("button" + count);
                    button.addActionListener(this);
                    button.setFont(button.getFont().deriveFont(Font.PLAIN, (float)20));
                    button.setMargin(new Insets(10, 10, 10, 10));
                    panelList.add(button);
                    count++;
                }
            }

        } else {

            ArrayList<JButton> buttons = myController.populateSquadList();
            int count = 0;
            for (JButton button : buttons) {
                button.setName("button" + count);
                button.addActionListener(this);
                button.setFont(button.getFont().deriveFont(Font.PLAIN, (float)20));
                button.setMargin(new Insets(10, 10, 10, 10));
                panelList.add(button);
                count++;
            }
        }


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

        btnSearch.addActionListener(new ActionListener() {
            /**
             * Invoked when the search button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String searchResult = txtSearch.getText();
                boolean result = myController.validateNotEmpty(searchResult);

                if (result) {

                    setVisible(false);
                    myController.sceneChange("SquadList", currentCoach, searchResult);
                } else {
                    JOptionPane.showMessageDialog(null, "You need to input data in the search field to search.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            /**
             * Invoked when the back button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("MenuScreen", currentCoach);
            }
        });
    }

    /**
     * Invoked when any squad button is clicked.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();
        setVisible(false);
        myController.sceneChangePlayerList(currentCoach, buttonText);
    }
}
