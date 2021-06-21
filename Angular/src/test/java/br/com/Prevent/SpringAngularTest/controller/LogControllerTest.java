package br.com.Prevent.SpringAngularTest.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.Prevent.SpringAngular.controller.LogControler;
import br.com.Prevent.SpringAngular.dominio.Log;
import br.com.Prevent.SpringAngular.request.LogPostDto;
import br.com.Prevent.SpringAngular.request.LogPutDto;
import br.com.Prevent.SpringAngular.service.ServiçosLog;
import br.com.Prevent.SpringAngularTest.util.LogCreator;
import br.com.Prevent.SpringAngularTest.util.LogPostDtoTest;
import br.com.Prevent.SpringAngularTest.util.LogPutDtoTest;

@ExtendWith(SpringExtension.class)
public class LogControllerTest {

	@InjectMocks
	private LogControler logControler;

	@Mock
	private ServiçosLog serviceLogMock;

	@BeforeEach
	void setUp() {

		BDDMockito.when(serviceLogMock.listAll()).thenReturn(Arrays.asList(LogCreator.creatValidLog()));

		BDDMockito.when(serviceLogMock.findById(ArgumentMatchers.anyLong())).thenReturn(LogCreator.creatValidLog());

		BDDMockito.when(serviceLogMock.findByName(ArgumentMatchers.anyString()))
				.thenReturn(Arrays.asList(LogCreator.creatValidLog()));

		BDDMockito.when(serviceLogMock.save(ArgumentMatchers.any(LogPostDto.class)))
				.thenReturn(LogCreator.creatValidLog());

		BDDMockito.doNothing().when(serviceLogMock).replace(ArgumentMatchers.any(LogPutDto.class));
	}

	@Test
	@DisplayName("Metodo ListAll Retorna uma lista de Log quando sucesso")
	void ListAll_ReturnofLog() {

		String nomeEsperado = LogCreator.creatValidLog().getMetodo();

		List<Log> logList = logControler.listaAll().getBody();

		Assertions.assertThat(logList).isNotNull();

		Assertions.assertThat(logList.get(0).getMetodo()).isEqualTo(nomeEsperado);

	}

	@Test
	@DisplayName("Metodo FindById Retorna um Log a partir do Id quando sucesso")
	void FindById_ReturnOfLog() {

		Long idEsperado = LogCreator.creatValidLog().getId();

		Log logId = logControler.findById(1L).getBody();

		Assertions.assertThat(logId).isNotNull();

		Assertions.assertThat(logId.getId()).isEqualTo(idEsperado);

	}

	@Test
	@DisplayName("Metodo FindByName Retorna o nome do Log quando sucesso")
	void FindByName_ReturnListOfNameLog() {

		String NomeEsperado = LogCreator.creatValidLog().getMetodo();

		List<Log> logName = logControler.findByName("TESTE").getBody();

		Assertions.assertThat(logName).isNotNull();

		Assertions.assertThat(logName.get(0).getMetodo()).isEqualTo(NomeEsperado);

	}

	@Test
	@DisplayName("Metodo save Log quando sucesso")
	void save_ReturnLog_WhenSuccessful() {

		Long idEsperado = LogCreator.creatValidLog().getId();

		Log log = logControler.save(LogPostDtoTest.creatPost()).getBody();

		Assertions.assertThat(log).isNotNull();

		Assertions.assertThat(log.getId()).isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("Metodo update Log quando sucesso")
	void replace_WhenSuccessful() {

		Assertions.assertThatCode(() -> logControler.replace(LogPutDtoTest.creatPut())).doesNotThrowAnyException();

	}

}
