<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
  <head>
  	<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"> 
  </head>
  
  <body>
  <form action="list" method="get" name="f" role="formRole">
  		<input type="text" name="name" placeholder="名称" style="line-height: 26px;">
  		<button type="submit" class="btn btn-primary">查询</button>
  </form>
  <a href="edit.jsp">添加</a>
  <br><br>
    <table class="table table-striped table-hover">
    	<thead>
    		<tr>
    			<th>&nbsp;</th>
    			<th>名称</th>
    			<th>价格</th>
    			<th>生产日期</th>
    			<th>操作</th>
    		</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="toy" items="${requestScope.toys }" varStatus="rows">
    		<tr>
    			<td>${rows.index + 1 }</td>
    			<td>${toy.name }</td>
    			<td>${toy.price }</td>
    			<td><fmt:formatDate value="${toy.createDate }" pattern="yyyy-MM-dd"/></td>
    			<td>
    			<a href="findById?id=${toy.id}">修改</a>&nbsp;&nbsp;
    			<a onclick="return window.confirm('是否确认删除'+name+'?')" href="remove?id=${toy.id}">删除</a>
    			</td>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
  </body>
</html>
