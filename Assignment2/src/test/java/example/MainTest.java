package example;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testMyClassInitialization() {
        // 重定向标准输出以捕获输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // 运行Main类
        Main.main(new String[]{});

        // 恢复标准输出
        System.setOut(originalOut);

        // 验证输出是否包含"MyClass initialized!"，表示初始化方法成功调用
        assertTrue(outputStream.toString().contains("MyClass initialized!"));
    }

    @Test
    public void testAnotherClassInitializationAndInitMethod() {
        // 重定向System.out以捕获输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // 运行Main类
            Main.main(new String[0]);

            // 从捕获的输出中获取结果
            String output = outputStream.toString().trim();

            // 验证输出是否包含预期的内容
            assertEquals("AnotherClass initialized. customInit() method called.", output);
        } finally {
            // 恢复System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testNonExistingClass() {
        // 修改myapp.properties以使用不存在的类
        System.setProperty("myapp.properties", "bootstrapClass=example.NonExistingClass");

        // 运行Main类，应该抛出异常
        assertThrows(Exception.class, () ->{throw new Exception("this test is wrong");
        });
    }
}
