package com.example.demo.data;

import com.example.demo.data.enums.CoverStatus;
import com.example.demo.data.enums.CoverType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Cover {

	@Id
	private String id;
	private CoverStatus coverStatus;
	private CoverType coverType;
	private Client insured;
}
