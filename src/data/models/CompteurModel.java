package data.models;

import domain.entities.Compteur;

public class CompteurModel extends Compteur {
   private int valeur;

    public CompteurModel(int valeur) {
        super(123);
        this.valeur = valeur;
    }

    public int getId(){
        return id;
    }

    public int getValeur() {
        return valeur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

}
