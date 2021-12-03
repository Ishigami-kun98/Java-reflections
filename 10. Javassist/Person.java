public class Person{
    public String name = "";
    public static int birth = 4;
    public Person(){}
    public Person(String name, int birthyear){this.name = name; birth = birthyear;}
    public String getName(){return name;}
    public int getAge(int year){return year - birth;}
}