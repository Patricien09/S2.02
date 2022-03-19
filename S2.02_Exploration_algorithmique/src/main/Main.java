/** main/Main.java */

package main;

import java.util.ArrayList;
import java.util.Collections;

import graphe.*;


public class Main {
	private final static String TIRET = " -------------------------------------- ";
	private final static String SLASH = "//";
	
	/**
	 * Exemple:
	 * (Sommet) (Voisins 1) (Voisins 2) (Voisins 3)
	 * 1 2 3 4
	 * 2 1 3
	 * 3 1 2 5
	 * 4 1
	 * 5 3
	 */
	public static void main(String[] args) {
		//---------------------------------- Les Sommets ---------------------------------//
		Sommet s1 = new Sommet("1", 1);
		Sommet s2 = new Sommet("2", 5);
		Sommet s3 = new Sommet("3", 7);
		Sommet s4 = new Sommet("4", 9);
		Sommet s5 = new Sommet("5", 20);
		
		s1.addLienVoisin(s2);
		s1.addLienVoisin(s3);
		s1.addLienVoisin(s4);
		s2.addLienVoisin(s3);
		s3.addLienVoisin(s5);
		
		System.out.println(SLASH+TIRET+"Les Sommets"+TIRET+SLASH);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		
		System.out.println("\n");
		
		//---------------------------------- Les Couleurs ---------------------------------//
		Couleur c1 = new Couleur("Rouge", 10);
		Couleur c2 = new Couleur("Orange", 5);
		Couleur c3 = new Couleur("Bleu", 2);
		Couleur c4 = new Couleur("Vert", 1);
		
		System.out.println(SLASH+TIRET+"Les Couleurs"+TIRET+SLASH);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		
		System.out.println("\n");
		
		//---------------------------------- Graphe ---------------------------------//
		ArrayList<Sommet> sommets = new ArrayList<>();
		sommets.add(s1);
		sommets.add(s2);
		sommets.add(s3);
		sommets.add(s4);
		sommets.add(s5);
		
		ArrayList<Couleur> couleurs = new ArrayList<>();
		couleurs.add(c1);
		couleurs.add(c2);
		couleurs.add(c3);
		couleurs.add(c4);
		Graphe graphe = new Graphe(sommets, couleurs);
		
		System.out.println(SLASH+TIRET+"Graphe"+TIRET+SLASH);
		System.out.println("Graphe non trie");
		System.out.println(graphe);
		
		System.out.println("Graphe trie");
		Collections.sort(sommets);
		System.out.println(graphe);
		
		// 1 et 5 : Vert , 2 : Orange , 3 et 4 : Bleu
		graphe.algoWelshPowell();
		
		//just testing git commit on eclipse
	}

}
