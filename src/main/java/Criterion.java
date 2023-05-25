import java.util.TreeMap;

/**
 * Classe des critères
 * @author Eliott-B
 * @see Solution
 */
public class Criterion {
    /**
     * Map des solutions
     * (Solution, (Meilleur/Pire, nombre de fois))
     */
    private TreeMap <String, TreeMap <Boolean, Integer>> solutions;

    /**
     * Constructeur de la classe.
     * Instancie le TreeMap
     */
    public Criterion(){
        solutions = new TreeMap<>();
    }

    /**
     * Ajoute un critère au TreeMap.
     * (solution, meilleur ou pire, nombre de fois)
     * @param solution String
     * @param type boolean
     * @param number int
     */
    public void addCriterion(String solution, boolean type, int number){
        // Vérifie si la solution n'existe pas déjà
        if(solutions.get(solution) == null){
            TreeMap <Boolean,Integer> map = new TreeMap<>();
            map.put(type, number);
            solutions.put(solution, map);
        }else{
            TreeMap<Boolean, Integer> object = solutions.get(solution);
            // Vérifie si le type de cette solution n'a pas déjà été défini
            if(object.get(type) == null){
                object.put(type, number);
            }else{
                object.replace(type, object.get(type), number);
            }
        }
    }

    /**
     * Affiche l'objet dans le terminal
     * @return String
     */
    public String toString(){
        return solutions.toString();
    }
}
