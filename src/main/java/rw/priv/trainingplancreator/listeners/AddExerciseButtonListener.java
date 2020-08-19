package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import rw.priv.trainingplancreator.Exercise;
import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class AddExerciseButtonListener implements View.OnClickListener {

    private Exercise exercise;
    private Activity activity;
    private boolean correct;
    private SQLiteManager manager;

    public AddExerciseButtonListener(Activity activity){
        this.exercise = new Exercise();
        this.activity=activity;
        this.correct=false;
        this.manager = new SQLiteManager(this.activity.getBaseContext());
    }
    @Override
    public void onClick(View view) {
        this.correct=true;
        this.exercise.setMuscles(((Spinner)this.activity.findViewById(R.id.spinnerPartia)).getSelectedItem().toString());
        this.exercise.setName(((EditText)this.activity.findViewById(R.id.nameEditText)).getText().toString());
        try {
            this.exercise.setSeries(Integer.parseInt(((EditText) this.activity.findViewById(R.id.serieEditText)).getText().toString()));
            this.exercise.setRepeat(Integer.parseInt(((EditText) this.activity.findViewById(R.id.powtorzeniaEditText)).getText().toString()));
            this.exercise.setWeight(Double.parseDouble(((EditText) this.activity.findViewById(R.id.ciezarEditText)).getText().toString()));
            this.exercise.setIncrement(Double.parseDouble(((EditText) this.activity.findViewById(R.id.przyrostEditText)).getText().toString()));
            this.exercise.setDescription(((EditText)this.activity.findViewById(R.id.opisEditText)).getText().toString());
            if(this.exercise.getMuscles().trim().isEmpty()) {
                Toast.makeText(this.activity.getBaseContext(),"Nie podano partii mięsni.",Toast.LENGTH_SHORT).show();
                this.correct=false;
            }
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
                correct=false;
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
                if(this.manager.addExercise(this.exercise)>-1){
                    Toast.makeText(this.activity.getBaseContext(), "Dodano ćwiczenie do bazy", Toast.LENGTH_SHORT).show();
                    ((EditText)this.activity.findViewById(R.id.nameEditText)).setText("");
                    ((EditText) this.activity.findViewById(R.id.ciezarEditText)).setText("0.0");
                    ((EditText) this.activity.findViewById(R.id.przyrostEditText)).setText("0.0");
                    ((EditText) this.activity.findViewById(R.id.opisEditText)).setText("");
                }
                else{
                    Toast.makeText(this.activity.getBaseContext(),"Błąd przy dodawaniu ćwiczenia do bazy.",Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(NumberFormatException e){
            Toast.makeText(this.activity.getBaseContext(),"Błąd. Sprawdź wartości serii, powtórzeń, ciężaru i przyrostu.\nPola nie mogą być puste.",Toast.LENGTH_LONG).show();
        }
    }
}
