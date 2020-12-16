package Presentation;




@BaseEntity
public class GameEntity {
    @Id long id;
    ..
    //Attributes, methods...

}


@Entity
public class Perso extends GameEntity implements SelectCharPage.PersoInfo {

    int sexe; // 0M 1F
    ..
    //Methods
}