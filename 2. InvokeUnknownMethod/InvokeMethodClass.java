import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class InvokeMethodClass {
    public static void main(String[] args) {
        String[] argomenti = { "ac", "b" };
        System.out.println("the result is " + callMethod("MethodClass", "append", argomenti));
    }

    static Object callMethod(String className, String method, String[] args) {
        try {
            Class<?> cls = Class.forName(className);
            Method[] methods = cls.getMethods();
            Class<?>[] paramets = new Class<?>[args.length];
            boolean check = false;
            for (Method m : methods) {
                if (m.getName() == method) {
                    if (m.getParameterCount() == args.length) {
                        System.out.println("Method exist with this many of parameter needed " + args.length);
                        check = true;
                        paramets = m.getParameterTypes();
                        for (Class<?> par : paramets) {
                            System.out.println("The type is " + par.getTypeName());
                        }
                    }
                }
            }
            if (!check) {
                System.out.println("Method don't exist or the number of argument doesn't match");
                return null;
            }
            Class<?>[] enterParamets = new Class<?>[args.length];
            Object[] argumentsConverted = new Object[args.length];
            Method methodo = cls.getMethod(method, paramets);
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
                if (Pattern.compile("\\w$").matcher(args[i]).matches()) {
                    enterParamets[i] = String.class;
                    argumentsConverted[i] = String.valueOf(args[i]);
                    System.out.println("The argument is String");
                }
                if (Pattern.compile("\\d+").matcher(args[i]).matches()) {
                    enterParamets[i] = Integer.class;
                    int a = Integer.valueOf(args[i]);
                    argumentsConverted[i] = a;
                    System.out.println("The argument is Integer");

                }
                if (Pattern.compile("[+-]\\d*\\.\\d*").matcher(args[i]).matches()) {
                    enterParamets[i] = double.class;
                    argumentsConverted[i] = Double.valueOf(args[i]);
                    System.out.println("The argument is double");
                }
            }
            Object invoke = methodo.invoke(cls.getConstructor().newInstance(), argumentsConverted);
            return invoke;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
