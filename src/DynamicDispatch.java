public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }
    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }
    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        /*

        public static void main(java.lang.String[]);
           descriptor: ([Ljava/lang/String;)V
           flags: ACC_PUBLIC, ACC_STATIC
           Code:
             stack=2, locals=3, args_size=1
                0: new           #2                  // class DynamicDispatch$Man
                3: dup
                4: invokespecial #3                  // Method DynamicDispatch$Man."<init>":()V
                7: astore_1                           -- 将Man对象引用存储到局部变量表
                8: new           #4                  // class DynamicDispatch$Woman
               11: dup
               12: invokespecial #5                  // Method DynamicDispatch$Woman."<init>":()V
               15: astore_2                          -- 将Woman对象引用存储到局部变量表
               16: aload_2                           -- 将Woman对象加载到操作数栈
               17: invokevirtual #6                  // Method DynamicDispatch$Human.sayHello:()V
               20: aload_1                           -- 将Man对象加载到操作数栈
               21: invokevirtual #6                  // Method DynamicDispatch$Human.sayHello:()V
               24: new           #4                  // class DynamicDispatch$Woman
               27: dup
               28: invokespecial #5                  // Method DynamicDispatch$Woman."<init>":()V
               31: astore_1
               32: aload_1
               33: invokevirtual #6                  // Method DynamicDispatch$Human.sayHello:()V
               36: return

        * */
        Human man = new Man();
        Human woman = new Woman();
        woman.sayHello();
        man.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
