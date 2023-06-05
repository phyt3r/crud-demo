package com.example.demo.controllers;

import com.example.demo.data.Client;
import com.example.demo.data.Cover;
import com.example.demo.data.enums.CoverStatus;
import com.example.demo.data.enums.CoverType;
import com.example.demo.repos.ClientRepository;
import com.example.demo.repos.CoverRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CoverController {

	private final CoverRepository coverRepository;
	private final ClientRepository clientRepository;

	public CoverController(CoverRepository coverRepository, ClientRepository clientRepository) {
		this.coverRepository = coverRepository;
		this.clientRepository = clientRepository;
	}

	@QueryMapping
	public Iterable<Cover> covers() {
		return coverRepository.findAll();
	}

	@MutationMapping
	public Cover addCover(@Argument CoverInput cover) {
		Client insured = clientRepository.findById(cover.insuredId).orElseThrow(() -> new IllegalArgumentException("Client with given ID not found"));
		return coverRepository.save(new Cover(null, cover.coverStatus, cover.coverType, insured));
	}

	@MutationMapping
	public Cover updatedCover(@Argument String id, @Argument CoverInput cover) {
		Client insured = clientRepository.findById(cover.insuredId).orElseThrow(() -> new IllegalArgumentException("Client with given ID not found"));
		return coverRepository.save(new Cover(id, cover.coverStatus, cover.coverType, insured));
	}

	record CoverInput(CoverStatus coverStatus, CoverType coverType, String insuredId) {}
}
