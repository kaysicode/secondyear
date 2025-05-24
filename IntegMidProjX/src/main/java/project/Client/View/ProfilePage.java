package project.Client.View;

import project.Client.Controller.ProfileControl;
import project.Client.Controller.RegisterControl;
import project.Client.Controller.WelcomeControl;
import project.Client.Model.Customer;
import project.Client.Model.Event;
import project.Client.Model.Links;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

public class ProfilePage extends JFrame {

    JPanel mainPanel;

    // Components of Profile Panel Side
    JPanel profPanel;
    JLabel text;
    JLabel imageIcon;
    JLabel profile;
    JLabel picture;
    JLabel fullName;
    JPanel hor1;
    JButton create;
    JButton view;
    JButton exit;

    // Components of Customer Panel Side
    JPanel cusPanel;
    JLabel aCustomer;
    JLabel fn;                  // TextField
    JLabel ln;
    JLabel cn;
    JLabel em;
    JLabel pw;
    JLabel gender;
    JLabel fnHolder;            // Place Holder
    JLabel lnHolder;
    JLabel cnHolder;
    JLabel emHolder;
    JLabel pwHolder;
    JLabel genderHolder;
    JPanel h1, h2, h3, h4;      // Horizontal Line Separator

    // Components of Event Panel Side
    JPanel evePanel;
    JLabel aEvent;
    JLabel eid;                 // TextField
    JLabel en;
    JLabel desc;
    JLabel ed;
    JLabel loc;
    JLabel sn;
    JLabel eidHolder;
    JLabel enHolder;
    JLabel descHolder;
    JLabel locHolder;
    JLabel snHolder;

