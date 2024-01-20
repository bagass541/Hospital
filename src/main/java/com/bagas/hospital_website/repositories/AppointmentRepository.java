package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
