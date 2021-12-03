import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.tools.*;
class ByteCodeEngeneering{
    public static void main(String[] args) {
     
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get("java.lang.StringBuilder");
            for (CtMethod string : cc.getMethods()) {
                if(string.getName().equals("append")){
                    string.insertBefore("System.out.println(\"append\");");
                }
                if(string.getName().equals("insert")){
                    string.insertBefore("System.out.println(\"append\");");
                }
            }
            Class<?> cls = Class.forName("java.lang.StringBuilder");
            for (Method string : cls.getMethods()) {
                if(string.getName().equals("append")){
                    System.out.println(string.getName());
                }
                if(string.getName().equals("insert")){
                    
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CannotCompileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}