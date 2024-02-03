package com.bagas.hospital_website.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Класс-конвертер для преобразования типа DoctorType в строку и обратно
 * Реализует интерфейс AttributeConverter<DoctorType, String>
 */

@Converter(autoApply = true)
@Component
public class DoctorTypeConverter implements AttributeConverter<DoctorType, String>{

	
	/**
	 * Преобразует значение типа DoctorType в строку для сохранения в базе данных.
	 * 
	 * @param attribute Значение типа DoctorType.
	 * @return Строкове представление значения типа DoctorType.
	 */
	@Override
	public String convertToDatabaseColumn(DoctorType attribute) {
		return attribute.getTranslate();
	}

	/**
	 * Преобразует строку, полученную из базы данных, обратно в значение типа DoctorType.
	 * 
	 * @param dbData Строковое представление значения типа DoctorType из базы данных.
	 * @return Значение типа DoctorType, соответствующее переданной строке, или null, если такого значения нет.
	 */
	@Override
	public DoctorType convertToEntityAttribute(String dbData) {
		return DoctorType.getByTranslate(dbData);
	}
		
}
