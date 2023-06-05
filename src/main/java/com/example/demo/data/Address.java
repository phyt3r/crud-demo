package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Address {
	@Id
	private String id;
	private String country;
	private String city;
	private String street;
	private String zipCode;
}
