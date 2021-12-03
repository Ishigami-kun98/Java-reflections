
class ClassLoaderExercize {

    static SimpleClassLoader scl;

    public static void main(String[] args) {
        scl = new SimpleClassLoader(
                "C:/Users/hulio/Desktop/School/Tecniche Speciali di programmazione/LabMaterial/Solutions/Java-reflections/7. ClassLoader");
        try {
            Class<?> cls = scl.findClass("ClassLoaderExercize");
            System.out.println(cls.getClassLoader().getName());

            // VerboseClassLoader vcl = new VerboseClassLoader(
            // "C:/Users/hulio/Desktop/School/Tecniche Speciali di
            // programmazione/LabMaterial/Solutions/Java-reflections/7. ClassLoader");
            // vcl.loadClass(
            // "C:/Users/hulio/Desktop/School/Tecniche Speciali di
            // programmazione/LabMaterial/Solutions/Java-reflections/7.
            // ClassLoader/ClassLoaderExercize");
            // vcl.getClass(
            // "C:/Users/hulio/Desktop/School/Tecniche Speciali di
            // programmazione/LabMaterial/Solutions/Java-reflections/7.
            // ClassLoader/ClassLoaderExercize");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}