<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/jsp/lib.jsp"></jsp:include>
<div>
		<label>部门名称</label>
		<input type="text" id="deptName"/>
		<input type="button" id="query" value="查询"/>
</div>

<table id ="dg"></table>

<div id="dialog">
	<form action="#" id="from1">
			<label>部门名称</label>
			<input type="text" id="ideptName"/>
		<input type="button" id="insert" value="提交"/>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		//模糊查询
		$("#query").click(function(){
			var deptName = $("#deptName").val();
			if(deptName){
				$('#dg').datagrid({
					queryParams: {
						deptname:deptName
					}
				});
			}
		})
		
		//全查
		$("#dg").datagrid({    
		    url:'dept/list.do',    
		    columns:[[    
		        {field:'id',title:'ID',width:100},    
		        {field:'deptName',title:'部门名称',width:100},    	         
		    ]],
		   // fit:true,
		    fitColumns: true,
		    singleSelect:true,
		    pagination:true,
		    pageList:[5,10,20,25,50],
		    toolbar: [{
		    	text:"修改",
				iconCls: 'icon-edit',
				handler: function(){					
					$("#dialog").dialog("setTitle","修改部门");
					$("#dialog").dialog("open");
					$("#insert").unbind();
					var row = $("#dg").datagrid("getSelected");
					if(row){					
						$("#ideptName").val(row.deptName);
						$("#insert").click(function(){
							var deptName = $("#ideptName").val();
							if(deptName){
								$.ajax({
									url:'dept/update.do', 
									data:{id:row.id,deptname: deptName},
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
						var ideptName = $("#ideptName").val();	
						alert(ideptName);
						if(ideptName){
							$.ajax({
								url:'dept/add.do', 
								data:{deptName: ideptName},
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
			},{
				text:"删除",
				iconCls: 'icon-remove',
				handler: function(){
					//抓取到被选择的那行
					var row = $("#dg").datagrid("getSelected");
					alert(row.id);
					//利用ajax处理请求
					$.ajax({
						url:'dept/del.do', 
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
		$('#dialog').dialog({    
		    title: '增加部门',    
		    closed: true,    
		    modal: true   
		});    

	})
</script>
	