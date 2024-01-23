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

	public static DoctorType getByTranslate(String translate) {
		
		for(DoctorType type : values()) {
			if(type.getTranslate().equals(translate)) {
				return type;
			}
		}
		return null;
	}
}
