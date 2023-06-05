package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@AllArgsConstructor
public class Policy {

	@Id
	private String id;
	private Client policyHolder;
	private List<Cover> covers;
	private List<Invoice> invoices;
}
