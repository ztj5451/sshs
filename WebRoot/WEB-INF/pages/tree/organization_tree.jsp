<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>

<div class="pageContent">
	<div class="tabs">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="../tree/toAdd.action" target="dialog"   width="654" height="400" fresh="false" resizable="false"><span>添加</span>
				</a>
				</li>
				<li><a class="delete" href="../user/dele.action?uid={sid_user}"
					target="ajaxTodo" title="确定要删除吗?"><span>删除</span> </a>
				</li>
				<li><a class="edit"
					href="../user/queryOne.action?uid={sid_user}" target="navTab"><span>修改</span>
				</a>
				</li>
				<li class="line">line</li>
				<li><a class="icon" href="demo/common/dwz-team.xls"
					target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span>
				</a>
				</li>
			</ul>
		</div>

		<div class="tabsContent">
			<div>
				<div layoutH="40" 
					style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
					<ul class="tree treeFolder collapse" id="root">
					</ul>
				</div>
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>

</div>
<script>

	var organizations = ${requestScope.message};
	var $root = $("#root");

	var message;
	for ( var i = 0; i < organizations.length; i++) {
		var object = organizations[i];

		if (object.pid == 0) {
			message = '<li ><a href="javascript"  >'
					+ object.name + '<input type="text" value="'+object.id+'" style="display:none;"></a><ul>';
		} else {
			
			message += '<li ><a id="test" href="" target="ajax" rel="jbsxBox" >'
					+ object.name + '<input type="text" value="'+object.id+'" style="display:none;"></a></li>';

		}

	}
	message += '</ul></li>';
	$root[0].innerHTML = message;
	//alert(organizations);
	
	$(document).ready(function(){ 
	$("a").click(function(){ 
		//alert($(this).text()); 
		//alert($(this).find("input").val());
		}); 
	
});
 
</script>



