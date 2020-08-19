package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class MuscleSpinnerListener implements AdapterView.OnItemSelectedListener {

    private String text;
    private String[] tab;
    private SQLiteManager man;
    private Activity activity;
    private ArrayAdapter<String> adapter;
    private Spinner exerciseSpinner;

    public MuscleSpinnerListener(Activity activity){
        this.activity = activity;
        this.text = null;
        this.tab = null;
        this.man = new SQLiteManager(this.activity.getBaseContext());
        this.adapter = null;
        this.exerciseSpinner = (Spinner)this.activity.findViewById(R.id.exerciseSpinner);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.text = (String)adapterView.getSelectedItem();
        this.tab = this.man.getMuscleExercisesNames(text);
        if(this.tab.length==0) this.tab = new String[]{""};
        this.adapter = new ArrayAdapter<String>(this.activity.getBaseContext(),android.R.layout.simple_spinner_item, this.tab);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.exerciseSpinner.setAdapter(this.adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
