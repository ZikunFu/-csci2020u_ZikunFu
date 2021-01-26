package lab02;

public class SimpleTest {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new SimpleTest().getGreeting());
    }
}
