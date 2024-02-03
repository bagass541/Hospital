package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Сущность аптеки.
 * Расширяет класс StructureElement.
 */

@Table(name = "pharmacies")
@Entity
public class Pharmacy extends StructureElement{

}
