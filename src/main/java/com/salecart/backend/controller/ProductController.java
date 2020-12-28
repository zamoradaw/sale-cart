package com.salecart.backend.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.salecart.backend.MessageCustom;
import com.salecart.backend.model.entity.Product;
import com.salecart.backend.model.service.IProductService;
import com.salecart.backend.model.service.IUploadFileService;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	
	private MessageCustom message = new MessageCustom();
	
	@GetMapping("/listar")
	public ResponseEntity<?> index(){
		List<Product> products = productService.listAllProduct();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/nuevo")
	public ResponseEntity<?> created(@Valid @RequestBody Product product, BindingResult result){
		if(result.hasErrors())
			return message.error(result.getFieldErrors());
		
		return (productService.saveProduct(product)) ?
					message.error("Revise algun campo."):
					message.success("El product se a guardado exitosamente.");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/subir")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		boolean existsProduct = productService.ifProductExists(id);
		
		if(!file.isEmpty() && existsProduct) {
			String nameFile = null;
			Product product = productService.getProductById(id);
			Optional<String> namePhoto = Optional.ofNullable(product.getPhoto());
			
			try {
				nameFile = uploadService.save(file);
			}catch(IOException e) {
				return message.errorCatch("Error al subir foto");
			}
			
			uploadService.delete(namePhoto);
			product.setPhoto(nameFile);
			productService.saveProduct(product);
			
			return message.success("Good Job");
		}
		
		return message.error("File is empty or not exists product");
	}
	
	@GetMapping("/subir/imagen/{nameFile:.+}")
	public ResponseEntity<Resource> viewFile(@PathVariable String nameFile){

		Resource resource = null;
		
		try {
			resource = uploadService.load(nameFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(resource, cabecera, HttpStatus.OK);
	}
	
}
