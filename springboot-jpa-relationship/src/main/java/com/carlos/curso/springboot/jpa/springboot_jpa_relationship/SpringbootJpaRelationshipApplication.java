package com.carlos.curso.springboot.jpa.springboot_jpa_relationship;

import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		OnteToManyFindById();
	}

	@Transactional
	public void OnteToManyFindById() {
		Optional<Client> clientOpt = this.clientRepository.findById(2L);

		clientOpt.ifPresent(client ->  {
			Address address1 = new Address("El Verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			client.setAddresses(Arrays.asList(address1, address2));

			this.clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void OnteToMany() {
		Client client = new Client("Fran", "Espin");
		Address address1 = new Address("El Verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		this.clientRepository.save(client);
		System.out.println(client);
	}


	@Transactional
	public void menyToOneFindByIdClient() {
		Optional<Client> clientOpt = this.clientRepository.findById(2L);

		if (clientOpt.isPresent()) {
			Client client = clientOpt.orElseThrow();

			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = this.invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

	@Transactional
	public void menyToOne() {
		Client client = new Client("John", "Doe");
		this.clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = this.invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}
}
