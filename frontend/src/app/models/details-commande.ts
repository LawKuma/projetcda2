export class DetailsCommande {
  commande_id: number;
  produit_id: number;
  quantite: number;
  prix_unite: number;

  constructor(
    commande_id: number,
    produit_id: number,
    quantite: number,
    prix_unite: number
  ) {
    this.commande_id = commande_id;
    this.produit_id = produit_id;
    this.quantite = quantite;
    this.prix_unite = prix_unite;
  }
}
