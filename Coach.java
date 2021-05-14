package simplyRugby;

import java.io.Serializable;

/**
 * Coach class: <br>
 * Holds the information on the coaches of the system. <br>
 * Subclass of the {@link Member} class and implements {@link Serializable}.
 * @date 10/05/2021
 * @author Conor King
 */
public class Coach extends Member implements Serializable {

    // Attributes.
    private String username;
    private String password;

    /**
     * The constructor for the Coach class.
     * Instantiates a new Coach.
     * @param firstName A string first name
     * @param lastName  A string last name
     * @param username  A string username
     * @param password  A string password
     */
    public Coach(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
