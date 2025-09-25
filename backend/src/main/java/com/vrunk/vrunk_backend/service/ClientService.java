package com.vrunk.vrunk_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.entity.Client;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 🔹 Inscription d'un client (création)
    @Transactional
    public Client inscrireClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new RuntimeException("Un client avec cet email existe déjà.");
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    // 🔹 Récupérer un client par ID
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // 🔹 Récupérer un client par email
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    // 🔹 Mise à jour d'un client
    @Transactional
    public Client updateClient(Long id, Client clientDetails) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setEmail(clientDetails.getEmail());
            client.setTelephone(clientDetails.getTelephone());

            if (clientDetails.getPassword() != null && !clientDetails.getPassword().isEmpty()) {
                client.setPassword(passwordEncoder.encode(clientDetails.getPassword()));
            }

            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client non trouvé avec ID : " + id));
    }

    // 🔹 Suppression d'un client par ID
    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec ID : " + id);
        }
        clientRepository.deleteById(id);
    }

    // 🔹 Obtenir tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
