package utils;

import model.Quest;
import model.Scenario;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe pour récupérer les données d'un fichier
 * @author Eliott-B, Chakib
 * @see Quest
 * @see Scenario
 * @see java.util.Scanner
 * @see java.io.File
 * @see java.io.FileNotFoundException
 */
public class Parsing {

    /**
     * Sépare les données du fichier donné en paramètre et créé des scénarios.
     * @param file File
     * @return Scenario
     * @see Scenario
     * @see java.util.Scanner
     * @see java.io.File
     * @see java.io.FileNotFoundException
     */
    public static Scenario parsing(File file) throws FileNotFoundException{
        Scenario scenario = new Scenario(file);
        Scanner scan = new Scanner(file);
        while(scan.hasNext()){
            // Ajout de la quête, formée dans la méthode questParsing, dans le scénario.
            scenario.addQuest(questParsing(scan.nextLine()));
        }
        return scenario;
    }

    /**
     * Récupère la quête depuis la ligne donnée en paramètre
     * @param line String
     * @return Quest
     * @see Quest
     */
    public static Quest questParsing(String line){
        String[] data = line.split("\\|");

        // ID
        int id = Integer.parseInt(data[0]);

        // POSITION
        data[1] = data[1].replace(" ", "");

        String[] coordsstring = data[1].replace("(", "").replace(")", "").split(",");
        Integer[] coords = {Integer.parseInt(coordsstring[0]), Integer.parseInt(coordsstring[1])};

        // PRECONDITION
        data[2] = data[2].replaceAll(" ","");
        Integer[][] precondition = new Integer[2][2];
        if(!Objects.equals(data[2], "()")) {
            String[] precond = data[2].split("\\),");
            String[] precondx = precond[0].replaceAll("\\(", "").split(",");
            String[] precondy = precond[1].replace("(", "").replaceAll("\\)", "").split(",");
            // Vérification de la taille de la première partie
            if (precondx.length == 2) {
                if (precondx[1].length() != 0) {
                    precondition[0][1] = Integer.parseInt(precondx[1]);
                }
            }
            if (precondx[0].length() != 0) {
                precondition[0][0] = Integer.parseInt(precondx[0]);
            }
            // Vérification de la taille de la deuxième partie
            if(Objects.equals(precondy.length, 2)) {
                if (precondy[1].length() != 0) {
                    precondition[1][1] = Integer.parseInt(precondy[1]);
                }
            }
            if (precondy[0].length() != 0) {
                precondition[1][0] = Integer.parseInt(precondy[0]);
            }
        }

        // DURATION
        int duration = Integer.parseInt(data[3]);

        // XP
        int xp = Integer.parseInt(data[4]);

        // TITLE
        String title = data[5];

        return new Quest(id, coords, precondition, duration, xp, title);
    }
}