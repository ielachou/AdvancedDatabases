package Model.Game.Interactions;

import Model.Game.Monster;
import Model.Game.Perso;

import java.util.ArrayList;
import java.util.Collections;

public class SQLFight extends Fight {
    ArrayList<Perso> persos;
    Monster monster;

    public SQLFight() {
        super();
        this.persos = new ArrayList<>();
    }

    public SQLFight(long id) {
        super(id);
        this.persos = new ArrayList<>();
    }

    public SQLFight(Monster boss, Perso... persos) {
        super();
        this.persos = new ArrayList<>();
        setMonster(boss);
        addPersos(persos);
    }

    @Override
    public void setMonster(Monster boss) {
        monster = boss;
    }

    @Override
    public void addPersos(Perso... persos) {
        Collections.addAll(this.persos, persos);
    }

    @Override
    public Monster getMonster() {
        return monster;
    }

    public Iterable<? extends Perso> getPersos() {
        return persos;
    }
}
