import java.io.*;

/**
 * Passenger.java
 * <p>
 * Passenger name and age
 *
 *
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/22/19
 *
 */

public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s. %s, %d",
                firstName.charAt(0), lastName, age);
    }


}
