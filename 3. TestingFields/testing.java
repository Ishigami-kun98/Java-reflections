
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class testing {

    public static void main(String... args) {
        // This test is used only to get the fields of java and
        // to Manipulate it's fields
        try {
            Class<?> cls = Class.forName("TestingField");
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Field name is : " + field.getName());
                System.out.println("Field type is : " + field.getType());
                System.out.println("Field Modifiers is : " + field.getModifiers());
            }
            TestingField tf = new TestingField(7, 3.14);
            Field field = tf.getClass().getDeclaredField("s");
            field.setAccessible(true);
            String toChange = "Testing...passed";
            field.set(field, toChange);
            System.out.println(TestingField.s);
            Field[] tfFields = tf.getClass().getDeclaredFields();
            
            for (Field fields2 : tfFields) {
                fields2.setAccessible(true);
                System.out.println(fields2.get(tf));
            }
            Method clsFirstMethod = cls.getMethods()[0];
            System.out.println("Class name : " + cls.getName() + "\n first method is " + clsFirstMethod.getName());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}