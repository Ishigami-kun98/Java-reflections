import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
public class readWriteByteCode {

    public class Hello{
        public void say(){
            System.out.println("Hello");
        }
    }

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        /*CtClass cc = pool.get("Person");
        System.out.println(cc.getName());
        cc.setSuperclass(pool.get("VerboseMetaobj"));
        System.out.println(cc.getSuperclass().getName());
       */
        
        CtClass cc2 = pool.makeClass("Mc");
        
        
        CtClass iface = pool.makeInterface("Aragon");
        CtClass iface2 = pool.makeInterface("Ayrthon");
        cc2.addInterface(iface2);
        cc2.addInterface(iface);
        for (CtClass c : cc2.getInterfaces()){
            System.out.println(c.getName());
        }
        CtClass ccc = pool.get("Person");
        CtMethod m = ccc.getDeclaredMethod("getAge");
        m.insertBefore("{System.out.println(\"Hello getAge:\");}");
        Class<?> c = ccc.toClass();
        Person p = (Person)c.newInstance();
        p.getAge(2018);
        
    }
}
