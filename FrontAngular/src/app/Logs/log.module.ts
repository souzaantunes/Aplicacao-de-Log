import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { LogListComponent } from "./log-list/log-list.component";
import { AppRoutingModule } from "../app.routing.module";
import { LogFormComponent } from "./log-form/log-form.component";

@NgModule({
  declarations: [LogListComponent,LogFormComponent],

  imports: [
    HttpClientModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    LogListComponent,
    AppRoutingModule,
  ],
})
export class LogModule {}
