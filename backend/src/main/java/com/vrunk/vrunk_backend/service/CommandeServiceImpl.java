package com.vrunk.vrunk_backend.service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;
import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.dto.Achat;
import com.vrunk.vrunk_backend.dto.InfosPaiement;
import com.vrunk.vrunk_backend.dto.ReponseAchat;
import com.vrunk.vrunk_backend.entity.Client;
import com.vrunk.vrunk_backend.entity.Commande;
import com.vrunk.vrunk_backend.exception.ClientNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "stripe.enabled", havingValue = "true", matchIfMissing = true)
public class CommandeServiceImpl implements CommandeService {

    private final ClientRepository clientRepository;

    public CommandeServiceImpl(ClientRepository clientRepository, @Value("${stripe.key.secret:}") String secretKey) {
        this.clientRepository = clientRepository;

        if (secretKey == null || secretKey.isEmpty()) {
            System.out.println("⚠️ Stripe désactivé : Aucune clé secrète fournie.");
        } else {
            Stripe.apiKey = secretKey;
            System.out.println("✅ Stripe initialisé avec une clé secrète.");
        }
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
        Map<String, Object> params = new HashMap<>();
        params.put("amount", infosPaiement.getMontant());
        params.put("currency", infosPaiement.getDevise());
        params.put("payment_method_types", new String[]{"card"});
        params.put("description", "Achat Vrunk");
        params.put("receipt_email", infosPaiement.getEmailRecu());

        try {
            return PaymentIntent.create(params);
        } catch (StripeException e) {
            return "Erreur de paiement Stripe.";
        }
    }

    private String genererNumeroSuivi() {
        return UUID.randomUUID().toString();
    }
}
