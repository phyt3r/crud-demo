package com.example.demo.data;

import com.example.demo.data.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Client {
	@Id
	private String id;
	private String firstName;
	private String secondName;
	private String pin;
	private Address address;
	private Gender gender;
}
