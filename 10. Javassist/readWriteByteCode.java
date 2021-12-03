import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
public class readWriteByteCode {

    public class Hello{
        public void say(){
            System.out.println("Hello");
        }
    }

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException {
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
        /*CtClass ccc = pool.get("Person");
        CtMethod m = ccc.getDeclaredMethod("getAge");
        m.insertBefore("{System.out.println(\"Hello getAge:\");}");
        Class<?> c = ccc.toClass();
        Person p = (Person)c.newInstance();
        p.getAge(2018);
        //Nonostante ho fatto una sorta di copia incolla dal tutorial, non funziona ;)
        */
        //Modifying systemClass
        System.out.println("Adding to String a hidden value");
        CtClass cc3 = pool.get("java.lang.String");
        CtField f = new CtField(CtClass.intType, "hiddenvalue", cc3);
        f.setModifiers(Modifier.PUBLIC);
        cc3.addField(f);
        cc3.writeFile(".");
        for (CtField field : cc3.getFields()) {
            System.out.println(field.getName());
        }
    }
}
