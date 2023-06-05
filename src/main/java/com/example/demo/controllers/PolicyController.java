package com.example.demo.controllers;

import com.example.demo.data.Client;
import com.example.demo.data.Cover;
import com.example.demo.data.Invoice;
import com.example.demo.data.Policy;
import com.example.demo.repos.ClientRepository;
import com.example.demo.repos.CoverRepository;
import com.example.demo.repos.InvoiceRepository;
import com.example.demo.repos.PolicyRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PolicyController {

	private final CoverRepository coverRepository;
	private final ClientRepository clientRepository;
	private final PolicyRepository policyRepository;
	private final InvoiceRepository invoiceRepository;

	public PolicyController(CoverRepository coverRepository, ClientRepository clientRepository, PolicyRepository policyRepository, InvoiceRepository invoiceRepository) {
		this.coverRepository = coverRepository;
		this.clientRepository = clientRepository;
		this.policyRepository = policyRepository;
		this.invoiceRepository = invoiceRepository;
	}

	@QueryMapping
	public Iterable<Policy> policies() {
		return policyRepository.findAll();
	}

	@MutationMapping
	public Policy addPolicy(@Argument PolicyInput policy) {
		Client client = clientRepository.findById(policy.policyHolderId).orElseThrow(() -> new IllegalArgumentException("Client with given Id is not found"));
		List <Cover> covers = coverRepository.findAllById(policy.coversIds);
		if (covers.isEmpty()) {
			throw new IllegalArgumentException("Covers with given Id not found");
		}
		List<Invoice> invoices = invoiceRepository.findAllById(policy.invoicesIds);
		if (invoices.isEmpty()) {
			throw new IllegalArgumentException("Invoices with given Id not found");
		}
		return policyRepository.save(new Policy(null, client, covers, invoices));
	}

	@MutationMapping
	public Policy updatePolicy(@Argument String id, @Argument PolicyInput policy) {
		Client client = clientRepository.findById(policy.policyHolderId).orElseThrow(() -> new IllegalArgumentException("Client with given Id is not found"));
		List <Cover> covers = coverRepository.findAllById(policy.coversIds);
		if (covers.isEmpty()) {
			throw new IllegalArgumentException("Covers with given Id not found");
		}
		List<Invoice> invoices = invoiceRepository.findAllById(policy.invoicesIds);
		if (invoices.isEmpty()) {
			throw new IllegalArgumentException("Invoices with given Id not found");
		}
		return policyRepository.save(new Policy(id, client, covers, invoices));
	}

	record PolicyInput(String policyHolderId, List<String> coversIds, List<String> invoicesIds) {}
}
