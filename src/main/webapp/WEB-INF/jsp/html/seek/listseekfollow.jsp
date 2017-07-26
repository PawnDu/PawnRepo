<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/jsp/lib.jsp"></jsp:include>
<div>
		<label>学生名称</label>
		<input type="text" id="studentseekName"/>
		<input type="button" id="query" value="查询"/>
</div>

<table id ="dg"></table>

<div id="dialog">
	<form action="#" id="from1">
		<label>咨询日期</label>
				<input id="followDate" type="text" class="easyui-datebox" ></input>  <br>
		<label>学生姓名</label>
				<input id="stuname" ><br>
		<label>咨询方式</label>
				<select id="seekFun">
						<option value="1">电话咨询</option>
						<option value="2">QQ咨询</option>
						<option value="3">微信咨询</option>
						<option value="4">会面咨询</option>
				</select><br>
		<label>咨询人员</label>
				<input id="staff" ><br>
		<label>咨询效果</label>
				<input id="seekRus" ><br>
		<input type="button" id="insert" value="提交"/>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		//模糊查询
		$("#query").click(function(){
			var studentseekName = $("#studentseekName").val();
			if(studentseekName){
				$('#dg').datagrid({
					queryParams: {
						seekfollowname:studentseekName
					}
				});
			}
		})
		
		//全查
		$("#dg").datagrid({    
		    url:'seekfollow/list.do',    
		    columns:[[    
		        {field:'seekDate',title:'日期',width:100},    
		        {field:'stuname',title:'学生名称',width:100},  
		        {field:'seekFun',title:'咨询方式',width:100},
		        {field:'NAME',title:'咨询人',width:100},
		        {field:'seekRus',title:'效果',width:100}
		    ]],
		    fitColumns: true,
		    singleSelect:true,
		    pagination:true,
		    pageList:[5,10,20,25,50],
		    toolbar: [{
		    	text:"修改",
				iconCls: 'icon-edit',
				handler: function(){					
					$("#dialog").dialog("setTitle","修改学生信息");
					$("#dialog").dialog("open");
					$("#insert").unbind();
					var row = $("#dg").datagrid("getSelected");
					var stuname = $("#istudentseekName").val(row.stuname);
					var sex = $("#sex").val(row.sex);
					var phone = $("#phone").val(row.phone);
					var qq = $("#qq").val(row.qq);
					var stuclass = $("#class").val(row.stuclass);
					var status = $("#status").val(row.status);
					var staff = $("#staff").val(row.staff);	
					if(row){											
						$("#insert").click(function(){
							var istuname = $("#istudentseekName").val();
							var isex = $("#sex").val();
							var iphone = $("#phone").val();
							var iqq = $("#qq").val();
							var istuclass = $("#class").val();
							var istatus = $("#status").val();
							var istaffid = $("#staffid").val();	
							if(studentseekName){
								$.ajax({
									url:'seekfollow/update.do', 
									data:{seekid:row.id,stuname:istuname,stusex:isex,stuphone:iphone,stuqq:iqq,stuclass:istuclass,
										stustatus:istuclass,staffid	:istaffid	
									},
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
						var stuname = $("#istudentseekName").val();
						var sex = $("#sex").val();
						var phone = $("#phone").val();
						var qq = $("#qq").val();
						var stuclass = $("#class").val();
						var status = $("#status").val();
						var staffid = $("#staffid").val();				
						if(istudentseekName){
							$.ajax({
								url:'seekfollow/add.do', 
								data:{stuname: stuname,stusex:sex,stuphone:phone,stuqq:qq,stuclass:stuclass,stustatus:status,staffid:staffid},
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
						url:'seekfollow/del.do', 
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
		    title: '增加咨询跟踪信息',    
		    closed: true,    
		    modal: true   
		});    

	})
</script>
	