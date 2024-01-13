package com.bagas.hospital_website.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "filials")
@Data
@NoArgsConstructor
public class Filial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_filial;
	
	@Column(name = "name", unique = true)
	private String name;
}
