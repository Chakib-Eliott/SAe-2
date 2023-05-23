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
    private final TreeSet <Quest> quest;

    /**
     * Constructeur de la classe. Instancie le TreeSet sans objet.
     */
    public Scenario (){
        quest = new TreeSet<>();
    }

    /**
     * Constructeur de la classe. Instancie le TreeSet.
     * @param list ArrayList
     * @see java.util.ArrayList
     * @see Quest
     */
    public Scenario (ArrayList <Quest> list){
        quest = new TreeSet<>(list);
    }

    /**
     * Ajoute une quête donné en paramètre dans le TreeSet.
     * @param quest Quest
     * @see Quest
     */
    public void addQuest (Quest quest){
        this.quest.add(quest);
    }

    /**
     * Ajoute les quêtes données dans la ArrayList dans le TreeSet.
     * @param quest ArrayList <Quest>
     * @see Quest
     * @see java.util.ArrayList
     */
    public void addQuest (ArrayList <Quest> quest){
        this.quest.addAll(quest);
    }

    /**
     * Retourne le TreeSet de quête.
     * @return TreeSet<Quest>
     */
    public TreeSet<Quest> getQuest(){
        return quest;
    }

    /**
     * Affichage des scenarios dans le terminal.
     * Utilise StringBuilder pour avoir un affichage clair dans le terminal
     * @return String
     */
    public String toString (){
        StringBuilder tostring = new StringBuilder(quest.size() + "\n");
        for (Quest q : quest){
            tostring.append(q.toString()).append("\n");
        }
        return tostring.toString();
    }
}
