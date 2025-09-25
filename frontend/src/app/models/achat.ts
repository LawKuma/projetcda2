import { Adresse } from './adresse';
import { Client } from './client';
import { Commande } from './commande';
import { DetailsCommande } from './details-commande';

export class Achat {
  client: Client;
  adresse: Adresse;
  commande: Commande;
  detailsCommandes: DetailsCommande[];

  constructor(
    client: Client,
    adresse: Adresse,
    commande: Commande,
    detailsCommandes: DetailsCommande[]
  ) {
    this.client = client;
    this.adresse = adresse;
    this.commande = commande;
    this.detailsCommandes = detailsCommandes;
  }
}
