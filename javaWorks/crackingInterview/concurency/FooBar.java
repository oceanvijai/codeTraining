class FooBar {

    /**
     * Input: n = 1
     * Output: "foobar"
     * Explanation: There are two threads being fired asynchronously. 
     * One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
     * 
     * Input: n = 2
     * Output: "foobarfoobar"
     * Explanation: "foobar" is being output 2 times.
     */

    private int n;
    private boolean foo = true;

    public FooBar(int n) {
        this.n = n;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(!foo){
                this.wait();
            }
            
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            foo = false;
            this.notifyAll();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(foo){
                this.wait();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            foo = true;
            this.notifyAll();
        }
    }
}