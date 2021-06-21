package br.com.Prevent.SpringAngular.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.Prevent.SpringAngular.dominio.Log;
import br.com.Prevent.SpringAngular.persistence.PersistenceLog;
import br.com.Prevent.SpringAngular.request.LogPutDto;
import br.com.Prevent.SpringAngular.request.LogPostDto;

@Service
public class ServiçosLog {

	private final PersistenceLog perLog = new PersistenceLog();

	public List<Log> listAll() {

		return perLog.listAll();

	}

	public Log save(LogPostDto dtoPost) {

		Log log = new Log();

		log.setIp(dtoPost.getIp());
		log.setMetodo(dtoPost.getMetodo());
		log.setData(dtoPost.getData());
		log.setStatus(dtoPost.getStatus());
		log.setUserAgent(dtoPost.getUserAgent());

		

		return perLog.salvar(log);
	}

	public void replace(LogPutDto logPutDto) {

		Log log = new Log();

		Log findByid = perLog.findByid(logPutDto.getId());

		log.setId(findByid.getId());
		log.setIp(logPutDto.getIp());
		log.setMetodo(logPutDto.getMetodo());
		log.setData(logPutDto.getData());
		log.setStatus(logPutDto.getStatus());
		log.setUserAgent(logPutDto.getUserAgent());

		perLog.update(log);

	}

	public Log findById(long id) {
		return perLog.findByid(id);

	}

	public List<Log> findByName(String metodo) {

		return perLog.findByName(metodo);
	}

}
