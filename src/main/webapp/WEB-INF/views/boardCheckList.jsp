<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 기록</title>
<script type="text/javascript">
/* 
function ch_type(value , val1)
{
	$.ajax({
			url:"jBoardCheckList",
	        contentType : "application/x-www-form-urlencoded;charset=utf-8",
			data:{"boardtype":value},
			datatype:"json",
			success : function(msg){
				alert(msg);
				if(msg != "null")
				{
					msg = msg.substr(2 , msg.length);
					msg = msg.substr(0 , msg.length-2);
					tmp_data = msg.split("},{");
					
					str = '<select name="type" class="form-control" onchange="$(\'#test\').submit();" style="width: 180px; float: left;">';
					str += '<option value="">전체</option>';
	
					for(i=0; i< tmp_data.length;i++)
					{
						selected = "";
						data_info =tmp_data[i].split(",");
						data = data_info[2].split(":");
						data[1] = data[1].substr(1 , data[1].length);
						data[1] = data[1].substr(0 , data[1].length-1);
						if(val1 == data[1]) selected = 'selected = "selected"' 
						str += '<option ' + selected + ' value="' + data[1] + '">'+data[1]+'</option>';				
					}
					str += "</select>";
				}
				else
				{
	
				}
				
				$('#test_div').html(str);
			}
		});
}
ch_type("${bt}" , "${type}");
 */
 
 
 

 $(function(){
		$("#boardtype").change(function() {
			if($(this).val()==null && $(this).val()=="")
			{
				$('#type').css("display","none");
			}
			if($(this).val() =='0'){
				$('#type').empty();				
			}else{
				
			$.get('jBoardCheckList?boardtype=' + $(this).val(),function(data,status){
				$('#type').empty();
                var option = $("<option value=''>전체</option>");
                $('#type').append(option);
				for(var count = 0; count < data.length; count++){ 
	                var option = $("<option value='" + data[count].code +"'>" + data[count].type + "</option>");
	                $('#type').append(option);
	            }				
			});
		}
	});
 });

 
 
</script>
</head>
<body>

		<form method="post" id="test">
			<select name="boardtype" onchange="$('#test').submit();" class="form-control" style="width: 180px; float: left;">
			<!--  <select name="boardtype" id="boardtype"  class="form-control" style="width: 180px; float: left;">-->
				<option value="">-전체-</option>
				<c:forEach var="vo"  items="${alist }">
				<option value="${vo.boardtype}" <c:if test="${bt eq vo.boardtype}"> selected= "selected" </c:if> >${vo.boardtype}</option>
				</c:forEach>
			</select>
		</form>
		
		
	
	 
	<c:if test="${bt ne null }">
	
	  <form method="post" id="test2">
		<input type="hidden" name="boardtype" value="${bt}">
	 	<select name="type" class="form-control" onchange="$('#test2').submit();" style="width: 180px; float: left;">
	 		<option value="">-전체-</option>
			<c:forEach var="vo"  items="${blist}">
			<option value="${vo.type}" <c:if test="${type eq vo.type}"> selected= "selected" </c:if> >${vo.type}</option>
			</c:forEach>
	 	</select>
	</form>	
	</c:if>
	 
	
	<hr>
	
	<table class="table">
		<tr style="border: none;">
			<td colspan="6" align="left">
				<input type="button" class="btn btn-primary" value="엑셀" onclick="post_to_url('excelboardcheck', {'boardtype':'${bt}'});">
			</td>
		</tr>
		<tr>
			<th>저장공간</th>
			<th>글번호</th>
			<th>저장형태</th>
			<th>사번</th>
			<th>일자</th>
			<th>IP</th>
		</tr>
		<c:if test="${empty search}">
		<tr>
			<td colspan="6" align="center"> 비워짐</td>
		</tr>		
		</c:if>
		<c:forEach var="vo" items="${search}">
		<tr>		
			<td>${vo.boardtype}</td>			
			<td>${vo.board_idx}</td>			
			<td>${vo.type}</td>			
			<td>${vo.id_emp}</td>			
			<td><fmt:formatDate value="${vo.updatedate }" pattern="yyyy-MM-dd HH:mm"/></td>			
			<td>${vo.ip}</td>			
		</tr>		
		</c:forEach>
	</table>
		
	
</body>
</html>