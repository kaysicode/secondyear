package Server.GUI;

import Server.XML.XMLReader;
import Server.XML.XMLWriter;
import Client.Model.Event;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddEventPage extends JPanel {
    private JLabel nameLabel, dateLabel, monthLabel, startTimeLabel, endTimeLabel, yearLabel, descriptionLabel;
    private JComboBox<Integer> dayField;
    private JComboBox<String> monthField, startTimeField, endTimeField, nameField, yearField;
    private JButton addButton;
    private JTextField customEventField;
    private JTextArea descriptionField;
    private JLabel verLabel;
    private int yAxis = 0;

    public AddEventPage() {
        Date date = new Date();
        int currentYear = date.getYear() + 1900;

        // Event Info
        String[] events = {"Valentines Day", "Acquaintances Party", "ICON: Awardees", "Other"};
        String eventDesc = " ";

        // Initialize day, month, and year options
        String[] year = {String.valueOf(currentYear), String.valueOf(currentYear + 1)};
        String[] month = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        String[] times = {
                "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"
        };

        // Set up panel
        this.setLayout(null);
        this.setBounds(0, 0, 1231, 593);
        this.setBackground(new Color(217, 217, 217));

        // Event Name
        nameLabel = new JLabel("Event Name");
        nameLabel.setBounds(28, yAxis += 27, 100, 20);
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(nameLabel);

        //event name field
        nameField = new JComboBox<>(events);
        styleComboBox(nameField);
        nameField.setBounds(28, yAxis += 22, 300, 30);
        this.add(nameField);

        // Custom Event Field (Hidden initially)
        customEventField = new JTextField();
        customEventField.setBounds(28, yAxis += 22, 300, 30);
        customEventField.setFont(new Font("Roboto", Font.PLAIN, 14));
        customEventField.setVisible(false);
        this.add(customEventField);

        nameField.addActionListener(e -> {
            if (nameField.getSelectedItem().equals("Other")) {
                customEventField.setVisible(true);
            } else {
                customEventField.setVisible(false);
            }
        });

        // Event Year
        yearLabel = new JLabel("Event Year");
        yearLabel.setBounds(28, yAxis += 22, 130, 20);
        yearLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(yearLabel);

        yearField = new JComboBox<>(year);
        styleComboBox(yearField);
        yearField.setBounds(28, yAxis += 22, 80, 30);
        this.add(yearField);

        // Event Month
        monthLabel = new JLabel("Event Month");
        monthLabel.setBounds(28, yAxis += 25, 100, 20);
        monthLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(monthLabel);

        monthField = new JComboBox<>(month);
        styleComboBox(monthField);
        monthField.setBounds(28, yAxis += 22, 120, 30);
        this.add(monthField);

        // Event Date
        dateLabel = new JLabel("Event Date");
        dateLabel.setBounds(28, yAxis += 25, 100, 20);
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(dateLabel);

        dayField = new JComboBox<>();
        styleComboBox(dayField);
        dayField.setBounds(28, yAxis += 22, 60, 20);
        updateDays(); // Populate days based on the selected month and year
        this.add(dayField);

        // Add listeners to update days when year or month changes
        yearField.addActionListener(e -> {
            updateDays();
            updateMonths();
        });
        //updates the days when month is changed
        monthField.addActionListener(e -> updateDays());

        // Start Time
        startTimeLabel = new JLabel("Start Time");
        startTimeLabel.setBounds(160, yearLabel.getY(), 100, 20);
        startTimeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(startTimeLabel);

        //start time field
        startTimeField = new JComboBox<>(times);
        styleComboBox(startTimeField);
        startTimeField.setBounds(160, yearLabel.getY() + 22, 140, 30);
        this.add(startTimeField);

        // End Time
        endTimeLabel = new JLabel("End Time");
        endTimeLabel.setBounds(160, yearLabel.getY() + 44, 100, 20);
        endTimeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(endTimeLabel);

        //end time
        endTimeField = new JComboBox<>(times);
        styleComboBox(endTimeField);
        endTimeField.setBounds(160, yearLabel.getY() + 66, 140, 30);
        this.add(endTimeField);

        // Description
        descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(28, 260, 100, 20);
        descriptionLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.add(descriptionLabel);


        descriptionField = new JTextArea();
        descriptionField.setBounds(28, 285, 280, 60);
        descriptionField.setFont(new Font("Roboto", Font.PLAIN, 14));
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        this.add(descriptionField);

        // Add Event Button
        addButton = new JButton("Add Event");
        addButton.setBounds(28, 350, 280, 30);
        addButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        addButton.setBackground(new Color(176, 207, 139));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusable(false);
        this.add(addButton);

        // Add action listener for the button
        addButton.addActionListener(e -> addEvent());

        // Initialize verification label
        verLabel = new JLabel();
        verLabel.setBounds(514, 25, 684, 526); // Adjust size accordingly
        verLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        verLabel.setOpaque(true);
        verLabel.setBackground(new Color(176, 207, 139)); // Light green for success
        verLabel.setForeground(Color.WHITE);
        verLabel.setHorizontalAlignment(SwingConstants.CENTER);
        verLabel.setVisible(false); // Initially hidden
        this.add(verLabel);

    }

    private void updateDays() {
        String selectedMonth = (String) monthField.getSelectedItem();//gets the selected item
        int selectedYear = Integer.parseInt((String) yearField.getSelectedItem());//gets the year selected
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);//process

        // Populate dayField with the correct number of days
        Integer[] days = new Integer[daysInMonth];
        for (int i = 1; i <= daysInMonth; i++) {
            days[i - 1] = i;
        }
        dayField.setModel(new DefaultComboBoxModel<>(days));
    }

    private int getDaysInMonth(String month, int year) {
        switch (month) {
            case "January":
            case "March":
            case "May":
            case "July":
            case "August":
            case "October":
            case "December":
                return 31;//days will be 31
            case "April":
            case "June":
            case "September":
            case "November":
                return 30;
            case "February":
                return (year % 4 == 0) ? 29 : 28; // checks if it is a leap year
            default:
                return 31;//default days
        }
    }

    /**
     * get the data in the text field and store it into the variables and then that it will check their validation
     */
    private void addEvent() {
        String eventName = nameField.getSelectedItem().equals("Other") ? customEventField.getText()
                : (String) nameField.getSelectedItem();
        int day = (int) dayField.getSelectedItem();
        String month = (String) monthField.getSelectedItem();
        int year = Integer.parseInt((String) yearField.getSelectedItem());
        String startTime = (String) startTimeField.getSelectedItem();
        String endTime = (String) endTimeField.getSelectedItem();
        String description = (String) descriptionField.getText();
        int[] seatNo = new int[]{};

        if (eventName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the event name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate time selection
        if (!isValidTime(startTime, endTime)) {
            JOptionPane.showMessageDialog(this, "End time must be later than start time.", "Invalid Time",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate date selection
        if (!isValidDate(year, month, day)) {
            JOptionPane.showMessageDialog(this, "The selected date must not be in the past.", "Invalid Date",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Assuming XMLReader.readEvents("events.xml") returns a List<Event>
        List<Event> eventList1 = XMLReader.readEvents("events.xml");

        // Get the last Event ID from the list of events
        String lastEventId = "";
        if (!eventList1.isEmpty()) {
            // Get the last event's EventID
            lastEventId = eventList1.get(eventList1.size() - 1).getEventID();
        }

        // Extract the number part from the last Event ID
        int eventCounter = 0;
        if (!lastEventId.isEmpty()) {
            // Assuming EventID format is "EVENT_123"
            try {
                // Extract the number part from EventID (substring starting from index 6 onward)
                String eventNumberStr = lastEventId.substring(6);  // "EVENT_1" -> "1"
                eventCounter = Integer.parseInt(eventNumberStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Event ID format: " + lastEventId);
                eventCounter = 0; // Fallback if parsing fails
            }
        }

        // Generate the new EventID by incrementing the eventCounter
        eventCounter++;  // Increment by 1 for the next Event ID
        String newEventId = "EVENT_" + eventCounter;

        // Display confirmation
        String eventDate = day + " " + month + " " + year;
        System.out.println("Event Added:");
        System.out.println("Name: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Event Description: " + description);
        System.out.println("New Event ID: " + newEventId);


        // Create an Event object
        Event event = new Event(newEventId, eventName, startTime, endTime, String.valueOf(day), month, description);

        // Save the event to XML
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        XMLWriter.eventXML(eventList, "events.xml");

        JOptionPane.showMessageDialog(this, "Event added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        verLabel.setText("<html>"
                + "<div style='text-align: center; font-size: 16px;'><b>Event Added Successfully!</b></div><br><br>"

                + "<div style='font-size: 12px;'><b>Event Name:</b></div>"
                + "<div style='font-size: 25px;'><b>" + eventName + "</b></div><br>"

                + "<div style='font-size: 12px;'><b>Event Date:</b></div>"
                + "<div style='font-size: 16px;'>" + eventDate + "</div><br>"

                + "<div style='font-size: 12px;'><b>Event Time:</b></div>"
                + "<div style='font-size: 16px;'>" + startTime + " - " + endTime + "</div><br>"

                + "<div style='font-size: 12px;'><b>Description:</b></div>"
                + "<div style='font-size: 16px;'>" + description + "</div><br>"

                + "<div style='font-size: 12px;'><b>Event ID:</b></div>"
                + "<div style='font-size: 16px;'>" + newEventId + "</div><br>"

                + "</html>");

        verLabel.setVisible(true);

    }

    /**
     *
     * @param start - the start of time of the event
     * @param end - the end of time of the event
     * @return - it will return a true if the endIndex is greater than startIndex otherwise it will return false
     */
    private boolean isValidTime(String start, String end) {
        int startIndex = startTimeField.getSelectedIndex();
        int endIndex = endTimeField.getSelectedIndex();
        return endIndex > startIndex; // Ensure end time is after start time
    }


    /**
     *
     * @param year - year of the event
     * @param month - month of the event
     * @param day - day of the event
     * @return - it will return true if the eventDate is greater
     * than current to be able to check if it's already past current date
     */
    private boolean isValidDate(int year, String month, int day) {
        Calendar current = Calendar.getInstance();
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(year, getMonthIndex(month), day);

        return eventDate.after(current) || eventDate.equals(current);
    }


    private int getMonthIndex(String month) {
        switch (month) {
            case "January":
                return 0;
            case "February":
                return 1;
            case "March":
                return 2;
            case "April":
                return 3;
            case "May":
                return 4;
            case "June":
                return 5;
            case "July":
                return 6;
            case "August":
                return 7;
            case "September":
                return 8;
            case "October":
                return 9;
            case "November":
                return 10;
            case "December":
                return 11;
            default:
                return 0;
        }
    }

    private void updateMonths() {
        String[] month = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int selectedYear = Integer.parseInt((String) yearField.getSelectedItem());
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        if (selectedYear == currentYear) {
            // Current year - show remaining months
            String[] remMonth = new String[12 - currentMonth];
            for (int i = currentMonth; i < 12; i++) {
                remMonth[i - currentMonth] = month[i];
            }
            monthField.setModel(new DefaultComboBoxModel<>(remMonth));
        } else {
            // Non-current year - show all months
            monthField.setModel(new DefaultComboBoxModel<>(month));
        }
        updateDays(); // Update days when months change
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFocusable(false);

        // Custom renderer for highlighting selection
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(new Font("Roboto", Font.PLAIN, 11));
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
                setBackground(isSelected ? new Color(100, 150, 250) : Color.WHITE); // Highlight
                setForeground(isSelected ? Color.WHITE : Color.BLACK);
                return this;
            }
        });
    }
}