/** graphe/Sommet.java */

package graphe;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Definition d'un sommet de graphe
 */

public class Sommet implements Comparable<Sommet>{
	/** Un sommet caracterise par une chaine de caractere	 */
	private String nom;
	/** La superficie du sommet	 */
	private double superficie;
	/** Les voisins du sommet */
	private ArrayList<Sommet> voisin = new ArrayList<>();
	
	public Sommet(String nom, double superficie) {
		this.nom = nom;
		this.superficie = superficie;
	}
	/**
	 * 
	 * @param nom
	 * @param superfie 
	 * @param voisin 
	 */
	protected Sommet(String nom, double superficie, ArrayList<Sommet> voisin) {
		this(nom, superficie);
		this.voisin = voisin;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the superficie
	 */
	public double getSuperficie() {
		return superficie;
	}


	/**
	 * @param superficie the superficie to set
	 */
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}


	/**
	 * 
	 * @return the voisin
	 */
	public ArrayList<Sommet> getVoisin() {
		return voisin;
	}


	/**
	 * @param voisin the voisin to set
	 */
	public void setVoisin(ArrayList<Sommet> voisin) {
		this.voisin = voisin;
	}
	/**
	 * Retourne le degre du sommet
	 * @return int Le degre du sommet
	 */
	public int degre() {
		return this.getVoisin().size();
	}
	
	/**
	 * Ajoute un lien (arete) entre le voisin
	 * @param voisin Le voisin de ce sommet
	 */
	public void addLienVoisin(Sommet v) { //Exception arete deja là doit être traité
		this.voisin.add(v);
		v.getVoisin().add(this);
	}
	
	public void addLienVoisin(ArrayList<Sommet> v) { //Exception arete deja là doit être traité
		Iterator<Sommet> it = v.iterator();
		while (it.hasNext()) {
			addLienVoisin(it.next());		
		}
	}

	@Override
	public String toString() {
		String voisins = "";
		Iterator<Sommet> it = voisin.iterator();
		while(it.hasNext()) {
			voisins = voisins + " " + it.next().getNom();
		}
		return "Sommet [" + getNom() + ", Superficie=" + getSuperficie() + ", Voisins=" + voisins
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Sommet)) return false;
		return (((Sommet) obj).getNom().equals(nom)) && ((Sommet) obj).getVoisin().equals(voisin);
	}
	@Override
	public int compareTo(Sommet s) {
		// TODO Auto-generated method stub
		return this.degre() < s.degre() ? 1 : this.degre() == s.degre() ? 0 : -1;
	}
	
	
	
	
	

	
}