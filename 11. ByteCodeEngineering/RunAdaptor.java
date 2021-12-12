import javassist.*;
public class RunAdaptor {
    try {
        Adaptor adapter = new Adaptor();
        ClassPool pool = ClassPool.getDefault();
        Loader loader = new Loader(pool);
        loader.addTranslator(pool, adapter);
        loader.run("MyApp", null);
    } catch (Throwable throwable) {
        System.out.println(throwable.getClass().getName() + " : " + throwable.getMessage());
    }
}
