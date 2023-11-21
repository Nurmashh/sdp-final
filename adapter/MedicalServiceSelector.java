import javax.swing.*;
import Strategy.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MedicalServiceSelector extends JFrame implements Observer {

    private Clinic clinic;
    private List<JCheckBox> checkBoxes;
    private JLabel totalPriceLabel;

    public MedicalServiceSelector(Clinic clinic) {
        this.clinic = clinic;
        this.checkBoxes = new ArrayList<>();

        clinic.addObserver(this); // Register the MedicalServiceSelector as an observer

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Medical Service Selector");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        createCheckBox(panel, "Dentist");
        createCheckBox(panel, "Therapist");
        createCheckBox(panel, "Ophthalmologist");
        createCheckBox(panel, "Gynecologist");

        JButton selectButton = new JButton("Select Services");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalCost = 0;
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        totalCost += clinic.doService(checkBox.getText());
                    }
                }
                totalPriceLabel.setText("Total Price: " + totalCost);
            }
        });

        panel.add(selectButton);

        totalPriceLabel = new JLabel("Total Price: 0");
        panel.add(totalPriceLabel);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);

        selectButton = new JButton("Select Services");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Перед отображением услуг, покажем форму регистрации
                RegistrationForm registrationForm = RegistrationForm.getInstance();
                registrationForm.setVisible(true);

                // После заполнения формы, продолжим обработку
                double totalCost = 0;
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        totalCost += clinic.doService(checkBox.getText());
                    }
                }
                totalPriceLabel.setText("Total Price: " + totalCost);
            }
        });

        panel.add(selectButton);


    }

    private void createCheckBox(JPanel panel, String serviceName) {
        JCheckBox checkBox = new JCheckBox(serviceName);
        checkBoxes.add(checkBox);
        panel.add(checkBox);
    }



    @Override
    public void update(String name, double price) {
        // This method is called when a service is performed in the Clinic
        // You can implement specific logic here if needed
    }

    public static void main(String[] args) {
        MedicalServicesFactory medicalServicesFactory = new MedicalServicesFactory() {
            @Override
            public Service createService(String name, double price) {
                return new Service(name, price);
            }

            @Override
            public double getPrice(String name) {
                switch (name) {
                    case "Dentist":
                        return 200;
                    case "Therapist":
                        return 150;
                    case "Ophthalmologist":
                        return 180;
                    case "Gynecologist":
                        return 120;
                    default:
                        return 0;
                }
            }
        };

        Clinic clinic = new Clinic(medicalServicesFactory);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MedicalServiceSelector(clinic);
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Запуск формы регистрации перед созданием MedicalServiceSelector
                RegistrationForm.getInstance().setVisible(true);
                new MedicalServiceSelector(clinic);
            }
        });
    }

    public void updatePatientInfo(String firstName, String lastName, String gender, int birthYear) {
        RegistrationForm.getInstance().updatePatientInfo(
                firstName, lastName, gender, birthYear
        );
    }
}