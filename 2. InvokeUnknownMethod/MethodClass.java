public class MethodClass {
    public String append(String a, String b) {
        System.out.println("we are in append with a and b : " + a + " " + b + " as argument");
        return a + b;
    }

    public String mixAppend(String a, int b) {
        String conversion = "" + b;
        System.out.println("we are in mixAppend with a and b : " + a + " " + b + " as argument");
        return a + conversion;
    }
}
