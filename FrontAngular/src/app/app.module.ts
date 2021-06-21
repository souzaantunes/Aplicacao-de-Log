import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@Angular/router";


import { UploadFileComponent } from "./upload-file/upload-file.component";
import { LogModule } from "./Logs/log.module";
import { LogService } from "./Serviços/log.service";
import { AppComponent } from "./app.component";
import { PesquisaLogComponent } from "./pesquisa-log/pesquisa-log.component";




@NgModule({
  declarations: [
    AppComponent,
    PesquisaLogComponent,
    UploadFileComponent
    ],

  imports: [
    BrowserModule,
    HttpClientModule,
    LogModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,

  ],
  providers: [LogService],
  bootstrap: [AppComponent],
})
export class AppModule {}
