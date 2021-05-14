package simplyRugby;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Squad class: <br>
 * The squad class holds the name, coach, and player in the squad. <br>
 * Implements {@link Serializable}.
 * @author Conor King
 * @date 10/05/2021
 */
public class Squad implements Serializable {

    // Attributes.
    private String name;
    private Coach coach;
    private ArrayList<Player> players;

    /**
     * The constructor for the squad class. <br>
     * Instantiates a new Squad.
     * @param name  A string name
     * @param coach Coach instance coach
     */
    public Squad(String name, Coach coach) {
        this.name = name;
        this.coach = coach;
    }

    // Getters and Setters.
    /**
     * Gets name.
     * @return A string name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name A string name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets coach.
     * @return Coach instance coach
     */
    public Coach getCoach() {
        return coach;
    }

    /**
     * Sets coach.
     * @param coach Coach instance coach
     */
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    /**
     * Gets players.
     * @return Arraylist of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets players.
     * @param players Arraylist of players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
