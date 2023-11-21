import java.util.ArrayList;
import java.util.List;

public class Clinic {

    public List<Observer> observers = new ArrayList<>();
    public  List<Service> writtenServices = new ArrayList<>();
    public MedicalServicesFactory medicalServicesFactory;
    private String currentPatient;

    public Clinic(MedicalServicesFactory medicalServicesFactory) {
        this.medicalServicesFactory = medicalServicesFactory;
    }


    public List<Service> getWrittenServices() {
        return writtenServices;
    }


    public void addObserver(MedicalServiceSelector observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void addPatient(String patientName) {
        this.currentPatient = patientName;
    }
    public void notifyObservers(String name, double price) {
        for (Observer observer : observers) {
            observer.update(name, price);
        }
    }

    public void setMedicalServicesFactory(MedicalServicesFactory medicalServicesFactory) {
        this.medicalServicesFactory = medicalServicesFactory;
    }

    public MedicalServicesFactory getMedicalServicesFactory() {
        return medicalServicesFactory;
    }
    public double doService(String name) {
        double price = medicalServicesFactory.getPrice(name);
        Service service = medicalServicesFactory.createService(name, price);
        writtenServices.add(service);
        notifyObservers(name, price);
        return price; // Return the price of the service
    }
}