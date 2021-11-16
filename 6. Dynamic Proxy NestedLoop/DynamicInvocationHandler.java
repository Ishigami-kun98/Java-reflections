package lab6;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicInvocationHandler extends NestedCalls implements InvocationHandler {
    private IfaceTest ncs;
    int i = 0;

    public DynamicInvocationHandler() {
        ncs = (IfaceTest) Proxy.newProxyInstance(IfaceTest.class.getClassLoader(), new Class[] { IfaceTest.class },
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("method  " + method.getName());
            System.out.println(i++);
            final MethodHandle h = MethodHandles.lookup().unreflectSpecial(
                    NestedCalls.class.getDeclaredMethod(method.getName(), method.getParameterTypes()), getClass());
            result = h.bindTo(this).invokeWithArguments(args);
            i--;
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int a() {
        return ncs.a();
    }

    @Override
    public int b(int a) {
        return ncs.b(a);
    }

    @Override
    public int c(int a) {
        return ncs.c(a);
    }

}
