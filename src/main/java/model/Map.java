package model;
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
        // Définition de la taille de la map
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
        // Ajout des quêtes sur la map
        iterator = scenario.getQuest().iterator();
        while(iterator.hasNext()) {
            Quest quest = iterator.next();
            map[quest.getPosition()[0]][quest.getPosition()[1]] = quest;
        }
    }

    /**
     * Retourne la distance (arrondie à l'entier supérieur) entre deux positions
     * @param position1 Integer[]
     * @param position2 Integer[]
     * @see Math
     * @return int
     */
    public static int distance(Integer[] position1, Integer[] position2){
        return Math.max(position1[0], position2[0])-Math.min(position1[0], position2[0])+Math.max(position1[1], position2[1])-Math.min(position1[1], position2[1]);
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
