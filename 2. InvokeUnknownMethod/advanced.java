import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

class advanced{
    public static void main(String[] args) {
        callMethod(args[0],args[1],Arrays.copyOfRange(args, 2, args.length));
    }
    public static LinkedHashMap<String, Class<?>> pattern = 
    new LinkedHashMap<String, Class<?>>(){{
        put("^[+-]?\\d*\\.\\d*$", double.class); // ^inizio $fine considero inizio fino alla fine ?significa che puo o non eserci, *significa lungezza arbitraria
        put("^\\d+$", int.class);
        put("^.*$", String.class);}
    };
    static Class<?>[] recognizeType(String[] args){
        return Arrays.stream(args)
            .map(e -> pattern.entrySet()
                .stream()
                .map(p -> {
                    if(Pattern.matches(p.getKey(), e)) return Optional.of(p.getValue());
                    return Optional.empty();
                })
                .filter(v -> v.isPresent())
                .map(p -> p.get())
                .findFirst().get())
            .toArray(Class[]::new);

    }
    private static void callMethod(String className, String methodName, String[] copyOf) {
        
        try {
            Class<?>[] type = recognizeType(copyOf);
            Class<?> cls = Class.forName(className);
            Method method = cls.getMethod(className, type);
            Object self = cls.getConstructor().newInstance();
            Object[] arguments = null;
            method.invoke(self, arguments);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

} 