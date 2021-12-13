import java.io.IOException;

import javassist.*;

public class Adaptor2 implements Translator{
    
    CtClass clazz;
    CtMethod append;
    CtMethod insert;

    public void start(ClassPool cp){
        try {
            clazz = cp.get("java.lang.StringBuilder");

            CtMethod[] searchappend = clazz.getDeclaredMethods("append");
            for (CtMethod method : searchappend) {
                if(method.getSignature().equals("([C)Ljava/lang/StringBuilder;")){
                    append = method;
                }
            }
            
            CtMethod[] searchinsert = clazz.getDeclaredMethods("insert");
            for (CtMethod method : searchinsert) {
                if(method.getSignature().equals("(ILjava/lang/String;)Ljava/lang/StringBuilder;")){
                    insert = method;
                }
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onLoad(ClassPool cp, String classname){
        try {
            append.addLocalVariable("startMs", CtClass.longType);
            append.insertBefore("startMs = System.nanoTime();");
            clazz.defrost();
            append.insertAfter("System.out.println(\"Append execution time: \" + (System.nanoTime() - startMs));");
            
            insert.addLocalVariable("startMs", CtClass.longType);
            insert.insertBefore("startMs = System.nanoTime();");
            clazz.defrost();
            insert.insertAfter("System.out.println(\"Insert execution time: \" + (System.nanoTime() - startMs));");

            try {
                clazz.writeFile(".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }
}