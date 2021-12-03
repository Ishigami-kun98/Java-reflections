import javassist.tools.reflect.Loader;
public class example3 {
    public static void main(String[] args) throws Throwable{
        Loader cl = (Loader)example3.class.getClassLoader();
        cl.makeReflective("Person", "VerboseMetaobj", "javassist.tools.reflect.ClassMetaobject");
        cl.run("Person", args);
    }
}