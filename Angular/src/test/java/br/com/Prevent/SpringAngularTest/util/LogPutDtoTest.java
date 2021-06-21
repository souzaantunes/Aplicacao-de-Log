package br.com.Prevent.SpringAngularTest.util;


import br.com.Prevent.SpringAngular.request.LogPutDto;

public class LogPutDtoTest {
	
	
	public static LogPutDto creatPut() {
		LogPutDto lPT = new LogPutDto();
		lPT.setId(LogCreator.creatLogUpdate().getId());
		lPT.setMetodo(LogCreator.creatLogUpdate().getMetodo());
	

		return lPT;
	}
	
	
	
	

}
