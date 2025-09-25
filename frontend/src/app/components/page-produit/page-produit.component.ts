import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-produit',
  templateUrl: './page-produit.component.html',
  styleUrls: ['./page-produit.component.css'],
})
export class PageProduitComponent implements OnInit {
  product: any; // Variable pour stocker les informations du produit

  constructor() {}

  ngOnInit(): void {
    // Simuler les données d'un produit (dans un vrai cas, vous appelleriez un service backend pour récupérer les données)
    this.product = {
      name: 'T-shirt Vrunk',
      description: 'Un t-shirt de haute qualité avec un design moderne.',
      price: 19.99,
      imageUrl: 'assets/bcn_3.png', // Ajoutez une image dans votre dossier assets
    };
  }
}
