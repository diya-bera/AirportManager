import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
/**
 * ReservationClient.java
 * <p>
 * A client used to connect to a {@code ReservationServer} instance.
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/30/19
 */

public final class ReservationClient {

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    } //isParsable

    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String hostname;
        String portString;
        int port;
        Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;
        String request;
        String response;

        try {

            hostname = JOptionPane.showInputDialog(null,
                    "What is the hostname you'd like to connect to?",
                    "Hostname?",
                    JOptionPane.QUESTION_MESSAGE);

            if (hostname.equals("")) {
                System.out.println();
                JOptionPane.showMessageDialog(null,
                        "Null hostname! Goodbye!",
                        "Invalid Hostname",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                portString = JOptionPane.showInputDialog(null,
                        "What is the port you'd like to connect to?",
                        "Port?",
                        JOptionPane.QUESTION_MESSAGE);

                if (portString.equals("")) {
                    System.out.println();
                    JOptionPane.showMessageDialog(null,
                            "Null port! Goodbye!",
                            "Invalid Port",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!isParsable(portString)) {
                    System.out.println();

                    JOptionPane.showMessageDialog(null,
                            "The specified port must be a non-negative integer! Goodbye!",
                            "Port?", JOptionPane.ERROR_MESSAGE);
                } else {
                    port = Integer.parseInt(portString);

                    socket = new Socket(hostname, port);

                    socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    System.out.println();

                    //System.out.print("Enter your request: ");

                    //request = userInputReader.readLine();

                    //while (request != null) {
                    //socketWriter.write(request);

                    socketWriter.newLine();

                    socketWriter.flush();

                    response = socketReader.readLine();

                    System.out.println();

                    System.out.printf("Response from server: %s%n", response);

                    System.out.println();

                    //System.out.print("Enter your request: ");

                    //request = userInputReader.readLine();
                    //} //end while

                    System.out.println();

                    System.out.println("Goodbye!");
                } //end if
            } //end if
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                userInputReader.close();

                if (socketWriter != null) {
                    socketWriter.close();
                } //end if

                if (socketReader != null) {
                    socketReader.close();
                } //end if
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch
        } //end try catch finally
    } //main
}