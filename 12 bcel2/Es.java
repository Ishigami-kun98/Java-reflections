public class Es {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("as9d9");
        char[] c = {'a','b'};
        sb.append(c);
        sb.insert(1, new String("aosdo"));
        System.out.println(sb.toString());
    }
}
