package br.com.Prevent.SpringAngular.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Scanner;


import br.com.Prevent.SpringAngular.dominio.Log;
import br.com.Prevent.SpringAngular.persistence.PersistenceLog;
import br.com.Prevent.SpringAngular.util.DateUtil;

public class FileTratamento {
	private String nomeArquivo;

	public void readFile() {

		DateUtil util = new DateUtil();
		PersistenceLog pl = new PersistenceLog();

		try {
			Scanner scan = new Scanner(new File(nomeArquivo + ".txt"));

			while (scan.hasNextLine()) {
				Log log = new Log();
				String linha = scan.nextLine();
				String[] split = linha.split("\\|");

				for (int i = 0; i < split.length; i++) {
					String string = split[i];

					if (i == 0) {

						log.setData(util.formatLocalDateTime(string));

					} else if (i == 1) {

						log.setIp(string);

					} else if (i == 2) {

						log.setMetodo(string);

					} else if (i == 3) {

						log.setStatus(string);

					} else {

						log.setUserAgent(string);

					}
				}

				pl.salvar(log);

			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void writeFile(InputStream input, String nome) {

		this.nomeArquivo = nome;
		try {
			Reader ir = new InputStreamReader(input, Charset.defaultCharset());
			BufferedReader bf = new BufferedReader(ir);

			OutputStream fole = new FileOutputStream(nomeArquivo + ".txt");
			Writer wir = new OutputStreamWriter(fole);
			BufferedWriter bw = new BufferedWriter(wir);

			String leitura = bf.readLine();

			while (leitura != null && !leitura.isEmpty()) {
				bw.write(leitura);
				bw.newLine();
				leitura = bf.readLine();

			}

			bw.close();
			bf.close();

		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}