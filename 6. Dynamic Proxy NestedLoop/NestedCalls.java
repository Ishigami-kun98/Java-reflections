package lab6;

public class NestedCalls implements IfaceTest {
    private int i = 0;

    public int a() {
        return b(i++);
    }

    public int b(int a) {
        return (i < 42) ? c(b(a())) : 1;
    }

    public int c(int a) {
        return --a;
    }

}
