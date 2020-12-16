package Presentation;



/*
selectFight("SELECT  fp.* , " +
            "persos.ID as PID,persos.pseudo as pseudo,persos.sexe as sexe" +
            ",persos.dommages as Pdommages,persos.agilite as Pagilite," +
            "persos.intelligence as Pintelligence,persos.chance as Pchance," +
            "persos.force as Pforce,persos.vitality as Pvitality" +
            ",persos.energy as Penergy,persos.y as Py,persos.x as Px"+
            ",monsters.ID as MID,monsters.name as name,monsters.rank as rank" +
            ",monsters.dommages as Mdommages,monsters.agilite as Magilite," +
            "monsters.intelligence as Mintelligence,monsters.chance as Mchance," +
            "monsters.force as Mforce,monsters.vitality as Mvitality" +
            ",monsters.energy as Menergy,monsters.y as My,monsters.x as Mx  " +
                " FROM " +
            "fightPerso as fp " +
            "INNER JOIN fightMonster as fightMonster ON fightMonster.FIGHT_ID = fp.FIGHT_ID "+
            "INNER JOIN persos as persos ON persos.ID = fp.PERSO_ID " +
            "INNER JOIN monsters as monsters ON fp.FIGHT_ID = monsters.ID " +
            "WHERE EXISTS( " +
                    "SELECT * " +
                        "FROM fightPerso as fp2 " +
                            "WHERE fp2.PERSO_ID = ? AND fp.FIGHT_ID == fp2.FIGHT_ID " +
                    ") " +
            "ORDER BY fp.FIGHT_ID"),
 */





public ArrayList<Fight> getFights(String perso) {
        Box<Fight> box = store.boxFor(Fight.class);

        QueryBuilder<Fight> qb = box.query();
        qb.link(Fight_.PersoList).equal(Perso_.name, perso);
        List<Fight> fightList = qb.build().find();
        return (ArrayList<Fight>) fightList;
        }