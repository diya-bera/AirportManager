import com.sun.source.doctree.AttributeTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * RequestHandler.java
 * <p>
 * A handler for requests made to book flight.
 *
 * @author Diya Bera, Arjun Jayant Shankar, B02
 * @version 11/30/19
 */

public final class RequestHandler implements Runnable {

    private Socket clientSocket;

    private Southwest southwest;
    private Delta delta;
    private Alaska alaska;

    private JFrame frame = new JFrame("Purdue University Airline Reservation System");
    private JPanel panel = new JPanel();

    private Passenger passenger = new Passenger();

    private BufferedReader reader;
    private BufferedWriter writer;

    private Passenger[] passengerList;
    private JList<Passenger> list = new JList<Passenger>();


    public RequestHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");

        this.clientSocket = clientSocket;
        try {

            alaska = new Alaska();
            southwest = new Southwest();
            delta = new Delta();

        } catch (Exception e) {
            System.out.println("RequestHandler constructor");
            e.printStackTrace();
        }
    } //CensoringRequestHandler

    @Override
    public void run() {
        try {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            stageTwo();
            alaska.writeGate(alaska, delta, southwest);
            alaska.writeToFile(alaska, delta, southwest);

        } catch (IOException e) {
            e.printStackTrace();
        }


    } //run

    public void stageTwo() throws IOException {
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
        label.setFont(new Font(f.getName(), Font.BOLD, 16));

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

    public void stageThree() {
        JPanel panel1 = new JPanel();
        panel = new JPanel();
        panel.setVisible(true);
        JLabel label = new JLabel();
        label.setSize(650, 350);
        label.setText("Do you want to book a flight today?");

        //Making text bold
        Font f = label.getFont();
        label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        label.setFont(new Font(f.getName(), Font.BOLD, 16));

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

    public void stageFour() {
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
        label.setFont(new Font(f.getName(), Font.BOLD, 20));
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

        JLabel text = new JLabel();
        text.setText("<html><center>Delta Airlines is proud to be one of the five premier " +
                "Airlines at Purdue University.<br/>" +
                "We are extremely offer exceptional services, with free limited WiFi for all customers.<br/>" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits.<br/>" +
                "We are also happy to offer power outlets in each seat for passenger use.<br/>" +
                "We hope you choose to fly Delta as your next Airline.</center></html>");
        text.setPreferredSize(new Dimension(600, 150));
        text.setVisible(true);
        text.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        box.add(text, BorderLayout.SOUTH);

        frame.add(box, BorderLayout.CENTER);

        Font f1 = text.getFont();
        text.setFont(new Font(f1.getName(), Font.PLAIN, 14));

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
                    text.setText("<html><center>Delta Airlines is proud to be one of the five premier " +
                            "Airlines at Purdue University.<br/>" +
                            "We are extremely offer exceptional services, with free limited " +
                            "WiFi for all customers.<br/>" +
                            "Passengers who use T-Mobile as a cell phone carrier get additional benefits.<br/>" +
                            "We are also happy to offer power outlets in each seat for passenger use.<br/>" +
                            "We hope you choose to fly Delta as your next Airline.</center></html>");
                else if (comboBox.getSelectedItem().equals("Alaska"))
                    text.setText("<html><center>Alaska Airlines is proud to serve the strong and knowledgeable " +
                            "Boilermakers from " +
                            "Purdue University.<br/>" +
                            "We primarily fly westward, and often have stops in Alaska and California.<br/>" +
                            "We have first class amenities, even in coach class.<br/>" +
                            "We provide fun snacks, such as pretzels and goldfish.<br/>" +
                            "We also have comfortable seats, and free WiFi.<br/>" +
                            "We hope you choose Alaska Airlines for your next itinerary!</html></center>");
                else if (comboBox.getSelectedItem().equals("Southwest"))
                    text.setText("<html><center>Southwest Airlines is proud to offer flights to " +
                            "Purdue University.<br/>" +
                            "We are happy to offer free in flight WiFi, as well as out amazing snacks.<br/>" +
                            "In addition, we offer flights for much cheaper than other airlines, " +
                            "and offer two free checked bags.<br/>" +
                            "We hope you choose Southwest for your next flight.</html></center>");
            }
        });
        comboBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_ENTER) {
                    JFrame passengerListFrame = new JFrame();
                    passengerListFrame.setVisible(true);
                    passengerListFrame.setSize(300, 300);

                    JPanel flightInfo = new JPanel();
                    flightInfo.setVisible(true);

                    JLabel airline = new JLabel();
                    JLabel passengerLabel = new JLabel();

                    airline.setVisible(true);
                    passengerLabel.setVisible(true);

                    JPanel jPanel = new JPanel();
                    jPanel.setVisible(true);

                    JPanel passengers = new JPanel();
                    passengers.setVisible(true);

                    JList<Passenger> listP = new JList<Passenger>();

                    JPanel exitButton = new JPanel();
                    exitButton.setVisible(true);

                    JButton exit = new JButton("Exit");

                    exitButton.add(exit);

                    Font f = airline.getFont();
                    airline.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                    airline.setFont(new Font(f.getName(), Font.BOLD, 16));
                    if (comboBox.getSelectedItem().equals("Alaska")) {
                        airline.setText("Alaska Airlines.");
                        passengerLabel.setText(alaska.getPassengerCount() + ":" + alaska.getMaxCapacity());
                        listP = new JList<>(alaska.getPassengers());
                    } else if (comboBox.getSelectedItem().equals("Delta")) {
                        airline.setText("Delta Airlines.");
                        passengerLabel.setText(delta.getPassengerCount() + ":" + delta.getMaxCapacity());
                        listP = new JList<>(delta.getPassengers());
                    } else if (comboBox.getSelectedItem().equals("Southwest")) {
                        airline.setText("Southwest Airlines.");
                        passengerLabel.setText(southwest.getPassengerCount() + ":" + southwest.getMaxCapacity());
                        listP = new JList<>(southwest.getPassengers());
                    }
                    Dimension d = new Dimension(300, 250);
                    JScrollPane scrollPane = new JScrollPane(listP);
                    scrollPane.setPreferredSize(d);
                    listP.setPreferredSize(d);

                    flightInfo.add(airline);
                    flightInfo.add(passengerLabel);

                    passengers.add(scrollPane);

                    passengerListFrame.add(flightInfo, BorderLayout.NORTH);
                    passengerListFrame.add(passengers, BorderLayout.CENTER);
                    passengerListFrame.add(exitButton, BorderLayout.SOUTH);
                    exit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            passengerListFrame.dispose();
                        }
                    });
                }
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

    public void stageFive(int i) {
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
        label.setFont(new Font(f.getName(), Font.BOLD, 16));

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

    public void stageSix(int i) throws IOException {
        panel = new JPanel();

        JLabel label = new JLabel("Please input your information below.");
        Font f = label.getFont();
        label.setFont(new Font(f.getName(), Font.BOLD, 20));

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
                boolean check = false;
                if (firstNameInput.getText().equals("") ||
                        lastNameInput.getText().equals("") ||
                        ageInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill in all information.",
                            "Empty Field",
                            JOptionPane.ERROR_MESSAGE);
                }
                for (int i = 0; i < firstNameInput.getText().length(); i++) {
                    if (!Character.isAlphabetic(firstNameInput.getText().charAt(i))) {
                        JOptionPane.showMessageDialog(null,
                                "First name should contain only letters!",
                                "First Name",
                                JOptionPane.ERROR_MESSAGE);
                        check = false;
                        break;
                    } else check = true;
                }
                if (check) {
                    for (int i = 0; i < lastNameInput.getText().length(); i++) {
                        if (!Character.isAlphabetic(lastNameInput.getText().charAt(i))) {
                            JOptionPane.showMessageDialog(null,
                                    "Last name should contain only letters!",
                                    "Last Name",
                                    JOptionPane.ERROR_MESSAGE);
                            check = false;
                            break;
                        } else check = true;
                    }
                }
                if (check) {
                    try {
                        int age = Integer.parseInt(ageInput.getText());
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid age value!",
                                "Age",
                                JOptionPane.ERROR_MESSAGE);
                        check = false;
                    }
                }
                if (check) {
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
                        try {

                            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                            writer.flush();

                            writer.write(passenger.toString());
                            writer.newLine();
                            writer.flush();

                            reader.close();
                            writer.close();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        panel.setVisible(false);
                        form.setVisible(false);
                        buttons.setVisible(false);
                        try {
                            stageSeven(i);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void stageSeven(int i) throws IOException {
        BoardingPass bp = new BoardingPass();

        panel = new JPanel();
        JPanel passengers = new JPanel();
        JPanel boardingPass = new JPanel();
        JPanel buttons = new JPanel();

        panel.setVisible(true);
        passengers.setVisible(true);
        boardingPass.setVisible(true);
        buttons.setVisible(true);

        JLabel airline = new JLabel();
        Font f = airline.getFont();
        airline.setFont(new Font(f.getName(), Font.BOLD, 16));

        JLabel count = new JLabel();

        Dimension d = new Dimension(650, 100);

        JPanel boarding = new JPanel();

        list.setPreferredSize(d);
        panel.setPreferredSize(new Dimension(650, 90));

        JButton exit = new JButton("Exit");
        JButton refresh = new JButton("Refresh Flight Status");

        passengerList = new Passenger[200];

        int passengerCount = 0;

        switch (i) {
            case 0:
                airline.setText("<html>Flight data displaying for Delta Airlines<br/>" +
                        "<center>Enjoy your flight!</center>" +
                        "<center>Flight is now boarding at Gate " + delta.getGate().getGate() + "</center></html>");
                passengerCount = delta.getPassengerCount();
                count.setText(passengerCount + " : " + delta.getMaxCapacity());
                boarding.add(bp.printBoardingPass(passenger, delta));
                int k = 0;
                for (int j = delta.getPassengerCount() - 1; j >= 0; j--) {
                    passengerList[k] = delta.getPassengers()[j];
                    k++;
                }
                break;
            case 1:
                airline.setText("<html>Flight data displaying for Southwest Airlines<br/>" +
                        "<center>Enjoy your flight!</center><br/>" +
                        "<center>Flight is now boarding at Gate " + southwest.getGate().getGate() + "</center></html>");
                passengerCount = southwest.getPassengerCount();
                count.setText(passengerCount + " : " + southwest.getMaxCapacity());
                boarding.add(bp.printBoardingPass(passenger, southwest));
                k = 0;
                for (int j = southwest.getPassengerCount() - 1; j >= 0; j--) {
                    passengerList[k] = southwest.getPassengers()[j];
                    k++;
                }
                break;
            case 2:
                airline.setText("<html>Flight data displaying for Alaska Airlines<br/>" +
                        "<center>Enjoy your flight!</center><br/>" +
                        "<center>Flight is now boarding at Gate " + alaska.getGate().getGate() + "</center></html>");
                passengerCount = alaska.getPassengerCount();
                count.setText(passengerCount + " : " + alaska.getMaxCapacity());
                boarding.add(bp.printBoardingPass(passenger, alaska));
                k = 0;
                for (int j = alaska.getPassengerCount() - 1; j >= 0; j--) {
                    passengerList[k] = alaska.getPassengers()[j];
                    k++;
                }
                break;
        }


        list = new JList<>(passengerList);

        panel.add(airline, Component.CENTER_ALIGNMENT);

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
        alaska.writeGate(alaska, delta, southwest);
        alaska.writeToFile(alaska, delta, southwest);

        refresh.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                switch (i) {
                    case 0:
                        count.setText(delta.getPassengerCount() + " : " + delta.getMaxCapacity());
                        int k = 0;
                        for (int j = delta.getPassengerCount() - 1; j >= 0; j--) {
                            passengerList[k] = delta.getPassengers()[j];
                            k++;
                        }
                        break;
                        case 1:
                            count.setText(southwest.getPassengerCount() + " : " +
                                                          southwest.getMaxCapacity());

                            k = 0;
                            for (int j = southwest.getPassengerCount() - 1; j >= 0; j--) {
                                passengerList[k] = southwest.getPassengers()[j];
                                k++;
                            }
                            break;
                        case 2:
                            count.setText(alaska.getPassengerCount() + " : " + alaska.getMaxCapacity());
                            k = 0;
                            for (int j = alaska.getPassengerCount() - 1; j >= 0; j--) {
                                passengerList[k] = alaska.getPassengers()[j];
                                k++;
                            }
                            break;
                }
                list.setListData(passengerList);
            }
        }
        );
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


