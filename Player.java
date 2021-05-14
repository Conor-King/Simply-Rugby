package simplyRugby;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Player class: <br>
 * The player player stores the information of players in the system. <br>
 * Subclass of the {@link Member} class and implements {@link Serializable}.
 * @author Conor King
 * @date 10/05/2021
 */
public class Player extends Member implements Serializable {

    // Attributes.
    private LocalDate dateOfBirth;
    private Integer sruNum;
    private String address;
    private String postcode;
    private String phoneNum;
    private String email;
    private String doctorInfo;
    private String nextOfKinInfo;
    private String position;
    private Squad squad;
    private ArrayList<SkillCategory> playerSkillCategories = new ArrayList<>();

    /**
     * The constructor of the player class.
     * Instantiates a new Player.
     * @param firstName     A string first name
     * @param lastName      A string last name
     * @param dateOfBirth   LocalDate instance date of birth
     * @param sruNum        A integer sru num
     * @param address       A string address
     * @param postcode      A string postcode
     * @param phoneNum      A string phone num
     * @param email         A string email
     * @param doctorInfo    A string doctor info
     * @param nextOfKinInfo A string next of kin info
     * @param position      A string position
     * @param squad         Squad instance squad
     */
    public Player(String firstName, String lastName, LocalDate dateOfBirth, Integer sruNum, String address, String postcode, String phoneNum, String email, String doctorInfo, String nextOfKinInfo, String position, Squad squad) {

        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.sruNum = sruNum;
        this.address = address;
        this.postcode = postcode;
        this.phoneNum = phoneNum;
        this.email = email;
        this.doctorInfo = doctorInfo;
        this.nextOfKinInfo = nextOfKinInfo;
        this.position = position;
        this.squad = squad;
    }

    /**
     * An overloaded constructor for the player class. <br>
     * Used by subclass {@link JuniorPlayer}.
     * Instantiates a new Player.
     * @param firstName     A string first name
     * @param lastName      A string last name
     * @param dateOfBirth   LocalDate instance date of birth
     * @param sruNum        A integer sru num
     * @param address       A string address
     * @param postcode      A string postcode
     * @param phoneNum      A string phone num
     * @param email         A string email
     * @param doctorInfo    A string doctor info
     * @param position      A string position
     * @param squad         Squad instance squad
     */
    public Player(String firstName, String lastName, LocalDate dateOfBirth, Integer sruNum, String address, String postcode, String phoneNum, String email, String doctorInfo, String position, Squad squad) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.sruNum = sruNum;
        this.address = address;
        this.postcode = postcode;
        this.phoneNum = phoneNum;
        this.email = email;
        this.doctorInfo = doctorInfo;
        this.position = position;
        this.squad = squad;
    }

    // Getters and Setters.
    /**
     * Gets date of birth.
     * @return LocalDate instance date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets sru num.
     * @return A integer sru num
     */
    public Integer getSruNum() {
        return sruNum;
    }

    /**
     * Gets address.
     * @return A string address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets postcode.
     * @return A string postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Gets phone num.
     * @return A string phone num
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Gets email.
     * @return A string email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets doctor info.
     * @return A string doctor info
     */
    public String getDoctorInfo() {
        return doctorInfo;
    }

    /**
     * Gets next of kin info.
     * @return A string next of kin info
     */
    public String getNextOfKinInfo() {
        return nextOfKinInfo;
    }

    /**
     * Gets position.
     * @return A string position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gets squad.
     * @return A string squad
     */
    public Squad getSquad() {
        return squad;
    }

    /**
     * Sets squad.
     * @param squad Squad instance squad
     */
    public void setSquad(Squad squad) {
        this.squad = squad;
    }

    /**
     * Gets player skill categories.
     * @return Arraylist of all player skill categories
     */
    public ArrayList<SkillCategory> getPlayerSkillCategories() {
        return playerSkillCategories;
    }

    /**
     * Add player skill category (String). <br>
     * Adds skill category to the player instance or if its exists adds the skill to it.
     * @param skill         Skill instance skill
     * @param rating        A integer rating
     * @param skillCategory SkillCategory instance skill category
     * @return A string containing the result of the addition.
     */
    public String addPlayerSkillCategory(Skill skill, int rating, SkillCategory skillCategory) {

        // If arraylist playerSkillCategories is not null and arraylist playerSkillCategories contains the passed in skill category then.
        if (playerSkillCategories != null && playerSkillCategories.size() != 0) {

            for (SkillCategory skillCategory1: playerSkillCategories) {

                if (skillCategory1.getSkillCategory().equals(skillCategory.getSkillCategory())) {
                    return skillCategory1.addSkill(skill, rating);
                }
            }

            addNewPlayerCategory(skill, rating, skillCategory);
            return "Skill " + skill.getName().toLowerCase() + " and skill category " + skillCategory.getSkillCategory().toLowerCase() + " successfully added!";

        // Else Create
        } else if (playerSkillCategories != null){
            addNewPlayerCategory(skill, rating, skillCategory);
            return "Skill " + skill.getName().toLowerCase() +" and skill category " + skillCategory.getSkillCategory().toLowerCase() + " successfully added!";
        }

        return "Add Skill Failed!";
    }

    /**
     * Add new player category. <br>
     * Creates a new SkillCategory instance and adds it to the players skill categories.
     * @param skill Skill instance skill
     * @param rating A integer rating
     * @param skillCategory SkillCategory instance skill category
     */
    private void addNewPlayerCategory(Skill skill, int rating, SkillCategory skillCategory) {
        ArrayList<Skill> tempSkillList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Skill skill1 = new Skill(skill.getName(), skill.getDescription(), rating, now);
        tempSkillList.add(skill1);
        SkillCategory tempSkillCategory = new SkillCategory(skillCategory.getSkillCategory(), tempSkillList);
        playerSkillCategories.add(tempSkillCategory);
    }
}