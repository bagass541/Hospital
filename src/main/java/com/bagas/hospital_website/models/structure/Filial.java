package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Сущность филиала.
 * Расширяет класс StructureElement.
 */

@Table(name = "filials")
@Entity
public class Filial extends StructureElement{
	
}
