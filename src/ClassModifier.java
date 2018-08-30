/**
 * 修改Class文件，暂时只提供修改常量池常量的功能
 * @author zzm
 */
public class ClassModifier {

    /**
     * Class文件中常量池的起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info常量的tag标志
     */
    private static final int CONSTANT_Utf8_info = 1;

    /**
     * 常量池中11种常量所占的长度，CONSTANT_Utf8_info型常量除外，因为它不是定长的
     * jdk1.7之前有11种，jdk1.7之后有14种。
     * 这14种常量项结构还有一个特点是，其中13表占用得字节固定，只有CONSTANT_Utf8_info占用字节不固定，其大小由length决定
     */
    private static final int[] CONSTANT_ITEM_LENGTH = { -1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5 };

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 修改常量池中CONSTANT_Utf8_info常量的内容
     * @param oldStr 修改前的字符串
     * @param newStr 修改后的字符串
     * @return 修改结果
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        //常量池的起始偏移位
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        //取出每一个常量元素
        for (int i = 0; i < cpc; i++) {
            //每一个的常量元素的tag占1byte
            int tag = ByteUtils.bytes2Int(classByte, offset, u1);
            //常量元素是否为字符串
            if (tag == CONSTANT_Utf8_info) {
                //计算字符串的长度
                int len = ByteUtils.bytes2Int(classByte, offset + u1, u2);
                //计算字符串的起始偏移位
                offset += (u1 + u2);
                String str = ByteUtils.bytes2String(classByte, offset, len);
                //判断是否为要替换的字符串
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(), u2);
                    classByte = ByteUtils.bytesReplace(classByte, offset - u2, u2, strLen);
                    classByte = ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    //计算下一个常量的偏移位，即字符串的偏移位加上字符串的长度
                    offset += len;
                }
            } else {
                //计算下一个常量的偏移位
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /**
     * 获取常量池中常量的数量
     * @return 常量池数量
     *
     * 4byte(魔数) + 4byte(版本号) + 2byte(常亮数量) = 10byte(偏移位) = 8+ 2
     */
    public int getConstantPoolCount() {
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}