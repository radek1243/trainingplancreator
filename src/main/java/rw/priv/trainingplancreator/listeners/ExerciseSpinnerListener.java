package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import rw.priv.trainingplancreator.Exercise;
import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 01.05.2017.
 */

public class ExerciseSpinnerListener implements AdapterView.OnItemSelectedListener {

    private String text;
    private Activity activity;
    private Exercise e;
    private SQLiteManager man;

    public ExerciseSpinnerListener(Activity activity){
        this.activity = activity;
        this.text = null;
        this.e = new Exercise();
        this.man = new SQLiteManager(this.activity.getBaseContext());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.text = (String)adapterView.getSelectedItem();
        if(this.text.equals("")){
            ((EditText) this.activity.findViewById(R.id.nameEditText)).setText("");
            ((EditText) this.activity.findViewById(R.id.serieEditText)).setText("");
            ((EditText) this.activity.findViewById(R.id.powtorzeniaEditText)).setText("");
            ((EditText) this.activity.findViewById(R.id.ciezarEditText)).setText("");
            ((EditText) this.activity.findViewById(R.id.przyrostEditText)).setText("");
            ((EditText) this.activity.findViewById(R.id.opisEditText)).setText("");
        }
        else {
            this.e = this.man.getExercise(this.text);
            ((EditText) this.activity.findViewById(R.id.nameEditText)).setText(this.e.getName());
            ((EditText) this.activity.findViewById(R.id.serieEditText)).setText("" + this.e.getSeries());
            ((EditText) this.activity.findViewById(R.id.powtorzeniaEditText)).setText("" + this.e.getRepeat());
            ((EditText) this.activity.findViewById(R.id.ciezarEditText)).setText("" + this.e.getWeight());
            ((EditText) this.activity.findViewById(R.id.przyrostEditText)).setText("" + this.e.getIncrement());
            ((EditText) this.activity.findViewById(R.id.opisEditText)).setText(this.e.getDescription());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
