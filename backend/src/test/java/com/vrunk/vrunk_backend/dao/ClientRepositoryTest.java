package com.vrunk.vrunk_backend.dao;

import com.vrunk.vrunk_backend.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties") // Utilise application.properties
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Garde PostgreSQL
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testFindByEmail_ClientExiste() {
        Optional<Client> clientOpt = clientRepository.findByEmail("john.doe@example.com");

        assertTrue(clientOpt.isPresent(), "Le client devrait exister dans la base de données.");

        Client client = clientOpt.get();
        System.out.println("✅ Client trouvé : " + client.getNom() + " " + client.getPrenom());
        System.out.println("📧 Email : " + client.getEmail());
        System.out.println("📞 Téléphone : " + client.getTelephone());

        assertEquals("John", client.getNom(), "Le nom du client est incorrect.");
        assertEquals("Doe", client.getPrenom(), "Le prénom du client est incorrect.");
        assertEquals("063456789", client.getTelephone(), "Le téléphone du client est incorrect.");
    }
}