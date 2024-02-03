package com.bagas.hospital_website.models.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Сущность детского учреждения.
 * Расширяет класс StructureElement.
 */

@Table(name = "child_institutions")
@Entity
public class ChildInstitution extends StructureElement {

}
