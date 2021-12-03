import javassist.tools.reflect.*;
public class VerboseMetaobj extends Metaobject{
    public VerboseMetaobj(Object self, Object[] args){
        super(self, args);
        //if we didn't import the library, we are doomed because there isnt Object.getClass().getName() functions.
        System.out.println("--Constructed: "+self.getClass().getName());
    }

    public Object trapFieldRead(String name){
        System.out.println("-- field read: " +name);
        return super.trapFieldRead(name);
    }

    public void trapFieldWrite(String name, Object value){
        System.out.println("-- field write: " + name);
        super.trapFieldWrite(name, value);
    }

    public Object trapMethodCall(int identifier, Object[] args) throws Throwable{
        System.out.println("--trap: " + getMethodName(identifier) + "() in " + getClassMetaobject().getName());
        return super.trapMethodcall(identifier, args);
    }
    
}
