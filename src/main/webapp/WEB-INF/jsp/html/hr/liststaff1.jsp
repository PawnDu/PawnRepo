<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/jsp/lib.jsp"></jsp:include>
<div>
		<label>员工姓名</label>
		<input type="text" id="staffName"/>
		<input type="button" id="query" value="查询"/>
</div>

<table id ="dg"></table>

<div id="dialog">
	<form action="#" id="from1">
			<label>姓名</label>
			<input type="text" id="name"/><br/>
			<label>性别</label>
			<select id="sex">
				<option value ="1">男</option>
				<option value ="0">女</option>
			</select><br/>
			<label>出生日期</label>
			<input id="birth" type="text" name="birthday"  class="easyui-datebox" ></input>  <br/>
			<label>入职日期</label>
			<input id="entry" type="text" name="birthday"  class="easyui-datebox" ></input>  <br/>
			<label>部门名称</label>
			<select id="deptId">		
			</select><br/>
			<label>职位名称</label>			
			<select id="iposition">
			<option >--请选择--</option>
		</select><br>
		<input type="button" id="insert" value="提交"/>
	</form>
</div>

<script type="text/javascript">

	$(function(){
		//select标签中获取所有的部门
		$.ajax({
			 url:'dept/list.do',
			 data:{rows:100},//显示所有部门，默认显示3个
			success:function(data){
				if(data.rows){
					//清空select中的option标签
					$("#deptId").empty();
					//迭代data.rows
					$(data.rows).each(function(){
						//this指的是rows中的每一条数据
						$("#deptId").append(	"<option value="+this.id+">"+this.deptName+"</option>");	
					})
				}
			}
		})
		$('#dialog').dialog({    
		    title: '增加员工',    
		    closed: true,    
		    modal: true   
		});    
		//change时间，当选择部门时，出现相对应的职位名称
		$("#deptId").change(function(){
			var isdeptid = $("#deptId").val();
			$.ajax({
				url:'position/getPositionByDept.do',
				data:{deptId:isdeptid},
				success:function(data){
					$("#iposition").empty();
					$(data.rows).each(function(){
						//this指的是 rows里面每一条数据
						$("#iposition").append('<option value='+this.id+'>'+this.positionName+'</option>')
					})
				}
			})
			})
		});
		
		//模糊查询
		$("#query").click(function(){
			var staffName = $("#staffName").val();
			if(staffName){
				$('#dg').datagrid({
					queryParams: {
						staffname:staffName
					}
				});
			}
		})
		
		//全查
		$("#dg").datagrid({    
		    url:'staff/list.do',    
		    columns:[[    				
		        {field:'id',title:'编号',width:100},  
		        {field:'staffName',title:'员工姓名',width:100},  
		        {field:'sex',title:'性别',width:100},  
		        {field:'birthday',title:'生日',width:100},  
		        {field:'entry',title:'入职日期',width:100},
		        {field:'deptName',title:'部门名称',width:100},
		        {field:'positionName',title:'职位名称',width:100}
		    ]],
		 	//fit:true,
		    fitColumns: true,
		    singleSelect:true,
		    pagination:true,
		    pageList:[10,20,30,40,50],
		    toolbar: [{
		    	text:"修改",
				iconCls: 'icon-edit',
				handler: function(){					
					$("#dialog").dialog("setTitle","修改职位");
					$("#dialog").dialog("open");
					$("#insert").unbind();
					var row = $('#dg').datagrid('getSelected');                    ;
					if(row){					
						//设置回显
						$("#istaffName").val(row.staffName);
						$("#deptId").val(row.deptId);
						$("#insert").click(function(){
							var staffName = $("#istaffName").val();
							var deptId = $("#deptId").val();
							if(staffName){
								$.ajax({
									url:'staff/update.do', 
									data:{id:row.id,staffName: staffName,deptId:deptId},
									success:function(data){
										if(data.flag){
											$("#dg").datagrid("reload");
											$("#dialog").dialog("close");
										}
									}
								})
							}						
						})
					}
				}
			},{
				text:"增加",
				iconCls: 'icon-add',
				handler: function(){
					$("#dialog").dialog("open");
					$("#insert").unbind();
					$("#insert").click(function(){
						var name = $("#name").val();
						var sex = $("#sex").val();
						var birth = $("#birth").val();
						var entry = $("#entry").val();
						var deptId = $("#deptId").val();
						var istaffName = $("#istaffName").val();	
							$.ajax({
								url:'staff/add.do', 
								data:{name:name,sex:sex,birthday:birthday,entry:entry,positionId:ipositionid},
								success:function(data){
									if(data.flag){
										$("#dg").datagrid("reload");
										$("#dialog").dialog("close");
									}
								}
							})					
					})
				}
			},{
				text:"删除",
				iconCls: 'icon-remove',
				handler: function(){
					//抓取到被选择的那行
					var row = $("#dg").datagrid("getSelected");
					//利用ajax处理请求
					$.ajax({
						url:'staff/del.do', 
						data:{id : row.id},
						success:function(data){
							if(data.flag){
								$("#dg").datagrid("reload");
							}
						}
					})
				}
			}]    
		});

</script>
	