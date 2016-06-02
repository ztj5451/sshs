<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="../tree/treeAdd.action"
		class="pageForm required-validate"
		onsubmit=""return validateCallback(this, dialogAjaxDone);">
		<div layoutH="40"
			style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
			<ul class="tree treeFolder collapse"  id="add_list">
			</ul>
		</div>
		<div class="pageFormContent" layoutH="84"
			style="width: 60%;text-align: center;float: left;">


			<!-- 第一列 -->
			<div>
				
				<p>
					<label>父目录:</label> <input name="pname"  id='pname'  type="text" size="30"
						class="required" alt="请选择父目录"  />
				</p>
				<p>
					<label>父目录pid:</label> <input name="pid"  id='pid'  type="text" size="30"
						class="required" alt="父目录的id" value="22" />
				</p>
				<p>
					<label>父目录id:</label> <input name="ids"  type="text" size="30"
						class="required" alt="父目录的id" value="" />
				</p>
				<p>
					<label>子目录:</label> <input name="name" type="text" size="30"
						class="required" alt="请输入子目录名称"  />
				</p>
				<p>
					<label>排  序:</label> <input name="orders" type="text" size="30"
						class="required" alt="请输入排序id" >
				</p>
			

			</div>

		</div>


		<!-- 底部保存、取消按钮 -->
		<div class="formBar">

			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>

	</form>
</div>
<script type="text/javascript">
		var organizations = ${requestScope.message};
	var $root = $("#add_list");
	var message;
	for ( var i = 0; i < organizations.length; i++) {
		var object = organizations[i];

		if (object.pid == 0) {
			message = '<li ><a href="javascript"  >'
					+ object.name + '<input type="text" value="'+object.id+'" style="display:none;"></a><ul>';
		} else {
			message += '<li ><a id="test"  rel="jbsxBox" >'
					+ object.name + '<input type="text" value="'+object.id+'" style="display:none;"></a></li>';

		}

	}
	message += '</ul></li>';
	$root[0].innerHTML = message;
	
	
		$(document).ready(function(){ 
	    $("a").click(function(){ 
		$("#pid").val($(this).find("input").val());
		$("#pname").val($(this).text());
		}); 
		
});

</script>
