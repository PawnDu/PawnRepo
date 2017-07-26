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
			<label>学生姓名</label>
				<input type="text" id="istudentseekName"/><br>
			<label>性别</label>
				<select id="sex">
				<option value='1'>男</option>
				<option value='0'>女</option>
			</select><br>
			<label>学生电话</label>
				<input type="text" id="phone"/><br>
			<label>QQ</label>
				<input type="text" id="qq"/><br>
			<label>预报班级</label>
				<input type="text" id="class"/><br>
			<label>状态</label>
				<input type="text" id="status"/><br>
			<label>咨询人工作号</label>
				<input type="text" id="staffid"/><br>
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
						studentseekname:studentseekName
					}
				});
			}
		})
		
		//全查
		$("#dg").datagrid({    
		    url:'studentseek/list.do',    
		    columns:[[    
		        {field:'id',title:'编号',width:100},    
		        {field:'stuname',title:'学生名称',width:100},  
		        {field:'sex',title:'性别',width:100,formatter: function(value){
					if (value == '1'){
						return '男';
					} else if(value == '0') {
						return '女';
					}
				}	
		        	},
		        {field:'phone',title:'学生电话',width:100},
		        {field:'qq',title:'QQ',width:100},
		        {field:'stuclass',title:'预报班级',width:100},
		        {field:'status',title:'状态',width:100},
		        {field:'staffid',title:'咨询人编号',width:100},
		        {field:'staff',title:'咨询人',width:100}
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
									url:'studentseek/update.do', 
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
								url:'studentseek/add.do', 
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
						url:'studentseek/del.do', 
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
		    title: '增加学生信息',    
		    closed: true,    
		    modal: true   
		});    

	})
</script>
	