package simplyRugby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * AddPlayerSkill class: <br>
 * The add player skill class controls how the data is displayed in the corresponding AddPlayerSkill screen. <br>
 * Subclass of {@link JFrame}.
 * @author Conor King
 * @date 10/05/2021
 */
public class AddPlayerSkill extends JFrame{

    // Attributes.
    private Controller myController;
    private JButton btnBack;
    private JLabel lblTitle;
    private JLabel lblTitleSort;
    private JComboBox<String> cmbSort;
    private JButton btnSort;
    private JComboBox<String> cmbSkills;
    private JLabel lblTitleSkills;
    private JLabel lblTitleRating;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JRadioButton radio4;
    private JRadioButton radio5;
    private JButton btnAddSkill;
    private JPanel panelAddPlayerSkill;

    /**
     * The constructor for the add player skill class. <br>
     * Instantiates a new Add player skill.
     * @param cont            Controller instance cont
     * @param currentCoach    Coach instance current coach
     * @param currentPlayer   Object instance current player
     * @param skillCategories Arraylist of skill categories
     */
    public AddPlayerSkill(Controller cont, Coach currentCoach, Object currentPlayer, ArrayList<SkillCategory> skillCategories) {

        populateScreen(cont, skillCategories);

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window has been closed.
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
             * Invoked when back button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("PlayerProfile", currentCoach, currentPlayer);
            }
        });

        btnSort.addActionListener(new ActionListener() {
            /**
             * Invoked when the sort button is pressed.
             * Sorts the data in the combo box for skills with the selected category.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = cmbSort.getSelectedItem();
                cmbSkills.removeAllItems();

                assert selectedItem != null;
                if (selectedItem.equals("All")) {
                    for (SkillCategory skillCategory: skillCategories) {

                        ArrayList<Skill> skills = skillCategory.getSkills();
                        for (Skill skill: skills){
                            cmbSkills.addItem(skill.getName());
                        }
                    }
                }

                for (SkillCategory skillCategory: skillCategories) {
                    if (skillCategory.getSkillCategory().equals(selectedItem)) {
                        ArrayList<Skill> skills = skillCategory.getSkills();
                        cmbSkills.removeAllItems();
                        for (Skill skill: skills) {
                            cmbSkills.addItem(skill.getName());
                        }
                    }
                }
            }
        });

        btnAddSkill.addActionListener(new ActionListener() {
            /**
             * Invoked when add skill button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean run = true;
                int rating = 0;
                if (radio1.isSelected()) {
                    rating = 1;
                } else if (radio2.isSelected()) {
                    rating = 2;
                } else if (radio3.isSelected()) {
                    rating = 3;
                } else if (radio4.isSelected()) {
                    rating = 4;
                } else if (radio5.isSelected()) {
                    rating = 5;
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a rating!");
                    run = false;
                }

                if (run) {


                    Object selectedSkill = cmbSkills.getSelectedItem();

                    myController.addPlayerSkill(currentPlayer, selectedSkill, rating);

                    setVisible(false);
                    myController.sceneChange("PlayerProfile", currentCoach, currentPlayer);
                }

            }
        });

    }


    /**
     * An overloaded constructor for the add player skill class. <br>
     * Instantiates a new Add player skill. <br>
     * Used when edit skill button is pressed in the player profile screen. <br>
     * Note: when this constructor is used it changes the screen title and text to have edit instead of add.
     * @param cont              Controller instance cont
     * @param currentCoach      Coach instance current coach
     * @param currentPlayer     Object instance current player
     * @param skillCategories   Arraylist of skill categories
     * @param selectedSkillName A string selected skill name
     */
    public AddPlayerSkill(Controller cont, Coach currentCoach, Object currentPlayer, ArrayList<SkillCategory> skillCategories, String selectedSkillName) {

        populateScreen(cont, skillCategories);

        setTitle("Edit Player Skill");
        lblTitle.setText("Edit Player Skill");
        btnAddSkill.setText("Edit");

        cmbSkills.setEnabled(false);
        cmbSort.setEnabled(false);
        btnSort.setEnabled(false);

        cmbSkills.setToolTipText("Not available in the prototype.");
        cmbSort.setToolTipText("Not available in the prototype.");
        btnSort.setToolTipText("Not available in the prototype.");


        Player player = (Player)currentPlayer;

        for (SkillCategory skillCategory: player.getPlayerSkillCategories()) {
            for (Skill skill: skillCategory.getSkills()) {
                if (skill.getName().equals(selectedSkillName)) {
                    cmbSkills.setSelectedItem(skill.getName());
                    switch (skill.getRating()) {
                        case 1 -> radio1.setSelected(true);
                        case 2 -> radio2.setSelected(true);
                        case 3 -> radio3.setSelected(true);
                        case 4 -> radio4.setSelected(true);
                        case 5 -> radio5.setSelected(true);
                    }
                    break;
                }
            }
        }

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window has been closed.
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
             * Invoked when an action occurs.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myController.sceneChange("PlayerProfile", currentCoach, currentPlayer);
            }
        });

        btnSort.addActionListener(new ActionListener() {
            /**
             * Invoked when the sort button is pressed.
             * Sorts the data in the combo box for skills with the selected category.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = cmbSort.getSelectedItem();
                cmbSkills.removeAllItems();

                assert selectedItem != null;
                if (selectedItem.equals("All")) {
                    for (SkillCategory skillCategory: skillCategories) {
                        ArrayList<Skill> skills = skillCategory.getSkills();

                        for (Skill skill: skills){
                            cmbSkills.addItem(skill.getName());
                        }
                    }
                } else {

                    for (SkillCategory skillCategory: skillCategories) {
                        if (skillCategory.getSkillCategory().equals(selectedItem)) {
                            ArrayList<Skill> skills = skillCategory.getSkills();
                            cmbSkills.removeAllItems();
                            for (Skill skill: skills) {
                                cmbSkills.addItem(skill.getName());
                            }
                        }
                    }
                }

            }
        });

        btnAddSkill.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean run = true;
                int rating = 0;
                if (radio1.isSelected()) {
                    rating = 1;
                } else if (radio2.isSelected()) {
                    rating = 2;
                } else if (radio3.isSelected()) {
                    rating = 3;
                } else if (radio4.isSelected()) {
                    rating = 4;
                } else if (radio5.isSelected()) {
                    rating = 5;
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a rating!");
                    run = false;
                }

                if (run) {

                    Object selectedSkill = cmbSkills.getSelectedItem();


                    myController.editPlayerSkill(currentPlayer, selectedSkill, rating);

                    setVisible(false);
                    myController.sceneChange("PlayerProfile", currentCoach, currentPlayer);
                }

            }
        });

    }

    /**
     * Populate Screen. <br>
     * Used to set the look of most components in the screen. From window size to font size.
     * @param cont            Controller instance cont
     * @param skillCategories Arraylist of skill categories
     */
    private void populateScreen(Controller cont, ArrayList<SkillCategory> skillCategories) {

        myController = cont;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(600,340,750,400);
        setContentPane(panelAddPlayerSkill);
        setTitle("Add Player Skill");

        // Setting button group for the radio buttons so only one can be selected at a time.
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);
        buttonGroup.add(radio4);
        buttonGroup.add(radio5);

        // Setting the data for the combo boxes for the skill category and skills.
        cmbSort.addItem("All");
        for (SkillCategory skillCategory: skillCategories) {
            cmbSort.addItem(skillCategory.getSkillCategory());

            ArrayList<Skill> skills = skillCategory.getSkills();
            for (Skill skill: skills){
                cmbSkills.addItem(skill.getName());
            }
        }


        // Setting font size for each label on the PlayerList view. Resizing the font for the button and textboxes.
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, (float)40));
        btnBack.setFont(btnBack.getFont().deriveFont(Font.PLAIN, (float)18));
        btnSort.setFont(btnSort.getFont().deriveFont(Font.PLAIN, (float)18));
        btnAddSkill.setFont(btnAddSkill.getFont().deriveFont(Font.PLAIN, (float)18));
        lblTitleRating.setFont(lblTitleRating.getFont().deriveFont(Font.PLAIN, (float)20));
        lblTitleSkills.setFont(lblTitleSkills.getFont().deriveFont(Font.PLAIN, (float)20));
        lblTitleSort.setFont(lblTitleSort.getFont().deriveFont(Font.PLAIN, (float)20));
        cmbSkills.setFont(cmbSkills.getFont().deriveFont(Font.PLAIN, (float)20));
        cmbSort.setFont(cmbSort.getFont().deriveFont(Font.PLAIN, (float)20));
        radio1.setFont(radio1.getFont().deriveFont(Font.PLAIN, (float)20));
        radio2.setFont(radio2.getFont().deriveFont(Font.PLAIN, (float)20));
        radio3.setFont(radio3.getFont().deriveFont(Font.PLAIN, (float)20));
        radio4.setFont(radio4.getFont().deriveFont(Font.PLAIN, (float)20));
        radio5.setFont(radio5.getFont().deriveFont(Font.PLAIN, (float)20));

    }
}
