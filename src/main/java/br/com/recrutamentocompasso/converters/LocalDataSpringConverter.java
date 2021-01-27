package br.com.recrutamentocompasso.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class LocalDataSpringConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String value) {
		if(value != null) {
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(value, formatter);
		}else {
			return null;
		}
	}

}
