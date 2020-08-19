package rw.priv.trainingplancreator.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import rw.priv.trainingplancreator.TimeOperation;
import rw.priv.trainingplancreator.Exercise;
import rw.priv.trainingplancreator.R;
import rw.priv.trainingplancreator.SQLiteManager;

/**
 * Created by Radek on 14.03.2017.
 */

public class TrainButtonListener implements View.OnClickListener {

    private Activity activity;
    private SQLiteManager manager;
    private Exercise e;
    private Exercise next;
    private Calendar cal;
    private int series;

    public TrainButtonListener(Activity activity, Exercise e, Calendar cal){
        this.activity=activity;
        this.manager = new SQLiteManager(this.activity.getBaseContext());
        this.e=e;
        this.cal=cal;
        this.series=1;
        this.next=null;
    }

    @Override
    public void onClick(View view) {
        if(((Button)view).getText().toString().equals("Koniec")){
            this.activity.finish();
        }
        else {
            this.series = Integer.parseInt(((TextView) this.activity.findViewById(R.id.seriaValueTextView)).getText().toString());
            if (this.series < this.e.getSeries()) {
                this.series++;
                ((TextView) this.activity.findViewById(R.id.seriaValueTextView)).setText("" + this.series);
            } else {
                ((TextView) this.activity.findViewById(R.id.seriaValueTextView)).setText("" + 1);
                this.e = this.manager.getExercise(TimeOperation.getDayText(this.cal.get(Calendar.DAY_OF_WEEK)), this.e.getNumber() + 1);
                if (this.e.getNumber() == (-1)) {
                    ((TextView) this.activity.findViewById(R.id.nameTextView)).setText("Koniec treningu");
                    ((TextView) this.activity.findViewById(R.id.textViewMuscles2)).setText("");
                    ((TextView) this.activity.findViewById(R.id.repValueTextView)).setText("");
                    ((TextView) this.activity.findViewById(R.id.weiValueTextView)).setText("");
                    ((TextView) this.activity.findViewById(R.id.seriaValueTextView)).setText("");
                    ((TextView) this.activity.findViewById(R.id.opisValueTextView1)).setText("");
                    ((Button) view).setText("Koniec");
                } else {
                    ((TextView) this.activity.findViewById(R.id.textViewMuscles2)).setText(this.e.getMuscles());
                    ((TextView) this.activity.findViewById(R.id.nameTextView)).setText(this.e.getNumber() + ". " + this.e.getName());
                    ((TextView) this.activity.findViewById(R.id.repValueTextView)).setText("" + this.e.getRepeat());
                    ((TextView) this.activity.findViewById(R.id.weiValueTextView)).setText("" + this.e.getWeight() + " kg");
                    ((TextView) this.activity.findViewById(R.id.seriaValueTextView)).setText("1");
                    ((TextView) this.activity.findViewById(R.id.opisValueTextView1)).setText(this.e.getDescription());
                    this.next = this.manager.getExercise(TimeOperation.getDayText(this.cal.get(Calendar.DAY_OF_WEEK)), this.e.getNumber() + 1);
                    if (this.next.getNumber() == (-1)) {
                        ((TextView) this.activity.findViewById(R.id.nameNextTextView)).setText("koniec");
                        ((TextView) this.activity.findViewById(R.id.weiNextValueTextView)).setText("");
                        ((TextView) this.activity.findViewById(R.id.opisNextValueTextView)).setText("");
                    } else {
                        ((TextView) this.activity.findViewById(R.id.nameNextTextView)).setText(this.next.getNumber() + ". " + this.next.getMuscles() + ", " + this.next.getName());
                        ((TextView) this.activity.findViewById(R.id.weiNextValueTextView)).setText("" + this.next.getWeight() + " kg");
                        ((TextView) this.activity.findViewById(R.id.opisNextValueTextView)).setText(this.next.getDescription());
                    }
                }
            }
        }
    }
}
