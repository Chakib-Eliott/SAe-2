package model;
import java.util.HashMap;

/**
 * Interface des constantes pour les solutions.
 * @author Eliott B
 */
public interface SolutionConstants {
    /**
     * Tableau des noms des solutions
     */
    String [] SOLUTIONS_NAME = {"GLOUTONNE", "SPEED RUN"};

    /**
     * Dictionnaire des types de solution
     */
    HashMap<String, Object[]> SOLUTIONS_TYPE = new HashMap<>() {{
        put("Gloutonne efficace", new Object[]{SOLUTIONS_NAME[0], 0}); // Gloutonne efficace
        put("Gloutonne 100%", new Object[]{SOLUTIONS_NAME[0], 1}); // Gloutonne 100%
        put("Speedrun efficace", new Object[]{SOLUTIONS_NAME[1], 0}); // Speedrun efficace
        put("Speedrun 100%", new Object[]{SOLUTIONS_NAME[1], 1}); // Speedrun 100%
        put("Speedrun déplacements minimaux", new Object[]{SOLUTIONS_NAME[1], 2}); // Speedrun déplacements minimaux
    }};
}
