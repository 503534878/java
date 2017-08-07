<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE>
<html>
  <head>
  	<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"> 
  </head>
  
  <body>
  	<c:if test="${empty toy }">新增</c:if>
  	<c:if test="${!empty toy }">修改</c:if>
  	
  	<form action="save" method="post" name="f" role="formRole">
  		<input type="hidden" name="id" value="${toy.id }">
  		<div class="form-group">
  			<input type="text" name="name" placeholder="名称" value="${toy.name }" class="form-control" required>
  			<small style="color: red">*</small>
  		</div>
  		<div class="form-group">
  			<input type="number" name="price" step="0.01" placeholder="价格" value="${toy.price }" class="form-control">
  		</div>
  		<c:if test="${empty toy }">
  		<div class="form-group">
  			<input type="date" name="createDate" placeholder="生产日期" value="${toy.createDate }" class="form-control">
  		</div>
  		</c:if>
  		<c:if test="${!empty toy }">
  		<div class="form-group">
  			<input type="date" name="createDate" placeholder="生产日期" value="<spring:eval expression="toy.createDate"/>" class="form-control">
  		</div>
  		</c:if>
  		<button type="submit" class="btn btn-success btn-block">保存</button>
  	</form>
  </body>
</html>
