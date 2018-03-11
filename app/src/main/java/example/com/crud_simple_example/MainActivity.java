package example.com.crud_simple_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonCreatePerson = findViewById(R.id.buttonCreatePerson);
        buttonCreatePerson.setOnClickListener(new OnClickListenerCreatePerson());
    }
}
