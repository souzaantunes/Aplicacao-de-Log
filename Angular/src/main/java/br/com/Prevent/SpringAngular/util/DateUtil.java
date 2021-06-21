package br.com.Prevent.SpringAngular.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	public LocalDateTime formatLocalDateTime(String string) {

		return LocalDateTime.parse(string, formatter);
	}
}
