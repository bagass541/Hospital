package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Сущность больницы.
 * Расширяет класс StructureElement.
 */

@Table(name = "hospitals")
@Entity
public class Hospital extends StructureElement{

}
