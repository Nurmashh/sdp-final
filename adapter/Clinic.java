import java.util.ArrayList;
import java.util.List;

public class Clinic {

    private List<Observer> observers = new ArrayList<>();
    private List<Service> writtenServices = new ArrayList<>();
    private MedicalServicesFactory medicalServicesFactory;

    public Clinic(MedicalServicesFactory medicalServicesFactory) {
        this.medicalServicesFactory = medicalServicesFactory;
    }


    public List<Service> getWrittenServices() {
        return writtenServices;
    }


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String name, double price) {
        for (Observer observer : observers) {
            observer.update(name, price);
        }
    }

    public double doService(String name) {
        double price = medicalServicesFactory.getPrice(name);
        Service service = medicalServicesFactory.createService(name, price);
        writtenServices.add(service);
        notifyObservers(name, price);
        return price; // Return the price of the service
    }
}