package rw.priv.trainingplancreator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import rw.priv.trainingplancreator.listeners.EditButtonListener;
import rw.priv.trainingplancreator.listeners.ExerciseSpinnerListener;
import rw.priv.trainingplancreator.listeners.MuscleSpinnerListener;

public class EditExerciseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);
        ((Button)this.findViewById(R.id.btnEdit)).setOnClickListener(new EditButtonListener(this));
        ((Spinner)this.findViewById(R.id.spinnerPartia)).setOnItemSelectedListener(new MuscleSpinnerListener(this));
        ((Spinner)this.findViewById(R.id.exerciseSpinner)).setOnItemSelectedListener(new ExerciseSpinnerListener(this));
    }
}
