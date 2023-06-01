import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Classe des scénarios
 * @author Eliott-B, Chakib
 * @see Quest
 * @see java.util.TreeSet
 * @see java.util.ArrayList
 */
public class Scenario implements Cloneable {
    /**
     * TreeSet qui contient des objets de la classe Quest
     */
    private final TreeSet <Quest> quest;
    /**
     * Fichier du scénario
     */
    private File file;

    /**
     * Constructeur vide sans fichier instancié.
     */
    public Scenario() {
        quest = new TreeSet<>();
    }

    /**
     * Constructeur de la classe. Instancie le TreeSet sans objet.
     */
    public Scenario (File file){
        quest = new TreeSet<>();
        this.file = file;
    }

    /**
     * Constructeur de la classe. Instancie le TreeSet.
     * @param list ArrayList
     * @see java.util.ArrayList
     * @see Quest
     */
    public Scenario (ArrayList <Quest> list, File file){
        quest = new TreeSet<>(list);
        this.file = file;
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
     * Supprime la quête en paramètre du scénario
     *
     * @param quest La quête à supprimer
     */
    public void removeQuestbyId(int quest) {
        if(getQuestbyId(quest) != null){
            this.quest.remove(getQuestbyId(quest));
        }
    }

    /**
     * Retourne la quête en fonction de son id
     *
     * @param quest L'id de la quête
     * @return La quête
     */
    public Quest getQuestbyId(int quest) {
        for (Quest q : this.quest){
            if (q.getId() == quest){
                return q;
            }
        }
        return null;
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

    /**
     * Clone le scenario
     * @return Scenario
     */
    @Override
    public Scenario clone() {
        try {
            return Parsing.parsing(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
