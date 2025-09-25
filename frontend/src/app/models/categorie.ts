export class Categorie {
  categorie_id: number;
  nom: string;
  description: string;

  constructor(categorie_id: number, nom: string, description: string) {
    this.categorie_id = categorie_id;
    this.nom = nom;
    this.description = description;
  }
}
