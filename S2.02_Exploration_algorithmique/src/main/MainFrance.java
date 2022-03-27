package main;

import java.util.ArrayList;
import java.util.Collections;

import graphe.*;


public class MainFrance {
	private final static String TIRET = " -------------------------------------- ";
	private final static String SLASH = "//";

	public static void main(String[] args) {
		//---------------------------------- Les Sommets ---------------------------------//
		Sommet s10 = new Sommet("Haut-de-France", 31800);
		Sommet s11 = new Sommet("Normandie", 29900);
		Sommet s5 = new Sommet("Bretagne", 34000);
		Sommet s12 = new Sommet("Pays de la Loire", 32000);
		Sommet s2 = new Sommet("Nouvelle-Aquitaine", 84000);
		Sommet s9 = new Sommet("Occitanie", 72700);
		Sommet s13 = new Sommet("PACA", 31400);
		Sommet s7 = new Sommet("Corse", 8680);
		Sommet s3 = new Sommet("Auvergne-Rhone-Alpes", 69700);
		Sommet s6 = new Sommet("Centre-Val de Loire", 39100);
		Sommet s4 = new Sommet("Bourgogne-Franche-Comte", 47700);
        Sommet s1 = new Sommet("Grand-Est", 57400);
        Sommet s8 = new Sommet("Ile-de-France", 12000);
		
		s1.addLienVoisin(s4);
		s1.addLienVoisin(s8);
		s1.addLienVoisin(s10);
		s10.addLienVoisin(s8);
		s10.addLienVoisin(s11);
		s11.addLienVoisin(s8);
		s11.addLienVoisin(s6);
		s11.addLienVoisin(s12);
		s11.addLienVoisin(s5);
		s5.addLienVoisin(s12);
		s12.addLienVoisin(s6);
		s12.addLienVoisin(s2);
		s2.addLienVoisin(s6);
		s2.addLienVoisin(s3);
		s2.addLienVoisin(s9);
		s9.addLienVoisin(s3);
		s9.addLienVoisin(s13);
		s13.addLienVoisin(s7);
		s13.addLienVoisin(s3);
		s3.addLienVoisin(s4);
		s3.addLienVoisin(s6);
		s4.addLienVoisin(s6);
		s4.addLienVoisin(s8);
		s6.addLienVoisin(s8);
		
		// System.out.println(SLASH+TIRET+"Les Sommets"+TIRET+SLASH);
		// System.out.println(s1);
		// System.out.println(s2);
		// System.out.println(s3);
		// System.out.println(s4);
		// System.out.println(s5);
		
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
		sommets.add(s12);
		sommets.add(s13);
		
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

