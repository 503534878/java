package com.toy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.pojo.Toy;
import com.toy.service.ToyService;

@Controller
public class ToyController {
	@Resource(name="toyService")
	private ToyService toyService;
	
	@RequestMapping("list")
	public String list(
			@RequestParam(value="name",required=false) String name,
			ModelMap modelMap){
		List<Toy> toys = toyService.list(name, null, null, 0, 3);
		modelMap.put("toys", toys);
		return "list";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Toy toy){
		if(toy!=null&&toy.getId()!=null){
			toyService.modify(toy);
		}else{
			toyService.add(toy);
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="findById")
	public ModelAndView findById(@RequestParam("id") Integer id){
		Toy t = toyService.findById(id);
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject(t);
		return mv;
	}
	
	@RequestMapping(value="remove")
	public String remove(@RequestParam("id") Integer id){
		Toy t = toyService.findById(id);
		toyService.remove(t);
		return "redirect:list";
	}
}
