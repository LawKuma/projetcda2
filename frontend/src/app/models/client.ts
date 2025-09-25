export class Client {
  client_id: number;
  nom: string;
  email: string;

  constructor(client_id: number, nom: string, email: string) {
    this.client_id = client_id;
    this.nom = nom;
    this.email = email;
  }
}
