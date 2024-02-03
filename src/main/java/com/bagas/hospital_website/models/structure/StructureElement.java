package com.bagas.hospital_website.models.structure;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * Абстрактный класс для структурных элементов.
 * Расширяет класс StructureElement.
 */

@MappedSuperclass
@Data
public class StructureElement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
}
