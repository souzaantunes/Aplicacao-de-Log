import { Component, OnInit } from "@angular/core";
import {FormControl} from "@angular/forms";

import {
  debounceTime,
  distinctUntilChanged,
  filter,
  map,
  switchMap,
  tap,
} from "rxjs/operators";

import { Log } from "../Logs/Dominio/Log.component";
import { LogService } from "../Serviços/log.service";

@Component({
  selector: "ap-Pesquisa-Log",
  templateUrl: "./pesquisa-log.component.html",
  styleUrls: ["./pesquisa-log.component.css"],
})
export class PesquisaLogComponent implements OnInit {
  busca = new FormControl();
  total: number = 0;
  resultados: Log[];

  constructor(
    private logservice: LogService
  ) {}

  ngOnInit() {
    this.busca.valueChanges
      .pipe(
        map((value) => value.trim()),
        filter((value) => value.length > 2),
        debounceTime(200),
        distinctUntilChanged(),
        switchMap((value) => this.logservice.pesquisaLog(value)),
        tap((value: any) => (this.total = value.length))
      )
      .subscribe((value) => (this.resultados = value));
  }

  onSearch() {
    let value = this.busca.value;
    if (!this.busca.invalid) {
      if (value && (value = value.trim()) != "") {
        this.total = value.length;

        this.logservice
          .pesquisaLog(value)
          .pipe(tap((value: any) => (this.total = value.length)))
          .subscribe((results) => (this.resultados = results));
      }
    } else alert("Preencha o campo pesquisa");
  }
}
