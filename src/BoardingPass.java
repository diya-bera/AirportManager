import javax.swing.*;
import java.io.Serializable;

/**
 * BoardingPass.java
 * <p>
 * Prints boarding pass
 * of passenger
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/22/19
 */

public class BoardingPass implements Serializable {
    public JPanel printBoardingPass(Passenger passenger, Airline airline) {
        String pass = String.format("<html>-------------------------------------" +
                        "------------------------------------------<br/>" +
                        "BOARDING PASS FOR FLIGHT %d WITH %s Airlines <br/>" +
                        "PASSENGER FIRST NAME : %s <br/>" +
                        "PASSENGER LAST NAME : %s <br/>" +
                        "PASSENGER AGE : %d <br/>" +
                        "You can now begin boarding at gate %s <br/>" +
                        "--------------------------------------------------" +
                        "-----------------------------<br/></html>", airline.getFlightNumber(),
                airline.getName(), passenger.getFirstName(), passenger.getLastName()
                , passenger.getAge(),
                airline.getGate().getGate());
        JPanel s = new JPanel();
        JLabel label = new JLabel(pass);
        s.add(label);
        return s;
    }
}
