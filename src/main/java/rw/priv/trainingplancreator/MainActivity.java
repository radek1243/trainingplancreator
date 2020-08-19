package rw.priv.trainingplancreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.editExercise:{
                this.startActivity(new Intent(this,EditExerciseActivity.class));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public void addExercise(View view){
        this.startActivity(new Intent(this, AddActivity.class));
    }

    public void createPlan(View view){
        this.startActivity(new Intent(this, CreatePlanActivity.class));
    }

    public void train(View view){
        this.startActivity(new Intent(this, TrainActivity.class));
    }
}
