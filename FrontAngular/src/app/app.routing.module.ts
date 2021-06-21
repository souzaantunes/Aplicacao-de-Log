import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@Angular/router";

import { LogFormComponent } from "./Logs/log-form/log-form.component";
import { LogListComponent } from "./Logs/log-list/log-list.component";
import { PesquisaLogComponent } from "./pesquisa-log/pesquisa-log.component";
import { UploadFileComponent } from "./upload-file/upload-file.component";

const routes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "home" },
  { path: "home", component: LogListComponent },
  { path: "novo", component: LogFormComponent },
  { path: "editar/:id", component: LogFormComponent },
  { path: "upload", component: UploadFileComponent },
  { path: "pesquisa", component: PesquisaLogComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  exports: [RouterModule],

  declarations: [],
})
export class AppRoutingModule {}
