

public abstract class ServiceDecorator extends Service {
    protected Service service;

    public ServiceDecorator(Service service) {
        this.service = service;
    }

    @Override
    public String name() {
        return service.name();
    }

    @Override
    public double price() {
        return service.price();
    }
}