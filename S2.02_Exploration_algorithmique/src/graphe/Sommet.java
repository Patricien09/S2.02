/** graphe/Sommet.java */

package graphe;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Definition d'un sommet de graphe
 */

public class Sommet implements Comparable<Sommet>{
	/** Un sommet, caracterise par une chaine de caractere	 */
	private String nom;
	/** La superficie du sommet	 */
	private Double superficie;
	/** Les voisins du sommet */
	private ArrayList<Sommet> voisin;
	/** La couleur du sommet: valeur non assigne par defaut */
	private Couleur couleur;


	/** Declare un sommet grace a un nom et une superficie
	 * @param nom le nom
	 * @param superficie la superficie
	 */
	public Sommet(String nom, double superficie) {
		if(nom == null || nom.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom du sommet ne peut pas etre vide");
		}
		if (superficie < 0) {
			throw new IllegalArgumentException("La superficie ne peut pas etre negative");
		}
		this.nom = nom;
		this.superficie = Double.valueOf(superficie);
		this.voisin = new ArrayList<>();
	}

	/** Declare un Sommet grace a un nom, une superficie et une liste de voisin
	 * @param nom Le nom du sommet
	 * @param superficie La superficie du sommet
	 * @param voisin La liste des voisins
	 */
	protected Sommet(String nom, double superficie, ArrayList<Sommet> voisin) {
		this(nom, Double.valueOf(superficie));
		this.voisin = voisin;
	}


	/** Renvoie le nom du sommet
	 * @return le nom du sommet
	 */
	public String getNom() {
		return nom;
	}


	/** Change le nom du sommet par celui passe en parametre
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		if(nom == null || nom.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom du sommet ne peut pas etre vide");
		}
		this.nom = nom;
	}


	/** Renvoie la superficie du sommet
	 * @return la superficie du sommet
	 */
	public Double getSuperficie() {
		return superficie;
	}


	/** Change la superficie du sommet par celle passee en parametre
	 * @param superficie la nouvelle superficie
	 */
	public void setSuperficie(double superficie) {
		if (superficie < 0) {
			throw new IllegalArgumentException("La superficie ne peut pas etre negative");
		}
		this.superficie = Double.valueOf(superficie);
	}

	/** Renvoie la liste des voisins du sommet
	 * @return La liste des voisins
	 */
	public ArrayList<Sommet> getVoisin() {
		return voisin;
	}


	/** Change la liste des voisins du sommet par celle passee en parametre
	 * @param voisin La liste de voisin
	 */
	public void setVoisin(ArrayList<Sommet> voisin) {
		if (voisin == null) {
			return;
		}
		this.voisin = voisin;
	}

	/** Renvoie le degre du sommet
	 * @return le degre du sommet
	 */
	public int degre() {
		return this.getVoisin().size();
	}
	/**
	 * Renvoie la couleur du sommet
	 * @return la couleur du sommet
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	/**
	 * Change la couleur du sommet
	 * @param couleur La couleur
	 */
	public void setCouleur(Couleur couleur) {
		if (couleur == null) {
			throw new IllegalArgumentException("La couleur ne peut pas etre null");
		}
		this.couleur = couleur;
	}

	/** Ajoute un lien (arete) entre ce sommet et le sommet en parametre
	 * @param v le nouveau voisin du sommet
	 */
	public void addLienVoisin(Sommet v) {
		if(v == null) return;
		if(!this.voisin.contains(v)) {
			this.voisin.add(v);
		}
		if (!v.getVoisin().contains(v)) {
			v.getVoisin().add(this);
		}
	}

	/** Convertit le sommet en chaine de caractere
	 * @return Chaine de caractere au format Sommet [ nom, superficie, voisins]
	 */
	@Override
	public String toString() {
		String voisins = "";
		Iterator<Sommet> it = voisin.iterator();
		while(it.hasNext()) {
			voisins = voisins + " [" + it.next().getNom() + "] ";
		}
		return "Sommet [ " + getNom() + ", " + getSuperficie() + "," + voisins + "]";
	}

	/** Compare deux sommets en fonction de leur degre
	 * Renvoie 1 si le degre du sommet s est superieur au degre de ce sommet
	 * Renvoie -1 si le degre de s est inferieur
	 * Remarque: Ceci fait l'inverse du standard compareTo pour avoir un trie decroissant et NON croissant
	 * @param s Le sommet a comparer
	 */
	@Override
	public int compareTo(Sommet s) {
		return this.degre() < s.degre() ? 1 : this.degre() == s.degre() ? 0 : -1; 
	}


}