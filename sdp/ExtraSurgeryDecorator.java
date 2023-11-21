
public class ExtraSurgeryDecorator extends ServiceDecorator {

    public ExtraSurgeryDecorator(Service service) {
        super(service);
    }

    @Override
    public String name() {

        return super.name() + ", more faster";
    }

    @Override
    public double price() {
        return service.price() + 50;
    }
}