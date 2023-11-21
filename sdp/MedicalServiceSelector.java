import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import Strategy.*;

public class MedicalServiceSelector extends JFrame implements Observer {

    private Clinic clinic;
    private List<JCheckBox> checkBoxes;
    private JComboBox<String> paymentStrategyComboBox;
    private JLabel totalPriceLabel;

    private PatientAuthentication patientAuthentication;

    public MedicalServiceSelector(Clinic clinic, MedicalServicesFactory medicalServicesFactory) {
        this.clinic = clinic;
        clinic.setMedicalServicesFactory(medicalServicesFactory); // Устанавливаем фабрику напрямую
        this.checkBoxes = new ArrayList<>();
        this.patientAuthentication = PatientAuthentication.getInstance();

        // Авторизация пациента
        patientAuthentication.promptForPatientName();
        clinic.addPatient(patientAuthentication.getPatientName());

        clinic.addObserver(this);
        initializeUI();
    }


    private String getPatientName() {
        return JOptionPane.showInputDialog("Enter your name:");
    }

    private void initializeUI() {

// Адаптер для кнопки OK
        class OKButtonAdapter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Thank you for contacting us!");
            }
        }

// Адаптер для кнопки Exit
        class ExitButtonAdapter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You have completed your session.");
                System.exit(0); // Завершаем приложение
            }
        }

        setTitle("Medical Service Selector");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        createCheckBox(panel, "Dentist");
        createCheckBox(panel, "Therapist");
        createCheckBox(panel, "Ophthalmologist");
        createCheckBox(panel, "Gynecologist");

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OKButtonAdapter());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonAdapter());


        panel.add(okButton);
        panel.add(exitButton);

        // Добавляем комбо-бокс для выбора способа оплаты
        paymentStrategyComboBox = new JComboBox<>(new String[]{"Cash", "Kaspi Card", "Online Payment"});
        panel.add(paymentStrategyComboBox);

        JButton selectButton = new JButton("Select Services");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalCost = 0;
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        // Используем метод createService из фабрики
                        totalCost += clinic.doService(checkBox.getText());
                    }
                }
                // Получаем выбранную стратегию оплаты
                PaymentStrategy paymentStrategy = getSelectedPaymentStrategy();
                // Выполняем оплату с использованием выбранной стратегии
                paymentStrategy.pay(totalCost);
                totalPriceLabel.setText("Total Price: " + totalCost);
            }


        });

        panel.add(selectButton);

        totalPriceLabel = new JLabel("Total Price: 0");
        panel.add(totalPriceLabel);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);


        ActionListener patientAuthenticationAdapter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientAuthentication.promptForPatientName();
                // Теперь вы можете использовать полученные данные
                System.out.println("Patient Name: " + patientAuthentication.getPatientName());
            }
        };

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(patientAuthenticationAdapter);
        panel.add(loginButton);
    }

    private void createCheckBox(JPanel panel, String serviceName) {
        JCheckBox checkBox = new JCheckBox(serviceName);
        checkBoxes.add(checkBox);
        panel.add(checkBox);

        // Добавляем ExtraSurgeryDecorator в комбо-бокс, если услуга не является декоратором
        if (!(clinic.getMedicalServicesFactory().createService(serviceName, 50) instanceof ServiceDecorator)) {
            String decoratorOption = "ExtraSurgeryDecorator for " + serviceName;
            panel.add(new JCheckBox(decoratorOption));
        }

    }
    private PaymentStrategy getSelectedPaymentStrategy() {
        String selectedStrategy = (String) paymentStrategyComboBox.getSelectedItem();
        switch (selectedStrategy) {
            case "Cash":
                return new CashPaymentStrategy();
            case "Kaspi Card":
                return new KaspiCardPaymentStrategy();
            case "Online Payment":
                return new OnlinePaymentStrategy();
            default:
                throw new IllegalArgumentException("Invalid payment strategy: " + selectedStrategy);
        }
    }

    @Override
    public void update(String name, double price) {
        // Метод вызывается, когда услуга выполняется в клинике
    }

    public static void main(String[] args) {
        MedicalServicesFactory medicalServicesFactory = new MedicalServicesFactory() {
            // ... (реализация методов создания услуг и получения их цен)
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
                new MedicalServiceSelector(clinic, medicalServicesFactory);

            }

        });
    }


    static class PatientAuthentication {
        private static PatientAuthentication instance;
        // Добавьте поля для хранения информации о пациенте (например, имя)
        public String patientName;
        // Метод для получения единственного экземпляра класса (Singleton)
        public static synchronized PatientAuthentication getInstance() {
            if (instance == null) {
                instance = new PatientAuthentication();
            }
            return instance;
        }

        // Добавьте методы для установки и получения информации о пациенте
        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientName() {
            return patientName;
        }

        // Метод для запроса имени пациента
        public void promptForPatientName() {

            setPatientName(JOptionPane.showInputDialog("Enter your name:"));

            setPatientName(JOptionPane.showInputDialog("Enter your surname:"));

        }
    }
}