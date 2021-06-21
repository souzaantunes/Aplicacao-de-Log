package br.com.Prevent.SpringAngular.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.Prevent.SpringAngular.dominio.Log;
import br.com.Prevent.SpringAngular.file.FileTratamento;
import br.com.Prevent.SpringAngular.request.LogPostDto;
import br.com.Prevent.SpringAngular.request.LogPutDto;
import br.com.Prevent.SpringAngular.service.ServiçosLog;


@RestController
@RequestMapping("logs")
public class LogControler {
	private ServiçosLog servicelog = new ServiçosLog();

	@GetMapping
	public ResponseEntity<List<Log>> listaAll() {

		return ResponseEntity.ok(servicelog.listAll());

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Log> findById(@PathVariable long id) {

		return ResponseEntity.ok(servicelog.findById(id));
	}

	@PostMapping
	public ResponseEntity<Log> save(@RequestBody LogPostDto dtoPost) {

		return new ResponseEntity<>(servicelog.save(dtoPost), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody LogPutDto logPutDto) {

		servicelog.replace(logPutDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<Log>> findByName(@RequestParam String metodo) {

		return ResponseEntity.ok(servicelog.findByName(metodo));
	}

	@PostMapping(path = "/import")
	public ResponseEntity<Void> importFile(@RequestBody MultipartFile file) {

		try {

			FileTratamento fileTratamento = new FileTratamento();
			fileTratamento.writeFile(file.getInputStream(), file.getName());
			Thread.sleep(2000);
			fileTratamento.readFile();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

}
