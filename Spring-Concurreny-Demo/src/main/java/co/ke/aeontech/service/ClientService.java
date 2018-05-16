package co.ke.aeontech.service;

import java.util.concurrent.CompletableFuture;

import co.ke.aeontech.model.Client;

public interface ClientService {

	void createClient(Client client);
	
	void createClientAsync(Client client);
	
	CompletableFuture<Client> findbyId(Long id);
	
}
