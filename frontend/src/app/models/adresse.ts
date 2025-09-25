export class Adresse {
  adresse_id: number;
  rue: string;
  ville: string;
  code_postal: string;
  pays: string;

  constructor(adresse_id: number, rue: string, ville: string, code_postal: string, pays: string) {
    this.adresse_id = adresse_id;
    this.rue = rue;
    this.ville = ville;
    this.code_postal = code_postal;
    this.pays = pays;
  }
}
