import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 作业2：自定义ClassLoader，加载Hello.xlass文件
 * @author 邓超 2021.03.21
 */
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> clazz = helloClassLoader.findClass("Hello");
        Object o = clazz.newInstance();
        Method helloMethod = clazz.getMethod("hello");
        helloMethod.invoke(o);
    }

    /**
     * 重写findClass方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) {
        byte[] realBytes = getRealBytes();
        // 使用内置方法，转换真实类型
        return defineClass(name, realBytes, 0, realBytes.length);
    }

    /**
     * 处理Hello.xlass，获取准确的byte数组
     * @return
     */
    private byte[] getRealBytes() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("Hello.xlass");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        while (true) {
            try {
                int x = inputStream.read(buff);
                if (x == -1) {
                    break;
                }
                byteArrayOutputStream.write(buff, 0, x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byte[] realBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            realBytes[i] = (byte) (255 - bytes[i]);
        }
        return realBytes;
    }
}
