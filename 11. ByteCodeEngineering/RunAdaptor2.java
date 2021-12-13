import javassist.*;
public class RunAdaptor2 {
    public static void main(String[] args) {
        try {
            Adaptor2 adaptor2 = new Adaptor2();
            ClassPool cp = ClassPool.getDefault();
            Loader loader = new Loader(cp);
            loader.addTranslator(cp, adaptor2);
            loader.run("MyApp", args);
        } catch (NotFoundException | CannotCompileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
