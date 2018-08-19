public class LargeObjectAllocation {
    private static final int _1MB = 1024 * 1024;

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     */
    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
}
