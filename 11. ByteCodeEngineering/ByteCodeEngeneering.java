

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
class ByteCodeEngeneering{
    public static void main(String[] args) {
     
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get("java.lang.StringBuilder");
            for (CtMethod string : cc.getMethods()) {
                if(string.getName().equals("append")){
                    string.insertBefore("System.out.println(\"started method at \" + new java.util.Date());");
                    cc.defrost();
                    string.insertAfter("System.out.println(\"ended method at \" + new java.util.Date());");
                    cc.writeFile();
                    cc.defrost();
                }
            }
            
            StringBuilder sb = new StringBuilder("prova");
            char[] c = {'a','b','c'};
            sb.append(c);
            System.out.println(sb.toString());
        }
         catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }  catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}
