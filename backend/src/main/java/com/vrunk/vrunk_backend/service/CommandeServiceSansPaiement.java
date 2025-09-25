package com.vrunk.vrunk_backend.service;

import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.dto.Achat;
import com.vrunk.vrunk_backend.dto.InfosPaiement;
import com.vrunk.vrunk_backend.dto.ReponseAchat;
import com.vrunk.vrunk_backend.entity.Client;
import com.vrunk.vrunk_backend.entity.Commande;
import com.vrunk.vrunk_backend.exception.ClientNotFoundException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary  // Assure que cette implémentation est choisie si aucune autre n'est disponible
@ConditionalOnProperty(name = "stripe.enabled", havingValue = "false")
public class CommandeServiceSansPaiement implements CommandeService {

    private final ClientRepository clientRepository;

    public CommandeServiceSansPaiement(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        System.out.println("✅ Stripe désactivé, utilisation du service sans paiement.");
    }

    @Override
    @Transactional
    public ReponseAchat passerCommande(Achat achat) {
        Commande commande = achat.getCommande();
        String numeroSuiviCommande = genererNumeroSuivi();
        commande.setNumeroSuiviCommande(numeroSuiviCommande);

        Client client = achat.getClient();
        String email = client.getEmail();
        Optional<Client> clientFromDB = clientRepository.findByEmail(email);

        if (clientFromDB.isPresent()) {
            client = clientFromDB.get();
        } else {
            throw new ClientNotFoundException("Le client avec l'email " + email + " n'existe pas. Veuillez vous inscrire.");
        }

        client.addCommande(commande);
        clientRepository.save(client);

        return new ReponseAchat(numeroSuiviCommande);
    }

    @Override
    public Object creerIntentPaiement(InfosPaiement infosPaiement) {
        System.out.println("❌ Paiement désactivé : Stripe est désactivé dans l'application.");
        return "Paiement indisponible : Stripe est désactivé.";
    }

    private String genererNumeroSuivi() {
        return UUID.randomUUID().toString();
    }
}
