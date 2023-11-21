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

    public MedicalServiceSelector(Clinic clinic) {
        this.clinic = clinic;
        this.checkBoxes = new ArrayList<>();

        clinic.addObserver(this);

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
    }

    private void createCheckBox(JPanel panel, String serviceName) {
        JCheckBox checkBox = new JCheckBox(serviceName);
        checkBoxes.add(checkBox);
        panel.add(checkBox);
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
                new MedicalServiceSelector(clinic);
            }
        });
    }
}
