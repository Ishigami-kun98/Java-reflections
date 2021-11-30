
import java.lang.reflect.*;
import java.util.ArrayList;

public class DumpMethod {

    public static void main(String[] args) {

        // this class find all interfaces and superclass that the target class have

        try {
            Class<?> cls = Class.forName("TestClass");
            Class<?>[] ilist = getInterface(cls);

            Class<?>[] scls = getSuperClasses(cls);
            for (var scl : scls) {
                System.out.println(scl.getName());
            }
            // This is using args
            // Class<?>[] ilist = getInterface(Class.forName(args[0]));

            for (Class<?> iface : ilist) {
                Method[] methods = iface.getMethods();
                Field[] fields = iface.getDeclaredFields();
                System.out.println("With method " + methods.length + " and field " + fields.length);
                for (Method method : methods) {
                    System.out.println("with method" + method.getName());
                }
                for (var field : fields) {
                    System.out.println("with fields " + field.getName());
                }
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public static Class<?>[] getInterface(Class<?> cls) {
        System.out.println("Class name : " + cls);
        System.out.println("the interface he got is : " + cls.getInterfaces().length);
        return cls.getInterfaces();
    }

    static boolean isSubClass(Class<?> cls) {
        if (cls.getSuperclass().equals(Object.class)) {
            return false;
        }
        return true;
    }

    static boolean isSuperClass(Class<?> cls) {
        return true;
    }

    static Class<?>[] getSuperClasses(Class<?> cls) {
        System.out.println("Ci sono " + cls.getSuperclass().getName());
        ArrayList<Class<?>> superClasses = new ArrayList<Class<?>>();
        boolean cond = true;
        Class<?> cls2 = cls.getSuperclass();
        while (cond) {
            if (!cls2.equals(Object.class)) {
                superClasses.add(cls2);
                cls2 = cls2.getSuperclass();
            } else
                cond = false;
        }

        return superClasses.toArray(new Class<?>[superClasses.size()]);
    }

}
