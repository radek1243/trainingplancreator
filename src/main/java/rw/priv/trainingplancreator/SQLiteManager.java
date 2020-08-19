package rw.priv.trainingplancreator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Radek on 11.03.2017.
 */
//zeby zrobic update trzeba zmienic version code i wersion name w pliku gradle: build.gradle (Module:app)
public class SQLiteManager extends SQLiteOpenHelper{

    private SQLiteDatabase db;
    private ArrayList<String> list;
    private ContentValues values;
    private Cursor c;

    //jeszcze posprawdzac jak to jest z tymi metodami co zwracaja bo chyba jest tak ze musi zwracac nowy obiekt bo zwraca go a potem juz tylko referencja jest przekazywana i dlatego bylop zle posprawdzac jeszcze wszedzie potem zwlaszcza jak ma cos zwracac
    public SQLiteManager(Context context){
        super(context,"trening.db",null,1);
        this.db = null;
        this.list = new ArrayList<String>();
        this.values = new ContentValues();
        this.c = null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table cwiczenia(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nazwa varchar(100) not null," +
                "partia varchar(50) not null," +
                "serie integer not null," +
                "powtorzenia integer not null," +
                "ciezar double not null," +
                "przyrost double not null,"+
                "opis varchar(200));");
        sqLiteDatabase.execSQL("create table plan(id integer primary key autoincrement," +
                "dzien varchar(20) not null," +
                "numer_cw_w_planie integer not null," +
                "id_cwiczenia INTEGER," +
                "foreign key(id_cwiczenia) references cwiczenia(id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addExercise(Exercise exercise){
        this.db = this.getWritableDatabase();
        this.values.put("nazwa",exercise.getName());
        this.values.put("partia",exercise.getMuscles());
        this.values.put("serie",exercise.getSeries());
        this.values.put("powtorzenia",exercise.getRepeat());
        this.values.put("ciezar",exercise.getWeight());
        this.values.put("przyrost",exercise.getIncrement());
        this.values.put("opis",exercise.getDescription());
        long result = this.db.insertOrThrow("cwiczenia", null, this.values);
        this.db.close();
        this.resetValues();
        return result;
    }

    public String[] getMuscleExercisesNames(String muscles){
        this.resetList();
        this.db = this.getReadableDatabase();
        this.c = this.db.rawQuery("select nazwa from cwiczenia where partia='"+muscles+"'",null);
        while(this.c.moveToNext()){
            this.list.add(this.c.getString(0));
        }
        this.resetCursor();
        this.db.close();
        return this.toStringArray(this.list.toArray());
    }

    public String[] getExercisesNamesFromDay(String day){
        this.resetList();
        this.db = this.getReadableDatabase();
        this.c = this.db.rawQuery("select plan.numer_cw_w_planie, cwiczenia.partia, cwiczenia.nazwa from plan join cwiczenia on plan.id_cwiczenia=cwiczenia.id where plan.dzien=?",new String[]{day});
        while(this.c.moveToNext()){
            this.list.add(this.c.getString(0)+". "+this.c.getString(1)+", "+this.c.getString(2));
        }
        this.resetCursor();
        this.db.close();
        return this.toStringArray(this.list.toArray());
    }

    public void addExerciseToPlan(String day, long number, long exercise_id){
        this.db = this.getWritableDatabase();
        this.values = new ContentValues();
        this.values.put("dzien",day);
        this.values.put("numer_cw_w_planie",number);
        this.values.put("id_cwiczenia",exercise_id);
        this.db.insertOrThrow("plan",null,this.values);
        this.resetValues();
        this.db.close();
    }

    public long getExercisesCountFromDay(String day){
        this.db = this.getReadableDatabase();
        this.c = this.db.rawQuery("select count(numer_cw_w_planie) from plan where dzien='"+day+"'",null);
        long result=-1;
        if(this.c.moveToNext()){
            result=this.c.getInt(0);
        }
        this.resetCursor();
        this.db.close();
        return result;
    }

    public Exercise getExercise(String day, int number){
        Exercise e = new Exercise();
        this.db = this.getReadableDatabase();
        this.c = this.db.rawQuery("select cwiczenia.partia, plan.numer_cw_w_planie, cwiczenia.nazwa, cwiczenia.powtorzenia, cwiczenia.ciezar, "+
        "cwiczenia.serie, cwiczenia.opis, cwiczenia.przyrost from plan join cwiczenia on plan.id_cwiczenia=cwiczenia.id where plan.dzien='"+day+"' "+
                "and numer_cw_w_planie="+number,null);
        if(this.c.moveToNext()){
            e.setMuscles(this.c.getString(0));
            e.setNumber(this.c.getInt(1));
            e.setName(this.c.getString(2));
            e.setRepeat(this.c.getInt(3));
            e.setWeight(this.c.getDouble(4));
            e.setSeries(this.c.getInt(5));
            e.setDescription(this.c.getString(6));
            e.setIncrement(this.c.getDouble(7));
        }
        this.resetCursor();
        this.db.close();
        return e;
    }

    public Exercise getExercise(String name){
        Exercise e = new Exercise();
        this.db = this.getReadableDatabase();
        this.c = this.db.rawQuery("select * from cwiczenia where nazwa='" + name + "'", null);
        if (this.c.moveToNext()) {
            e.setId(c.getInt(0));
            e.setName(c.getString(1));
            e.setMuscles(c.getString(2));
            e.setSeries(c.getInt(3));
            e.setRepeat(c.getInt(4));
            e.setWeight(c.getDouble(5));
            e.setIncrement(c.getDouble(6));
            e.setDescription(c.getString(7));
        }
        this.resetCursor();
        this.db.close();
        return e;
    }

    public long updateExercise(String oldName, Exercise e){
        this.db = this.getWritableDatabase();
        this.values.put("nazwa",e.getName());
        this.values.put("serie", e.getSeries());
        this.values.put("powtorzenia", e.getRepeat());
        this.values.put("ciezar", e.getWeight());
        this.values.put("przyrost", e.getIncrement());
        this.values.put("opis", e.getDescription());
        long result = -1;
        result = this.db.update("cwiczenia",this.values,"nazwa=?",new String[]{oldName});
        this.db.close();
        this.resetValues();
        return result;
    }

    public long getExerciseId(String name){
        this.db = this.getReadableDatabase();
        this.c = this.db.rawQuery("select id from cwiczenia where nazwa='"+name+"'",null);
        long result = -1;
        if(this.c.moveToNext()) {
            result = this.c.getInt(0);
        }
        this.resetCursor();
        this.db.close();
        return result;
    }

    public int deleteLastAddedExercise(String day, long number){
        this.db = this.getWritableDatabase();
        int result = this.db.delete("plan","dzien=? and numer_cw_w_planie=?",new String[]{day,""+number});
        this.db.close();
        return result;
    }

    private void resetList(){
        this.list.clear();
    }

    private void resetValues(){
        this.values.clear();
    }

    private void resetCursor(){
        this.c.close();
    }

    private String[] toStringArray(Object[] tab){
        String stringTab[] = new String[tab.length];
        for(int i=0;i<stringTab.length;i++){
            stringTab[i]=tab[i].toString();
        }
        return stringTab;
    }
}
