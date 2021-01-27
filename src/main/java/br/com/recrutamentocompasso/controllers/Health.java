package br.com.recrutamentocompasso.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("HEALTH API")
@RestController
@RequestMapping("api/health")
public class Health {
	
	@ApiOperation(value = "testing api")
	@GetMapping("/")
	public String getHelath(){
		return "ok!";
	}
	
}
