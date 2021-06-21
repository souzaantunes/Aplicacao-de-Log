import { Component, OnInit } from "@angular/core";
import { Router } from "@Angular/router";

import { LogService } from "src/app/Serviços/log.service";
import { Log } from "../Dominio/Log.component";

@Component({
  selector: "ap-log-list",
  templateUrl: "./log-list.component.html",
})
export class LogListComponent implements OnInit {
  imports: Log[];

  constructor(private service: LogService, private router: Router) {}

  ngOnInit() {

    this.service.listAll().subscribe((imports) => (this.imports = imports));
  }

  onEdit(id) {
    this.router.navigateByUrl("editar/" + id);
  }


}