    public ProfilePage(Customer cus, Event event) {

        // Frame
        ImageIcon imageApp = new ImageIcon(Links.APP_LOGO_ICON.getFilePath());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1491, 791);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(imageApp.getImage());
        this.setTitle("Profile Page");

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1491, 791);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        this.add(mainPanel);

        // Profile Panel
        profPanel = new JPanel();
        profPanel.setBounds(0, 0, 372, 791);
        profPanel.setLayout(null);
        profPanel.setBackground(Color.WHITE);
        mainPanel.add(profPanel);

        // Text Logo (Profile Panel)
        text = new JLabel("<html><span style='font-family: DM Sans; " +
                "font-weight: bold; " +
                "font-size: 20px; "
                + "letter-spacing: 0.5px; " +
                "color: #B0CF8B;'>FLOWEVENT</span></html>");
        text.setBounds(140, 45, 200, 46);
        profPanel.add(text);

        // Image Logo (Profile Panel)
        imageIcon = new JLabel();
        ImageIcon image = new ImageIcon(Links.APP_LOGO_WOBG.getFilePath());
        Image newImage = image.getImage().getScaledInstance(108, 108, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        imageIcon.setIcon(img);
        imageIcon.setBounds(41, 20, 108, 108);
        profPanel.add(imageIcon);

        // Profile Text Label (Profile Panel)
        profile = new JLabel("<html><span style='font-family: DM Sans; " +
                "font-weight: bold; " +
                "font-size: 40px; "
                + "letter-spacing: 0.5px; " +
                "color: #1E1E1E;'>PROFILE</span></html>");
        profile.setBounds(72, 133, 300, 76);
        profPanel.add(profile);

        // Profile Image Label (Profile Panel)
        picture = new JLabel();
        String getGender = ProfileControl.checkGender(cus);
        ImageIcon picImage = new ImageIcon(getGender);
        Image picNewImage = picImage.getImage().getScaledInstance(179, 169, Image.SCALE_SMOOTH);
        ImageIcon pImg = new ImageIcon(picNewImage);
        picture.setIcon(pImg);
        picture.setBounds(97, 225, 179, 169);
        profPanel.add(picture);

        // Full Name Text Label (Profile Panel)
        fullName = new JLabel("<html><span style='font-family: DM Sans; " +
                "font-weight: bold; " +
                "font-size: 18px; "
                + "letter-spacing: 0.15px; " +
                "color: #000000;'>" + cus.firstName().toUpperCase() + " " + cus.lastName().toUpperCase() + "</span></html>");
        fullName.setBounds(50, 400, 306, 65);
        profPanel.add(fullName);

        // Horizontal Line Separator 1 (Profile Panel)
        hor1 = new JPanel();
        hor1.setBounds(34, 465, 312, 2);
        hor1.setBackground(Color.DARK_GRAY);
        profPanel.add(hor1);

        // CREATE NEW ONE Button (Profile Panel)
        create = new JButton("CREATE NEW ONE");
        applyHoverEffect(create, new Color(0x6E854F),new Color(0xE9FFCB));
        create.setBounds(60, 494, 260, 41);
        create.addActionListener(e -> ProfileControl.createNewOne(this, cus));
        profPanel.add(create);

        // VIEW ONLINE PEOPLE Button (Profile Panel)
        view = new JButton("VIEW ONLINE PEOPLE");
        applyHoverEffect(view, new Color(0xA9A058), new Color(0xFFEC5E));
        view.setBounds(40, 560, 300, 41);
        view.addActionListener(e -> {
            List<String> onlineCustomers = RegisterControl.getOnlineCustomers();
            ProfileControl.showOnlineCustomers(onlineCustomers);
            for (String s : onlineCustomers) {
                System.out.println(s);
            }
        });
        profPanel.add(view);

        // EXIT THE PROGRAM Button (Profile Panel)
        exit = new JButton("EXIT THE PROGRAM");
        applyHoverEffect(exit, new Color(0xA94A4C), new Color(0xFF7678));
        exit.setBounds(50, 626, 270, 41);
        exit.addActionListener(e -> ProfileControl.logCustomer("EXIT_PROGRAM",this, cus));
        profPanel.add(exit);


        // -----END OF PROFILE PANEL-----

        // Customer Panel
        cusPanel = new JPanel();
        cusPanel.setBounds(372, 5, 1100, 366);
        cusPanel.setLayout(null);
        cusPanel.setBackground(new Color(0x8BA36D));
        cusPanel.setBorder(BorderFactory.createLineBorder(new Color(0xB0CF8B), 5, true));
        mainPanel.add(cusPanel);

        // ABOUT CUSTOMER Text Label (Customer Panel)
        aCustomer = new JLabel("ABOUT CUSTOMER");
        aCustomer.setBounds(38, 22, 400, 35);
        aCustomer.setFont(new Font("DM Sans", Font.BOLD, 40));
        aCustomer.setForeground(Color.WHITE);
        cusPanel.add(aCustomer);

        // FIRST NAME Text Label (Customer Panel)
        fn = new JLabel("FIRST NAME");
        defaultTextLabel(fn);
        fn.setBounds(39,98, 132, 30);
        cusPanel.add(fn);

        // LAST NAME Text Label (Customer Panel)\
        ln = new JLabel("LAST NAME");
        defaultTextLabel(ln);
        ln.setBounds(39, 183, 132, 30);
        cusPanel.add(ln);

        // CONTACT NUMBER Text Label (Customer Panel)
        cn = new JLabel("CONTACT NUMBER");
        defaultTextLabel(cn);
        cn.setBounds(39, 265, 212, 30);
        cusPanel.add(cn);

        // EMAIL Text Label (Customer Panel)
        em = new JLabel("EMAIL");
        defaultTextLabel(em);
        em.setBounds(532, 98, 80, 30);
        cusPanel.add(em);

        // PASSWORD Text Label (Customer Panel)
        pw = new JLabel("PASSWORD");
        defaultTextLabel(pw);
        pw.setBounds(532, 183, 124, 30);
        cusPanel.add(pw);

        // GENDER Text Label (Customer Panel)
        gender = new JLabel("GENDER");
        defaultTextLabel(gender);
        gender.setBounds(532, 265, 124, 30);
        cusPanel.add(gender);

        // First Name Placeholder (Customer Panel)
        fnHolder = new JLabel(cus.firstName());
        defaultPlaceHolder(fnHolder, 282, 98, 300, 30, 18);
        cusPanel.add(fnHolder);

        // Last Name Placeholder (Customer Panel)
        lnHolder = new JLabel(cus.lastName());
        defaultPlaceHolder(lnHolder, 282, 183, 300, 30, 18);
        cusPanel.add(lnHolder);

        // Contact Number Placeholder (Customer Panel)
        cnHolder = new JLabel(String.valueOf(cus.contactNo()));
        defaultPlaceHolder(cnHolder, 282, 265, 300, 30, 18);
        cusPanel.add(cnHolder);

        // Email Placeholder (Customer Panel)
        String ph4 = "<html><div style='width:280px;'>" + cus.email() +"</div></html>";
        emHolder = new JLabel(ph4);
        defaultPlaceHolder(emHolder, 723, 98, 300, 50, 14);
        cusPanel.add(emHolder);

        // Password (Customer Panel)
        pwHolder = new JLabel(cus.password());
        defaultPlaceHolder(pwHolder, 723, 183, 300, 30, 18);
        cusPanel.add(pwHolder);

        // Gender (Customer Panel)
        genderHolder = new JLabel(cus.gender());
        defaultPlaceHolder(genderHolder, 723, 265, 300, 30, 18);
        cusPanel.add(genderHolder);

        // Horizontal Line Separator 1 (Customer Panel)
        h1 = new JPanel();
        defaultHorizontal(h1, 39, 146, 420);
        cusPanel.add(h1);

        // Horizontal Line Separator 2 (Customer Panel)
        h2 = new JPanel();
        defaultHorizontal(h2, 39, 231, 420);
        cusPanel.add(h2);

        // Horizontal Line Separator 3 (Customer Panel)
        h3 = new JPanel();
        defaultHorizontal(h3, 532, 146, 464);
        cusPanel.add(h3);

        // Horizontal Line Separator 4 (Customer Panel)
        h4 = new JPanel();
        defaultHorizontal(h4, 532, 232, 464);
        cusPanel.add(h4);


        // -----END OF CUSTOMER PANEL-----

        // Event Panel
        evePanel = new JPanel();
        evePanel.setBounds(372, 380, 1100, 366);
        evePanel.setLayout(null);
        evePanel.setBackground(new Color(0xB0CF8B));
        evePanel.setBorder(BorderFactory.createLineBorder(new Color(0x8BA36D), 5, true));
        mainPanel.add(evePanel);

        // ABOUT Event Text Label (Event Panel)
        aEvent = new JLabel("ABOUT EVENT");
        aEvent.setBounds(38, 22, 400, 35);
        aEvent.setFont(new Font("DM Sans", Font.BOLD, 40));
        aEvent.setForeground(Color.WHITE);
        evePanel.add(aEvent);

        // EVENT ID Text Label (Event Panel)
        eid = new JLabel("EVENT ID");
        defaultTextLabel(eid);
        eid.setBounds(39,98, 132, 30);
        evePanel.add(eid);

        // EVENT NAME Text Label (Event Panel)
        en = new JLabel("EVENT NAME");
        defaultTextLabel(en);
        en.setBounds(39, 183, 132, 30);
        evePanel.add(en);

        // DESCRIPTION Text Label (Event Panel)
        desc = new JLabel("DESCRIPTION");
        defaultTextLabel(desc);
        desc.setBounds(39, 265, 212, 30);
        evePanel.add(desc);

        // EVENT DATE Text Label (Event Panel)
        ed = new JLabel("EVENT DATE");
        defaultTextLabel(ed);
        ed.setBounds(532, 98, 200, 30);
        evePanel.add(ed);

        // LOCATION Text Label (Event Panel)
        loc = new JLabel("LOCATION");
        defaultTextLabel(loc);
        loc.setBounds(532, 183, 150, 30);
        evePanel.add(loc);

        // SEAT NUMBER Text Label (Event Panel)
        sn = new JLabel("SEAT NUMBER ");
        defaultTextLabel(sn);
        sn.setBounds(532, 265, 200, 30);
        evePanel.add(sn);

        // Event ID Placeholder (Event Panel)
        eidHolder = new JLabel(event.eventID());
        defaultPlaceHolder(eidHolder, 282, 98, 300, 30, 18);
        evePanel.add(eidHolder);

        // Event Name Placeholder (Event Panel)
        enHolder = new JLabel(event.eventName());
        defaultPlaceHolder(enHolder, 282, 183, 300, 30, 18);
        evePanel.add(enHolder);

        // Description Placeholder (Event Panel)
        String ph9 = "<html><div style='width:280px;'>" + event.description() + "</div></html>";
        descHolder = new JLabel(ph9);
        defaultPlaceHolder(descHolder, 230, 255, 300, 50, 14);
        evePanel.add(descHolder);

        // Event Date (Event Panel)
        String eventDate = "<html><div style='width:280px;'>"
                + event.eventMonth() + " " + event.eventDay() + ", " + event.eventYear() + " AT " + event.startTime() + " - " + event.endTime()
                + "</div></html>";
        emHolder = new JLabel(eventDate);
        defaultPlaceHolder(emHolder, 723, 98, 300, 50, 14);
        evePanel.add(emHolder);

        // Location (Event Panel)
        String ph11 = "<html><div style='width:280px;'>Giant Steps at " +
                "Saint Louis University, Maryheights Campus</div></html>";
        locHolder = new JLabel(ph11);
        defaultPlaceHolder(locHolder, 690, 183, 300, 50, 11);
        evePanel.add(locHolder);

        // Seat Number (Event Panel)
        String ph12 = "20";
        snHolder = new JLabel(ph12);
        defaultPlaceHolder(snHolder, 723, 265, 300, 30, 18);
        evePanel.add(snHolder);

        // Horizontal Line Separator 1 (Event Panel)
        h1 = new JPanel();
        defaultHorizontal(h1, 39, 146, 420);
        evePanel.add(h1);

        // Horizontal Line Separator 2 (Event Panel)
        h2 = new JPanel();
        defaultHorizontal(h2, 39, 231, 420);
        evePanel.add(h2);

        // Horizontal Line Separator 3 (Event Panel)
        h3 = new JPanel();
        defaultHorizontal(h3, 532, 146, 464);
        evePanel.add(h3);

        // Horizontal Line Separator 4 (Event Panel)
        h4 = new JPanel();
        defaultHorizontal(h4, 532, 232, 464);
        evePanel.add(h4);

        this.setVisible(true);
    }

    private static void applyHoverEffect(JButton button, Color normalColor, Color hoverColor) {
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setForeground(normalColor);
        button.setFocusable(false);
        button.setFont(new Font("DM Sans", Font.BOLD, 23));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(hoverColor);
                button.setContentAreaFilled(true);
                button.setBackground(normalColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
                button.setForeground(normalColor);
            }
        });
    }

    private static void defaultTextLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("DM Sans", Font.BOLD, 20));
    }

    private static void defaultPlaceHolder(JLabel label, int x, int y, int width, int height, int size) {
        label.setLayout(new FlowLayout());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("DM Sans", Font.PLAIN, size));
        label.setBounds(x, y, width, height);
    }

    private static void defaultHorizontal(JPanel panel, int x, int y, int width) {
        panel.setBounds(x, y, width, 1);
        panel.setBackground(Color.WHITE);
    }
}
