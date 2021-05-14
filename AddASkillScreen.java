package simplyRugby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * AddASkillScreen class: <br>
 * The add a skill screen class controls how the data is displayed in the corresponding AddASkillScreen. <br>
 * Subclass of {@link JFrame}.
 * @author Conor King
 * @date 10/05/2021
 */
public class AddASkillScreen extends JFrame{

    // Attributes.
    private Controller myController;
    private Coach currentCoach;
    private JButton btnBack;
    private JComboBox<String> cboxCategory;
    private JTextArea txtAreaSkillDesc;
    private JTextField txtNameOfSkill;
    private JPanel panelAddASkill;
    private JLabel lblCategory;
    private JLabel lblName;
    private JLabel lblSkillDescription;
    private JLabel lblTitle;
    private JButton btnAdd;

    /**
     * The constructor for the add a skill screen. <br>
     * Instantiates a new Add a skill screen.
     * @param cont            Controller instance cont
     * @param coach           Coach instance coach
     * @param skillCategories Arraylist of skill categories
     */
    public AddASkillScreen(Controller cont, Coach coach, ArrayList<SkillCategory> skillCategories) {
        myController = cont;
        currentCoach = coach;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700,370,600,400);
        setContentPane(panelAddASkill);
        setTitle("Add A Skill");

        // Setting font size for each label on the LoginView. Resizing the font for the button and textboxes.
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, (float)40));
        lblCategory.setFont(lblCategory.getFont().deriveFont(Font.PLAIN, (float)20));
        lblName.setFont(lblName.getFont().deriveFont(Font.PLAIN, (float)20));
        lblSkillDescription.setFont(lblSkillDescription.getFont().deriveFont(Font.PLAIN, (float)20));
        txtAreaSkillDesc.setFont(txtAreaSkillDesc.getFont().deriveFont(Font.PLAIN, (float)15));
        txtNameOfSkill.setFont(txtNameOfSkill.getFont().deriveFont(Font.PLAIN, (float)20));
        btnBack.setFont(btnBack.getFont().deriveFont(Font.PLAIN, (float)20));
        btnAdd.setFont(btnAdd.getFont().deriveFont(Font.PLAIN, (float)20));

        // Populating the Category combo box.
        cboxCategory.addItem("");
        for (SkillCategory skillCategory: skillCategories) {
            cboxCategory.addItem(skillCategory.getSkillCategory());
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
                myController.sceneChange("MenuScreen", currentCoach);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            /**
             * Invoked when the add button is clicked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                Object inputCategory = cboxCategory.getSelectedItem();
                String inputSkill = txtNameOfSkill.getText();
                String inputDescription = txtAreaSkillDesc.getText();

                assert inputCategory != null;
                boolean resultCategory = myController.validateNotEmpty((String)inputCategory);
                boolean resultSkill = myController.validateNotEmpty(inputSkill);
                boolean resultDesc = myController.validateNotEmpty(inputDescription);

                if (!resultCategory || !resultSkill || !resultDesc) {

                    JOptionPane.showMessageDialog(null, "You need to enter data into all fields", "Error", JOptionPane.ERROR_MESSAGE);

                } else {

                    myController.addSkill(inputCategory, inputSkill, inputDescription);
                    setVisible(false);
                    myController.sceneChange("MenuScreen", currentCoach);
                }
            }
        });
    }
}
