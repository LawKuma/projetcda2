import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageProduitComponent } from './components/page-produit/page-produit.component';
import { PageLoginComponent } from './components/page-login/page-login.component';
import { HomeComponent } from './components/home/home.component'; // Si tu veux aussi ajouter la home page

const routes: Routes = [
  { path: '', component: HomeComponent }, // Route pour la home page
  { path: 'produit', component: PageProduitComponent }, // Route pour la page produit
  { path: 'login', component: PageLoginComponent }, // Route pour la page login
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
