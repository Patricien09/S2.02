/** main/Main.java */
/** Le site ou y a le graphe :
 * https://fr.acervolima.com/algorithme-de-coloration-graphique-de-welsh-powell/
 */


package main;

import java.util.ArrayList;
import java.util.Collections;

import graphe.*;

/**
 * 
 * @author Yifru Abenezer
 */
public class Main {
	private final static String TIRET = " -------------------------------------- ";
	private final static String SLASH = "//";

	public static void main(String[] args) {
		//---------------------------------- Les Sommets ---------------------------------//
		Sommet s1 = new Sommet("G", 1);
		Sommet s2 = new Sommet("H", 5);
		Sommet s3 = new Sommet("A", 7);
		Sommet s4 = new Sommet("B", 9);
		Sommet s5 = new Sommet("D", 20);
		Sommet s6 = new Sommet("C", 20);
		Sommet s7 = new Sommet("I", 20);
		Sommet s8 = new Sommet("J", 20);
		Sommet s9 = new Sommet("K", 20);
		Sommet s10 = new Sommet("E", 20);
		Sommet s11 = new Sommet("F", 20);
		
		s1.addLienVoisin(s2);
		s1.addLienVoisin(s11);
		s1.addLienVoisin(s9);
		s2.addLienVoisin(s3);
		s2.addLienVoisin(s7);
		s2.addLienVoisin(s8);
		s2.addLienVoisin(s9);
		s3.addLienVoisin(s4);
		s4.addLienVoisin(s5);
		s5.addLienVoisin(s6);
		s5.addLienVoisin(s7);
		s5.addLienVoisin(s9);
		s7.addLienVoisin(s8);
		s8.addLienVoisin(s9);
		s9.addLienVoisin(s10);
		s10.addLienVoisin(s11);

		
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
		sommets.add(s6);
		sommets.add(s7);
		sommets.add(s8);
		sommets.add(s9);
		sommets.add(s10);
		sommets.add(s11);
		
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
	}

}
