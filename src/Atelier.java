/**
 * Les objets instances de la classe Atelier representent des ateliers de transformation.
 * Le fonctionnement est le suivant : l'appel a transformer retire un element du stock A,
 * attend 100 ms, puis ajoute un element au stock B.
 * La methode travailler() effectue n transformations successives, n etant un parametre
 * fourni a la creation de l'objet.
 */
class Atelier extends Thread{

	/**
	 * Le stock de fourniture de depart
	 */
    private Stock A;
    /**
     * Le stock de produits transformes
     */
    private Stock B;
    /**
     * Le nombre de transformations effectuees lors d'un appel a
     * la methode travailler().
     */
    private int nbTransfo;

    /**
     * Construit un objet instance d'Atelier
     * @param A Le stock de pieces de depart
     * @param B Le stock de pieces transformees
     * @param nbTransfo Le nombre de transformations par appel a travailler()
     */
    public Atelier(Stock A, Stock B, int nbTransfo) {
        this.A = A;
        this.B = B;
        this.nbTransfo = nbTransfo;
    }

    /**
     * Effectue une transformation
     */
    //Ajout affichage
    //La trace déxécution ne sera pas la même à chaque fois
    //car elle dépendra de l'ordonenceur
    public void transformer() {
        A.destocker();
        System.out.println("Le "+Thread.currentThread().getName()+" vient de dépiler."+A.afficher()+" "+B.afficher());
        try { Thread.sleep(100); } catch(InterruptedException e) {}
        B.stocker();
    }

    /**
     * Effectue nbTransfo transformations
     */
    @Override
    public void run() {
        for(; nbTransfo > 0; nbTransfo--)
            transformer();
    }

}
