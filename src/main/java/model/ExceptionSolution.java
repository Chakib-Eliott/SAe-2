package model;

/**
 * Classe des erreurs des solutions
 * @author Eliott-B
 * @see Exception
 * @see ErrorConstants
 */
public class ExceptionSolution extends Exception implements ErrorConstants {

    public ExceptionSolution(int errorCode){
        super(ERROR_SOLUTION[errorCode]);
    }
}
