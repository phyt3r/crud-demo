package com.example.demo.controllers;

import com.example.demo.data.Address;
import com.example.demo.repos.AddressRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AddressController {
	private final AddressRepository addressRepository;

	public AddressController(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@QueryMapping
	public Iterable<Address> addresses() {
		return addressRepository.findAll();
	}

	@MutationMapping
	public Address addAddress(@Argument AddressInput address) {
		return addressRepository.save(new Address(null, address.country, address.city, address.street, address.zipCode));
	}

	@MutationMapping
	public Address updateAddress(@Argument String id, @Argument AddressInput address) {
		return addressRepository.save(new Address(id, address.country, address.city, address.street, address.zipCode));
	}

	record AddressInput(String country, String city, String street, String zipCode) {}
}
