package example.com.crud_simple_example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonCreatePerson = findViewById(R.id.buttonCreatePerson);
        buttonCreatePerson.setOnClickListener(new OnClickListenerCreatePerson());
        countRecords();
        readRecords();
    }

    @SuppressLint("SetTextI18n")
    public void countRecords(){

        int recordCount = new TableControllerPerson(this).count();
        TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }

    public void readRecords(){

        LinearLayout linearLayoutRecords = findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectPerson> persons = new TableControllerPerson(this).read();

        if (persons.size() > 0){

            for (ObjectPerson obj : persons){

                int id = obj.id;
                String personFirstName = obj.firstName;
                String personEmail = obj.email;

                String textViewContents = personFirstName + " - " + personEmail;

                TextView textViewPersonsItem = new TextView(this);
                textViewPersonsItem.setPadding(0,10,0,10);
                textViewPersonsItem.setText(textViewContents);
                textViewPersonsItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewPersonsItem);
            }
        } else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }
}
