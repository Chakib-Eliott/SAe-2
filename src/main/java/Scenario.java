import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Classe des scénarios
 * @author Eliott-B, Chakib
 * @see Quest
 * @see java.util.TreeSet
 * @see java.util.ArrayList
 */
public class Scenario {
    /**
     * TreeSet de qui contient des objets de la classe Quest
     */
    private TreeSet <Quest> quest;

    /**
     * Constructeur de la classe. Instancie le TreeSet.
     * @param list ArrayList
     * @see java.util.ArrayList
     */
    public Scenario (ArrayList <Quest> list){
        quest = new TreeSet<>(list);
    }
}
