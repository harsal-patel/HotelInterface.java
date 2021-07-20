/*
 Created by Harsal Patel
 Project began on 7/10/2021
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class MotelHomepage extends MotelGuest implements ActionListener {
    public static LocalDate currentDate = LocalDate.now();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
    public static MotelGuest noGuest = new MotelGuest();
    public static ArrayList<Integer> roomNumbers = new ArrayList<>(Arrays.asList(101, 102, 103, 104, 105, 106,
            107, 108, 109, 110, 111, 112, 114, 115, 116, 117, 118, 119, 120));
    public static ArrayList<Character> roomStatus = new ArrayList<>(Arrays.asList('V', 'V', 'V', 'V', 'V', 'V',
            'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'));
    public static ArrayList<String> roomType = new ArrayList<>(Arrays.asList("K", "K", "K", "K", "K", "K", "QQ",
            "K", "QQ", "K", "K", "QQ", "K", "K", "QQ", "QQ", "QQ", "QQ", "QQ"));
    public static ArrayList<MotelGuest> guests = new ArrayList<>(Arrays.asList(noGuest, noGuest, noGuest, noGuest,
            noGuest, noGuest, noGuest, noGuest, noGuest, noGuest, noGuest, noGuest, noGuest, noGuest, noGuest,
            noGuest, noGuest, noGuest, noGuest));
    public static int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) throws InputMismatchException {
         JButton viewRooms;
         JButton checkIn;
         JButton checkOut;
         JButton inHouse;
         JButton guestInfo;
         JButton nextDay;
         JPanel roomPanel;
         JPanel inHousePanel;
         JLabel showDate;
        
        int tempNum;
        String tempFirst, tempLast;

        JFrame mainMenu = new JFrame();
        ImageIcon titleLogo = new ImageIcon("src/menuImage.png");
        JLabel titleImage = new JLabel("Hotel Management Menu");
        titleImage.setIcon(titleLogo);
        titleImage.setHorizontalAlignment(JLabel.CENTER);
        titleImage.setVerticalAlignment(JLabel.TOP);
        titleImage.setFont(new Font("Verdana", Font.BOLD, 22));
        titleImage.setIconTextGap(10);
        titleImage.setBounds(120, 10, 380, 50);

        showDate = new JLabel();
        showDate.setText(formatter.format(currentDate));
        showDate.setBounds(205, 539, 120, 40);
        showDate.setFont(new Font("Verdana", Font.BOLD, 14));

        roomPanel = new JPanel();
        roomPanel.setBackground(new Color(209, 237, 255));
        roomPanel.setBounds(150, 80, 460, 432);
        roomPanel.setVisible(false);

        inHousePanel = new JPanel();
        inHousePanel.setBackground(new Color(209, 237, 255));
        inHousePanel.setBounds(150, 80, 460, 432);
        inHousePanel.setVisible(false);

        viewRooms = new JButton();
        viewRooms.setBounds(20, 100, 110, 40);
        viewRooms.setText("Room List");
        viewRooms.setFont(new Font("Verdana", Font.BOLD, 13));
        viewRooms.setFocusable(false);
        viewRooms.setOpaque(true);
        viewRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!roomPanel.isVisible()) {
                    roomPanel.removeAll();
                    String tempColumnNames[] = {"Room", "Type", "Status"};
                    String tempGrid[][] = new String[19][3];
                    for (int i = 0; i < roomNumbers.size(); i++) {
                        tempGrid[i][0] = String.valueOf(roomNumbers.get(i));
                    }
                    for (int i = 0; i < roomType.size(); i++) {
                        tempGrid[i][1] = roomType.get(i);
                    }
                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('V')) {
                            tempGrid[i][2] = "Vacant";
                        } else {
                            tempGrid[i][2] = "Occupied";
                        }
                    }
                    JTable tempTable = new JTable(tempGrid, tempColumnNames);
                    JScrollPane tempSP = new JScrollPane(tempTable);
                    roomPanel.add(tempSP);
                    if (inHousePanel.isVisible()) { inHousePanel.setVisible(false); }
                    roomPanel.setVisible(true);
                }
                else { roomPanel.setVisible(false); }
            }
        });

        checkIn = new JButton();
        checkIn.setBounds(20, 160, 110, 40);
        checkIn.setText("Check-In");
        checkIn.setFont(new Font("Verdana", Font.BOLD, 13));
        checkIn.setFocusable(false);
        checkIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField roomNumberField = new JTextField();
                JTextField firstNameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JTextField streetAddressField = new JTextField();
                JTextField cityStateZipField = new JTextField();
                JTextField numberGuestsField = new JTextField();
                JTextField numberNightsField = new JTextField();

                Object[] fields = {
                        "Room Number", roomNumberField,
                        "First Name", firstNameField,
                        "Last Name", lastNameField,
                        "Street Address", streetAddressField,
                        "City, State and Zip", cityStateZipField,
                        "Number of Guests", numberGuestsField,
                        "Length of Stay", numberNightsField
                };
                int result = JOptionPane.showConfirmDialog(
                        null, fields, "Guest Check-In", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION && roomNumbers.contains(Integer.parseInt(roomNumberField.getText())) &&
                roomStatus.get(roomNumbers.indexOf(Integer.parseInt(roomNumberField.getText()))).equals('O')) {
                    JOptionPane.showMessageDialog(null, "Error: Selected room is occupied.");
                }
                else if (result == JOptionPane.OK_OPTION && !roomNumbers.contains(Integer.parseInt(roomNumberField.getText()))) {
                    JOptionPane.showMessageDialog(null, "Error: Invalid room number.");
                }
                else if (result == JOptionPane.OK_OPTION && Integer.parseInt(numberGuestsField.getText()) > 4) {
                    JOptionPane.showMessageDialog(null, "Error: Maximum occupancy for a room is 4 guests.");
                }
                else if (result == JOptionPane.OK_OPTION && roomNumbers.contains(Integer.parseInt(roomNumberField.getText()))) {
                    MotelGuest tempGuest = new MotelGuest();
                    tempGuest.setRoomNumber(Integer.parseInt(roomNumberField.getText()));
                    tempGuest.setName(firstNameField.getText(), lastNameField.getText());
                    tempGuest.setStreetAddress(streetAddressField.getText());
                    tempGuest.setCityStateZip(cityStateZipField.getText());
                    tempGuest.setNumGuests(Integer.parseInt(numberGuestsField.getText()));
                    tempGuest.setNumNights(Integer.parseInt(numberNightsField.getText()), currentDate, counter);

                    int yesNO = JOptionPane.showConfirmDialog(null,
                            "Room Number: " + tempGuest.getRoomNumber() + "\nName: " + tempGuest.getName() +
                                    "\nStreet Address: " + tempGuest.getStreetAddress() + "\nCity, State and Zip: "
                                    + tempGuest.getCityStateZip() + "\nNumber of Guests: " + tempGuest.getNumGuests()
                                    + "\nLength of stay: " + tempGuest.getNumNights() + "\nCheck-Out Date: " +
                                    formatter.format(tempGuest.getCheckOut()) + "\n\nIs this correct?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (yesNO == JOptionPane.YES_OPTION) {
                        guests.set(roomNumbers.indexOf(tempGuest.getRoomNumber()), tempGuest);
                        roomStatus.set(roomNumbers.indexOf(tempGuest.getRoomNumber()), 'O');
                        JOptionPane.showMessageDialog(null, "Guest successfully checked in!");
                    }
                }
            }
        });

        checkOut = new JButton();
        checkOut.setBounds(20, 220, 110, 40);
        checkOut.setText("Check-Out");
        checkOut.setFont(new Font("Verdana", Font.BOLD, 13));
        checkOut.setFocusable(false);
        checkOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField searchRoom = new JTextField();
                Object[] roomSearch = {"Room Number", searchRoom};
                int result = JOptionPane.showConfirmDialog(null, roomSearch,
                        "Guest Check-Out", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION && !roomNumbers.contains(Integer.parseInt(searchRoom.getText()))) {
                    JOptionPane.showMessageDialog(null, "Error: Invalid room number.");
                }
                else if (result == JOptionPane.OK_OPTION &&
                roomStatus.get(roomNumbers.indexOf(Integer.parseInt(searchRoom.getText()))).equals('V')) {
                    JOptionPane.showMessageDialog(null, "Error: Room is already vacant.");
                }
                else if (result == JOptionPane.OK_OPTION &&
                guests.get(roomNumbers.indexOf(Integer.parseInt(searchRoom.getText()))).getRemainingNights() != 0) {
                    JOptionPane.showMessageDialog(null, "Error: Today is not this guest's check-out date.");
                }
                else if (result == JOptionPane.OK_OPTION) {
                    int tempIndex = roomNumbers.indexOf(Integer.parseInt(searchRoom.getText()));
                    int yesNO = JOptionPane.showConfirmDialog(null,
                            "Room Number: " + guests.get(tempIndex).getRoomNumber() + "\nName: " +
                                    guests.get(tempIndex).getName() + "\nStreet Address: " +
                                    guests.get(tempIndex).getStreetAddress() + "\nCity, State and Zip: "
                                    + guests.get(tempIndex).getCityStateZip() + "\nNumber of Guests: " +
                                    guests.get(tempIndex).getNumGuests() +  "\n\nCheck-Out Date: " +
                                    formatter.format(guests.get(tempIndex).getCheckOut()) +
                                    "\n\nWould you like to check this guest out?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (yesNO == JOptionPane.YES_OPTION) {
                        guests.set(tempIndex, noGuest);
                        roomStatus.set(tempIndex, 'V');
                        JOptionPane.showMessageDialog(null, "Guest successfully checked out!");
                    }
                }
            }
        });

        inHouse = new JButton();
        inHouse.setBounds(20, 280, 110, 40);
        inHouse.setText("In House");
        inHouse.setFont(new Font("Verdana", Font.BOLD, 13));
        inHouse.setFocusable(false);
        inHouse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!inHousePanel.isVisible()) {
                    inHousePanel.removeAll();
                    int tempNumInHouse = 0;
                    int tempIndex = 0;
                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('O')) {
                            tempNumInHouse++;
                        }
                    }
                    String[] tempColumnNames = {"Room", "Last Name", "First Name", "Check-Out Date"};
                    String[][] tempGrid = new String[tempNumInHouse][4];

                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('O')) {
                            tempGrid[tempIndex][0] = String.valueOf(roomNumbers.get(i));
                            tempIndex++;
                        }
                    }
                    tempIndex = 0;
                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('O')) {
                            tempGrid[tempIndex][1] = guests.get(i).getLastName();
                            tempIndex++;
                        }
                    }
                    tempIndex = 0;
                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('O')) {
                            tempGrid[tempIndex][2] = guests.get(i).getFirstName();
                            tempIndex++;
                        }
                    }
                    tempIndex = 0;
                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('O')) {
                            if (guests.get(i).getRemainingNights() == 0) {
                                tempGrid[tempIndex][3] = "TODAY";
                            }
                            else {
                                tempGrid[tempIndex][3] = formatter.format(guests.get(i).getCheckOut());
                            }
                            tempIndex++;
                        }
                    }
                    JTable tempTable = new JTable(tempGrid, tempColumnNames);
                    JScrollPane tempSP = new JScrollPane(tempTable);
                    inHousePanel.add(tempSP);
                    if (roomPanel.isVisible()) { roomPanel.setVisible(false); }
                    inHousePanel.setVisible(true);
                }
                else { inHousePanel.setVisible(false); }
            }
        });

        guestInfo = new JButton();
        guestInfo.setBounds(20, 340, 110, 40);
        guestInfo.setText("Guest Info");
        guestInfo.setFont(new Font("Verdana", Font.BOLD, 13));
        guestInfo.setFocusable(false);
        guestInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField searchRoom = new JTextField();
                Object[] roomSearch = {"Room Number", searchRoom};
                int result = JOptionPane.showConfirmDialog(null, roomSearch,
                        "Guest Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    if (roomNumbers.contains(Integer.parseInt(searchRoom.getText())) &&
                            roomStatus.get(roomNumbers.indexOf(Integer.parseInt(searchRoom.getText()))).equals('V')) {
                        JOptionPane.showMessageDialog(null, "Error: Selected room is vacant.");
                    }
                    else if (!roomNumbers.contains(Integer.parseInt(searchRoom.getText()))) {
                        JOptionPane.showMessageDialog(null, "Error: Invalid room number.");
                    }
                    else if (roomNumbers.contains(Integer.parseInt(searchRoom.getText()))) {
                        int tempIndex = roomNumbers.indexOf(Integer.parseInt(searchRoom.getText()));
                        JOptionPane.showMessageDialog(null, "Room Number: " +
                                guests.get(tempIndex).getRoomNumber() + "\nName: " + guests.get(tempIndex).getName() +
                                "\nStreet Address: " + guests.get(tempIndex).getStreetAddress() + "\nCity, State and Zip: "
                                + guests.get(tempIndex).getCityStateZip() + "\nNumber of Guests: " +
                                guests.get(tempIndex).getNumGuests() + "\nCheck-In Date: " +
                                formatter.format(guests.get(tempIndex).getCheckIn()) + "\nCheck-Out Date: " +
                                formatter.format(guests.get(tempIndex).getCheckOut()), "Guest Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        nextDay = new JButton();
        nextDay.setBounds(20, 540, 175, 40);
        nextDay.setText("Advance Business Day");
        nextDay.setFont(new Font("Verdana", Font.BOLD, 11));
        nextDay.setFocusable(false);
        nextDay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int yesNo = JOptionPane.showConfirmDialog(null,
                        "Are you sure you would like to advance to the next business date?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    boolean canAdvance = true;
                    for (int i = 0; i < roomStatus.size(); i++) {
                        if (roomStatus.get(i).equals('O') && guests.get(i).getRemainingNights() == 0) {
                            canAdvance = false;
                            JOptionPane.showMessageDialog(null,
                                    "Error: One or more guests need to be checked out.");
                            break;
                        }
                    }
                    if (canAdvance) {
                        for (int i = 0; i < roomStatus.size(); i++) {
                            if (roomStatus.get(i).equals('O')) {
                                guests.get(i).removeNight();
                            }
                        }
                        counter++;
                        String tempDate = formatter.format(currentDate.plusDays(counter));
                        showDate.setText("");
                        showDate.setText(tempDate);
                        showDate.paintImmediately(showDate.getVisibleRect());
                        currentDate.plusDays(1);
                        JOptionPane.showMessageDialog(null, "Business day has been advanced!");
                    }
                }
            }
        });

        mainMenu.setTitle("Hotel Management System");
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(650, 640);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainMenu.setLocation(dim.width/2-mainMenu.getSize().width/2, dim.height/2-mainMenu.getSize().height/2);
        mainMenu.setResizable(false);
        mainMenu.setVisible(true);
        ImageIcon logo = new ImageIcon("src/logo.png");
        mainMenu.setIconImage(logo.getImage());
        mainMenu.setLayout(null);
        mainMenu.getContentPane().setBackground(new Color(235, 247, 255));

        mainMenu.add(titleImage);
        mainMenu.add(showDate);
        mainMenu.add(roomPanel);
        mainMenu.add(inHousePanel);
        mainMenu.add(viewRooms);
        mainMenu.add(checkIn);
        mainMenu.add(checkOut);
        mainMenu.add(inHouse);
        mainMenu.add(guestInfo);
        mainMenu.add(nextDay);
    }
}
