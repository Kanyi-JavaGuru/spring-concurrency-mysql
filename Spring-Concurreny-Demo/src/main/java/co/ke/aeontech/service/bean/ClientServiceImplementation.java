package co.ke.aeontech.service.bean;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import co.ke.aeontech.model.Client;
import co.ke.aeontech.repository.ClientRepository;

@Service
public class ClientServiceImplementation{

	@Autowired
	private ClientRepository repository;
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public void createClient(Client client) {
		try {
			Thread.sleep(500);
			LOGGER.info("Creating client from: "+client.getOrganisation());
			repository.save(client);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Async
	public void createClientAsync(Client client) {
		try {
			Thread.sleep(500);
			LOGGER.info("Creating client from: "+client.getOrganisation());
			repository.save(client);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Async
	public CompletableFuture<Client> findbyId(Long id) {
		Client client = repository.findOne(id);
		try {
			Thread.sleep(500);
			if(client!=null) {
				LOGGER.info("Found client: "+client.getClientId()+" from: "+client.getOrganisation());
				return CompletableFuture.completedFuture(client);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
