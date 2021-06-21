package br.com.Prevent.SpringAngularTest.util;

import br.com.Prevent.SpringAngular.dominio.Log;

public class LogCreator {

	public static Log creatLogSaved() {
		Log log = new Log();
		log.setMetodo("TESTE");
	

		return log;

	}

	public static Log creatValidLog() {
		Log log = new Log();
		log.setId(1L);
		log.setMetodo("TESTE");
	

		return log;
	}

	public static Log creatLogUpdate() {
		Log log = new Log();
		log.setId(1L);
		log.setMetodo(" atualizado");

		return log;
	}

}