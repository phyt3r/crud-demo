package com.example.demo.controllers;

import com.example.demo.data.Address;
import com.example.demo.data.Client;
import com.example.demo.data.enums.Gender;
import com.example.demo.repos.AddressRepository;
import com.example.demo.repos.ClientRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Clientcontroller {

	private final ClientRepository clientRepository;
	private final AddressRepository addressRepository;

	public Clientcontroller(ClientRepository clientRepository, AddressRepository addressRepository) {
		this.clientRepository = clientRepository;
		this.addressRepository = addressRepository;
	}

	@QueryMapping
	public Iterable<Client> clients() {
		return clientRepository.findAll();
	}

	@MutationMapping
	public Client addClient(@Argument ClientInput client) {
		Address address = addressRepository.findById(client.addressId).orElseThrow(() -> new IllegalArgumentException("Address with given ID not found"));
		return clientRepository.save(new Client(null, client.firstName, client.secondName, client.pin, address, client.gender));
	}

	@MutationMapping
	public Client updateClient(@Argument String id, @Argument ClientInput client) {
		Address address = addressRepository.findById(client.addressId).orElseThrow(() -> new IllegalArgumentException("Address with given ID not found"));
		return clientRepository.save(new Client(id, client.firstName, client.secondName, client.pin, address, client.gender));
	}

	record ClientInput (String firstName, String secondName, String pin, String addressId, Gender gender) {}
}
