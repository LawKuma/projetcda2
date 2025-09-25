export class Collection {
  collection_id: number;
  nom: string;
  description: string;

  constructor(collection_id: number, nom: string, description: string) {
    this.collection_id = collection_id;
    this.nom = nom;
    this.description = description;
  }
}
