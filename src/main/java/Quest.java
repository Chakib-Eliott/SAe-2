import java.util.Arrays;

/**
 * Class des quêtes
 * @author Eliott-B, Chakib
 */
public class Quest implements Comparable<Quest>{
    /**
     * Identifiant de la quête
     */
    private final int id;
    /**
     * Position de la quête
     */
     private final Integer[] position;
    /**
     * Précondition de la quest
     */
    private final Integer[][] precondition;
    /**
     * Durée de la quête
     */
     private final Integer duration;
     /**
     * Expérience gagnée à la fin de la quête
     */
    private final Integer xp;
     /**
     * Intitulé de la quête
     */
    private final String title;

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

    /**
     * Compare des quêtes (Quest)
     * Retourne un entier positif si l'id de This est plus petit que celui de la quête en paramètre.
     * Retourne 0 si elles sont en même temps
     * @param quest the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(Quest quest) {
        return this.id - quest.id;
    }

    /**
     * Retourne l'ID de l'objet
     * @return int
     */
    public int getId(){
        return id;
    }

    /**
     * Retourne la position de l'objet
     * @return Integer[]
     */
    public Integer[] getPosition(){
        return position;
    }

    /**
     * Retourne la précondition de l'objet
     * @return Integer [][]
     */
    public Integer[][] getPrecondition(){
        return precondition;
    }

    /**
     * Retourne la durée de l'objet
     * @return int
     */
    public Integer getDuration(){
        return duration;
    }

    /**
     * Retourne l'expérience de l'objet
     * @return int
     */
    public Integer getXp(){
        return xp;
    }

    /**
     * Retourne le titre de l'objet
     * @return string
     */
    public String getTitle(){
        return title;
    }

    /**
     * Renvoie un string contenant les informations de la quête sous la forme
     * id position precondition duration xp title
     * @return String
     */
    public String toString(){
        return id + " | (" +
                position[0] + "," + position[1] + ") | " +
                Arrays.deepToString(precondition) + " | " +
                duration + " | " +
                xp + "xp |" +
                title;
    }

    /**
     * Renvoie la position de la quête
     * @return Integer[]
     * @see Integer
     */
    public Integer[] getPosition() {
        return position;
    }

    /**
     * Renvoie l'identifiant de la quête
     * @return int
     */
    public int getId() {
        return id;
    }
}
