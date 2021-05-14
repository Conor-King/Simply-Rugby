package simplyRugby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * PlayerProfile class: <br>
 * The player profile class controls how the data is displayed in the corresponding PlayerProfile screen. <br>
 * Subclass of {@link JFrame}.
 * @author Conor King
 * @date 10/05/2021
 */
public class PlayerProfile extends JFrame {

    // Attributes.
    private Controller myController;
    private JButton btnBack;
    private JLabel lblTitleName;
    private JLabel lblTitlePosition;
    private JLabel lblTitleSkills;
    private JList<String> listSkills;
    private JButton btnAddSkill;
    private JButton btnEditSkill;
    private JButton btnRemoveSkill;
    private JLabel lblTitleDoB;
    private JLabel lblPlayerDoB;
    private JLabel lblTitleSruNum;
    private JLabel lblPlayerSRU;
    private JLabel lblTitleAddress;
    private JLabel lblPlayerAddress;
    private JLabel lblTitlePostcode;
    private JLabel lblTitleEmail;
    private JLabel lblPlayerPostcode;
    private JLabel lblTitlePhoneNum;
    private JLabel lblPlayerPhoneNum;
    private JLabel lblPlayerEmail;
    private JLabel lblTitleDocName;
    private JLabel lblTitleNextOfKin;
    private JLabel lblPlayerDocName;
    private JLabel lblTitleDocPhone;
    private JLabel lblPlayerNoK;
    private JLabel lblPlayerDocPhone;
    private JLabel lblTitleNoKPhone;
    private JLabel lblPlayerNoKPhone;
    private JPanel panelPlayerProfile;
    private JPanel panelPlayerInfo;
    private JPanel panelSkills;

    /**
     * The constructor for the player profile class. <br>
     * Instantiates a new Player profile screen.
     * @param cont          Controller instance cont
     * @param currentCoach  Coach instance current coach
     * @param currentPlayer Object instance current player
     */
    public PlayerProfile(Controller cont, Coach currentCoach, Object currentPlayer) {

        myController = cont;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 250, 1100, 600);
        setContentPane(panelPlayerProfile);
        setTitle("Player Profile");

        // Setting font size for each label on the PlayerList view. Resizing the font for the button and textboxes.
        lblTitleSkills.setFont(lblTitleSkills.getFont().deriveFont(Font.PLAIN, (float) 30));
        btnBack.setFont(btnBack.getFont().deriveFont(Font.PLAIN, (float) 18));
        btnAddSkill.setFont(btnAddSkill.getFont().deriveFont(Font.PLAIN, (float) 18));
        btnEditSkill.setFont(btnEditSkill.getFont().deriveFont(Font.PLAIN, (float) 18));
        btnRemoveSkill.setFont(btnRemoveSkill.getFont().deriveFont(Font.PLAIN, (float) 18));
        listSkills.setFont(listSkills.getFont().deriveFont(Font.PLAIN, (float) 20));

