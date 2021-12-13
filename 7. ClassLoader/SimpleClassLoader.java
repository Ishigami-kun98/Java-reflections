import java.io.File;
import java.io.FileInputStream;

public class SimpleClassLoader extends ClassLoader {
    String[] directories;
    int systemLoad = 0;
    int userLoad = 0;
    public SimpleClassLoader(String path) {
        directories = path.split(";");
    }

    public SimpleClassLoader(String path, ClassLoader parent) {
        super(parent);
        directories = path.split(path);
    }

    public synchronized Class<?> findClass(String name) throws ClassNotFoundException {
        for (int i = 0; i < directories.length; i++) {
            byte[] buf = getClassData(directories[i], name);
            
            if (buf != null){
                Class<?> c = defineClass(name, buf, 0, buf.length);
                resolveClass(c);
                return c;
            }    
        }
        throw new ClassNotFoundException();
    }
   
 @Override
 public Class<?> loadClass(String name) throws ClassNotFoundException {
     if(!name.startsWith("java")){
         userLoad++;
         findClass(name);
     }
     systemLoad++;
     return super.loadClass(name);
 }
    @Override
    public String toString() {
        
        return "system " + systemLoad + "     user " + userLoad;
    }
    
    protected byte[] getClassData(String directory, String fileName) {
        String classFile = directory + "/" + fileName.replace(".", "/") + ".class";
        
        int classSize = (int) (new File(classFile)).length();
        byte[] buf = new byte[classSize];
        try {
            FileInputStream filein = new FileInputStream(classFile);
            classSize = filein.read(buf);
            filein.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }
}
