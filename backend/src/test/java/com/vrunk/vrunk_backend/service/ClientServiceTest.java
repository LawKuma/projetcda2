package com.vrunk.vrunk_backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vrunk.vrunk_backend.dao.ClientRepository;
import com.vrunk.vrunk_backend.entity.Client;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInscrireClient_ClientDejaExistant() {
        Client client = new Client();
        client.setEmail("john.doe@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.of(client));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clientService.inscrireClient(client);
        });

        System.out.println("Message d'exception: " + exception.getMessage());

        assertEquals("Un client avec cet email existe déjà.", exception.getMessage());

        verify(clientRepository, never()).save(any(Client.class));
    }

   
    @Test
    public void testInscrireClient_NouveauClient() {
        Client client = new Client();
        client.setEmail("new@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.empty());

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.inscrireClient(client);

        assertNotNull(result);
        assertEquals(client.getEmail(), result.getEmail());

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testInscrireClient_ExceptionBDD() {
        // Arrange
        Client client = new Client();
        client.setEmail("error@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenThrow(new RuntimeException("Erreur BDD"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clientService.inscrireClient(client);
        });

        System.out.println("Message d'exception: " + exception.getMessage());

        assertEquals("Erreur BDD", exception.getMessage());
        
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}
