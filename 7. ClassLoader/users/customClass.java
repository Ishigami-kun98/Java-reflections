
public class customClass extends myClass1{
    String c;
    public customClass(String c){
        super(3);
        this.c = c;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.equals(c);
    }
    public String getC(){
        return c;
    }
}
