package simplyRugby;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

/**
 * Controller class: <br>
 * The controller class controls most if not all the functionality of the system.
 * It take the information from the model and tells the view how to display it.
 * @author Conor King
 * @date 10/05/2021
 */
public class Controller {

    // Attributes.
    private Model myModel;
    private LoginScreen loginScreen;

    /**
     * The constructor for the controller class. <br>
     * Instantiates a new Controller.
     */
    public Controller() {
        // Initialize an instance of the model class.
        myModel = new Model(this);

        // Display the login screen.
        loginScreen = new LoginScreen(this);
        loginScreen.setVisible(true);
    }

    /**
     * Verify login boolean. <br>
     * Check the provided username and password against the stored username and password of the coaches in the system.
     * @param username A string username
     * @param password A character array password
     * @return True if login is valid, False if login is invalid.
     */
    public boolean verifyLogin(String username, char[] password){

        boolean result = false;
        ArrayList<Coach> coachList = myModel.getCoaches();

        // For each coach in the Arraylist "coachList". Check if the username provided equals the username stored.
        for (Coach currentCoach : coachList) {
            if (currentCoach.getUsername().equals(username)) {

                // If usernames match then, set the result of method "validatePassword" to variable "passwordResult" (boolean).
                boolean passwordResult = validatePassword(password, currentCoach.getPassword());

                // If "passwordResult" is true then. Close the login screen and display the menu screen passing in this controller class and the currently logged in coach.
                if (passwordResult){
                    JOptionPane.showMessageDialog(null, "Login successful.\nWelcome " + currentCoach.getFirstName() + " " + currentCoach.getLastName() + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                    loginScreen.setVisible(false);
                    MenuScreen menuScreen = new MenuScreen(this, currentCoach);
                    menuScreen.setVisible(true);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Scene change. <br>
     * Used to navigate screens in the system.
     * @param windowID     A string window id
     * @param currentCoach Coach instance current coach
     */
    public void sceneChange(String windowID,  Coach currentCoach) {
        switch (windowID) {

            case "MenuScreen" -> {
                MenuScreen menuScreen = new MenuScreen(this, currentCoach);
                menuScreen.setVisible(true);
            }

            case "AddSkill" -> {
                AddASkillScreen addASkillScreen = new AddASkillScreen(this, currentCoach, myModel.getSkillCategories());
                addASkillScreen.setVisible(true);
            }

            case "PlayerList" -> {
                PlayerList playerList = new PlayerList(this, currentCoach, false, null);
                playerList.setVisible(true);
            }

            case "SquadList" -> {
                SquadList squadList = new SquadList(this, currentCoach, false, null);
                squadList.setVisible(true);
            }
        }
    }

    /**
     * Overloaded method scene change. <br>
     * Used to navigate screens in the system.
     * @param windowID      A string window id
     * @param currentCoach  Coach instance current coach
     * @param currentPlayer Player instance current player
     */
    public void sceneChange(String windowID,  Coach currentCoach, Object currentPlayer) {
        switch (windowID) {

            case "AddPlayerSkill" -> {
                AddPlayerSkill addPlayerSkill = new AddPlayerSkill(this, currentCoach, currentPlayer, myModel.getSkillCategories());
                addPlayerSkill.setVisible(true);
            }

            case "PlayerList" -> {
                PlayerList playerList = new PlayerList(this, currentCoach, true, (String)currentPlayer);
                playerList.setVisible(true);
            }

            case "SquadList" -> {
                SquadList squadList = new SquadList(this, currentCoach, true, (String)currentPlayer);
                squadList.setVisible(true);
            }

            case "PlayerProfile" -> {
                PlayerProfile playerProfile = new PlayerProfile(this, currentCoach, currentPlayer);
                playerProfile.setVisible(true);
            }
        }
    }

    /**
     * Overloaded method scene change. <br>
     * Used to navigate screens in the system.
     * @param windowID          A string window id
     * @param currentCoach      Coach instance current coach
     * @param currentPlayer     Player instance current player
     * @param selectedSkillName A string selected skill name
     */
    public void sceneChange(String windowID,  Coach currentCoach, Object currentPlayer, String selectedSkillName) {
        if ("EditPlayerSkill".equals(windowID)) {
            AddPlayerSkill addPlayerSkill = new AddPlayerSkill(this, currentCoach, currentPlayer, myModel.getSkillCategories(), selectedSkillName);
            addPlayerSkill.setVisible(true);
        }
    }

    /**
     * Scene change player profile. <br>
     * Used to navigate to the player profile screen
     * @param currentCoach Coach instance current coach
     * @param playerInfo   A string player info
     */
    public void sceneChangePlayerProfile(Coach currentCoach, String playerInfo) {

        String playerNoSpace = playerInfo.replaceAll("\\s","");
        String[] playerInfoArray = playerNoSpace.split(":");

        ArrayList<Object> playerList = myModel.getPlayers();
        for (Object player: playerList) {
            Player player1 = (Player)player;

            if ((player1.getFirstName() + player1.getLastName()).equals(playerInfoArray[0]) && player1.getDateOfBirth().toString().equals(playerInfoArray[1])) {

                PlayerProfile playerProfile = new PlayerProfile(this, currentCoach, player1);
                playerProfile.setVisible(true);
            }
        }
    }

    /**
     * Scene change player list. <br>
     * Used to navigate to the player list screen.
     * Called when moving from the squad list to player list.
     * @param currentCoach Coach instance current coach
     * @param squadInfo    A string squad info
     */
    public void sceneChangePlayerList(Coach currentCoach, String squadInfo){

        String squadNoSpace = squadInfo.replaceAll("\\s","");
        String[] squadInfoArray = squadNoSpace.split(":");

        PlayerList playerList = new PlayerList(this, currentCoach, squadInfoArray[0]);
        playerList.setVisible(true);

    }

    /**
     * Populate player (ArrayList). <br>
     * Used to populate an arraylist of JButtons for display in the player list screen.
     * @return the array list
     */
    public ArrayList<JButton> populatePlayerList() {
        ArrayList<JButton> buttons = new ArrayList<>();
        ArrayList<Object> playerList;
        playerList = myModel.getPlayers();
        for (Object player: playerList) {

            Player castPlayer = (Player)player;
            JButton tempJButton;
            tempJButton = new JButton(castPlayer.getFirstName() + " " + castPlayer.getLastName() + "  :  " + castPlayer.getDateOfBirth() + "  :  " + "Squad: " + castPlayer.getSquad().getName());
            buttons.add(tempJButton);
        }

        return buttons;
    }

    /**
     * Populate squad list (ArrayList). <br>
     * Used to populate an arraylist of JButtons for display in the squad list screen.
     * @return the array list
     */
    public ArrayList<JButton> populateSquadList() {
        ArrayList<JButton> buttons = new ArrayList<>();
        ArrayList<Squad> squadList;
        int playerCount;
        squadList = myModel.getSquads();
        for (Squad squad: squadList) {

            ArrayList<Player> tempPlayers = new ArrayList<>();
            for (Object player: myModel.getPlayers()) {
                Player selectedPlayer = (Player)player;
                if (selectedPlayer.getSquad() == squad) {
                    tempPlayers.add(selectedPlayer);
                }
            }
            squad.setPlayers(tempPlayers);

            try {
                playerCount = squad.getPlayers().size();
            } catch (NullPointerException n) {
                playerCount = 0;
            }

            JButton tempJButton;
            tempJButton = new JButton(squad.getName() + "  :  Players: " + playerCount + "  :  Coach: " + squad.getCoach().getFirstName() + " " + squad.getCoach().getLastName());
            buttons.add(tempJButton);
        }
        return buttons;
    }

    /**
     * Add player skill. <br>
     * Adds a skill to the players achieved skills.
     *
     * @param player        Object instance player
     * @param selectedSkill Object instance selected skill
     * @param rating        A integer rating
     */
    public void addPlayerSkill(Object player, Object selectedSkill, int rating) {

        // Assigning passed in player to a local namespace currentPlayer.
        Player currentPlayer = (Player)player;
        // Assigning Arraylist "skillCategories" to the result of method getSkillCategories in the model class.
        ArrayList<SkillCategory> skillCategories = myModel.getSkillCategories();

        // For all skillCategories in Arraylist skillCategories.
        for (SkillCategory skillCategory: skillCategories){
            // For all skills in Arraylist returned from method getSkills in SkillCategory class.
            for (Skill skill: skillCategory.getSkills()) {
                // If skill name equals the passing parameter selectedSkill then.
                if (skill.getName().equals(selectedSkill)){

                    // Call method addPlayerSkillCategory in Player class with passing parameters (skill, rating, skillCategory) then assign the result to variable "result".
                    String result = currentPlayer.addPlayerSkillCategory(skill, rating, skillCategory);
                    // Show popup dialog box with the result as the message.
                    JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    /**
     * Edit player skill. <br>
     * Edits the selected players achieved skill. <br>
     * Note: Edits can only be made on the rating for the prototype.
     * @param player        Object instance player
     * @param selectedSkill Object instance selected skill
     * @param rating        A integer rating
     */
    public void editPlayerSkill(Object player, Object selectedSkill, int rating) {

        // Assigning passed in player to a variable currentPlayer.
        Player currentPlayer = (Player)player;
        for (SkillCategory skillCategory: currentPlayer.getPlayerSkillCategories()) {
            for (Skill skill: skillCategory.getSkills()) {
                if (skill.getName().equals(selectedSkill)) {
                    skill.setRating(rating);

                    JOptionPane.showMessageDialog(null, "Successfully edited skill " + skill.getName().toLowerCase() + "'s rating to " + skill.getRating() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    /**
     * Remove skill string. <br>
     * Removes the selected players achieved skill.
     * @param currentPlayer Object instance current player
     * @param selectedItem  A string selected item
     * @return the string with the result.
     */
    public String removeSkill(Object currentPlayer, String selectedItem) {

        Player player = (Player)currentPlayer;

        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "You need to select a skill to remove.\nClick on the skill you want to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (SkillCategory playerSkillCategory: player.getPlayerSkillCategories()) {
                if (selectedItem.equals(playerSkillCategory.getSkillCategory())) {
                    int userSelection = JOptionPane.showConfirmDialog(null, "Are you sure you wish to remove a whole skill category: " + selectedItem + "?", "Confirm Skill Category Removal", JOptionPane.YES_NO_OPTION);
                    if (userSelection == 0) {

                        ArrayList<SkillCategory> playerSkillCategories = player.getPlayerSkillCategories();
                        playerSkillCategories.remove(playerSkillCategory);
                        JOptionPane.showMessageDialog(null, "Successfully Removed: " + selectedItem, "Success", JOptionPane.INFORMATION_MESSAGE);
                        return "Success";

                    } else if (userSelection == 1) {
                        JOptionPane.showMessageDialog(null, "Cancelled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        return "Failed";
                    }

                }

                String skillNoSpaces = selectedItem.replaceAll("\\s","");
                String[] skillInfoArray = skillNoSpaces.split("-");
                for (Skill skill: playerSkillCategory.getSkills()) {

                    if (skillInfoArray[0].equals(skill.getName())) {

                        int userSelection = JOptionPane.showConfirmDialog(null, "Are you sure you wish to remove skill: " + selectedItem, "Confirm Skill Remove", JOptionPane.YES_NO_OPTION);
                        if (userSelection == 0) {

                            ArrayList<Skill> skills = playerSkillCategory.getSkills();
                            JOptionPane.showMessageDialog(null, "Successfully Removed " + selectedItem, "Success", JOptionPane.INFORMATION_MESSAGE);
                            skills.remove(skill);
                            return "Success";

                        } else if (userSelection == 1) {
                            JOptionPane.showMessageDialog(null, "Cancelled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return "Failed";
                        } else {
                            JOptionPane.showMessageDialog(null, "Remove Skill Confirm Failed.");
                            return "Failed";
                        }
                    }
                }
            }
        }
        return "Failed";
    }

    /**
     * Add skill. <br>
     * Adds a skill to the system.
     * @param inputCategory    Object instance input category
     * @param inputSkill       A string input skill
     * @param inputDescription A string input description
     */
    public void addSkill(Object inputCategory, String inputSkill, String inputDescription) {

        String result = "";
        Skill tempSkill = new Skill(inputSkill, inputDescription);
        ArrayList<SkillCategory> tempSkillCategories = new ArrayList<>(myModel.getSkillCategories());

        for (SkillCategory skillCategory: tempSkillCategories) {
            String skillCategoryName = skillCategory.getSkillCategory();
            if (inputCategory.equals(skillCategoryName)) {
                result = skillCategory.addSkill(tempSkill, 0);
                JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }

        if (result.equals("")) {
            ArrayList<Skill> skillArrayList = new ArrayList<>();
            skillArrayList.add(tempSkill);
            SkillCategory tempSkillCategory = new SkillCategory((String)inputCategory, skillArrayList);
            tempSkillCategories.add(tempSkillCategory);
            myModel.setSkillCategories(tempSkillCategories);
            result = "Successfully added skill category " + tempSkillCategory.getSkillCategory() + " and skill " + tempSkill.getName();


            JOptionPane.showMessageDialog(null, result, "Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Hash password (String). <br>
     * Puts character array passwords through an algorithm to create a hashed password. <br>
     * Note: This function does not get used as the coaches are hardcoded into the system. Will be needed for the full system.
     * @param password A character array password
     * @return the hashed password as a string
     */
    public String hashPassword(char[] password) {

        // Set the amount of iterations the algorithm does and set the salt for the password.
        int iterations = 10000;
        byte[] salt = getSalt();

        // Try, create an instance of SecretKeyFactory using the "PBKDF2WithHmacSHA1" algorithm.
        // Set up the password hash specifications with the password, salt, iterations, key length.
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 128);

            // Execute the hashing algorithm and collect the result in variable hash.
            byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();

            // Return a string the iterations done, hex converted salt and hex converted hash. Separated with a colon ":".
            return iterations + ":" + toHex(salt) + ":" + toHex(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Validate Password (Boolean). <br>
     * Used to validate two passwords to see if they match.
     * @param inputPassword A character array input password.
     * @param storedPassword A string stored password.
     * @return True if passwords match. False if passwords do not match.
     */
    private boolean validatePassword(char[] inputPassword, String storedPassword) {

        // Splitting the stored password at the colon":". Setting variables to hold the iterations, salt and hash.
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        // Try, create an instance of SecretKeyFactory using the "PBKDF2WithHmacSHA1" algorithm.
        // Set up the password hash specifications with the input password, salt, iterations, key length.
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(inputPassword, salt, iterations, 128);

            // Execute the hashing algorithm and collect the result in variable newHash.
            byte[] newHash = secretKeyFactory.generateSecret(spec).getEncoded();

            // Check the two password hashes length side by side. If they don't match set variable difference to the value 1. If they match it is set to 0.
            int difference = hash.length ^ newHash.length;
            for (int i = 0; i < hash.length && i < newHash.length; i++) {
                // Variable difference is set to itself or the result of (hash[i] ^ newHash[i]).
                difference |= hash[i] ^ newHash[i];
            }
            // Return true if difference's value is 0. False if not.
            return difference == 0;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method getSalt: <br>
     * Generates a random salt for the hashing algorithm.
     * @return A byte array holding the salt value.
     */
    private byte[] getSalt() {

        // Try, create an instance of SecureRandom class and call method getInstance using "SHA1PRNG" algorithm.
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];

            // Setting the salt variable to the generated salt.
            sr.nextBytes(salt);
            return salt;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method toHex: <br>
     * Takes a byte array and converts it to hex. <br>
     * Used to make the stored password string more secure.
     *
     * @param array byte[] holding value for byte to hex conversion.
     * @return String hex value.
     */
    private String toHex(byte[] array){

        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0);
        } else {
            return hex;
        }
    }

    /**
     * Method fromHex: <br>
     * Takes a String and converts it to a byte array.
     *
     * @param hex String holds hex value for conversion to byte[].
     * @return Byte array.
     */
    private byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * Serialize players. <br>
     * Serializes the players in the system. <br>
     * Junior players into file "juniorPlayerData.ser". <br>
     * Players into file "playerData.ser".
     */
    public void serializePlayers() {

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<JuniorPlayer> juniorPlayers = new ArrayList<>();

        for (Object player: myModel.getPlayers()) {
            if (player instanceof JuniorPlayer) {
                juniorPlayers.add((JuniorPlayer)player);
            } else {
                players.add((Player)player);
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("juniorPlayerData.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(juniorPlayers);

            fileOutputStream = new FileOutputStream("playerData.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(players);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serialize skills. <br>
     * serializes the skill in the system to file "skillData.ser".
     */
    public void serializeSkills() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("skillData.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(myModel.getSkillCategories());
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize players array list.
     * Deserializes all players from files "playerData.ser" and "juniorPlayerData.ser".
     * @return The array list of all serialized players (including junior players).
     */
    public ArrayList<Object> deserializePlayers() {

        ArrayList<Object> mainPlayers = new ArrayList<>();
        ArrayList<Player> players;
        ArrayList<JuniorPlayer> juniorPlayers;
        boolean fileCheck1 = new File("playerData.ser").isFile();
        boolean fileCheck2 = new File("juniorPlayerData.ser").isFile();

        if (fileCheck1 && fileCheck2) {
            try {
                FileInputStream fileInPlayer = new FileInputStream("playerData.ser");
                FileInputStream fileInJunior = new FileInputStream("juniorPlayerData.ser");

                ObjectInputStream objectIn = new ObjectInputStream(fileInPlayer);
                players = (ArrayList<Player>) objectIn.readObject();

                objectIn = new ObjectInputStream(fileInJunior);
                juniorPlayers = (ArrayList<JuniorPlayer>) objectIn.readObject();

                fileInPlayer.close();
                fileInJunior.close();
                objectIn.close();

                mainPlayers.addAll(players);
                mainPlayers.addAll(juniorPlayers);

            } catch (FileNotFoundException e) {
                System.out.println("No such file found.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception triggered.");
                e.printStackTrace();
            } catch (ClassNotFoundException classError) {
                System.out.println("The class was not found in the file");
                classError.printStackTrace();
            }
        }

        return mainPlayers;
    }

    /**
     * Deserialize skills array list.
     * Deserializes all skills from the file "skillData.ser".
     * @return The array list of all serialized skills.
     */
    public ArrayList<SkillCategory> deserializeSkills() {

        ArrayList<SkillCategory> skillCategories = null;
        boolean fileCheck = new File("skillData.ser").isFile();

        if (fileCheck) {
            try {

                FileInputStream fileIn = new FileInputStream("skillData.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                skillCategories = (ArrayList<SkillCategory>)objectIn.readObject();

                fileIn.close();
                objectIn.close();

            } catch (FileNotFoundException e) {
                System.out.println("No such file found.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception triggered.");
                e.printStackTrace();
            } catch (ClassNotFoundException classError) {
                System.out.println("The class was not found in the file");
                classError.printStackTrace();
            }
        }

        return skillCategories;
    }

    public boolean validateNotEmpty (String input) {
        return !input.equals("");
    }

    /**
     * Handle window closed.
     * Handles the action when a window closes.
     * @param windowID A string window id
     */
    public void handleWindowClosed(String windowID) {
        if (windowID.equals("Logout")) {
            loginScreen = new LoginScreen(this);
            loginScreen.setVisible(true);
        }
    }
}
