package com.bagas.hospital_website.models;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "doctors")
@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "doctor_type")
	private DoctorType doctorType;
	
	private String fio;
	
	@Column(name = "start_work")
	private LocalTime startWork;
	
	@Column(name = "end_work")
	private LocalTime endWork;
	
}
