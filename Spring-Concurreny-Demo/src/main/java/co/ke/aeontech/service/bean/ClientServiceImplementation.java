package co.ke.aeontech.service.bean;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
	private Object lock = new Object();
	
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
	public Client find(Long id) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client client = repository.findOne(id);
		LOGGER.info("Found client: "+id);
		return client;
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
//	@Async
	public void findbyIdNonBlock(Long id, List<Client> clients) {
		try {
			Thread.sleep(500);
			Client client = repository.findOne(id);
			LOGGER.info("Found client: "+client.getClientId()+" from: "+client.getOrganisation());			
			synchronized (lock) {
				clients.add(client);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
