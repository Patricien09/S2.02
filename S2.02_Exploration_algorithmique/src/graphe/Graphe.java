/** graphe/Graphe.java */

package graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	 * Liste des couleurs
	 */
	private ArrayList<Couleur> couleurs;
	
	/** Declare un graphe grace a une liste de sommet et une liste de couleur
	 * @param sommets Liste des sommets
	 * @param couleurs Liste des couleurs
	 */
	public Graphe(ArrayList<Sommet> sommets, ArrayList<Couleur> couleurs) {
		this.sommets = sommets;
		this.couleurs = couleurs;
	}

	/**
	 * Constructeur par copie du graphe passe en parametre
	 * @param graphe Le graphe a copier
	 */
	public Graphe(Graphe graphe) {
		this(graphe.getSommets(), graphe.getCouleurs());
	}
	/**
	 * Verifie si le graphe est vide ou non
	 * @return boolean Renvoie vrai si graohe vide, faux sinon
	 */
	public boolean isEmpty() {
		if (this.getSommets() == null) {
			return true;
		}
		return false;
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
		if(this.isEmpty()) {
			return "Le graphe est vide. Aucun sommets!";
		}
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
	public void algoWelshPowell() {
		//Si graphe vide, on fait rien
		if(this.isEmpty()) return;
		Graphe graphe = new Graphe(this); //copie graphe avant trie
		Collections.sort(graphe.getSommets()); //trie les sommets selon l'ordre decroissant du degre
		Collections.sort(this.getCouleurs()); //trie les couleurs selon l'ordre decroissant du prix
		ArrayList<Sommet> listeSommetNonTraite = new ArrayList<>(graphe.getSommets());
		HashMap<Couleur, ArrayList<Sommet>> hashMapCouleurSommets = new HashMap<>();
		
		//Algo de WP, on veut juste attribuer des couleurs à des sommets - Nombre chromatique minimal 
		//on ne se contente pas du prix des couleurs
		int i = 0;
		while (!listeSommetNonTraite.isEmpty()) {
			ArrayList<Sommet> listeSommetColorie = new ArrayList<>();
			ArrayList<Sommet> succ = new ArrayList<>(listeSommetNonTraite.get(0).getVoisin()); //Liste de tous les successeurs du premier sommet non traité
			for (Sommet s: listeSommetNonTraite) {
				if(!succ.contains(s)) {
					if (i >= couleurs.size()) {
						System.out.println("Pas assez de couleurs différents pour colorier tous les sommets");
						return;
					}
					listeSommetColorie.add(s);
					succ.addAll(s.getVoisin()); // ensemble succ Union ensemble des successeurs du sommet traité
					s.setCouleur(new Couleur(couleurs.get(i))); //On colorie le sommet
					hashMapCouleurSommets.put(couleurs.get(i), listeSommetColorie);
				}
			}
			listeSommetNonTraite.removeAll(listeSommetColorie); // on enlève tous les sommets portant une couleur de la liste des sommets à traiter
			i++;
		}
		
		/*//Pour visualiser notre HashMap
		System.out.println("hashMapCouleurSommets:");
		for (HashMap.Entry<Couleur, ArrayList<Sommet>> entry : hashMapCouleurSommets.entrySet()) {
			Couleur key = entry.getKey();
			ArrayList<Sommet> val = entry.getValue();
			System.out.print("Couleur: " + key.getNom() + " - Prix: "+ key.getPrix() + " euros - Sommet: ");
			val.forEach(e -> System.out.print(e.getNom() + " "));
			Double sommeSuperficie = 0.;
			for(Sommet s : val)
				sommeSuperficie += s.getSuperficie();
			System.out.println("- Superficie Total: " + sommeSuperficie);
		}*/
		
		//Dans cette partie, on va attribuer une couleur optimale par superficie totale
		//Tout d'abord, on convertit le hashmap en Liste pour pouvoir les trier
		//On regroupe d'abord chaque <K,V> dans un set, puis on les ajoute dans une Liste
		List<Entry<Couleur, ArrayList<Sommet>>> listeDesEntrees = new ArrayList<>(hashMapCouleurSommets.entrySet()); 
		//Comparator anonyme pour comparé la superficie totale par couleur attribué
		Comparator<Entry<Couleur, ArrayList<Sommet>>> superficieComparator = new Comparator<>() {  
            @Override
            public int compare(Entry<Couleur, ArrayList<Sommet>> e1, Entry<Couleur, ArrayList<Sommet>> e2) {
                ArrayList<Sommet> v1 = e1.getValue();
                ArrayList<Sommet> v2 = e2.getValue();
				Double sommeV1 = 0.;
				for(Sommet d : v1){
					sommeV1 += (Double) d.getSuperficie();
				}
				Double sommeV2 = 0.;
				for(Sommet d : v2){
					sommeV2 += (Double) d.getSuperficie();
				}
                return sommeV1.compareTo(sommeV2);
            }
        };
        //On trie la liste selon la superficie totale par couleur attribué
		Collections.sort(listeDesEntrees, superficieComparator.reversed());
        // On attribue a chaque sommet une couleur optimale, c'est-à-dire du plus petit prix au plus grand
        Collections.sort(this.getCouleurs()); //trie les couleurs selon l'ordre decroissant du prix
        Iterator<Entry<Couleur, ArrayList<Sommet>>> it = listeDesEntrees.iterator();
        i = 0;
        while(it.hasNext() && i < couleurs.size()) {
        	for (Sommet listSommet : it.next().getValue()) {
        		listSommet.setCouleur(new Couleur(couleurs.get(i)));
			}
        	i++;
        }
        //Pour visualiser le résultat
        Collections.sort(sommets, (s1,s2)-> s1.getNom().compareTo(s2.getNom()));
        Collections.sort(sommets, (s1,s2)-> s1.getCouleur().getNom().compareTo(s2.getCouleur().getNom()));
        System.out.println("\nCouleur attribué à chaque sommet:");
        System.out.println("Sommet - Couleur - Superficie - Prix couleur\n");
        for (Sommet sommet : sommets) {
			System.out.println(sommet.getNom() + " - " + sommet.getCouleur().getNom() + " - " + sommet.getSuperficie() + " - "+ sommet.getCouleur().getPrix()+ " euros");
		}        
	}
}
