import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class Adaptor implements Translator{
    CtMethod append;
    CtMethod insert;
    CtClass cls;
    @Override
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        try {
            cls = pool.get("java.lang.StringBuilder");
            CtMethod[] methods = cls.getDeclaredMethods("append");
            for (CtMethod ctMethod : methods) {
                
                if(ctMethod.getSignature().equals("([C)Ljava/lang/StringBuilder;")){
                    append = ctMethod;
                }
            }
            CtMethod[] insertMethods = cls.getDeclaredMethods("insert");
            for (CtMethod ctMethod : insertMethods) {
                if(ctMethod.getSignature().equals("(ILjava/lang/String;)Ljava/lang/StringBuilder;")){
                    insert = ctMethod;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void onLoad(ClassPool pool, String arg1) throws NotFoundException, CannotCompileException {
        try {
            CtClass clazz = pool.get("MyApp");
            CtMethod ctMethod = clazz.getDeclaredMethod("main");
            ctMethod.addLocalVariable("AppendResult", CtClass.longType);
            ctMethod.addLocalVariable("InsertResult", CtClass.longType);
            ctMethod.insertBefore("AppendResult = 0;");
            ctMethod.insertBefore("InsertResult = 0;");
            ctMethod.insertAfter("if(AppendResult < InsertResult){" +
                "System.out.println(\"Append takes less time than Insert\");"+
                "}else {"+
                "System.out.println(\"Insert takes less time than Append\");}"
            );
            ctMethod.instrument(new ExprEditor(){
                public void edit(final MethodCall m) throws CannotCompileException{
                    if(m.getClassName().equals("java.lang.StringBuilder") && 
                    m.getMethodName().equals(append.getName()) && 
                    m.getSignature().equals(append.getSignature())){
                        m.replace("{long appendStartMs = System.nanoTime(); " + 
                        "$_ = $proceed($$);" +
                        "long appendEndMs = System.nanoTime();" +
                        "AppendResult = appendEndMs - appendStartMs;" +
                        "System.out.println(\"Append ExecutionTime: \" + AppendResult);}"
                        );
                    }
                    else if (m.getClassName().equals("java.lang.StringBuilder") && m.getMethodName().equals(insert.getName()) && m.getSignature().equals(insert.getSignature())){
                        m.replace("{long appendStartMs = System.nanoTime(); " + 
                        "$_ = $proceed($$);" +
                        "long appendEndMs = System.nanoTime(); " +
                        "AppendResult = appendEndMs - appendStartMs;" +
                        "System.out.println(\"insert ExecutionTime: \" + AppendResult);}"
                        );
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   

    
}
