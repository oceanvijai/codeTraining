public class CallCenterApp extends ICallCoordinator{
    IEmployeePool empPool;
    ICallPool callPool;

    // Constructor injection
    public CallCenterApp(IEmployeePool empPool, ICallPool callPool){
        this.empPool = empPool;
        this.callPool = callPool;
    }

    void assignCallToEmployee(){
        Imployee e = empPool.getAvailableEmployee();
        // then get a call from the call poll
        // and assign it to the employee
    }
    void assignCallToWaitingQueue(){
        // This happens when we either assign a new call to the 
    }
}