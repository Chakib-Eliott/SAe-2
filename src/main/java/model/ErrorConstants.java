package model;

/**
 * Constantes des erreurs
 */
public interface ErrorConstants {
    /**
     * Erreur des solutions :
     *      0 -> La solution n'existe pas
     *      1 -> Le type n'existe pas pour cette solution
     */
    String[] ERROR_SOLUTION = {
            "ERROR -> The solution doesn't exist !",
            "ERROR -> The type doesn't exist for this solution !"
    };
}
