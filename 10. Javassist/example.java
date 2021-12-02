import javassist.ClassPool;
import javassist.Loader;

class example{
    public static void main(String[] args) throws Throwable {
        Adapter adapt = new Adapter();
        ClassPool pool = ClassPool.getDefault(adapt);
        Loader cl = new Loader(pool);
        cl.run("MyApplication", args);
    }
}