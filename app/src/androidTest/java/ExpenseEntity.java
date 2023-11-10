import java.util.Date;

public class ExpenseEntity {
    public int id;
    public String expenseName;
    public Date expenseDate;
    public String expenseType;

    public ExpenseEntity() {
    }

    public ExpenseEntity(int id, String expenseName, Date expenseDate,
                         String expenseType) {
        id = id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseType = expenseType;
    }
}
