package example;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            // 读取属性文件
            Properties properties = new Properties();
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("myapp.properties");
            properties.load(inputStream);

            // 获取配置的类名
            String className = properties.getProperty("bootstrapClass");

            // 使用反射创建对象
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.getDeclaredConstructor().newInstance();

            // 查找带有@InitMethod注解的方法并调用
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(InitMethod.class)) {
                    method.invoke(obj);
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException |
                 SecurityException | IllegalArgumentException |
                 java.lang.reflect.InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

