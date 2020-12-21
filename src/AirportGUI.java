import com.sun.source.tree.PackageTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AirportGUI {
    static JFrame frame = new JFrame("Purdue University Airline Reservation System");
    static JPanel panel = new JPanel();

    static Passenger passenger = new Passenger();

    static Alaska alaska;
    static Delta delta;
    static Southwest southwest;

    static {
        try {
            alaska = new Alaska();
            delta = new Delta();
            southwest = new Southwest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public AirportGUI() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stageOne();
        alaska.writeToFile(alaska, delta, southwest);
    }

    public static void stageOne() throws IOException {
        String hostname =
                JOptionPane.showInputDialog(null,
                        "What is the hostname you'd like to connect to?",
                        "Hostname?",
                        JOptionPane.QUESTION_MESSAGE);
        String port =
                JOptionPane.showInputDialog(null,
                        "What is the port you'd like to connect to?",
                        "Port?",
                        JOptionPane.QUESTION_MESSAGE);
        stageTwo();
    }

    public static void stageTwo() throws IOException {
        frame.setSize(650, 400);
        //frame to center
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        //Welcome text
        JLabel label = new JLabel();
        label.setVisible(true);
        label.setText("<html>" + "Welcome to the " +
                "Purdue University Airline " +
                "Reservation Management System!" + "</html>");

        //Making text bold
        Font f = label.getFont();
        label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        label.setFont(new Font(f.getName(), Font.PLAIN, 16));

        //Adding logo
        BufferedImage picture = ImageIO.read(new File("src/logo.png"));
        Image image = picture.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setVisible(true);
        picLabel.setSize(400, 200);

        //Adding buttons
        JButton button = new JButton("Exit");
        JButton button1 = new JButton("Book a Flight");

        panel.add(label);
        panel.add(picLabel);
        panel.add(button);
        panel.add(button1);
        frame.add(panel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank You!",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                stageThree();
            }
        });
    }

    public static void stageThree() {
        JPanel panel1 = new JPanel();
        panel = new JPanel();
        panel.setVisible(true);
        JLabel label = new JLabel();
        label.setSize(650, 350);
        label.setText("Do you want to book a flight today?");

        //Making text bold
        Font f = label.getFont();
        label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        label.setFont(new Font(f.getName(), Font.PLAIN, 16));

        //Adding buttons
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Yes, I want to book a flight.");

        //Adding buttons at the bottom and aligning it in the center
        panel1.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(button1);
        panel.add(button2);
        frame.add(panel1);
        frame.add(panel, BorderLayout.SOUTH);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank You!",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                panel.setVisible(false);
                stageFour();
            }
        });
    }

    public static void stageFour() {
        panel = new JPanel();
        panel.setVisible(true);

        JPanel box = new JPanel();
        box.setVisible(true);

        JPanel textPanel = new JPanel();
        textPanel.setVisible(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setVisible(true);

        //Label declaration
        JLabel label = new JLabel();
        label.setVisible(true);
        label.setText("<html> " +
                "Choose a flight from the drop down menu. " +
                "<br>" +
                "</html");

        //Making text bold
        Font f = label.getFont();
        label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        label.setFont(new Font(f.getName(), Font.PLAIN, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Drop down menu
        String[] airlines = new String[]{"Delta", "Southwest", "Alaska"};
        JComboBox<String> comboBox = new JComboBox<String>(airlines);
        comboBox.setVisible(true);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        box.add(comboBox, BorderLayout.NORTH);
        box.setAlignmentY(Component.CENTER_ALIGNMENT);

        frame.add(panel, BorderLayout.NORTH);

        JTextArea text = new JTextArea();
        text.setText("Delta Airlines is proud to be one of the five premier Airlines at Purdue University.\n" +
                "We are extremely offer exceptional services, with free limited WiFi for all customers.\n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits.\n" +
                "We are also happy to offer power outlets in each seat for passenger use.\n" +
                "We hope you choose to fly Delta as your next Airline.");
        text.setSize(600, 500);
        text.setLineWrap(true);
        text.setVisible(true);
        text.setEditable(false);
        text.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        box.add(text, BorderLayout.SOUTH);
        frame.add(box, BorderLayout.CENTER);

        //Adding buttons

        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Choose this flight");
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        frame.add(buttonPanel, BorderLayout.SOUTH);


        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (comboBox.getSelectedItem().equals("Delta"))
                    text.setText("Delta Airlines is proud to be one of the five premier Airlines at Purdue University.\n" +
                            "We are extremely offer exceptional services, with free limited WiFi for all customers.\n" +
                            "Passengers who use T-Mobile as a cell phone carrier get additional benefits.\n" +
                            "We are also happy to offer power outlets in each seat for passenger use.\n" +
                            "We hope you choose to fly Delta as your next Airline.");
                else if (comboBox.getSelectedItem().equals("Alaska"))
                    text.setText("Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from " +
                            "Purdue University.\n" +
                            "We primarily fly westward, and often have stops in Alaska and California.\n" +
                            "We have first class amenities, even in coach class.\n" +
                            "We provide fun snacks, such as pretzels and goldfish.\n" +
                            "We also have comfortable seats, and free WiFi." +
                            "We hope you choose Alaska Airlines for your next itinerary!");
                else if (comboBox.getSelectedItem().equals("Southwest"))
                    text.setText("Southwest Airlines is proud to offer flights to Purdue University.\n" +
                            "We are happy to offer free in flight WiFi, as well as out amazing snacks.\n" +
                            "In addition, we offer flights for much cheaper than other airlines, " +
                            "and offer two free checked bags.\n" +
                            "We hope you choose Southwest for your next flight.");
            }
        });
        comboBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                JFrame passengerList = new JFrame();
                passengerList.setVisible(true);
                passengerList.setSize(300, 300);

                JPanel flightInfo = new JPanel();
                flightInfo.setVisible(true);

                JLabel airline = new JLabel();
                JLabel passenger = new JLabel();

                airline.setVisible(true);
                passenger.setVisible(true);

                JPanel jPanel = new JPanel();
                jPanel.setVisible(true);

                JPanel passengers = new JPanel();
                passengers.setVisible(true);

                JList<Passenger> list = new JList<Passenger>();

                JPanel exitButton = new JPanel();
                exitButton.setVisible(true);

                JButton exit = new JButton("Exit");

                exitButton.add(exit);

                Font f = airline.getFont();
                airline.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                airline.setFont(new Font(f.getName(), Font.PLAIN, 16));
                if (comboBox.getSelectedItem().equals("Alaska")) {
                    airline.setText("Alaska Airlines.");
                    passenger.setText(alaska.getPassengerCount() + ":" + alaska.getMaxCapacity());
                    list = new JList<>(alaska.getPassengers());
                } else if (comboBox.getSelectedItem().equals("Delta")) {
                    airline.setText("Delta Airlines.");
                    passenger.setText(delta.getPassengerCount() + ":" + delta.getMaxCapacity());
                    list = new JList<>(delta.getPassengers());
                } else if (comboBox.getSelectedItem().equals("Southwest")) {
                    airline.setText("Southwest Airlines.");
                    passenger.setText(southwest.getPassengerCount() + ":" + southwest.getMaxCapacity());
                    list = new JList<>(southwest.getPassengers());
                }
                Dimension d = new Dimension(300, 250);
                JScrollPane scrollPane = new JScrollPane(list);
                scrollPane.setPreferredSize(d);
                list.setPreferredSize(d);

                flightInfo.add(airline);
                flightInfo.add(passenger);

                passengers.add(scrollPane);

                passengerList.add(flightInfo, BorderLayout.NORTH);
                passengerList.add(passengers, BorderLayout.CENTER);
                passengerList.add(exitButton, BorderLayout.SOUTH);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        passengerList.dispose();
                    }
                });
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank You!",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                box.setVisible(false);
                buttonPanel.setVisible(false);
                textPanel.setVisible(false);
                stageFive(comboBox.getSelectedIndex());
            }
        });
    }

    public static void stageFive(int i) {
        panel = new JPanel();
        JPanel buttons = new JPanel();
        buttons.setVisible(true);

        JLabel label = new JLabel();

        switch (i) {
            case 0:
                label.setText("Are you sure you want to book a flight on Delta Airlines?");
                break;
            case 1:
                label.setText("Are you sure you want to book a flight on Southwest Airlines?");
                break;
            case 2:
                label.setText("Are you sure you want to book a flight on Alaska Airlines?");
                break;
        }
        Font f = label.getFont();
        label.setFont(new Font(f.getName(), Font.PLAIN, 16));

        JButton exit = new JButton("Exit");
        JButton no = new JButton("No, I want a different flight.");
        JButton yes = new JButton("Yes, I want this flight.");
        buttons.add(exit);
        buttons.add(no);
        buttons.add(yes);

        panel.add(label);

        frame.add(buttons, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.NORTH);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank You!",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                buttons.setVisible(false);
                stageFour();
            }
        });

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons.setVisible(false);
                panel.setVisible(false);
                try {
                    stageSix(i);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void stageSix(int i) throws IOException {
        panel = new JPanel();

        JLabel label = new JLabel("Please input your information below.");
        Font f = label.getFont();
        label.setFont(new Font(f.getName(), Font.PLAIN, 20));

        panel.add(label);

        JPanel form = new JPanel();
        form.setVisible(true);

        Dimension d = new Dimension(650, 75);

        JLabel firstName = new JLabel("What is your first name?");
        JTextField firstNameInput = new JTextField();
        firstNameInput.setPreferredSize(d);

        JLabel lastName = new JLabel("What is your last name?");
        JTextField lastNameInput = new JTextField();
        lastNameInput.setPreferredSize(d);


        JLabel age = new JLabel("What is your age?");
        JTextField ageInput = new JTextField();
        ageInput.setPreferredSize(d);


        form.add(firstName);
        form.add(firstNameInput);
        form.add(lastName);
        form.add(lastNameInput);
        form.add(age);
        form.add(ageInput);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(form);

        JButton exit = new JButton("Exit");
        JButton next = new JButton("Next");
        JPanel buttons = new JPanel();
        buttons.setVisible(true);

        buttons.add(exit);
        buttons.add(next);

        frame.add(buttons, BorderLayout.SOUTH);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank You!",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(null,
                        "Are all the details you entered correct?\n" +
                                "The passenger's name is " +
                                firstNameInput.getText() + " " +
                                lastNameInput.getText() +
                                " and their age is " +
                                ageInput.getText() +
                                ".\nIf all the information shown is correct, select Yes button below," +
                                " otherwise, select the No button.",
                        "Confirm Info",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"Yes", "No"},
                        "default");
                if (choice == 0) {
                    passenger.setFirstName(firstNameInput.getText());
                    passenger.setLastName(lastNameInput.getText());
                    passenger.setAge(Integer.parseInt(ageInput.getText()));

                    panel.setVisible(false);
                    form.setVisible(false);
                    buttons.setVisible(false);
                    stageSeven(i);
                }
            }
        });
    }

    public static void stageSeven(int i) {
        BoardingPass bp = new BoardingPass();

        panel = new JPanel();
        JPanel passengers = new JPanel();
        JPanel boardingPass = new JPanel();
        JPanel buttons = new JPanel();

        passengers.setVisible(true);
        boardingPass.setVisible(true);
        buttons.setVisible(true);

        JLabel airline = new JLabel();
        Font f = airline.getFont();
        airline.setFont(new Font(f.getName(), Font.PLAIN, 20));

        JLabel flight = new JLabel("Enjoy your flight!");
        Font f1 = flight.getFont();
        flight.setFont(new Font(f1.getName(), Font.PLAIN, 20));

        JLabel gate = new JLabel();
        Font f2 = gate.getFont();
        gate.setFont(new Font(f2.getName(), Font.PLAIN, 20));

        JLabel count = new JLabel();

        Dimension d = new Dimension(650, 100);

        JPanel boarding = new JPanel();

        JList<Passenger> list = new JList<Passenger>();
        list.setPreferredSize(d);

        JButton exit = new JButton("Exit");
        JButton refresh = new JButton("Refresh Flight Status");

        switch (i) {
            case 0:
                airline.setText("Flight data displaying for Delta Airlines");
                gate.setText("Flight is now boarding at Gate " + delta.getGate().getGate());
                count.setText(delta.getPassengerCount() + " : " + delta.getMaxCapacity());
                boarding.add(bp.printBoardingPass(passenger, delta));
                list = new JList<>(delta.getPassengers());
                break;
            case 1:
                airline.setText("Flight data displaying for Southwest Airlines");
                gate.setText("Flight is now boarding at Gate " + southwest.getGate().getGate());
                count.setText(southwest.getPassengerCount() + " : " + southwest.getMaxCapacity());
                boarding.add(bp.printBoardingPass(passenger, delta));
                list = new JList<>(delta.getPassengers());
                break;
            case 2:
                airline.setText("Flight data displaying for Alaska Airlines");
                gate.setText("Flight is now boarding at Gate " + alaska.getGate().getGate());
                count.setText(alaska.getPassengerCount() + " : " + alaska.getMaxCapacity());
                boarding.add(bp.printBoardingPass(passenger, delta));
                list = new JList<>(delta.getPassengers());
                break;
        }

        panel.add(airline);
        panel.add(flight);
        panel.add(gate);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(d);

        passengers.add(count);
        passengers.add(scrollPane);
        passengers.add(boarding);

        buttons.add(exit);
        buttons.add(refresh);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(passengers, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);

        switch (i) {
            case 0:
                delta.setPassengers(passenger);
                break;
            case 1:
                southwest.setPassengers(passenger);
                break;
            case 2:
                alaska.setPassengers(passenger);
                break;
        }
        try {
            alaska.writeGate(alaska, delta, southwest);
            alaska.writeToFile(alaska, delta, southwest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank You!",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        });
    }

}
