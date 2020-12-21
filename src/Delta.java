import java.io.*;

/**
 * Delta.java
 * <p>
 * Delta flight passengers and gate assignment
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/22/19
 */

public class Delta extends Airline implements Serializable {
    private int gateNumber = 5;

    public Delta() throws IOException {
        this.setName("Delta");
        File gateFile = new File("gate.txt");
        if (gateFile.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(gateFile));
            String s = br.readLine();
            s = br.readLine();
            this.gateNumber = this.getGate().getGates().indexOf(s);
        }
        if (gateNumber == -1) this.gateNumber = 5;
        this.getGate().generateGate(gateNumber);
        this.setPassengerCount(0);
        this.setMaxCapacity(100);
        this.setFlightNumber(25200);
        this.readFromFile();
    }

    public void addPassenger(Passenger passenger) {
        setPassengers(passenger);
    }

    public void readFromFile() throws IOException {
        File file = new File("reservations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (true) {
            String r;
            String s = br.readLine();
            if (s == null) break;
            if (s.equals(this.getName())) {
                s = br.readLine();
                if (s == null) break;
                s = br.readLine();
                if (s == null) break;
                s = br.readLine();
                if (s == null) break;
                while (true) {
                    s = br.readLine();
                    r = br.readLine();
                    if (s == null) break;
                    if (r != null && (r.contains("DELTA"))) {
                        String[] details = s.split(",");
                        Passenger passenger = new Passenger();
                        passenger.setFirstName(details[0].substring(0, 1));
                        passenger.setLastName(details[0].substring(3));
                        passenger.setAge(Integer.parseInt(details[1].substring(1)));
                        this.setPassengers(passenger);
                    } else break;
                    if (getPassengerCount() == getMaxCapacity()) {
                        this.setPassengers(new Passenger[getMaxCapacity()]);
                        this.setPassengerCount(0);
                        if (this.gateNumber == 54) {
                            this.gateNumber -= 54;
                            this.getGate().generateGate(0);
                        } else
                            this.getGate().generateGate(++gateNumber);
                    }
                }
            }
        }
        br.close();

    }


    public String readFile() throws IOException {
        String result = "";
        File file = new File("reservations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (true) {
            String r;
            String s = br.readLine();
            if (s == null) break;
            if (s.equals("Delta")) {
                s = br.readLine();
                if (s == null) break;
                s = br.readLine();
                if (s == null) break;
                s = br.readLine();
                if (s == null) break;
                while (true) {
                    s = br.readLine();
                    r = br.readLine();
                    if (s == null) break;
                    if (r != null && (r.contains("DELTA"))) {
                        result += s;
                    } else break;
                }
            }
        }
        br.close();
        return result;
    }
}


