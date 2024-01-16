package com.bagas.hospital_website.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DoctorType {
	SURGEON("Хирург"),
	THERAPIST("Терапевт"),
	ENT("Лор"),
	OPHTHAMLMOLOGITS("Окулист"),
	DERMATOLOGIST("Дерматолог"),
	OPRTHOPEDIST("Ортопед"),
	NEUROLOGIST("Невропатолог"),
	CARDIOLOGIST("Кардиолог"),
	PSYCHIATRIST("Психиатр");
	
	private final String translate;

}