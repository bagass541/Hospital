package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Сущность стоматологии.
 * Расширяет класс StructureElement.
 */

@Table(name = "stomatologies")
@Entity
public class Stomatology extends StructureElement{

}
