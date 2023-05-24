import java.util.Iterator;

/**
 * Class de la carte sur laquelle évolue le joueur
 * @author chak, Eliott-B
 * @see Quest
 * @see Scenario
 * @see java.util.TreeSet
 */
public class Map {
    Object[][] map;

    /**
     * Constructeur de la classe Map
     * @param scenario Scenario
     * @see Scenario
     */
    public Map(Scenario scenario){
        Iterator<Quest> iterator = scenario.getQuest().iterator();
        int maxX = 0;
        int maxY = 0;
        while(iterator.hasNext()) {
            Quest quest = iterator.next();
            if (quest.getPosition()[0] > maxX) {
                maxX = quest.getPosition()[0];
            }
            if (quest.getPosition()[1] > maxY) {
                maxY = quest.getPosition()[1];
            }
        }
        map = new Object[maxX+1][maxY+1];
        iterator = scenario.getQuest().iterator();
        while(iterator.hasNext()) {
            Quest quest = iterator.next();
            map[quest.getPosition()[0]][quest.getPosition()[1]] = quest;
        }
    }

    /**
     * Retourne la map de l'objet
     * @return Object[][]
     */
    public Object[][] getMap(){
        return map;
    }

    /**
     * Affichage de la carte dans le terminal, cases séparées par des virgules, les quêtes sont affichés par leurs iD
     * @return String
     */
    public String toString() {
        StringBuilder tostring = new StringBuilder();
        for (Object[] objects : map) {
            for (int j = 0; j < objects.length - 1; j++) {
                if (objects[j] == null) {
                    tostring.append("x,");
                } else {
                    tostring.append(((Quest) objects[j]).getId()).append(",");
                }
            }
            if (objects[objects.length - 1] == null) {
                tostring.append("x\n");
            } else {
                tostring.append(((Quest) objects[objects.length - 1]).getId()).append("\n");
            }
        }
        return tostring.toString();
    }
}
