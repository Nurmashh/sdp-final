
// Интерфейс фабрики для создания медицинских услуг
interface MedicalServicesFactory {
    Service createService(String name, double price);

    double getPrice(String name);
}