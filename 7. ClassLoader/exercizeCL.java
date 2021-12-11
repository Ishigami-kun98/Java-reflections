import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class exercizeCL extends ClassLoader{
    //Separate the user loaded class and system one
    String[] directory;
    int system = 0;
    int user = 0;
    public exercizeCL(String path, ClassLoader parent){
        super(parent);
        directory = path.split(";");
    }
    public exercizeCL(String path){
        directory = path.split(";");
    }
    public synchronized Class<?> findClass(String filename)throws ClassNotFoundException{
        System.out.println("Loading " + filename + "...");
            for (String string : directory) {
                byte[] b = loadClassData(string, filename);
                if (b != null){
                    if(filename.startsWith("java.")){
                        system ++;
                    }else user++;
                    System.out.println("Done");
                    return defineClass(filename, b, 0, b.length);
                }
            }
        throw new ClassNotFoundException();
    }
    public byte[] loadClassData(String directory, String filename){
        String filenameClass = directory + "/" + filename.replace('.', '/') + ".class";
        System.out.println(filenameClass);
        int bLenght = (int) (new File(filenameClass).length());
        byte[] b = new byte[bLenght];
        try {
            FileInputStream fis = new FileInputStream(filenameClass);
            bLenght = fis.read(b);
            fis.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e){
            return null;
        }
        return b;
    }

 

    public static void main(String[] args) {
        FileSystem fs = FileSystems.getFileSystem(URI.create("jrt:/"));
        //jrt è la variabile d'ambiente
        Path modulePath = fs.getPath("modules", "java.base");
        String dirs = modulePath + ";./users";
        System.out.println("Directories = " + dirs);
        try {
            ClassLoader eCL = new exercizeCL(dirs);

            eCL.loadClass("myClass1");
            eCL.loadClass("customClass");
            eCL.loadClass("java.io.FileInputStream");
            System.out.println(eCL.toString());
        } catch (ClassNotFoundException e) {
            //TODO: handle exception
            e.printStackTrace();
        } 
    }
}
