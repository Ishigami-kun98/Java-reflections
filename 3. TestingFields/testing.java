package lab3;

import java.lang.reflect.Field;

class testing {

    public static void main(String... args) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}