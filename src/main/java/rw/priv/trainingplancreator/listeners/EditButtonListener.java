package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import rw.priv.trainingplancreator.Exercise;
import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class EditButtonListener implements View.OnClickListener {

    private Activity activity;
    private boolean correct;
    private String oldName;
    private int itemPosition;
    private Exercise exercise;
    private SQLiteManager manager;
    private String[] tab;
    private ArrayAdapter<String> adapter;
    private Spinner exerciseSpinner;

    public EditButtonListener(Activity activity){
        this.activity=activity;
        this.correct=false;
        this.oldName=null;
        this.itemPosition=-1;
        this.exercise = new Exercise();
        this.manager = new SQLiteManager(this.activity.getBaseContext());
        this.tab=null;
        this.adapter=null;
        this.exerciseSpinner = (Spinner)this.activity.findViewById(R.id.exerciseSpinner);
    }

    @Override
    public void onClick(View view) {
        this.correct=true;
        this.oldName = (String)((Spinner)this.activity.findViewById(R.id.exerciseSpinner)).getSelectedItem();
        this.itemPosition = ((Spinner)this.activity.findViewById(R.id.exerciseSpinner)).getSelectedItemPosition();
        try {
            this.exercise.setName(((EditText)this.activity.findViewById(R.id.nameEditText)).getText().toString());
            this.exercise.setSeries(Integer.parseInt(((EditText) this.activity.findViewById(R.id.serieEditText)).getText().toString()));
            this.exercise.setRepeat(Integer.parseInt(((EditText) this.activity.findViewById(R.id.powtorzeniaEditText)).getText().toString()));
            this.exercise.setWeight(Double.parseDouble(((EditText) this.activity.findViewById(R.id.ciezarEditText)).getText().toString()));
            this.exercise.setIncrement(Double.parseDouble(((EditText) this.activity.findViewById(R.id.przyrostEditText)).getText().toString()));
            this.exercise.setDescription(((EditText)this.activity.findViewById(R.id.opisEditText)).getText().toString());
            if(this.exercise.getName().trim().isEmpty()) {
                Toast.makeText(this.activity.getBaseContext(),"Nie podano nazwy ćwiczenia.",Toast.LENGTH_SHORT).show();
                this.correct=false;
            }
            if(this.exercise.getSeries()<=0) {
                Toast.makeText(this.activity.getBaseContext(),"Podano błędną ilość serii.",Toast.LENGTH_SHORT).show();
                this.correct=false;
            }
            if(this.exercise.getRepeat()<=0) {
                Toast.makeText(this.activity.getBaseContext(),"Podano błędną ilość powtórzeń.",Toast.LENGTH_SHORT).show();
                this.correct=false;
            }
            if(this.exercise.getWeight()<0) {
                Toast.makeText(this.activity.getBaseContext(),"Podano błędny ciężar.",Toast.LENGTH_SHORT).show();
                this.correct=false;
            }
            if(this.exercise.getIncrement()<0) {
                Toast.makeText(this.activity.getBaseContext(),"Podano błędny przyrost.",Toast.LENGTH_SHORT).show();
                this.correct=false;
            }
            if(this.correct) {
                if(this.manager.updateExercise(this.oldName, this.exercise)==1){
                    Toast.makeText(this.activity.getBaseContext(), "Zedytowano ćwiczenie", Toast.LENGTH_SHORT).show();
                    this.tab = this.manager.getMuscleExercisesNames((String)((Spinner)this.activity.findViewById(R.id.spinnerPartia)).getSelectedItem());
                    this.adapter = new ArrayAdapter<String>(this.activity.getBaseContext(),android.R.layout.simple_spinner_item, this.tab);
                    this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    this.exerciseSpinner.setAdapter(this.adapter);
                    this.exerciseSpinner.setSelection(this.itemPosition);
                }
                else{
                    Toast.makeText(this.activity.getBaseContext(),"Błąd przy edytowaniu ćwiczenia.",Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(NumberFormatException e){
            Toast.makeText(this.activity.getBaseContext(),"Błąd. Sprawdź wartości serii, powtórzeń, ciężaru i przyrostu.\nPola nie mogą być puste.",Toast.LENGTH_LONG).show();
        }
    }
}
