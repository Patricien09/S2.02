/** graphe/Graphe.java */

package graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Definition d'un graphe
 */
public class Graphe {
	/**
	 * Liste de tous les sommets
	 */
	private ArrayList<Sommet> sommets;
	/**
	 * Liste de couleurs
	 */
	private ArrayList<Couleur> couleurs;
	
	/**
	 * @param sommets
	 * @param couleurs
	 */
	public Graphe(ArrayList<Sommet> sommets, ArrayList<Couleur> couleurs) {
		this.sommets = sommets;
		this.couleurs = couleurs;
	}
	/**
	 * Constructeur par copie
	 * @param graphe Le graphe a copier
	 */
	public Graphe(Graphe graphe) {
		this(graphe.getSommets(), graphe.getCouleurs());
	}
	
	/**
	 * @return the sommets
	 */
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	/**
	 * @param sommets the sommets to set
	 */
	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}

	/**
	 * @return the couleurs
	 */
	public ArrayList<Couleur> getCouleurs() {
		return couleurs;
	}

	/**
	 * @param couleurs the couleurs to set
	 */
	public void setCouleurs(ArrayList<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
	
	@Override
	public String toString() {
		String sommetString = "";
		for (Sommet s: sommets){
			String voisinString = " ";
			for (Sommet voisin : s.getVoisin()) {
				voisinString += voisin.getNom() + " ";	
			}
			sommetString += s.getNom() + ":" + voisinString + "\n";
		}
		return sommetString;
	}

	/**
	 * Associe a chaque somment une couleur
	 */
	public void algoWelshPowell() {
		Graphe graphe = new Graphe(this); //copie graphe avant trie
		Collections.sort(this.getSommets()); // sort selon l'ordre decroissant du degre
		Collections.sort(this.getCouleurs()); // trie couleur dans l'ordre decroissant du prix
		ArrayList<Sommet> L = graphe.getSommets();


		HashMap<Sommet, Couleur> couleur_sommet = new HashMap<Sommet, Couleur>(); //le nom du sommet comme cle et Couleur la valeur
		for (int i = 0; i < L.size(); i++) {
			if (couleur_sommet.containsKey(L.get(i))) {
				continue; //sommet déjà colorié
			}else {
				couleur_sommet.put(L.get(i), couleurs.get(i)); //On colorie le premier sommet non encore colorié
				for (int j = i+1; j < L.size(); j++){
					if (!(L.get(i).getVoisin().contains(L.get(j))) && !(couleur_sommet.containsKey(L.get(j)))){
						couleur_sommet.put(L.get(j), couleurs.get(i));
					}
					else{
						continue;
					}
				}
			}
		}
		//System.out.println(couleur_sommet);
		
		//On regroupe les sommets qui ont les memes couleurs pour enfin leur attribuer une couleur optimale
		HashMap<Couleur, ArrayList<Sommet>> reverseMap = new HashMap<>();

		for (HashMap.Entry<Sommet,Couleur> entry : couleur_sommet.entrySet()) {
		    if (!reverseMap.containsKey(entry.getValue())) {
		        reverseMap.put(entry.getValue(), new ArrayList<>());
		    }
		    ArrayList<Sommet> keys = reverseMap.get(entry.getValue());
		    keys.add(entry.getKey());
		    reverseMap.put(entry.getValue(), keys);
		}
		//System.out.println(reverseMap);
		for (HashMap.Entry<Couleur, ArrayList<Sommet>> entry : reverseMap.entrySet()) {
			Couleur key = entry.getKey();
			ArrayList<Sommet> val = entry.getValue();
			System.out.print("Couleur: " + key.getNom() + " - Prix: "+ key.getPrix() + "€ - Sommet: ");
			val.forEach(e -> System.out.print(e.getNom() + " "));
			double sommeSuperficie = 0;
			for(Sommet s : val)
				sommeSuperficie += s.getSuperficie();
			System.out.println("- Superficie Total: " + sommeSuperficie);
		}
		//à finir .... attribuer une couleur optimale à un sommet
		//ligne 85, j'ai trie couleur par ordre decroissant du prix, je ne sais pas si ça marche ou pas encore
		
	}

}
