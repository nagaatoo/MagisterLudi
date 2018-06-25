package ru.MagisterLudi.MasterManager.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileModel {

    private String Id;
    private String FileName;
   // private ByteArrayInputStream = file;
}
