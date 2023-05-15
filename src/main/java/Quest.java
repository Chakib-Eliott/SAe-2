import java.util.ArrayList;
import java.util.List;

/**
 * Class des quêtes
 */
public class Quest {
    private int id;
    private Integer[] position;
    private Integer[][] precondition;
    private Integer duration;
    private Integer xp;
    private String title;

    /**
     * Constructeur de la classe
     * @param id            Identifiant de la quête
     * @param position      Position de la quête
     * @param precondition  Prérequis de la quête
     * @param duration      Durée de la quête
     * @param xp            Expérience gagnée à la fin de la quête
     * @param title         Titre de la quête
     */
    Quest(int id, Integer[] position, Integer[][] precondition, Integer duration, Integer xp, String title){
        this.id = id;
        this.position = position;
        this.precondition = precondition;
        this.duration = duration;
        this.xp = xp;
        this.title = title;
    }

}
