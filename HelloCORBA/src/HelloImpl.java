import HelloApp.*;

public class HelloImpl extends HelloPOA {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}
