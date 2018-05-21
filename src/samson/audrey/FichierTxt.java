package samson.audrey;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;

public class FichierTxt {

	public static void write(String message){
		 final String chemin = "ai_proof.csv";
	     final File fichier =new File(chemin); 
	        try {
	            // Creation du fichier
	            fichier .createNewFile();
	            // creation d'un writer (un écrivain)
	            final FileWriter writer = new FileWriter(fichier);
	            try {
	                writer.write(message);
	                writer.write("\n");
	            } finally {
	                // quoiqu'il arrive, on ferme le fichier
	                writer.close();
	            }
	        } catch (Exception e) {
	            System.out.println("Impossible de creer le fichier");
	        }
	}
}
