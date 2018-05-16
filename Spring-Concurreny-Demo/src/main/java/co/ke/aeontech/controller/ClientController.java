package co.ke.aeontech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ke.aeontech.model.Client;
import co.ke.aeontech.service.ClientService;
import co.ke.aeontech.service.bean.ClientServiceImplementation;

@RestController
public class ClientController implements ClientService{

	@Autowired
	private ClientServiceImplementation service;
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> create(){	
		Long startTime = System.currentTimeMillis();
		for(int i=0; i<=99; i++) {
			Client client = new Client(0L, "organisation "+i);
			createClient(client);
		}
		Long timeTaken = (System.currentTimeMillis() - startTime);
		LOGGER.info("Completed in: "+timeTaken+" creating clients syncronously");
		return new ResponseEntity<Object>("created successfully", HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/async")
	public ResponseEntity<Object> createAsync(){
		Long startTime = System.currentTimeMillis();
		for(int i=0; i<=99; i++) {
			Client client = new Client(0L, "organisation "+i);
			createClientAsync(client);
		}
		Long timeTaken = (System.currentTimeMillis() - startTime);
		LOGGER.info("Completed in: "+timeTaken+" creating clients Asycronously");
		return new ResponseEntity<Object>("created successfully", HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> findById(){
		List<Client> listOfFoundClients = new ArrayList<>();
		Long startTime = System.currentTimeMillis();
		for(Long i=200L; i<=299; i++) {
			try {
				listOfFoundClients.add(findbyId(i).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Long timeTaken = (System.currentTimeMillis() - startTime);
		LOGGER.info("Completed finding clients in: "+timeTaken);
		return new ResponseEntity<Object>(listOfFoundClients, HttpStatus.OK);
	}
	
	@Override
	public void createClient(Client client) {
		service.createClient(client);
	}

	@Override
	public void createClientAsync(Client client) {
		service.createClientAsync(client);
	}

	@Override
	public CompletableFuture<Client> findbyId(Long id) {
		return service.findbyId(id);
	}
}
