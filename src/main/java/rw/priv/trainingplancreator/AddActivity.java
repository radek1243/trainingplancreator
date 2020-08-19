package rw.priv.trainingplancreator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import rw.priv.trainingplancreator.listeners.AddExerciseButtonListener;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        ((Button)this.findViewById(R.id.btnAdd)).setOnClickListener(new AddExerciseButtonListener(this));
    }

}
