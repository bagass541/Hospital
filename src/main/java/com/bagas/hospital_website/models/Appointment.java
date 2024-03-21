package com.bagas.hospital_website.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Сущность записи к врачу.
 */

@Table(name = "appointments")
@Entity
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	private LocalDateTime time;
}
