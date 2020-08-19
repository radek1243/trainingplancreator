package rw.priv.trainingplancreator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import java.util.Calendar;
import java.util.Date;

import rw.priv.trainingplancreator.listeners.TrainButtonListener;

public class TrainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        SQLiteManager man = new SQLiteManager(this);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        Exercise e = man.getExercise(TimeOperation.getDayText(day),1);
        if(e.getNumber()==(-1)){
            ((TextView) this.findViewById(R.id.nameTextView)).setText("Dzisiaj nie ma treningu");
            ((Button)this.findViewById(R.id.btnNext)).setEnabled(false);
        }
        else {
            ((Button) this.findViewById(R.id.btnNext)).setOnClickListener(new TrainButtonListener(this, e, cal));
            ((TextView) this.findViewById(R.id.textViewMuscles2)).setText(e.getMuscles());
            ((TextView) this.findViewById(R.id.nameTextView)).setText(e.getNumber() + ". " + e.getName());
            ((TextView) this.findViewById(R.id.repValueTextView)).setText("" + e.getRepeat());
            ((TextView) this.findViewById(R.id.weiValueTextView)).setText("" + e.getWeight() + " kg");
            ((TextView) this.findViewById(R.id.seriaValueTextView)).setText("1");
            ((TextView) this.findViewById(R.id.opisValueTextView1)).setText(e.getDescription());
            Exercise next = man.getExercise(TimeOperation.getDayText(day), 2);
            if(next.getNumber()==(-1)){
                ((TextView) this.findViewById(R.id.nameNextTextView)).setText("koniec");
            }
            else {
                ((TextView) this.findViewById(R.id.nameNextTextView)).setText(next.getNumber() + ". " + next.getMuscles() + ", " + next.getName());
                ((TextView) this.findViewById(R.id.weiNextValueTextView)).setText("" + next.getWeight() + " kg");
                ((TextView) this.findViewById(R.id.opisNextValueTextView)).setText(e.getDescription());
            }
        }
    }
}
