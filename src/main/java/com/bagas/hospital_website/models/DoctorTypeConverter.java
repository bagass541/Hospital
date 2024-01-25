package com.bagas.hospital_website.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
@Component
public class DoctorTypeConverter implements AttributeConverter<DoctorType, String>{

	@Override
	public String convertToDatabaseColumn(DoctorType attribute) {
		return attribute.getTranslate();
	}

	@Override
	public DoctorType convertToEntityAttribute(String dbData) {
		return DoctorType.getByTranslate(dbData);
	}
		
}
