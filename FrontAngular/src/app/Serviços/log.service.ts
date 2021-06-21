import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { take } from "rxjs/operators";
import { Log } from "../Logs/Dominio/Log.component";

const requestById = "http://localhost:8080/logs/";
const requestBack = "http://localhost:8080/logs";
const requestImport = "http://localhost:8080/logs/import";
const requestparams = "http://localhost:8080/logs/find?metodo="

@Injectable({ providedIn: "root" })
export class LogService {
  constructor(private http: HttpClient) {}

  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      "Access-Control-Allow-Methods": "GET, POST,PUT",
    }),
  };

  listAll() {
    return this.http.get<Log[]>(requestBack).pipe(take(1));
  }

  loadById(id) {
    return this.http.get(requestById + id).pipe(take(1));
  }

  save(formulario) {
    return this.http
      .post(requestBack, JSON.stringify(formulario), this.httpOptions)
      .pipe(take(1));
  }

  uploadFiles(files: Set<File>) {
    const formData = new FormData();

    files.forEach((file) => formData.append("file", file, file.name));

    return this.http.post(requestImport, formData);
  }

  update(formulario) {
    return this.http
      .put(requestBack, formulario, this.httpOptions)
      .pipe(take(1));
  }

  pesquisaLog(params){

    return this.http.get<Log[]>(requestparams + params)
    .pipe(take(1));
  }
}
