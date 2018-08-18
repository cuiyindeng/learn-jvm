import java.io.FileInputStream;
import java.io.InputStream;

public class ViewHackSystem {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("D:/JavaExcrise/learn_jvm/out/production/learn_jvm/TestClass.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();

        System.out.println(JavaClassExecuter.execute(b));
    }
}
