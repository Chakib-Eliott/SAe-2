import java.io.File;

/**
 * Classe pour récupérer les données d'un fichier
 * @author Eliott-B, Chakib
 * @see Quest
 * @see Scenario
 * @see File
 */
public class Parsing {

     /**
      * Sépare les données du fichier donné en paramètre et créer des scénarios.
      * @param file File
      * @return Scenario
      */
     public static Scenario parsing(File file){
          return new Scenario();
     }
}
