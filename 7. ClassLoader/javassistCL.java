import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import java.lang.reflect.*;
public class javassistCL extends ClassLoader{
    ClassPool pool;
    public javassistCL() throws NotFoundException{
        pool = new ClassPool();
        pool.insertClassPath("./class");
    }
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        try {
            CtClass cc = pool.get(name);
            byte[] b = cc.toBytecode();
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NotFoundException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        javassistCL s = new javassistCL();
        Class<?> c = s.loadClass("ClassLoaderExercize");
        c.getDeclaredMethod("main", new Class[]{String[].class}).invoke(null, new Object[]{args});
        System.out.println("Hello");
    }
}
