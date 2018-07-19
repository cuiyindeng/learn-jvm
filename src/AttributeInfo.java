public class AttributeInfo {
    private int m;

    public int getM() {
        //javac -g:none 不会生成attribute_info中的LineNumberTable属性，即源码行号和字节码行号，例如出异常时不会显示源码行号。
        // javac -g:lines则相反。
        int i = 1 / 0;
        return i;
    }

    public static void main(String[] args) {
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.getM();
    }
}
