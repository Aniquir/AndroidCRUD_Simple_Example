package example.com.crud_simple_example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class OnLongClickListenerPersonRecord implements View.OnLongClickListener{

    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = {"Edit", "Delete"};
        new AlertDialog.Builder(context).setTitle("Person Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        dialog.dismiss();
                        if (item == 0){
                            editRecord(Integer.parseInt(id));
                        } else if (item == 1){

                            boolean deleteSuccessful = new TableControllerPerson(context).delete(Integer.parseInt(id));

                            if(deleteSuccessful){
                                Toast.makeText(context, "Person record was deleted.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Unable to delete person record.", Toast.LENGTH_SHORT).show();
                            }
                            ((MainActivity) context).countRecords();
                            ((MainActivity) context).readRecords();
                        }
                    }
                }).show();
        return false;
    }

    private void editRecord(final int personId) {

        final TableControllerPerson tableControllerPerson = new TableControllerPerson(context);
        ObjectPerson objectPerson = tableControllerPerson.readSingleRecord(personId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.person_input_form, null, false);

        final EditText editTextPersonFirstName = formElementsView.findViewById(R.id.editTextPersonFirstname);
        final EditText editTextPersonEmail = formElementsView.findViewById(R.id.editTextPersonEmail);

        editTextPersonFirstName.setText(objectPerson.firstName);
        editTextPersonEmail.setText(objectPerson.email);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                ObjectPerson objectPerson = new ObjectPerson();
                                objectPerson.id = personId;
                                objectPerson.firstName = editTextPersonFirstName.getText().toString();
                                objectPerson.email = editTextPersonEmail.getText().toString();

                                boolean updateSuccessful = tableControllerPerson.update(objectPerson);

                                if (updateSuccessful){
                                    Toast.makeText(context, "Person record was updated.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to update person record.", Toast.LENGTH_SHORT).show();
                                }
                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();
                            }
                        }).show();

    }
}
