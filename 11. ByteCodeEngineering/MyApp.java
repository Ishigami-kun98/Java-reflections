public class MyApp {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Let's go ");
        sb.insert(4, "inserting ");
        char[] c = {'a','l','o','a'};
        sb.append(c);
        System.out.println(sb.toString());
    }
    
}
