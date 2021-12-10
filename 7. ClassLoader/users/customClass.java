package users;

public class customClass {
    String c;
    public customClass(String c){
        this.c = c;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.equals(c);
    }
}
