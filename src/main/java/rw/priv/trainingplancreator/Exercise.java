package rw.priv.trainingplancreator;

/**
 * Created by Radek on 12.03.2017.
 */

public class Exercise {

    private int id;
    private String name;
    private String muscles;
    private int series;
    private int repeat;
    private double weight;
    private double increment;
    private String description;
    private int number;

    public Exercise(){
        this.resetExercise();
    }

    public void resetExercise(){
        this.id=0;
        this.name="";
        this.muscles="";
        this.series=0;
        this.repeat=0;
        this.weight=0;
        this.increment=-1;
        this.description="";
        this.number=-1;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setMuscles(String muscles){
        this.muscles=muscles;
    }

    public void setSeries(int series){
        this.series=series;
    }

    public void setRepeat(int repeat){
        this.repeat=repeat;
    }

    public void setWeight(double weight){
        this.weight=weight;
    }

    public void setIncrement(double increment){
        this.increment=increment;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setNumber(int number){this.number=number;}

    public int getId(){return this.id;}

    public String getName(){
        return this.name;
    }

    public String getMuscles(){
        return this.muscles;
    }

    public int getSeries(){
        return this.series;
    }

    public int getRepeat(){
        return this.repeat;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getIncrement(){
        return this.increment;
    }

    public String getDescription(){
        return this.description;
    }

    public int getNumber(){return this.number; }

    @Override
    public String toString() {
        return "numer:"+this.number+" partia:"+this.muscles+" nazwa:"+this.name+" serie:"+this.series+" ciezar:"+this.weight;
    }
}
