package Server.GUI;

import Client.Model.Customer;
import Client.Model.Event;
import Server.XML.XMLReader;
import Server.XML.XMLWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

/**
 * DataPage is a JPanel that manages the user interface for displaying and managing customer and event data.
 * It allows users to search, delete, update, and load customer and event data from an XML file.
 */
public class DataPage extends JPanel {
    private JPanel boundaryPanel;
    private JLabel userLabel, eventLabel;
    private JTextField userSearchField, eventSearchField;
    private JButton userSearchButton, eventSearchButton, saveButton, loadButton, deleteUserButton, deleteEventButton, updateButton;
    private JScrollPane userScrollPane, eventScrollPane;
    private JTable userTable, eventTable;
    private DefaultTableModel userModel, eventModel;
    private TableRowSorter<DefaultTableModel> userSorter, eventSorter;

    /**
     * Constructs a DataPage and initializes the UI and listeners.
     */
    public DataPage() {
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the user interface components such as tables, buttons, and panels.
     */
    private void setupUI() {
        boundaryPanel = new JPanel();
        boundaryPanel.setBounds(615, 0, 3, 593);
        boundaryPanel.setBackground(Color.WHITE);

        userModel = new DefaultTableModel(new String[]{"Event ID", "Last Name", "First Name", "Contact Number", "Email", "Seat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        userTable = new JTable(userModel);
        userSorter = new TableRowSorter<>(userModel);
        userTable.setRowSorter(userSorter);
        styleTable(userTable);
        userScrollPane = new JScrollPane(userTable);
        userScrollPane.setPreferredSize(new Dimension(600, 100));

        eventModel = new DefaultTableModel(new String[]{"Event ID", "Event Name", "Start Time", "End Time", "Event Day", "Event Month"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        eventTable = new JTable(eventModel);
        eventSorter = new TableRowSorter<>(eventModel);
        eventTable.setRowSorter(eventSorter);
        styleTable(eventTable);
        eventScrollPane = new JScrollPane(eventTable);
        eventScrollPane.setPreferredSize(new Dimension(600, 100));

        userLabel = new JLabel("Customer", SwingConstants.CENTER);
        eventLabel = new JLabel("Event", SwingConstants.CENTER);

        userSearchField = new JTextField(15);
        userSearchButton = new JButton("Search");
        styleButton(userSearchButton);

        eventSearchField = new JTextField(15);
        eventSearchButton = new JButton("Search");
        styleButton(eventSearchButton);

        JPanel userSearchPanel = new JPanel(new FlowLayout());
        userSearchPanel.add(userLabel);
        userSearchPanel.add(userSearchField);
        userSearchPanel.add(userSearchButton);

        JPanel eventSearchPanel = new JPanel(new FlowLayout());
        eventSearchPanel.add(eventLabel);
        eventSearchPanel.add(eventSearchField);
        eventSearchPanel.add(eventSearchButton);

        JPanel userTablePanel = new JPanel(new BorderLayout());
        userTablePanel.add(userSearchPanel, BorderLayout.NORTH);
        userTablePanel.add(userScrollPane, BorderLayout.CENTER);

        JPanel eventTablePanel = new JPanel(new BorderLayout());
        eventTablePanel.add(eventSearchPanel, BorderLayout.NORTH);
        eventTablePanel.add(eventScrollPane, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel(new GridLayout(1, 2, 5, 5));
        tablePanel.add(userTablePanel);
        tablePanel.add(eventTablePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveButton = new JButton("Save");
        styleButton(saveButton);
        deleteUserButton = new JButton("Delete User");
        styleButton(deleteUserButton);
        deleteEventButton = new JButton("Delete Event");
        styleButton(deleteEventButton);
        loadButton = new JButton("Load");
        styleButton(loadButton);
        updateButton = new JButton("Update");
        styleButton(updateButton);

        buttonPanel.add(saveButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(deleteEventButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(updateButton);

        this.setLayout(new BorderLayout());
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setBounds(0, 0, 1231, 593);
        this.setBackground(new Color(219, 219, 219));
    }

    /**
     * Styles the given JTable by adjusting its font, row height, and other visual properties.
     *
     * @param table The table to be styled.
     */
    private void styleTable(JTable table) {
        table.setFont(new Font("Roboto", Font.PLAIN, 10));
        table.setRowHeight(15);
        table.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(176, 207, 139));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(0XCF8BB0));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setBorder(BorderFactory.createLineBorder(new Color(176, 207, 139)));
        table.setShowGrid(true);
    }

    /**
     * Styles the given JButton by setting background color, foreground color, and removing focusability.
     *
     * @param button The button to be styled.
     */
    private void styleButton(JButton button) {
        button.setBackground(new Color(176, 207, 139));
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
    }

    /**
     * Filters the rows of a table based on the search query entered in the given search field.
     *
     * @param searchField The search field where the user enters a query.
     * @param sorter      The sorter that will be applied to the table.
     */
    private void filterTable(JTextField searchField, TableRowSorter<DefaultTableModel> sorter) {
        String query = searchField.getText();
        if (query.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    /**
     * Sets up action listeners for various buttons like search, delete, update, and load buttons.
     */
    private void setupListeners() {
        userSearchButton.addActionListener(e -> filterTable(userSearchField, userSorter));
        eventSearchButton.addActionListener(e -> filterTable(eventSearchField, eventSorter));
        deleteUserButton.addActionListener(e -> deleteSelectedUser());
        deleteEventButton.addActionListener(e -> deleteSelectedEvent());
        loadButton.addActionListener(e -> {
            loadCustomerData("events.xml", userModel);
            loadEventData("events.xml", eventModel);
        });
        updateButton.addActionListener(e -> {
            updateSelectedRow();
            this.repaint();
            this.revalidate();
        });
    }


    /**
     * Deletes the selected customer from the table and the corresponding XML file.
     */
    private void deleteSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = userTable.convertRowIndexToModel(selectedRow);
            String lastName = (String) userModel.getValueAt(modelRow, 1);
            userModel.removeRow(modelRow);
            XMLWriter.removeNodeByAttribute("Customer1", "Last_Name", lastName, "events.xml");
        } else {
            JOptionPane.showMessageDialog(this, "No user selected for deletion.");
        }
    }

    /**
     * Deletes the selected event from the table and the corresponding XML file.
     */
    private void deleteSelectedEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = eventTable.convertRowIndexToModel(selectedRow);
            String eventID = (String) eventModel.getValueAt(modelRow, 0);
            eventModel.removeRow(modelRow);
            XMLWriter.removeNodeByAttribute("Event", "Event_ID", eventID, "events.xml");
        } else {
            JOptionPane.showMessageDialog(this, "No event selected for deletion.");
        }
    }

    /**
     * Updates the selected user row in the table and saves the changes to the XML file.
     */
    private void updateSelectedRow() {
        int selectedRow = userTable.getSelectedRow();  // You can change this to work with eventTable if necessary.
        if (selectedRow >= 0) {
            int modelRow = userTable.convertRowIndexToModel(selectedRow);

            // Get updated values from the table
            String eventID = (String) userModel.getValueAt(modelRow, 0);
            String lastName = (String) userModel.getValueAt(modelRow, 1);
            String firstName = (String) userModel.getValueAt(modelRow, 2);
            String contactNumber = (String) userModel.getValueAt(modelRow, 3);
            String email = (String) userModel.getValueAt(modelRow, 4);
            String seat = (String) userModel.getValueAt(modelRow, 5);

            String[] arr = {eventID, lastName, firstName, contactNumber, email};

            // Delete the old row first
            userModel.removeRow(modelRow);
            XMLWriter.removeNodeByAttribute("Customer1", "Last_Name", lastName, "events.xml");

            // Create a new Customer object with updated data
            Customer updatedCustomer = new Customer(arr, seat);
            userModel.addRow(updatedCustomer.toArray());

            // Save the updated customer data to the XML
            List<Customer> updatedCustomerList = List.of(updatedCustomer); // Wrap the updated customer in a list
            XMLWriter.customerXML(updatedCustomerList, "events.xml"); // Assuming updateCustomerXML is defined to update the XML with the new data
        } else {
            JOptionPane.showMessageDialog(this, "No user selected for update.");
        }
    }


    /**
     * Loads customer data from an XML file and populates the customer table with the data.
     *
     * @param filePath The file path of the XML file.
     * @param model    The table model to populate with customer data.
     */
    private void loadCustomerData(String filePath, DefaultTableModel model) {
        try {
            List<Customer> customers = XMLReader.readXML(filePath);
            model.setRowCount(0);
            customers.forEach(customer -> model.addRow(customer.toArray()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load customer data: " + e.getMessage());
        }
    }

    /**
     * Loads event data from an XML file and populates the event table with the data.
     *
     * @param filePath The file path of the XML file.
     * @param model    The table model to populate with event data.
     */
    private void loadEventData(String filePath, DefaultTableModel model) {
        try {
            List<Event> events = XMLReader.readEvents(filePath);
            model.setRowCount(0);
            events.forEach(event -> model.addRow(new Object[]{
                    event.getEventID(), event.getEventName(), event.getStartTime(), event.getEndTime(), event.getEvDay(), event.getEvMonth()
            }));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load event data: " + e.getMessage());
        }
    }
}
