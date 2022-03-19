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
	
	/** Declare une couleur grace a son nom et a son prix
	 * @param nom Le nom de la couleur
	 * @param prix Le prix de la couleur
	 */
	public Couleur(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
	}

	/** Renvoie le nom de la couleur
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}

	/** Change le nom de la couleur par celui en parametre
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Renvoie le prix de la couleur
	 * @return le prix
	 */
	public double getPrix() {
		return prix;
	}

	/** Change le prix de la couleur par celui en parametre
	 * @param prix le nouveau prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	/** Convertit la couleur en chaine de caractere
	 * C'est-a-dire son nom puis son prix en euros pour une unite de superficie
	 */
	@Override
	public String toString() {
		return "Couleur [ " + getNom() + ", Prix= " + getPrix() + "euros]";
	}

	/** Compare deux couleurs en fonction de leur prix par unite de superficie
	 * Renvoie -1 si le prix de la couleur c est inferieur au prix de cette couleur
	 * Renvoie 1 si le prix de c est superieur au prix de cette couleur
	 * Renvoie 0 si les deux prix sont identiques
	 */
	@Override
	public int compareTo(Couleur c) {
		if(this.getPrix() > c.getPrix())
			return 1;
		if(this.getPrix() < c.getPrix())
			return -1;
		return 0;
	}
}
