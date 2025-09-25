export class Commande {
  commande_id: number;
  date_creation: Date;

  constructor(commande_id: number, date_creation: Date) {
    this.commande_id = commande_id;
    this.date_creation = date_creation;
  }
}
