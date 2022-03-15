/** graphe/Couleur.java */

package graphe;

/**
 * Definition d'un couleur
 */
public class Couleur implements Comparable<Couleur>{
	/** Nom du couleur */
	private String nom;
	/** Le prix du couleur */
	private double prix;
	
	/**
	 * @param nom Le nom du couleur
	 * @param prix Le prix du couleur
	 */
	public Couleur(String nom, double prix) {
		super();
		this.nom = nom;
		this.prix = prix;
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
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Couleur [ " + getNom() + ", Prix= " + getPrix() + "€]";
	}

	@Override
	public int compareTo(Couleur c) {
		if(this.getPrix() > c.getPrix())
			return 1;
		if(this.getPrix() < c.getPrix())
			return -1;
		return 0;
	}
}
