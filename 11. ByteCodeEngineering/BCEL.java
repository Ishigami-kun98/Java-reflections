import org.apache.bcel.Repository;
import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.*;
import java.lang.annotation.*;
import java.util.Arrays;

import javassist.bytecode.AccessFlag;
public class BCEL {

    public static void printCode(Method[] methods){
        for(var m : methods){
            System.out.println(m);
            Code body = m.getCode();
            if(body != null){
                System.out.println(body);
            }
        }
    }

        public static void main(String[] args){
            try{
                JavaClass clazz = Repository.lookupClass("java.lang.String");
                printCode(clazz.getMethods());
                //Create a class
                ClassGen cg = new ClassGen("Test", "java.lang.Object", "Test.java", AccessFlag.PUBLIC,null);
                ConstantPoolGen cp = cg.getConstantPool();
                FieldGen gf = new FieldGen(AccessFlag.PUBLIC, org.apache.bcel.generic.Type.INT, "field1", cp);
                InstructionList il = new InstructionList();
                cg.addField(gf.getField());
                MethodGen mg = new MethodGen(AccessFlag.PUBLIC, org.apache.bcel.generic.Type.VOID, org.apache.bcel.generic.Type.NO_ARGS, null, "MethodACaso", "Test", il, cp);
                mg.setMaxStack();
                cg.addMethod(mg.getMethod());
                System.out.println(cg.getClassName() + " " +  Arrays.toString(cg.getFields()) + cg.getMethodAt(0).getName());                
            }catch(ClassNotFoundException e){
                e.printStackTrace();
                
            }
        }
    
    
}
