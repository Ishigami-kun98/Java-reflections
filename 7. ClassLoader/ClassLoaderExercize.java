import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class ClassLoaderExercize extends ClassLoader{
    public Class<?> findClass(String name) throws ClassNotFoundException{
        
 
            byte[] b = loadClassData(name);
            if(b != null) return defineClass(name, b, 0, b.length);
        
        throw new ClassNotFoundException();
    }

    byte[] loadClassData(String name){

        
        int size = (int) (new File(name)).length();
        byte[] buff = new byte[size];
        try {
            FileInputStream filein = new FileInputStream(name);
            size = filein.read(buff);
            filein.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buff;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoaderExercize cle = new ClassLoaderExercize();
        cle.findClass("ClassLoaderExercize.class");
    }
}