public class RuntimeConstantPool {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
//        String str2 = "计算机软件";
//        System.out.println(str1 == str2);
        System.out.println(str1.intern() == str1);

        String str3 = new StringBuilder("ja").append("va").toString();
        System.out.println(str3.intern() == str3);
    }
}
