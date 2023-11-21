package Strategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {

    private static RegistrationForm instance;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField genderField;
    private JTextField birthYearField;

    private RegistrationForm() {
        // Private constructor to enforce Singleton pattern
        initializeUI();
    }

    public static RegistrationForm getInstance() {
        if (instance == null) {
            instance = new RegistrationForm();
        }
        return instance;
    }

    private void initializeUI() {
        setTitle("Registration Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        panel.add(genderField);

        panel.add(new JLabel("Birth Year:"));
        birthYearField = new JTextField();
        panel.add(birthYearField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // При нажатии на кнопку Submit обновляем информацию пациента
                Patient.getInstance().updatePersonalInfo(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        genderField.getText(),
                        Integer.parseInt(birthYearField.getText())
                );
                // Скрываем форму регистрации
                setVisible(false);
            }
        });
        panel.add(submitButton);

        add(panel);
    }


    public void updatePatientInfo(String firstName, String lastName, String gender, int birthYear) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                firstNameField.setText(firstName);
                lastNameField.setText(lastName);
                genderField.setText(gender);
                birthYearField.setText(Integer.toString(birthYear));
            }
        });
    }
}