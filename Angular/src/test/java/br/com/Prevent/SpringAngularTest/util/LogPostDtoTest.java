package br.com.Prevent.SpringAngularTest.util;


import br.com.Prevent.SpringAngular.request.LogPostDto;

public class LogPostDtoTest {
	
	
	public static LogPostDto creatPost() {
		LogPostDto lPD = new LogPostDto();
		lPD.setMetodo(LogCreator.creatLogSaved().getMetodo());
	

		return lPD;
	}
	
	
	
	

}
