export class InfoPaiement {
  montant: number;
  devise: string;
  emailRecu: string;

  constructor(montant: number, devise: string, emailRecu: string) {
    this.montant = montant;
    this.devise = devise;
    this.emailRecu = emailRecu;
  }
}
