package example.com.crud_simple_example;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public int count(){

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM persons";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<ObjectPerson> read(){

        List<ObjectPerson> recordsList = new ArrayList<>();

        String sql = "SELECT * FROM persons ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String personFirstName = cursor.getString(cursor.getColumnIndex("firstname"));
                String personEmail = cursor.getString(cursor.getColumnIndex("email"));

                ObjectPerson objectPerson = new ObjectPerson();
                objectPerson.id = id;
                objectPerson.firstName = personFirstName;
                objectPerson.email = personEmail;

                recordsList.add(objectPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public ObjectPerson readSingleRecord(int personId) {

        ObjectPerson objectPerson = null;

        String sql = "SELECT * FROM persons WHERE id = " + personId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String firstName = cursor.getString(cursor.getColumnIndex("firstname"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            objectPerson = new ObjectPerson();
            objectPerson.id = id;
            objectPerson.firstName = firstName;
            objectPerson.email = email;
        }

        cursor.close();
        db.close();

        return objectPerson;
    }

    public boolean update(ObjectPerson objectPerson) {

        ContentValues values = new ContentValues();

        values.put("firstname", objectPerson.firstName);
        values.put("email", objectPerson.email);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectPerson.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("persons", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public boolean delete(int id) {

        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("persons", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }
}
