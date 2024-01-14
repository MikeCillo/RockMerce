package LogicTier.Entità;

import java.util.ArrayList;

public class Cart {

    private int id;

    private double tempTotal=0.00;


    private int numGuitars=0;


    private ArrayList<Guitar> guitars=new ArrayList<>();


    public void addGuitar(Guitar guitar){
        this.guitars.add(guitar);
        this.tempTotal=tempTotal+guitar.getPrice();
        this.numGuitars+=guitar.getDisponibility();
    }



    public Guitar removeGuitar(int pos){
        if(pos>=0) {
            for (Guitar guitar : this.guitars) {
                if (guitar.getId() == pos) {
                    this.guitars.remove(guitar);
                    this.tempTotal = tempTotal - guitar.getPrice();
                    this.numGuitars -= guitar.getDisponibility();
                    return guitar;
                }
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTempTotal() {
        return tempTotal;
    }

    public void setTempTotal(double tempTotal) {
        this.tempTotal = tempTotal;
    }

    public int getNumGuitars() {
        return this.numGuitars;
    }

    public void setNumGuitars(int numItems) {
        this.numGuitars = numItems;
    }

    public ArrayList<Guitar> getGuitars() {
        return this.guitars;
    }

    public void setGuitars(ArrayList<Guitar> guitars) {
        this.guitars = guitars;
    }

}








