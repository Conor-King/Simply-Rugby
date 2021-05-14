package simplyRugby;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * JuniorPlayer class: <br>
 * The junior player stores the information of junior players in the system. <br>
 * Subclass of the {@link Player} class and implements {@link Serializable}.
 * @author Conor King
 * @date 10/05/2021
 */
public class JuniorPlayer extends Player implements Serializable {

    // Attributes.
    private String guardianInfo;

    /**
     * The constructor for the junior player class. <br>
     * Instantiates a new Junior player.
     * @param firstName    A string first name
     * @param lastName     A string last name
     * @param dateOfBirth  LocalDate instance date of birth
     * @param sruNum       A integer sru num
     * @param address      A string address
     * @param postcode     A string postcode
     * @param phoneNum     A string phone num
     * @param email        A string email
     * @param doctorInfo   A string doctor info
     * @param position     A string position
     * @param squad        Squad instance squad
     * @param guardianInfo A string guardian info
     */
    public JuniorPlayer(String firstName, String lastName, LocalDate dateOfBirth, Integer sruNum, String address, String postcode, String phoneNum, String email, String doctorInfo, String position, Squad squad, String guardianInfo) {
        super(firstName, lastName, dateOfBirth, sruNum, address, postcode, phoneNum, email, doctorInfo, position, squad);
        this.guardianInfo = guardianInfo;
    }

    /**
     * Gets guardian info.
     * @return A string with guardian info
     */
    public String getGuardianInfo() {
        return guardianInfo;
    }

}
