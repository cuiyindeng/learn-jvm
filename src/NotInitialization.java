public class NotInitialization {
    /**
     * -XX:+TraceClassLoading
     */
    public static void main(String[] args) {
        System.out.println(SubClass.val);
    }
}

class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static int val = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}