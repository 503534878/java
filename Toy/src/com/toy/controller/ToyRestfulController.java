package com.toy.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toy.util.JsonDateValueProcessor;
import com.toy.pojo.Toy;
import com.toy.service.ToyService;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

@Controller
public class ToyRestfulController {
	@Resource(name="toyService")
	private ToyService toyService;
	
	@RequestMapping(value="toy",method=RequestMethod.GET,
			produces="application/json;charset=utf-8")
	@ResponseBody
	public String list(@RequestParam(value="name",required=false) String name){
		List<Toy> toys = toyService.list(name, null, null, 0, 3);
		JsonConfig jc = new JsonConfig();
		jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSON json = JSONSerializer.toJSON(toys, jc);
		return json.toString();
	}
	
	@RequestMapping(value="toy/{id}",produces="application/json;charset=utf-8")
	@ResponseBody
	public String findById(@PathVariable("id") Integer id){
		Toy toy = toyService.findById(id);
		JsonConfig jc = new JsonConfig();
		jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSON json = JSONSerializer.toJSON(toy, jc);
		return json.toString();
	}
	
	@RequestMapping(value="toy/{id}",method=RequestMethod.DELETE,produces="application/json;charset=utf-8")
	@ResponseBody
	public String remove(@PathVariable("id") Integer id){
		Toy toy = toyService.findById(id);
		int count = toyService.remove(toy);
		return String.valueOf(count);
	}
	
	@RequestMapping(value="toy",method=RequestMethod.PUT,produces="application/json;charset=utf-8")
	@ResponseBody
	public String modify(Toy toy){
		int count = toyService.modify(toy);
		return String.valueOf(count);
	}
	
	@RequestMapping(value="toy",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String add(Toy toy){
		int count = toyService.add(toy);
		return String.valueOf(count);
	}
}
