package Strategy;

public class Patient {

    private static Patient instance;

    private String firstName;
    private String lastName;
    private String gender;
    private int birthYear;

    private Patient() {
        // Private constructor to enforce Singleton pattern
    }

    public static Patient getInstance() {
        if (instance == null) {
            instance = new Patient();
        }
        return instance;
    }

    public void setPersonalInfo(String firstName, String lastName, String gender, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void updatePersonalInfo(String firstName, String lastName, String gender, int birthYear) {
        setPersonalInfo(firstName, lastName, gender, birthYear);
        System.out.println("Персональная информация обновлена: " + toString());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }


}
