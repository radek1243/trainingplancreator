package rw.priv.trainingplancreator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import rw.priv.trainingplancreator.listeners.AddNextExerciseListener;
import rw.priv.trainingplancreator.listeners.DaySpinnerListener;
import rw.priv.trainingplancreator.listeners.DeleteButtonListener;
import rw.priv.trainingplancreator.listeners.MuscleSpinnerListener;


public class CreatePlanActivity extends Activity {

    private Spinner dayOfWeekSpinner;
    private TextView exerciseInPlanTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        ((Button)this.findViewById(R.id.addNextExercise)).setOnClickListener(new AddNextExerciseListener(this));
        ((Button)this.findViewById(R.id.btnDelete)).setOnClickListener(new DeleteButtonListener(this));
        ((Spinner)this.findViewById(R.id.musclesSpinner)).setOnItemSelectedListener(new MuscleSpinnerListener(this));
        this.dayOfWeekSpinner = (Spinner)this.findViewById(R.id.daySpinner);
        this.dayOfWeekSpinner.setOnItemSelectedListener(new DaySpinnerListener(this));
        this.exerciseInPlanTextView = (TextView)this.findViewById(R.id.exerciseInPlan);
        SQLiteManager man = new SQLiteManager(this);
        String tab[] = man.getExercisesNamesFromDay((String)this.dayOfWeekSpinner.getSelectedItem());
        for(int i=0;i<tab.length;i++){
            this.exerciseInPlanTextView.append(tab[i]+"\n");
        }
        man = null;
        tab = null;
    }
}
