
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {

    private Object basObject;

    public DynamicInvocationHandler(Object base) {
        basObject = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("before " + method.getName() + " type required ");
            for (Object object : method.getParameterTypes()) {
                System.out.println(object);
            }
            if (args != null)
                for (Object object : args) {
                    if (object != null) {
                        System.out.println(object.toString());
                    }
                }
            Object result = method.invoke(basObject, args);

            System.out.println("after  " + method.getName());
            if (args != null)
                for (Object object : args) {
                    if (object != null) {
                        System.out.println(object.toString());
                    }
                }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
