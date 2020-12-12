package Model.Game;


import View.CharView.SelectCharPage;
import io.objectbox.annotation.Entity;

@Entity
public class Perso extends GameEntity implements SelectCharPage.PersoInfo {


    int sexe; // 0M 1F


    public Perso(long id, String pseudo, int x, int y, int energy, int vitality,
                 int force, int chance, int intelligence, int agilite,
                 int dommages, int sexe) {
        super(id, pseudo, x, y, energy, vitality, force, chance, intelligence, agilite, dommages);
        this.sexe = sexe;
    }




    public Perso(String pseudo, int sexe) {
        super();
        this.sexe = sexe;
        this.name = pseudo;
        this.energy = 10000;
        this.x = 0;
        this.y = 0;
    }

    public Perso(){
        super();

    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getPseudo() {
        return name;
    }

    public void setPseudo(String pseudo) {
        this.name = pseudo;
    }



    @Override
    public String getPos() {
        return "(" + x + ", " + y + ")";
    }
}