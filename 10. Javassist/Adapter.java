import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.Translator;

public class Adapter implements Translator{
    CtMethod printM;
    CtClass printable;
    @Override
    public void onLoad(ClassPool arg0, String arg1) throws NotFoundException, CannotCompileException {
        
        
    }

    @Override
    public void start(ClassPool arg0) throws NotFoundException, CannotCompileException {
        this.printable = arg0.get("Printable");
        CtClass ex = arg0.get("Exemplar");
        this.printM = ex.getDeclaredMethod("print", null);
    }

    public void onWrite(String cn, ClassPool p) throws CannotCompileException, NotFoundException{
        CtClass c = p.get(cn);
        CtClass[] interfaces = c.getInterfaces();
        for(int i = 0; i < interfaces.length; i++){
            if(interfaces[i].getName().equals("Writable")){
                interfaces[i] = printable;
                c.setInterfaces(interfaces);
                c.addMethod(CtNewMethod.copy(printM, c, null));
                return;
            }
        }
    }
    
}
