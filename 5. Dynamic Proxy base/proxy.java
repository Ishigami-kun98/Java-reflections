
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxy extends Object implements Serializable {
    public interface human {
        public void walk();

        public void sleep();

        public void dream(String s);
    }

    public static class provaInvocationHandler implements InvocationHandler {

        Object usedToInvoke;

        public provaInvocationHandler(Object obj) {
            usedToInvoke = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("This is invoke method " + method.getName());
            Object invoking = method.invoke(usedToInvoke, args);
            return invoking;
        }

    }

    public static class Person implements human {
        public void dream(String string) {
        }

        @Override
        public void walk() {
        }

        @Override
        public void sleep() {

        }

    }

    protected InvocationHandler h;

    protected proxy(InvocationHandler handler) {
        h = handler;
    }

    public static void main(String[] args) {
        // Person p = new Person();
        TestingFields tfs = new TestingFields(7, 3.14);
        DynamicInvocationHandler dih = new DynamicInvocationHandler(tfs);
        Go proxies = (Go) Proxy.newProxyInstance(Go.class.getClassLoader(), new Class[] { Go.class }, dih);
        proxies.setAnswer(10);
        System.out.println(proxies.message());
        Person p = new Person();
        provaInvocationHandler pih = new provaInvocationHandler(p);
        human h = (human) Proxy.newProxyInstance(human.class.getClassLoader(), new Class[] { human.class }, pih);
        h.walk();
        h.sleep();
        h.dream("s");

        // human tfs = (human) Proxy.newProxyInstance(human.class.getClassLoader(), new
        // Class[] { human.class }, dih);
        // tfs.walk();
        // tfs.sleep();
        // tfs.dream("bad");
    }
}
