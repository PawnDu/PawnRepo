<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/jsp/lib.jsp"></jsp:include>
<div>
		<label>职位名称</label>
		<input type="text" id="positionName"/>
		<input type="button" id="query" value="查询"/>
</div>

<table id ="dg"></table>

<div id="dialog">
	<form action="#" id="from1">
			<label>部门名称</label>
			<select id="deptId">		
			</select><br/>
			<label>职位名称</label>			
			<input type="text" id="ipositionName"/><br/>
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
		
		
		
		
		//模糊查询
		$("#query").click(function(){
			var positionName = $("#positionName").val();
			if(positionName){
				$('#dg').datagrid({
					queryParams: {
						positionname:positionName
					}
				});
			}
		})
		
		//全查
		$("#dg").datagrid({    
		    url:'position/list.do',    
		    columns:[[    				
		        {field:'deptName',title:'部门名称',width:100},
		        {field:'positionName',title:'职位名称',width:100},   
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
						$("#ipositionName").val(row.positionName);
						$("#deptId").val(row.deptId);
						$("#insert").click(function(){
							var positionName = $("#ipositionName").val();
							var deptId = $("#deptId").val();
							if(positionName){
								$.ajax({
									url:'position/update.do', 
									data:{id:row.id,positionName: positionName,deptId:deptId},
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
						var iDeptId = $("#deptId").val();
						var ipositionName = $("#ipositionName").val();	
						if(ipositionName){
							$.ajax({
								url:'position/add.do', 
								data:{deptId:iDeptId,positionName: ipositionName},
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
					//利用ajax处理请求
					$.ajax({
						url:'position/del.do', 
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
		    title: '增加职位',    
		    closed: true,    
		    modal: true   
		});    

	})
</script>
	