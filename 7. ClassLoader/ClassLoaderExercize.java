import java.nio.file.Paths;

class ClassLoaderExercize {

    static SimpleClassLoader scl;

    public static void main(String[] args) {
        String directory = Paths.get(".").toAbsolutePath().normalize().toString() + "/users";
        String fileName = "customClass";
        System.out.println(directory + fileName);
        scl = new SimpleClassLoader(directory);
        try {
            
            Class<?> cls = scl.loadClass(fileName);
            
            
            //SimpleClassLoader scl = new SimpleClassLoader(directory);
            //scl.loadClass(fileName);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}