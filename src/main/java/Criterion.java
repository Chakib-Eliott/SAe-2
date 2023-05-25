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


}