        // Player info titles.
        lblTitleName.setFont(lblTitleName.getFont().deriveFont(Font.PLAIN, (float) 40));
        lblTitlePosition.setFont(lblTitlePosition.getFont().deriveFont(Font.PLAIN, (float) 40));
        lblTitleDoB.setFont(lblTitleDoB.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleSruNum.setFont(lblTitleSruNum.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleAddress.setFont(lblPlayerAddress.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitlePostcode.setFont(lblTitlePostcode.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitlePhoneNum.setFont(lblTitlePhoneNum.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleEmail.setFont(lblTitleEmail.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleDocName.setFont(lblTitleDocName.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleDocPhone.setFont(lblTitleDocPhone.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleNextOfKin.setFont(lblTitleNextOfKin.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblTitleNoKPhone.setFont(lblTitleNoKPhone.getFont().deriveFont(Font.PLAIN, (float) 20));

        // Player info.
        lblPlayerDoB.setFont(lblPlayerDoB.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerSRU.setFont(lblPlayerSRU.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerAddress.setFont(lblPlayerAddress.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerPostcode.setFont(lblPlayerPostcode.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerPhoneNum.setFont(lblPlayerPhoneNum.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerEmail.setFont(lblPlayerEmail.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerDocName.setFont(lblPlayerDocName.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerDocPhone.setFont(lblPlayerDocPhone.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerNoK.setFont(lblPlayerNoK.getFont().deriveFont(Font.PLAIN, (float) 20));
        lblPlayerNoKPhone.setFont(lblTitleNoKPhone.getFont().deriveFont(Font.PLAIN, (float) 20));

        // Populating fields with the players info.
        if (currentPlayer instanceof JuniorPlayer) {
            JuniorPlayer juniorPlayer = (JuniorPlayer) currentPlayer;

            lblTitleName.setText(juniorPlayer.getFirstName() + " " + juniorPlayer.getLastName());
            lblTitlePosition.setText("Position: " + juniorPlayer.getPosition());
            lblPlayerDoB.setText(juniorPlayer.getDateOfBirth().toString());
            lblPlayerSRU.setText(juniorPlayer.getSruNum().toString());
            lblPlayerAddress.setText(juniorPlayer.getAddress());
            lblPlayerPostcode.setText(juniorPlayer.getPostcode());
            lblPlayerPhoneNum.setText(juniorPlayer.getPhoneNum());
            lblPlayerEmail.setText(juniorPlayer.getEmail());

            String[] docInfo = juniorPlayer.getDoctorInfo().split(":");
            lblPlayerDocName.setText(docInfo[0]);
            lblPlayerDocPhone.setText(docInfo[1]);

            lblTitleNextOfKin.setText("Guardian: ");
            lblTitleNoKPhone.setText("Guardian Phone: ");

            String[] guardianInfo = juniorPlayer.getGuardianInfo().split(":");
            lblPlayerNoK.setText(guardianInfo[0]);
            lblPlayerNoKPhone.setText(guardianInfo[1]);

        } else if (currentPlayer instanceof Player) {
            Player player = (Player) currentPlayer;

            lblTitleName.setText(player.getFirstName() + " " + player.getLastName());
            lblTitlePosition.setText("Position: " + player.getPosition());
            lblPlayerDoB.setText(player.getDateOfBirth().toString());
            lblPlayerSRU.setText(player.getSruNum().toString());
            lblPlayerAddress.setText(player.getAddress());
            lblPlayerPostcode.setText(player.getPostcode());
            lblPlayerPhoneNum.setText(player.getPhoneNum());
            lblPlayerEmail.setText(player.getEmail());

            String[] docInfo = player.getDoctorInfo().split(":");
            lblPlayerDocName.setText(docInfo[0]);
            lblPlayerDocPhone.setText(docInfo[1]);

            String[] nokInfo = player.getNextOfKinInfo().split(":");
            lblPlayerNoK.setText(nokInfo[0]);
            lblPlayerNoKPhone.setText(nokInfo[1]);
        }

        // Populating the achieved skills.
        // Assert that passing parameter currentPlayer is an instance of player. Throws an assertion error if null or false.
        assert currentPlayer instanceof Player;
        // Casting currentPlayer to Player class.
        Player player = (Player) currentPlayer;
        // If the result of the method getPlayerSkillCategories from the player class is not null then.
        if (player.getPlayerSkillCategories() != null) {
            // Create and initialise a string cast vector.
            Vector<String> vector = new Vector<>();
            // For all skill categories in the result of method getPlayerSkillCategories in player object.
            for (SkillCategory skillCategory : player.getPlayerSkillCategories()) {

                // Add the skill category's name to the vector.
                vector.add(skillCategory.getSkillCategory());

                // For all the skills in the result of method getSkills in skillCategory object.
                for (Skill skill : skillCategory.getSkills()) {
                    // Add the skill information as one string to the vector.
                    vector.add("    " + skill.getName() + " --- Rating: " + skill.getRating() + " --- Achieved: " + skill.getAchieved());
                }
            }
            // Set the UI listSkills list data to the vector.
            listSkills.setListData(vector);
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

        btnBack.addActionListener(new ActionListener() {
            /**
             * Invoked when the back button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("PlayerList", currentCoach);
            }
        });

        btnAddSkill.addActionListener(new ActionListener() {
            /**
             * Invoked when the add skill button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("AddPlayerSkill", currentCoach, currentPlayer);
            }
        });

        btnEditSkill.addActionListener(new ActionListener() {
            /**
             * Invoked when edit skill button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                if (listSkills.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "You need to select a skill to edit.\nClick on the skill you want to edit.", "Error", JOptionPane.ERROR_MESSAGE);

                //Todo Fix this so that it check through all the skill categories. could just check through the player's achieved skills maybe?
                } else if (listSkills.getSelectedValue().equals("Passing") || listSkills.getSelectedValue().equals("Tackling") || listSkills.getSelectedValue().equals("Kicking") || listSkills.getSelectedValue().equals("Test")) {
                    JOptionPane.showMessageDialog(null, "You are not allowed to edit the skill categories.\n               Please select a skill to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    String selectedSkill = listSkills.getSelectedValue();
                    String skillNoSpaces = selectedSkill.replaceAll("\\s", "");
                    String[] skillInfoArray = skillNoSpaces.split("-");

                    setVisible(false);
                    myController.sceneChange("EditPlayerSkill", currentCoach, currentPlayer, skillInfoArray[0]);
                }
            }
        });

        btnRemoveSkill.addActionListener(new ActionListener() {
            /**
             * Invoked when the remove skill button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String result = myController.removeSkill(currentPlayer, listSkills.getSelectedValue());

                if (result.equals("Success")) {
                    setVisible(false);
                    myController.sceneChange("PlayerProfile", currentCoach, currentPlayer);
                }
            }
        });
    }
}
