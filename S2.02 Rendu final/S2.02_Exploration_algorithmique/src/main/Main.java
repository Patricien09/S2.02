/** main/Main.java */
package main;

import java.util.ArrayList;
import java.util.Collections;

import graphe.*;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  

/**
 * L'application
 */
public class Main {
	private final static String TIRET = " -------------------------------------- ";
	private final static String SLASH = "//";

	public static void main(String[] args) {
		//--------- Mettre le nom du fichier csv apres la commande "java MainFinal" --------//
		//----------------- Exemple "java main/MainFinal main/France.csv" -----------------//
		//---------------------------------- Les Sommets ---------------------------------//
		String line = "";
        String splitBy = ",";
        
        if(args.length == 0)
        {
            System.out.println("Usage: java main.MainFinal fichier.csv");
            System.out.println("Exemple: java main.MainFinal ./src/test/France.csv");
            System.exit(0);
        }

        ArrayList<Sommet> somm = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            while ((line = br.readLine())!= null){
                String[] read = line.split(splitBy);
                Sommet tmp = new Sommet(read[1], Double.parseDouble(read[2]));
                somm.add(tmp);
            }
            br.close();
        } catch (IOException e) {
        	System.out.println("\nLe fichier " + "'" + args[0] +"'" +" n'est pas valide!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            while ((line = br.readLine())!= null){
                String[] read = line.split(splitBy);
                if (read.length > 3){
                    for (int i = 3; i<read.length; i++){
                        somm.get(Integer.parseInt(read[0])-1).addLienVoisin(somm.get(Integer.parseInt(read[i])-1));
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        System.out.println("\n");
        System.out.println(SLASH+TIRET+"Les Sommets"+TIRET+SLASH);
        for (Sommet sommet : somm) {
        	System.out.println(sommet);
		}
		
		//---------------------------------- Les Couleurs ---------------------------------//
        //theoreme des quatre couleurs
		Couleur c1 = new Couleur("Rouge", 10);
		Couleur c2 = new Couleur("Orange", 5);
		Couleur c3 = new Couleur("Bleu", 2);
		Couleur c4 = new Couleur("Vert", 1);
		
		System.out.println("\n");
		System.out.println(SLASH+TIRET+"Les Couleurs"+TIRET+SLASH);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		
		System.out.println("\n");
		
		//---------------------------------- Graphe ---------------------------------//
		
		ArrayList<Couleur> couleurs = new ArrayList<>();
		couleurs.add(c1);
		couleurs.add(c2);
		couleurs.add(c3);
		couleurs.add(c4);
		Graphe graphe = new Graphe(somm, couleurs);
		
		System.out.println(SLASH+TIRET+"Graphe"+TIRET+SLASH);
		System.out.println(TIRET+"Graphe non trie"+TIRET);
		System.out.println(graphe);
		
		System.out.println(TIRET+"Graphe trie par degre"+TIRET);
		Collections.sort(somm);
		System.out.println(graphe);
		
		System.out.println(TIRET+"Coloration - Welsh Powell"+TIRET);
		graphe.algoWelshPowell();
	}

}

