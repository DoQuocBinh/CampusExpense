import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExpenseDB.db";

    /* Inner class that defines the table contents */
    public static class ExpenseEntry implements BaseColumns {
        public static final String TABLE_NAME = "expense";
        public static final String COLUMN_NAME_EXPENSENAME = "name";
        public static final String COLUMN_NAME_DATE = "expenseDate";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_TYPE = "type";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ExpenseEntry.TABLE_NAME + " (" +
                    ExpenseEntry._ID + " INTEGER PRIMARY KEY," +
                    ExpenseEntry.COLUMN_NAME_EXPENSENAME + " TEXT," +
                    ExpenseEntry.COLUMN_NAME_DATE + " TEXT," +
                    ExpenseEntry.COLUMN_NAME_AMOUNT + " TEXT," +
                    ExpenseEntry.COLUMN_NAME_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ExpenseEntry.TABLE_NAME;

    private SQLiteDatabase database;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();

    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertExpense(ExpenseEntity expense){

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ExpenseEntry.COLUMN_NAME_EXPENSENAME, expense.expenseName);
        values.put(ExpenseEntry.COLUMN_NAME_AMOUNT, expense.amount);
        values.put(ExpenseEntry.COLUMN_NAME_TYPE, expense.expenseType);
        values.put(ExpenseEntry.COLUMN_NAME_DATE, expense.expenseDate.toString());

        // Insert the new row, returning the primary key value of the new row
        return database.insertOrThrow(DATABASE_NAME, null, values);
    }


}
