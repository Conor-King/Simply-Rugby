package simplyRugby;

import java.io.Serializable;

/**
 * Member class: <br>
 * The member class is a parent class to the {@link Player} and {@link Coach} class. <br>
 * Holds data on members of the system. <br>
 * Implements {@link Serializable}.
 * @author Conor King
 * @date 10/05/2021
 */
public class Member implements Serializable {

    // Attributes
    private String firstName;
    private String lastName;

    /**
     * Instantiates a new Member. <br>
     * The constructor for the Member class.
     * @param firstName A string first name
     * @param lastName  A string members last name
     */
    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    /**
     * Gets first name.
     * @return A string first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     * @return A string last name
     */
    public String getLastName() {
        return lastName;
    }
}
