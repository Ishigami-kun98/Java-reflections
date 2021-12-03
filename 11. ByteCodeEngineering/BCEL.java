import org.apache.bcel.Repository;
import org.apache.bcel.classfile.*;

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
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    
    
}
