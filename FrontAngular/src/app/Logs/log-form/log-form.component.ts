import { Location } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import { ActivatedRoute } from "@Angular/router";
import { map, switchMap } from "rxjs/operators";
import { LogService } from "src/app/Serviços/log.service";



@Component({
  selector: "ap-log-from",
  templateUrl: "./log-form.component.html"
})
export class LogFormComponent implements OnInit {
  formulario: FormGroup;

  constructor(
    private service: LogService,
    private formBuilder: FormBuilder,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params
      .pipe(
        map((params: any) => params["id"]),
        switchMap((id) => this.service.loadById(id))
      )
      .subscribe((log) => this.updateForm(log));

    this.formulario = this.formBuilder.group({
      id: [null],
      ip: [null,Validators.required],
      metodo: [null,Validators.required],
      userAgent: [null,Validators.required],
      data: [null,Validators.required],
      status: [null,Validators.required],
    });
  }

  updateForm(log) {
    this.formulario.patchValue({
      id: log.id,
      ip: log.ip,
      metodo: log.metodo,
      userAgent: log.userAgent,
      data: log.data,
      status: log.status,
    });
  }

  onSubmit() {
    if (!this.formulario.invalid) {
      if (this.formulario.value.id) {
        this.service.update(this.formulario.value).subscribe(() => {
          this.formulario.reset();
          alert("Log Atualizado com sucesso parabens");
          this.location.back();
        });
      } else {
        this.service
          .save(this.formulario.value)
          // .pipe(map((res) => res))
          .subscribe(() => {
            this.formulario.reset();
            alert("Log criado com sucesso parabens");
            this.location.back();
          });
      }
    }else

    alert("Preencha todos os campos");
  }
}
