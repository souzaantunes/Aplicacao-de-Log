package br.com.Prevent.SpringAngularTest.service;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.Prevent.SpringAngular.dominio.Log;
import br.com.Prevent.SpringAngular.persistence.PersistenceLog;

import br.com.Prevent.SpringAngular.service.ServiçosLog;
import br.com.Prevent.SpringAngularTest.util.LogCreator;
import br.com.Prevent.SpringAngularTest.util.LogPostDtoTest;
import br.com.Prevent.SpringAngularTest.util.LogPutDtoTest;

@ExtendWith(SpringExtension.class)
class ServiçosLogTest {

	@InjectMocks
	private ServiçosLog serviceLog;

	@Mock
	private PersistenceLog persistenceLogMock;

	@BeforeEach
	void setUp() {

		BDDMockito.when(persistenceLogMock.listAll()).thenReturn(Arrays.asList(LogCreator.creatValidLog()));

		BDDMockito.when(persistenceLogMock.findByid(ArgumentMatchers.anyLong())).thenReturn(LogCreator.creatValidLog());

		BDDMockito.when(persistenceLogMock.findByName(ArgumentMatchers.anyString()))
				.thenReturn(Arrays.asList(LogCreator.creatValidLog()));

		BDDMockito.when(persistenceLogMock.salvar(ArgumentMatchers.any(Log.class)))
				.thenReturn(LogCreator.creatValidLog());

		BDDMockito.doNothing().when(persistenceLogMock).update(ArgumentMatchers.any(Log.class));
	}

	@Test
	@DisplayName("Metodo ListAll Retorna uma lista de Log quando sucesso")
	void ListAll_ReturnofLog() {

		String nomeEsperado = LogCreator.creatValidLog().getMetodo();

		List<Log> logList = serviceLog.listAll();

		Assertions.assertThat(logList).isNotNull();

		Assertions.assertThat(logList.get(0).getMetodo()).isEqualTo(nomeEsperado);

	}

	@Test
	@DisplayName("Metodo FindById Retorna um Log a partir do Id quando sucesso")
	void FindById_ReturnOfLog() {

		Long idEsperado = LogCreator.creatValidLog().getId();

		Log logId = serviceLog.findById(83L);

		Assertions.assertThat(logId).isNotNull();

		Assertions.assertThat(logId.getId()).isEqualTo(idEsperado);

	}

	@Test
	@DisplayName("Metodo FindByName Retorna o nome do Log quando sucesso")
	void FindByName_ReturnListOfNameLog() {

		String NomeEsperado = LogCreator.creatValidLog().getMetodo();

		List<Log> logName = serviceLog.findByName("TESTE");

		Assertions.assertThat(logName).isNotNull();

		Assertions.assertThat(logName.get(0).getMetodo()).isEqualTo(NomeEsperado);

	}

	@Test
	@DisplayName("Metodo save Log quando sucesso")
	void save_ReturnLog_WhenSuccessful() {

		Long idEsperado = LogCreator.creatValidLog().getId();

		Log log = serviceLog.save(LogPostDtoTest.creatPost());

		Assertions.assertThat(log).isNotNull();

		Assertions.assertThat(log.getId()).isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("Metodo update Log quando sucesso")
	void replace_WhenSuccessful() {

		Assertions.assertThatCode(() -> serviceLog.replace(LogPutDtoTest.creatPut())).doesNotThrowAnyException();

	}

}
