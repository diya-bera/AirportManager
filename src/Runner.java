import java.io.File;

/**
 * Runner.java
 * <p>
 *
 * Port number generator.
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/30/19
 */

public final class Runner {
    public static void main(String[] args) {
        ReservationServer server;

        try {
            server = new ReservationServer(new File("reservations.txt"));
        } catch (Exception e) {
            e.printStackTrace();

            return;
        }

        server.serveClients();
    }
}