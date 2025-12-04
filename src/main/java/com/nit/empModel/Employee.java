package com.nit.empModel;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data                 // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor    // no-arg constructor
@AllArgsConstructor   
public class Employee 
{
	private long id;
	private String name;
	private String department;
	private double salary;
	
	
	
	
}
