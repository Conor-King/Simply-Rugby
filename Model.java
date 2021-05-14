package simplyRugby;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model class: <br>
 * The model class handles all the business data for the system.
 * @author Conor King
 * @date 10/05/2021
 */
public class Model {

    // Attributes.
    private ArrayList<Coach> coaches = new ArrayList<>();
    private ArrayList<Object> players = new ArrayList<>();
    private ArrayList<Squad> squads = new ArrayList<>();
    private ArrayList<SkillCategory> skillCategories = new ArrayList<>();

    /**
     * The constructor of the model class. <br>
     * Instantiates a new Model.
     * @param controller Controller instance controller
     */
    public Model(Controller controller) {

        // Pre-populating Coaches and squads into the system.
        createCoaches();
        createSquads();

        boolean filePlayers = new File("playerData.ser").isFile();
        boolean fileJunior = new File("juniorPlayerData.ser").isFile();
        boolean fileSkill = new File("skillData.ser").isFile();

        if (filePlayers && fileJunior && fileSkill) {

            players = controller.deserializePlayers();
            skillCategories = controller.deserializeSkills();

        } else {

            System.out.println("Files - playerData.ser, juniorPlayerData.ser, skillData.ser. Were not found, creating players and skills from scratch.");

            // Pre-populating new players and skills into the system.
            createPlayers();
            createSkills();
        }
    }

    /**
     * Create coaches. <br>
     * Pre-populates the system with hardcoded coaches.
     */
    private void createCoaches() {

        Coach tempCoach;

        tempCoach = new Coach("Conor","King", "ConorK", "10000:cbf94df6ede09c5c69bfca2e57e9c1c3:1a5b0992bb47d4c5921d140524718e6a" );
        coaches.add(tempCoach);

        tempCoach = new Coach("Fred", "Martin", "FredM", "10000:cbf94df6ede09c5c69bfca2e57e9c1c3:1a5b0992bb47d4c5921d140524718e6a");
        coaches.add(tempCoach);

        tempCoach = new Coach("Lamar", "Free", "LamarF", "10000:cbf94df6ede09c5c69bfca2e57e9c1c3:1a5b0992bb47d4c5921d140524718e6a");
        coaches.add(tempCoach);

    }

    /**
     * Create players. <br>
     * Pre-populates the system with hardcoded players.
     */
    private void createPlayers() {

        Object tempPlayer;
        tempPlayer = new Player("James", "King", LocalDate.of(1994, 5, 12), 221144, "13 Proven Road",
                "KY117SC", "07598735476", "JamesK@Gmail.com", "Dr.Albert Cummings:07657865354", "Peter King:07659966754", "Centre", squads.get(0));
        players.add(tempPlayer);

        tempPlayer = new Player("Jerome", "Hill", LocalDate.of(1999, 3, 23), 112233, "69 Strange Place",
                "KY126US", "07395628544", "JeromeH@Gmail.com", "Dr.Eliza Fitzpatrick:07396685661", "Martha Hill:07393368457", "Full Back", squads.get(0));
        players.add(tempPlayer);

        tempPlayer = new Player("Jose", "House", LocalDate.of(2000, 6, 16), 663388, "123 Walking Road",
                "KY152TR", "07224836547", "JoseH@Gmail.com", "Dr.Cloe Cherry:07446354785", "Patrick House:07899304784", "Fly Half", squads.get(0));
        players.add(tempPlayer);

        tempPlayer = new Player("Davis", "Burns", LocalDate.of(1996, 8, 21), 551233, "32 Champion Crescent",
                "KY117UY", "07654324563", "DavisB@Gmail.com", "Dr.Albert Cummings:07657865354", "Chris Burns:07998764763", "Scrum Half", squads.get(0));
        players.add(tempPlayer);

        tempPlayer = new Player("Cassie", "Mcguire", LocalDate.of(1997, 12, 11), 123321, "111 One Road",
                "KY229JC", "07897789364", "CassieM@Gmail.com", "Dr.Marin Strong:07456735465", "Thomas Mcguire:07873524534", "Hooker", squads.get(0));
        players.add(tempPlayer);

        tempPlayer = new JuniorPlayer("Casey", "Glass", LocalDate.of(2005, 5, 1), 991188, "23 Marsh Street",
                "KY417XZ", "07446558832", "CaseyG@Gmail.com", "Dr.Oliver Shah:07668937453", "Wing", squads.get(1), "Trevor Glass:07211004785");
        players.add(tempPlayer);

        tempPlayer = new JuniorPlayer("Leonardo", "Roth", LocalDate.of(2006, 2, 12), 236452, "21 Silly Street",
                "KY127DS", "07456376859", "LeonardoR@Gmail.com", "Dr.Jordan Merritt:07438932733", "Prop", squads.get(1), "Tim Roth:07485765847");
        players.add(tempPlayer);

        tempPlayer = new JuniorPlayer("Lain", "Wong", LocalDate.of(2003, 6, 8), 127643, "14 Mission Row",
                "KY417XZ", "07446558832", "LainW@Gmail.com", "Dr.Nolan Harris:07589368475", "2nd Row", squads.get(1), "Ming Wong:07587677423");
        players.add(tempPlayer);

        tempPlayer = new JuniorPlayer("James", "Dean", LocalDate.of(2005, 12, 27), 115322, "2 Prince Row",
                "KY129XG", "07489384756", "JamesD@Gmail.com", "Dr.Eliza Fitzpatrick:07396685661", "Back Row", squads.get(1), "Maggie Dean:07434533683");
        players.add(tempPlayer);

        tempPlayer = new JuniorPlayer("Reagan", "Levine", LocalDate.of(2008, 2, 18), 918273, "45 Magic Way",
                "KY219DK", "07123674658", "ReaganL@Gmail.com", "Dr.Cloe Cherry:07446354785", "Full Back", squads.get(1), "Trevor Levine:07348573687");
        players.add(tempPlayer);

    }

