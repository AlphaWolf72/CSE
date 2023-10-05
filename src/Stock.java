/**
 * Les objets instances de la classe Stock representent un ensemble de pieces,
 * empilees les unes sur les autres. Du fait de la disposition en piles, il n'est
 * pas possible que deux operateurs saisissent deux pieces au meme moment.
 *
 */
class Stock {
	/**
	 * Nombre de pieces dans la pile
	 */
    private int nbPieces;
    /**
     * Le nom du stock
     */
    private String nom;
    /**
     * Le nombre maximum de pièce(s) dans le stock
     */
    private int maxPieces;

    /**
     * Creer un nouvel objet instance de stock
     * @param nom Le nom du nouveau stock
     * @param nbPieces Le nombre de pieces initial
     */
    public Stock(String nom, int nbPieces, int maxPieces) {
        this.nbPieces = nbPieces;
        this.nom = nom;
        this.maxPieces = maxPieces;
    }

    /**
     * Poser une piece sur le haut de la pile de pieces
     * Ajout de synchronized pour ordonancer l'éxecution de la méthode par les treads
     */
    public synchronized void stocker() {
        //comme on veut trvailler en flux tendu
        // on verifie que notre nombre de pièces ne dépasse pas le maximum que l'atelier peux stocker
        while (nbPieces > maxPieces){
            try {
                wait(); // on attend, pour avoir le droit de modifier la variable
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nbPieces++;
        notifyAll();
    }

    /**
     * Saisir une piece sur le haut de la pile de pieces
     * Ajout de synchronized pour ordonancer l'éxecution de la méthode par les treads
     */
    public synchronized void destocker() {
        //comme on peux avoir 2 atelier sur le même stock, on revérifit après le réveil du threads que la condition est toujours valide
        while (nbPieces <= 0){ // on vérifie que le nombre de pièces, pour éviter dans prendre quand il n'y en a plus
            try {
                wait(); // on attend, pour avoir le droit de modifier la variable
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nbPieces--;
        notifyAll();
    }

    /**
     * Affiche l'etat de l'objet stock
     */
    public String afficher() {
        return ("Le stock " + nom + " contient " + nbPieces + " piece(s).");
    }

}
