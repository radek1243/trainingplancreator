package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class AddNextExerciseListener implements View.OnClickListener {

    private Activity activity;
    private SQLiteManager manager;
    private long id_exercise;
    private String muscles;
    private String day;
    private long number;
    private TextView textView;

    public AddNextExerciseListener(Activity activity){
        this.activity=activity;
        this.manager = new SQLiteManager(this.activity.getBaseContext());
        this.id_exercise = -1;
        this.muscles = null;
        this.day = null;
        this.number =-1;
        this.textView = (TextView)this.activity.findViewById(R.id.exerciseInPlan);
    }

    @Override
    public void onClick(View view) {
        this.id_exercise = this.manager.getExerciseId((String)((Spinner)this.activity.findViewById(R.id.exerciseSpinner)).getSelectedItem());
        if(this.id_exercise>-1) {
            this.muscles = (String)((Spinner)this.activity.findViewById(R.id.musclesSpinner)).getSelectedItem();
            this.day = (String)((Spinner)this.activity.findViewById(R.id.daySpinner)).getSelectedItem();
            this.number = this.manager.getExercisesCountFromDay(this.day);
            this.number++;
            this.manager.addExerciseToPlan(this.day, this.number, this.id_exercise);
            this.textView.append(this.number + ". " + this.muscles + ", " + (String)((Spinner)this.activity.findViewById(R.id.exerciseSpinner)).getSelectedItem() + "\n");
        }
    }
}
