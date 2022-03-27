/** graphe/Couleur.java */

package graphe;

/**
 * Definition d'une couleur
 */
public class Couleur implements Comparable<Couleur>{
	/** Nom de la couleur */
	private String nom;
	/** Le prix de la couleur */
	private double prix;
	
	/** Declare une couleur grace a son nom et a son prix
	 * @param nom Le nom de la couleur
	 * @param prix Le prix de la couleur
	 */
	public Couleur(String nom, double prix) {
		if(nom == null || nom.trim().isEmpty()) { 
			throw new IllegalArgumentException("Le nom de la couleur ne peut pas être vide");
		}
		if (prix < 0) {
			throw new IllegalArgumentException("Le prix de la couleur ne peut pas être négative");
		}
		this.nom = nom;
		this.prix = prix;
	}
	/**
	 * Constructeur par recopie
	 * @param couleur L'objet couleur a copier
	 */
	public Couleur(Couleur couleur) {
		this(couleur.getNom(),couleur.getPrix());
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
		if(nom == null || nom.trim().isEmpty()) { 
			throw new IllegalArgumentException("Le nom de la couleur ne peut pas être vide");
		}
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
		if (prix < 0) {
			throw new IllegalArgumentException("Le prix de la couleur ne peut pas être négative");
		}
		this.prix = prix;
	}
	
	
	/** Convertit la couleur en chaine de caractere
	 * C'est-a-dire son nom puis son prix en euros pour une unite de superficie
	 */
	@Override
	public String toString() {
		return "Couleur [ " + getNom() + ", Prix= " + getPrix() + " euros]";
	}

	/** Compare deux couleurs en fonction de leur prix par unite de superficie
	 * Renvoie -1 si le prix de la couleur c est inferieur au prix de cette couleur
	 * Renvoie 1 si le prix de c est superieur au prix de cette couleur
	 * Renvoie 0 si les deux prix sont identiques
	 */
	@Override
	public int compareTo(Couleur c) {
		return this.getPrix() > c.getPrix() ? 1 : this.getPrix() < c.getPrix() ? -1 : 0;
	}
}
