package model;

/**
 * Classe des erreurs des solutions
 * @author Eliott-B
 * @see Exception
 * @see ErrorConstants
 */
public class ExceptionSolution extends Exception implements ErrorConstants {

    /**
     * Constructeur exception
     * Code 0 -> La solution n'existe pas
     * Code 1 -> Le type n'existe pas pour la solution
     * @param errorCode int
     */
    public ExceptionSolution(int errorCode){
        super(ERROR_SOLUTION[errorCode]);
    }
}
