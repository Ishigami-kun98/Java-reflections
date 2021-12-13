import javassist.*;
public class Adaptor2 implements Translator{
    CtClass cls;
    CtMethod insert;
    CtMethod append;

    @Override
    public void onLoad(ClassPool pool, String arg) throws NotFoundException, CannotCompileException {
        System.out.println("onLoad " + arg);

    }

    @Override
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        cls = pool.get("java.lang.StringBuilder");
        CtMethod[] searchAppendMethod = cls.getDeclaredMethods("append");
        for (CtMethod append : searchAppendMethod) {
            System.out.println(append.getSignature());
        }
        CtMethod[] searchInsertMethod = cls.getDeclaredMethods("insert");

    }
    
}
