package br.com.Prevent.SpringAngularTest.persistence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.Prevent.SpringAngular.dominio.Log;
import br.com.Prevent.SpringAngular.persistence.PersistenceLog;
import br.com.Prevent.SpringAngularTest.util.LogCreator;

class PersistenceLogTest {
	PersistenceLog persistenceLog = new PersistenceLog();

	@Test
	@DisplayName("Verificando se salva o Log")
	public void Log_saved() {

		Log log = LogCreator.creatLogSaved();

		Log logSaved = this.persistenceLog.salvar(log);
		Assertions.assertThat(logSaved).isNotNull();
		Assertions.assertThat(logSaved.getMetodo()).isNotNull();

	}
}
