package co.ke.aeontech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ke.aeontech.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
