import javassist.tools.reflect.Metalevel;
import javassist.tools.reflect.Metaobject;

public class example2 {
    
    //how we use javassist to manipulate Person class
    public static void main(String[] args) {
        Person p = new Person("Mc", 1948);
        System.out.println("name" + p.getName());
        System.out.println("Object : " + p.toString());
        
        //Time to change the metaobject of p
        if (p instanceof Metalevel){
            ((Metalevel)p)._setMetaobject(new Metaobject(p, null));
            System.out.println("The meta object is changed");
        }
        System.out.println("age: " +p.getAge(2018));
    }
}
