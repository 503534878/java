package com.toy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.toy.pojo.Toy;

@Repository("toyMapper")
public interface ToyMapper {
	@Insert("insert into toy(name,price,createDate) values(#{name},#{price,jdbcType=DOUBLE},#{createDate,jdbcType=DATE})")
	public int add(Toy toy);
	@Delete("delete from toy where id=#{id}")
	public int remove(Toy toy);
	@Update("update toy set name=#{name},price=#{price,jdbcType=DOUBLE},createDate=#{createDate,jdbcType=DATE} where id=#{id}")
	public int modify(Toy toy);
	
	public List<Toy> list(
			@Param("name") String name,
			@Param("sort") String sort,
			@Param("order") String order,
			@Param("page") Integer page,
			@Param("rows") Integer rows);
	@Select("select id,name,price,createDate from toy where id=#{id}")
	public Toy findById(Integer id);
}
