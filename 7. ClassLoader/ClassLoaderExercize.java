import java.lang.reflect.*;

class ClassLoaderExercize {

    static SimpleClassLoader scl;

    public static void main(String[] args) {
        scl = new SimpleClassLoader(
                "C:/Users/hulio/Desktop/School/Tecniche Speciali di programmazione/LabMaterial/Solutions/Java-reflections");
        try {
            Class<?> cls = scl.findClass("prova");
            System.out.println(cls.getClassLoader().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}