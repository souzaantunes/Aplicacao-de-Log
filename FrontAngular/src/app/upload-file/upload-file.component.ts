import { Component, OnInit } from "@angular/core";
import { Location } from "@angular/common";
import { LogService } from "../Serviços/log.service";

@Component({
  selector: "ap-upload-file",
  templateUrl: "./upload-file.component.html",
})
export class UploadFileComponent implements OnInit {
  files: Set<File>;

  constructor(private service: LogService, private location: Location) {}

  ngOnInit() {}

  onChange(event) {
    console.log(event);

    const selectFile = <FileList>event.srcElement.files;

    const fileNames = [];
    this.files = new Set();

    for (let i = 0; i < selectFile.length; i++) {
      fileNames.push(selectFile[i].name);
      this.files.add(selectFile[i]);
    }
    document.getElementById("customFilelabel").innerHTML = fileNames.join(",");
  }
  onUpload() {

    alert("O seu arquivo esta sendo processado ")
    this.location.back();
    this.service
      .uploadFiles(this.files)
      .subscribe((response) => alert("Upload concluido"));


}
}
