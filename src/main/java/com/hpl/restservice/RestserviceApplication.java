package com.hpl.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hpl.restservice.objects.Teams;
import com.hpl.restservice.services.MongoService;
import com.hpl.restservice.services.ObjectMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;

@SpringBootApplication public class RestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestserviceApplication.class, args);
	}

//	@PostConstruct public void createNewTeam() throws IOException {
//		URL resource = getClass().getClassLoader().getResource("data.txt");
//		assert resource != null;
//		FileReader fr=new FileReader(resource.getFile());
//		BufferedReader br=new BufferedReader(fr);
//		String line = "";
//		while ((line=br.readLine())!=null) {
//			Teams teams = null;
//			try {
//				teams = objectMapperService.getObjectMapper().readValue(line.substring(0, line.length()-1), Teams.class);
//				mongoService.addNewTeam(teams);
//			} catch (JsonProcessingException e) { }
//		}
//	}
}
