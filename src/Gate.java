import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Gate.java
 * <p>
 * Assigning random gates
 * and deleting when flight is full
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/22/19
 */

public class Gate implements Serializable {
    private Character[] terminals = {'A', 'B', 'C'};
    private ArrayList<String> gates;
    private String gate;

    public String getGate() {
        return gate;
    }

    public Gate() {
        gates = new ArrayList<String>();
        for (Character c : terminals) {
            for (int i = 1; i <= 18; i++) {
                gates.add(c.toString() + i);
            }
        }
        generateGate(0);
    }

    public void deleteGate(String gateName) {
        for (String s : gates) {
            if (s.equals(gateName)) {
                gates.remove(gateName);
            }
        }
    }

    public void generateGate(int i) {
        gate = gates.get(i);
    }

    public ArrayList<String> getGates() {
        return gates;
    }
}