    /**
     * Create squads. <br>
     * Pre-populates the system with hardcoded squads.
     */
    private void createSquads() {
        Squad tempSquad;
        tempSquad = new Squad("Fire Hawks", getCoaches().get(0));
        squads.add(tempSquad);

        tempSquad = new Squad("Ice Demons", getCoaches().get(1));
        squads.add(tempSquad);

        tempSquad = new Squad("Earth Warriors", getCoaches().get(2));
        squads.add(tempSquad);

    }

    /**
     * Create skills. <br>
     * Pre-populates the system with hardcoded skills.
     */
    private void createSkills() {

        Skill tempSkill;
        SkillCategory tempSkillCategory;
        ArrayList<Skill> passing = new ArrayList<>();
        ArrayList<Skill> tackling = new ArrayList<>();
        ArrayList<Skill> kicking = new ArrayList<>();

        tempSkill = new Skill("Standard", "Standard passing left and right.");
        passing.add(tempSkill);

        tempSkill = new Skill("Spin", "Giving the ball a nice spin when passing left and right.");
        passing.add(tempSkill);

        tempSkill = new Skill("Pop", "Popping the ball up in the air left and right to another player.");
        passing.add(tempSkill);

        tempSkillCategory = new SkillCategory("Passing", passing);
        skillCategories.add(tempSkillCategory);

        tempSkill = new Skill("Front", "Tackling from the front.");
        tackling.add(tempSkill);

        tempSkill = new Skill("Rear", "Tackling from the rear.");
        tackling.add(tempSkill);

        tempSkill = new Skill("Side", "Tackling from the side.");
        tackling.add(tempSkill);

        tempSkill = new Skill("Scrabble", "Enter Scrabble Description Here.");
        tackling.add(tempSkill);

        tempSkillCategory = new SkillCategory("Tackling", tackling);
        skillCategories.add(tempSkillCategory);

        tempSkill = new Skill("Drop", "Kicking the ball while dropping it.");
        kicking.add(tempSkill);

        tempSkill = new Skill("Punt", "Punting the ball up the field.");
        kicking.add(tempSkill);

        tempSkill = new Skill("Grubber", "Kicking the ball up the field for a teammate to intercept.");
        kicking.add(tempSkill);

        tempSkill = new Skill("Goal", "Kicking the ball through the posts for a goal.");
        kicking.add(tempSkill);

        tempSkillCategory = new SkillCategory("Kicking", kicking);
        skillCategories.add(tempSkillCategory);

    }

    /**
     * Gets coaches.
     * @return Arraylist of all coaches
     */
    public ArrayList<Coach> getCoaches() {
        return coaches;
    }

    /**
     * Gets players.
     * @return Arraylist of all players
     */
    public ArrayList<Object> getPlayers() {
        return players;
    }

    /**
     * Gets squads.
     * @return Arraylist of all squads
     */
    public ArrayList<Squad> getSquads() {
        return squads;
    }

    /**
     * Gets skill categories.
     * @return Arraylist of all skill categories
     */
    public ArrayList<SkillCategory> getSkillCategories() {
        return skillCategories;
    }

    /**
     * Sets skill categories.
     * @param skillCategories Arraylist of skill categories
     */
    public void setSkillCategories(ArrayList<SkillCategory> skillCategories) {
        this.skillCategories = skillCategories;
    }
}
