package com.toy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.toy.dao.ToyMapper;
import com.toy.pojo.Toy;
@Service("toyService")
public class ToyService {
	@Resource(name="toyMapper")
	private ToyMapper toyMapper;
	
	public int add(Toy toy){
		return toyMapper.add(toy);
	}
	
	public int remove(Toy toy){
		return toyMapper.remove(toy);
	}
	
	public int modify(Toy toy){
		return toyMapper.modify(toy);
	}
	
	public Toy findById(Integer id){
		return toyMapper.findById(id);
	}
	
	public List<Toy> list(String name,String sort,String order,Integer page,Integer rows){
		return toyMapper.list(name,sort,order,page,rows);
	}
}
