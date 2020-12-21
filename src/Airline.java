import java.io.*;

/**
 * Airline.java
 * <p>
 * Includes parameters and functions
 * inherited by the flights
 *
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/22/19
 *
 */

public class Airline implements Serializable {
    private String name;
    private int passengerCount;
    private int maxCapacity;
    private Passenger[] passengers;
    private Gate gate;
    private int flightNumber;

    public Airline() {
        passengers = new Passenger[200];
        gate = new Gate();
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }

    public void setPassengers(Passenger passenger) {
        passengers[this.passengerCount] = passenger;
        setPassengerCount(++this.passengerCount);
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeGate(Alaska alaska, Delta delta, Southwest southwest) throws IOException {
        File file = new File("gate.txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        pw.println(alaska.getGate().getGate());
        pw.println(delta.getGate().getGate());
        pw.println(southwest.getGate().getGate());
        pw.flush();
    }

    public void writeToFile(Alaska alaska, Delta delta, Southwest southwest) throws IOException {
        File file = new File("reservations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));

        pw.println(alaska.getName());
        pw.println(String.format("%d/%d", alaska.getPassengerCount(), alaska.getMaxCapacity()));
        pw.println("Alaska passenger list");
        pw.println();
        pw.flush();
        for (Passenger passenger : alaska.getPassengers()) {
            if (passenger == null) break;
            pw.println(passenger.toString());
            pw.println("---------------------ALASKA");
            pw.flush();
        }

        pw.println(delta.getName());
        pw.println(String.format("%d/%d", delta.getPassengerCount(), delta.getMaxCapacity()));
        pw.println("Delta passenger list");
        pw.println();
        pw.flush();
        for (Passenger passenger : delta.getPassengers()) {
            if (passenger == null) break;
            pw.println(passenger.toString());
            pw.println("---------------------DELTA");
            pw.flush();
        }

        pw.println(southwest.getName());
        pw.println(String.format("%d/%d", southwest.getPassengerCount(), southwest.getMaxCapacity()));
        pw.println("Southwest passenger list");
        pw.println();
        pw.flush();
        for (Passenger passenger : southwest.getPassengers()) {
            if (passenger == null) break;
            pw.println(passenger.toString());
            pw.println("---------------------SOUTHWEST");
            pw.flush();
        }

        pw.close();
        br.close();
    }

}

