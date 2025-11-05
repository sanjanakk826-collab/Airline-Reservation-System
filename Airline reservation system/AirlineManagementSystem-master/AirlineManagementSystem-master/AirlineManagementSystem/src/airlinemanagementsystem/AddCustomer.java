package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfaadhar, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;
    ButtonGroup gendergroup;

    public AddCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 150, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 150, 25);
        add(tfnationality);

        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(60, 180, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 180, 150, 25);
        add(tfaadhar);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        gendergroup = new ButtonGroup();

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 70, 25);
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 280, 70, 25);
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 150, 25);
        add(tfphone);

        JButton save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        add(save);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/emp.png"));
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(450, 80, 280, 400);
        add(lblimage);

        setSize(900, 600);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText().trim();
        String nationality = tfnationality.getText().trim();
        String phone = tfphone.getText().trim();
        String address = tfaddress.getText().trim();
        String aadhar = tfaadhar.getText().trim();
        String gender = null;

        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()) {
            gender = "Female";
        }

        // Input validation
        if (name.isEmpty() || nationality.isEmpty() || phone.isEmpty() || address.isEmpty() || aadhar.isEmpty() || gender == null) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory. Please fill in all details.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Name validation (no digits or special characters)
        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Name cannot contain digits or special characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
            tfname.setBorder(BorderFactory.createLineBorder(Color.RED));
            return;
        } else {
            tfname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        // Nationality validation (no digits or special characters)
        if (!nationality.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Nationality cannot contain digits or special characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
            tfnationality.setBorder(BorderFactory.createLineBorder(Color.RED));
            return;
        } else {
            tfnationality.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        // Aadhar validation
        if (aadhar.length() != 12 || !aadhar.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Aadhar number must be exactly 12 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Phone validation
        if (phone.length() != 10 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Conn conn = new Conn();

            String query = "INSERT INTO passenger (name, nationality, phone, address, aadhar, gender) VALUES ('" + name + "', '" + nationality + "', '" + phone + "', '" + address + "', '" + aadhar + "', '" + gender + "')";
            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear all fields after successful insertion
            tfname.setText("");
            tfnationality.setText("");
            tfphone.setText("");
            tfaddress.setText("");
            tfaadhar.setText("");
            gendergroup.clearSelection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving customer details.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}

