package com.visitor.vmsvisitorservice;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VmsVisitorServiceApplication {

	public static ArrayList<String> arlist = new ArrayList<String>();
	public static void main(String[] args) {


		arlist.add("Gopish.Pillai@quest-global.com");
		arlist.add("anushree.sonawane@mobiliya.com");
		arlist.add("ekta@gmail.com");
		SpringApplication.run(VmsVisitorServiceApplication.class, args);
	}

}
