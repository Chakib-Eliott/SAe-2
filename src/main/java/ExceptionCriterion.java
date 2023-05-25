/**
 * Exception des critères
 * @author Eliott B
 * @see Exception
 * @see ErrorConstants
 */
public class ExceptionCriterion extends Exception implements ErrorConstants {

    /**
     * Constructeur de la classe
     * @param error int
     */
    public ExceptionCriterion(int error){
        super(CRITERION_ERROR[error]);
    }
}
