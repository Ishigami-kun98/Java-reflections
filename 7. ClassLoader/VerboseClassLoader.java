import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VerboseClassLoader extends ClassLoader {
    String directory;
    int systemClass;
    int userClass;

    public VerboseClassLoader(String _directory) {
        directory = _directory;
    }

    public Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        try {
            byte[] buf = loadClassFile(file);
            Class<?> c = defineClass(name, buf, 0, buf.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class " + name);
        if (!name.startsWith("Java")){
            System.out.println("UserClass");
            userClass++;
            return getClass(name);
        }
        System.out.println("SystemClass");
        systemClass++;
        return super.loadClass(name);
    }

    private byte[] loadClassFile(String name) throws IOException {
        //String classFile = directory+"/"+name;
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = 0;
        if(stream != null)
        if (stream.available() > 0)
            size = stream.available();
        //int size = (int)(new File(classFile).length());
        byte buff[] = new byte[size];
        //FileInputStream in = new FileInputStream(classFile);
        DataInputStream in = new DataInputStream(stream);
        if(in != null){
        in.read(buff);
        in.close();}
        return buff;
    }

}
