package co.ke.aeontech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="clientId")
	private Long clientId;
	@Column(name="organisation", nullable = false)
	private String organisation;
	
	public Client() {
	}

	public Client(Long clientId, String organisation) {
		this.clientId = clientId;
		this.organisation = organisation;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	
}
