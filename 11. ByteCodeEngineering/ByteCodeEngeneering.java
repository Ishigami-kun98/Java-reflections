import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
                    string.insertBefore("System.out.println(\"started method at \" + new java.util.Date());");
                    string.insertAfter("System.out.println(\"ended method at \" + new java.util.Date());");
                    
                }
                
            }
            Class<?> cls = Class.forName("java.lang.StringBuilder");
            for (Method string : cls.getMethods()) {
                if(string.getName().equals("append")){
                    Class<?>[] parameters = string.getParameterTypes();
                    Object[] argoments = new Object[parameters.length];
                    String a;
                    a = "ci provo";
                    for(int i = 0; i < parameters.length; i++){
                        if(parameters.getClass().equals(String.class)){
                            argoments[i] = a + i;
                        }
                    }
                    Object o = string.invoke(cls.getConstructors().getClass(), argoments);
                    
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
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}