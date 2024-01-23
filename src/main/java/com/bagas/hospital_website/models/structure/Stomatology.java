package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "stomatologies")
@Entity
@Data
@NoArgsConstructor
public class Stomatology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_stomatology;
	
	@Column(name = "name", unique = true)
	private String name;
}
