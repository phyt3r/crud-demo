package com.example.demo.controllers;

import com.example.demo.data.Invoice;
import com.example.demo.repos.InvoiceRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class InvoiceController {

	private final InvoiceRepository invoiceRepository;


	public InvoiceController(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@QueryMapping
	public Iterable<Invoice> invoices() {
		return invoiceRepository.findAll();
	}

	@MutationMapping
	public Invoice addInvoice(@Argument InvoiceInput invoice) {
		return invoiceRepository.save(new Invoice(null, LocalDate.now(), invoice.amount));
	}

	@MutationMapping
	public Invoice updateInvoice(@Argument String id, @Argument InvoiceInput invoice) {
		Invoice i = invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice with given Id not found"));
		i.setAmount(invoice.amount);
		return invoiceRepository.save(i);
	}

	record InvoiceInput(BigDecimal amount) {}
}
