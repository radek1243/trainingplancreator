package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class DaySpinnerListener implements AdapterView.OnItemSelectedListener {

    private Activity activity;
    private String text;
    private TextView textView;
    private String tab[];
    private SQLiteManager man;

    public DaySpinnerListener(Activity activity){
        this.activity = activity;
        this.text=null;
        this.textView = (TextView)this.activity.findViewById(R.id.exerciseInPlan);
        this.tab = null;
        this.man = new SQLiteManager(this.activity.getBaseContext());
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.text = (String)adapterView.getSelectedItem();
        this.textView.setText("");
        this.tab = this.man.getExercisesNamesFromDay(this.text);
        for(int j=0;j<this.tab.length;j++){
            this.textView.append(this.tab[j]+"\n");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
