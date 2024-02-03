package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Сущность поликлиники.
 * Расширяет класс StructureElement.
 */

@Table(name = "polyclinics")
@Entity
public class Polyclinic extends StructureElement{

}
