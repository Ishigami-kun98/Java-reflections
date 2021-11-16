import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

class InvokeUnknownMethod {

    public static void main(String... args) {
        // u need to use args as argument, something like
        // java InvokeUnknownMethod Calculator add 10 5
        System.out.println(callMethod(args[0], args[1], Arrays.copyOfRange(args, 2, args.length)));
    }

    static String callMethod(String className, String methodName, String[] argsArgument) {
        if (argsArgument.length > 2) {
            System.out.println("too many arguments");
            return null;

        }
        try {
            Class<?> cls = Class.forName(className);
            Class<?>[] parameterTypes = new Class<?>[argsArgument.length];
            Object[] parameter = new Object[argsArgument.length];
            System.out.println("The class is : " + className + " The method to be invoked is : " + methodName
                    + " the argument passed is ");
            for (int i = 0; i < argsArgument.length; i++) {
                if (Pattern.compile("^\\d+$").matcher(argsArgument[i]).matches()) {
                    parameterTypes[i] = int.class;
                    parameter[i] = Integer.valueOf(argsArgument[i]);
                } else if (Pattern.compile("[+-]\\d*\\.\\d*").matcher(argsArgument[i]).matches()) {
                    parameterTypes[i] = double.class;
                    parameter[i] = Double.valueOf(argsArgument[i]);
                }
            }

            Method method = cls.getDeclaredMethod(methodName, parameterTypes);
            Object invok = method.invoke(cls.getConstructor().newInstance(), parameter);
            return invok.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}