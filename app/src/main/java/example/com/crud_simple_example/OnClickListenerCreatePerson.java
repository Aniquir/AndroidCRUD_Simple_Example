package example.com.crud_simple_example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

class OnClickListenerCreatePerson implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        final Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.person_input_form, null, false);

        final EditText editTextPersonFirstName = formElementsView.findViewById(R.id.editTextPersonFirstname);
        final EditText editTextPersonEmail = formElementsView.findViewById(R.id.editTextPersonEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Person")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                String personFirstName = editTextPersonFirstName.getText().toString();
                                String personEmail = editTextPersonEmail.getText().toString();

                                ObjectPerson objectPerson = new ObjectPerson();
                                objectPerson.firstName = personFirstName;
                                objectPerson.email = personEmail;

                                boolean createSuccessful = new TableControllerPerson(context).create(objectPerson);

                                if (createSuccessful){
                                    Toast.makeText(context, "Person information was saved.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save person information", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();

    }
}
