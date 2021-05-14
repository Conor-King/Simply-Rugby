package simplyRugby;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Skill class: <br>
 * The skill class holds the information on the skill stored in the system. <br>
 * Implements {@link Serializable}.
 * @author Conor King
 * @date 10/05/2021
 */
public class Skill implements Serializable {

    // Attributes.
    private String name;
    private String description;
    private Integer rating;
    private LocalDate achieved;

    /**
     * The constructor for the skill class.
     * Instantiates a new Skill.
     * @param name        A string name
     * @param description A string description
     */
    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * An overloaded constructor for the skill class. <br>
     * Used when adding a skill to a player instance. <br>
     * Instantiates a new Skill.
     * @param name        A string name
     * @param description A string description
     * @param rating      A integer rating
     * @param achieved    LocalDate instance achieved
     */
    public Skill(String name, String description, int rating, LocalDate achieved) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.achieved = achieved;
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
     * Gets description.
     * @return A string description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets rating.
     * @return A integer rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets rating.
     * @param rating A integer rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Gets achieved.
     * @return LocalDate instance achieved
     */
    public LocalDate getAchieved() {
        return achieved;
    }

}
