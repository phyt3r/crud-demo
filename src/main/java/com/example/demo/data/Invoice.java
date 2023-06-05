package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document
@AllArgsConstructor
public class Invoice {
	@Id
	private String id;
	private LocalDate createdTime;
	private BigDecimal amount;
}
