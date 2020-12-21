import java.net.ServerSocket;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.io.FileReader;
import java.util.Objects;
import java.net.Socket;
/**
 * ReservationServer.java
 * <p>
 *
 * Server
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/30/19
 */
public final class ReservationServer {

    private ServerSocket serverSocket;

    private File file;

    public ReservationServer(File file) throws NullPointerException, IOException {
        Objects.requireNonNull(file, "the specified file is null");

        this.serverSocket = new ServerSocket(0);

        this.file = file;
    } //CensoringServer

    public void serveClients() {
        Socket clientSocket;
        Thread handlerThread;
        int clientCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                return;
            } //end try catch

            handlerThread = new Thread(new RequestHandler(clientSocket));

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", clientCount);

            clientCount++;
        } //end while
    } //serveClients
}