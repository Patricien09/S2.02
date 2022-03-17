/** graphe/Graphe.java */

package graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

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
	
	/** Declare un graphe grace a une liste de sommet et un liste de couleur
	 * @param sommets liste des sommets
	 * @param couleurs liste des couleurs
	 */
	public Graphe(ArrayList<Sommet> sommets, ArrayList<Couleur> couleurs) {
		this.sommets = sommets;
		this.couleurs = couleurs;
	}

	/** Declare un graphe
	 * Recopie du graphe passe en parametre
	 * @param graphe Le graphe a copier
	 */
	public Graphe(Graphe graphe) {
		this(graphe.getSommets(), graphe.getCouleurs());
	}
	
	/** Renvoie la liste des sommets
	 * @return liste des sommets
	 */
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	/** Change la liste des sommets par celle passee en parametre
	 * @param sommets la nouvelle liste des sommes
	 */
	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}

	/** Renvoie la liste des couleurs
	 * @return la liste des couleurs
	 */
	public ArrayList<Couleur> getCouleurs() {
		return couleurs;
	}

	/** Change la liste des couleurs par celle passee en parametre
	 * @param couleurs la nouvelle liste des couleurs
	 */
	public void setCouleurs(ArrayList<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
	
	/** Convertit un graphe en chaine de caractere
	 * C'est-a-dire le nom de chaque sommet du graphe suvi par tous ses voisins
	 */
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

	/** Associe a chaque sommet une couleur */
	public void algoWelshPowell() { //throws Exception --> Nb de couleurs pas assez
		//------------ Partie marche bien
		Graphe graphe = new Graphe(this); //copie graphe avant trie
		Collections.sort(graphe.getSommets()); //trie les sommets selon l'ordre decroissant du degre
		Collections.sort(graphe.getCouleurs()); //trie les couleurs selon l'ordre decroissant du prix
		ArrayList<Sommet> listeSommetNonTraite = new ArrayList<>(graphe.getSommets());
		HashMap<Couleur, ArrayList<Sommet>> hashMapCouleurSommets = new HashMap<>();
		for (int i = 0; i < listeSommetNonTraite.size(); i++) {
			ArrayList<Sommet> listeSommetColorie = new ArrayList<>();
			listeSommetColorie.add(listeSommetNonTraite.get(i)); //On colorie le premier sommet non encore colorie
			hashMapCouleurSommets.put(couleurs.get(i), listeSommetColorie); 
			for (int j = i+1; j < listeSommetNonTraite.size(); j++){
				if (!listeSommetNonTraite.get(i).getVoisin().contains(listeSommetNonTraite.get(j))){ //si le sommet n'est pas un sommet adjacent on colorie
					listeSommetColorie.add(listeSommetNonTraite.get(j));
					listeSommetNonTraite.remove(j); // on l'enlève de la liste des sommets traités
					hashMapCouleurSommets.put(couleurs.get(i), listeSommetColorie); 
				}
			}
		}
		System.out.println("hashMapCouleurSommets:");
		for (HashMap.Entry<Couleur, ArrayList<Sommet>> entry : hashMapCouleurSommets.entrySet()) {
			Couleur key = entry.getKey();
			ArrayList<Sommet> val = entry.getValue();
			System.out.print("Couleur: " + key.getNom() + " - Prix: "+ key.getPrix() + " euros - Sommet: ");
			val.forEach(e -> System.out.print(e.getNom() + " "));
			double sommeSuperficie = 0;
			for(Sommet s : val)
				sommeSuperficie += s.getSuperficie();
			System.out.println("- Superficie Total: " + sommeSuperficie);
		}
		//------------------------------------------

		//---------------------- juste des tests
		//à finir .... attribuer une couleur optimale à un sommet
		Set<Entry<Couleur, ArrayList<Sommet>>> entries = hashMapCouleurSommets.entrySet();
		List<Entry<Couleur, ArrayList<Sommet>>> listOfEntries = new ArrayList<>(entries);
		//Lambda express à tester
		Comparator<Entry<Couleur, ArrayList<Sommet>>> superficieComparator = new Comparator<>() {
            @Override
            public int compare(Entry<Couleur, ArrayList<Sommet>> e1, Entry<Couleur, ArrayList<Sommet>> e2) {
                ArrayList<Sommet> v1 = e1.getValue();
                ArrayList<Sommet> v2 = e2.getValue();
				Double sommeV1 = 0.;
				for(Sommet d : v1){
					sommeV1 += d.getSuperficie();
				}
				Double sommeV2 = 0.;
				for(Sommet d : v2){
					sommeV2 += d.getSuperficie();
				}
                return sommeV1.compareTo(sommeV2);
            }
        };

		Collections.sort(listOfEntries, superficieComparator);
		LinkedHashMap<Couleur, ArrayList<Sommet>> sortedByValue 
                    = new LinkedHashMap<>(listOfEntries.size());
        
        // copying entries from List to Map
        for(Entry<Couleur, ArrayList<Sommet>> entry : listOfEntries){
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        
        System.out.println("HashMap apres trie par superfice, ordre naturel ");
        Set<Entry<Couleur, ArrayList<Sommet>>> entrySetSortedByValue = sortedByValue.entrySet();
        // Print to console
        for(Entry<Couleur, ArrayList<Sommet>> mapping : entrySetSortedByValue){
            System.out.print(mapping.getKey().getNom() + " ==> ");
			mapping.getValue().forEach((Sommet s) -> System.out.print(s.getNom() + " "));
			System.out.println();
        }
	}

}
