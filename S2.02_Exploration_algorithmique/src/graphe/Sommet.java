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
	private ArrayList<Sommet> voisin = new ArrayList<>();
	/** La couleur du sommet: valeur non assigne par defaut */
	private Couleur couleur;
	

	/** Declare un sommet grace a un nom et une superficie 
	 * @param nom le nom
	 * @param superficie la superficie
	 */
	public Sommet(String nom, double superficie) {
		this.nom = nom;
		this.superficie = (Double) superficie;
	}

	/** Declare un Sommet grace a un nom, une superficie et une liste de voisin
	 * @param nom le nom
	 * @param superfie la superficie
	 * @param voisin la liste des voisins
	 */
	protected Sommet(String nom, double superficie, ArrayList<Sommet> voisin) {
		this(nom, (Double) superficie);
		this.voisin = voisin;
	}


	/** Renvoie le nom du sommet
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}


	/** Change le nom du sommet par celui passe en parametre
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/** Renvoie la superficie du sommet
	 * @return la superficie
	 */
	public Double getSuperficie() {
		return superficie;
	}


	/** Change la superficie du sommet par celle passee en parametre
	 * @param superficie la nouvelle superficie
	 */
	public void setSuperficie(double superficie) {
		this.superficie = (Double) superficie;
	}


	/** Renvoie la liste des voisins du sommet
	 * @return la liste des voisins
	 */
	public ArrayList<Sommet> getVoisin() {
		return voisin;
	}


	/** Change la liste des voisins du sommet par celle passee en parametre
	 * @param voisin La liste de voisin
	 */
	public void setVoisin(ArrayList<Sommet> voisin) {
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
		this.couleur = couleur;
	}
	
	/** Ajoute un lien (arete) entre ce sommet et le sommet en parametre
	 * @param v le nouveau voisin du sommet
	 */
	public void addLienVoisin(Sommet v) { //Exception arete deja lï¿½ doit ï¿½tre traitï¿½
		if(!this.voisin.contains(v)) {
			this.voisin.add(v);			
		}
		if (!v.getVoisin().contains(v)) {
			v.getVoisin().add(this);
		}
	}
	
	/** Ajoute un lien (arete) entre ce sommet et chaque sommet de la liste en parametre
	 * @param v la liste des tous les nouveaux voisins du sommet
	 */
	public void addLienVoisin(ArrayList<Sommet> v) { //Exception arete deja la doit ïetre traite
		Iterator<Sommet> it = v.iterator();
		while (it.hasNext()) {
			addLienVoisin(it.next());		
		}
	}

	/** Convertit le sommet en chaine de caractere 
	 * C'est-a-dire d'abord son nom + sa superficie + ses voisins
	 */
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

	/** Teste l'egalite entre ce sommet et un autre objet
	 * Renvoie true si les objets sont les memes
	 * Renvoie false si les objets sont differents
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Sommet)) return false;
		return (((Sommet) obj).getNom().equals(nom)) && ((Sommet) obj).getVoisin().equals(voisin);
	}

	/** Compare deux sommets en fonction de leur degre
	 * Renvoie 1 si le degre du sommet s est superieur au degre de ce sommet
	 * Renvoie -1 si le degre de s est infÃ©rieur
	 * Renvoie 0 si les degres sont les memes 
	 */
	@Override
	public int compareTo(Sommet s) {
		return this.degre() < s.degre() ? 1 : this.degre() == s.degre() ? this.superficie.compareTo(s.getSuperficie()) : -1;
	}

	
}