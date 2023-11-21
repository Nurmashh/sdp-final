import Strategy.*;

public interface Observer {


    void update(String name, double price);
    void updatePatientInfo(String firstName, String lastName, String gender, int birthYear);
}

