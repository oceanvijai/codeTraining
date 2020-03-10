public class PrintInOrder{

    // Suppose we have a class:

    /**
     * The same instance of Foo will be passed to three different threads. 
     * Thread A will call first(), thread B will call second(), and thread C will call third(). 
     * Design a mechanism and modify the program to ensure that second() is executed after first(),
     *  and third() is executed after second().
     */

     // Note: call wait and notifyall inside a synchronized

    class Foo {
    
        private Integer sec = 0;
        private Integer third = 0;
    
        public Foo() {
            
        }
    
        public void first(Runnable printFirst) throws InterruptedException {
            
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            sec = 1;
            synchronized(this){
                this.notifyAll();
            }
        }
    
        public void second(Runnable printSecond) throws InterruptedException {
            while(sec != 1){
                //System.out.println("second running");
                synchronized(this){
                    this.wait();
                }
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            third = 1;
            synchronized(this){
                this.notifyAll();
            }
        }
    
        public void third(Runnable printThird) throws InterruptedException {
            while(third != 1){
               //System.out.println("third running");
                synchronized(this){
                    this.wait();   
                }
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }



}