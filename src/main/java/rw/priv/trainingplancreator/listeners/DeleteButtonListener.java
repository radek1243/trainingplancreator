package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class DeleteButtonListener implements View.OnClickListener {

    private Activity activity;
    private String day;
    private long number;
    private SQLiteManager manager;
    private TextView textView;
    private String[] tab;

    public DeleteButtonListener(Activity activity){
        this.activity = activity;
        this.day = null;
        this.number = -1;
        this.manager = new SQLiteManager(this.activity.getBaseContext());
        this.textView = ((TextView)this.activity.findViewById(R.id.exerciseInPlan));
        this.tab = null;
    }

    @Override
    public void onClick(View view) {
        this.day = (String)((Spinner)this.activity.findViewById(R.id.daySpinner)).getSelectedItem();
        this.number = this.manager.getExercisesCountFromDay(this.day);
        if(this.manager.deleteLastAddedExercise(this.day, this.number)==1){
            this.textView.setText("");
            this.tab = this.manager.getExercisesNamesFromDay(this.day);
            for(int i=0;i<this.tab.length;i++){
                this.textView.append(this.tab[i]+"\n");
            }
        }
        else{
            Toast.makeText(this.activity.getBaseContext(),"Błąd przy usuwaniu dodango ćwiczenia lub brak ćwiczeń do usunięcia.",Toast.LENGTH_LONG).show();
        }
    }
}
