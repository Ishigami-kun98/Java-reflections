import java.nio.file.Paths;

class ClassLoaderExercize {

    static SimpleClassLoader scl;

    public static void main(String[] args) {
        String directory = Paths.get(".").toAbsolutePath().normalize().toString() + "/users/";
        String fileName = "customClass";
        System.out.println(directory + fileName);
        scl = new SimpleClassLoader(directory);
        try {
            
            scl.loadClass(fileName);
            scl.loadClass("java.lang.String");
            System.out.println(scl.toString());
            //SimpleClassLoader scl = new SimpleClassLoader(directory);
            //scl.loadClass(fileName);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}