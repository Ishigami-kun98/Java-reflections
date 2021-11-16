package lab6;

public class NestedCallsTest {
    public static void main(String[] args) {
        NestedCalls nc = new DynamicInvocationHandler();
        // System.out.println(nc.a());
        System.out.println(nc.b(nc.a()));
        // System.out.println(nc.c(nc.b(nc.a())));
    }
}
