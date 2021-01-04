package com.salecart.backend.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource load(String nameFile) throws MalformedURLException;

	public String save(MultipartFile file) throws IOException;

	public void delete(Optional<String> nameFile);

	public Path getPath(String nameFile);
	

}
