package com.toy.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.toy.pojo.Toy;
import com.toy.service.ToyService;

public class ToyTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ToyService service = (ToyService) ctx.getBean("toyService");
//		Toy t = new Toy(null,"ToyCar",99d,new Date());
//		service.add(t);
//		List<Toy> toys = service.list(null,null,"id", 0, 3);
//		for (Toy toy : toys) {
//			System.out.println(toy);
		
		Toy t = new Toy(3,null,null,null);
		service.remove(t);
		}
	}

