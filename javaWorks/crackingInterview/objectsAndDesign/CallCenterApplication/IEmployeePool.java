public interface IEmployeePool {
    IEmployee getAvailableEmployee(LevelEnum level);
}