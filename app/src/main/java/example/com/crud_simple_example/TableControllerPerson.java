package example.com.crud_simple_example;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TableControllerPerson extends DatabaseHandler{

    public TableControllerPerson(Context context){
        super(context);
    }

    public boolean create(ObjectPerson objectPerson){

        ContentValues values = new ContentValues();

        values.put("firstName", objectPerson.firstName);
        values.put("email", objectPerson.email);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("persons", null, values) > 0;
        db.close();

        return createSuccessful;

    }
}
