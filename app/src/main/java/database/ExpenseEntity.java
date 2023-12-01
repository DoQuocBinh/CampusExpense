package database;

import java.util.Date;

public class ExpenseEntity {
    public int id;
    public String expenseName;
    public String expenseDate;
    public String expenseType;

    public String amount;

    public ExpenseEntity() {
    }

    public ExpenseEntity(int id, String expenseName, String expenseDate,
                         String expenseType) {
        id = id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseType = expenseType;
    }
    @Override
    public String toString() {
        return  expenseName + "\n" + amount + "\n" +expenseDate;

    }
}
