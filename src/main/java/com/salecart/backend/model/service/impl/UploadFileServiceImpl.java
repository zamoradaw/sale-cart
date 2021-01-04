package com.salecart.backend.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salecart.backend.model.service.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService{
	
	private final static String PATH_URL = "C://Users//henry//Pictures//File";

	@Override
	public Resource load(String nameFile) throws MalformedURLException {
		Path routeFile = getPath(nameFile);
		
		Resource resource = new UrlResource(routeFile.toUri());
		
		if(!resource.exists() && !resource.isReadable()) {
			String nameImg = "default.png";
			routeFile = Paths.get(PATH_URL).resolve(nameImg).toAbsolutePath();
			
			resource = new UrlResource(routeFile.toUri());
		}
		
		return resource;
	}

	@Override
	public String save(MultipartFile file) throws IOException {
		String nameRandom = UUID.randomUUID().toString();
		String nameReplaced = file.getOriginalFilename().replace(" ", "");
		
		String nameFile = nameRandom.concat(nameReplaced); 
		Path routeFile = getPath(nameFile);
		
		Files.copy(file.getInputStream(), routeFile);
		return nameFile;
	}

	@Override
	public void delete(Optional<String> nameFile) {
		
		if(nameFile.isPresent() && nameFile.get().length() > 0) {
			Path routePhotoPrevious = getPath(nameFile.get());
			File filePhotoPrevious = routePhotoPrevious.toFile();
			
			if(filePhotoPrevious.exists() && filePhotoPrevious.canRead()) {
				filePhotoPrevious.delete();
			}
		}
	}

	@Override
	public Path getPath(String nameFile) {
		return Paths.get(PATH_URL).resolve(nameFile).toAbsolutePath();
	}

}
