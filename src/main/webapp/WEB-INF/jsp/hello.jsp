<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Create an account</title>
<style>
body {
	font-family: Arial;
}

/* Style the tab */
.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
	background-color: inherit;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	transition: 0.3s;
	font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
	background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
	background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
}
</style>
<script>
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}
</script>

</head>
<body>
	<c:if test="${pageContext.request.userPrincipal.name == null}">

		<h2>
			Please <a class="forgot" href="/">Login</a>
		</h2>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<div class="container">


			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()" href="javascript:void(0)"	>Logout</a>
			</h2>



		</div>



		<div class="tab">
			<button class="tablinks" onclick="openCity(event, 'GenerateApi')">Generate
				Api</button>
			<button class="tablinks" onclick="openCity(event, 'TinyURl')">Generate
				Tiny URl</button>
			<button class="tablinks" onclick="openCity(event, 'LongUrl')">Get
				Long URL</button>
		</div>


		<div id="GenerateApi" class="tabcontent">
			<h3>
				<button onclick="generateApi()">Generate API Key</button>
			</h3>
			<strong>Your Api key is : </strong> <span id="response"></span>
		</div>

		<div id="TinyURl" class="tabcontent">
			<form action="#" method="post" name="shortUrl" id="shortUrl">
				<h3>
					URL : <input type="text" value="" name="url" class="url">
					<button type="button" onClick="shorterUrl();">Generate
						Short URL</button>
				</h3>
			</form>
			<strong>Tiny URL : </strong> <span id="response1"></span>

		</div>

		<div id="LongUrl" class="tabcontent">

			<form action="#" method="post" name="longUrl" id="longUrl">
				<h3>
					Tiny URL : <input type="text" value="" name="url" class="url">
					<button type="button" onClick="getLongUrl();">Get Long URL</button>
				</h3>
			</form>
			<strong>Long URL : </strong> <a href=""  id="response2" target="_blank"><span id="response3"></span></a>  
			


		</div>
		
		
		




	</c:if>


	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>


	<script type="text/javascript">
function generateApi() {
	$.ajax({
     	type        : 'GET',  
         url         : '/api',
	 })
         .done(function( data ) {
     		  $("#response").html(data); 
     	  });

	
}

function shorterUrl(){
								
		            $.ajax({
		            	type        : 'POST',  
			            url         : '/shorternUrl',  
			            data        : $('#shortUrl').serialize(),  
			            dataType    : 'json',  
			            encode      : true,
		            	  beforeSend: function( xhr ) {
		            		  xhr.setRequestHeader('X-CSRF-Token', '${_csrf.token}')
		            	  },
		            	   complete: function(data){
		            		   console.log(data);
								$("#response1").html(data.responseText);
		            		   }
		            	});
		            }


function getLongUrl(){
    $.ajax({
    	type        : 'POST',  
        url         : '/longUrl',  
        data        : $('#longUrl').serialize(),  
        dataType    : 'json',  
        encode      : true,
    	  beforeSend: function( xhr ) {
    		  xhr.setRequestHeader('X-CSRF-Token', '${_csrf.token}')
    	  },
    	   complete: function(data){
    		   console.log(data);
				$("#response3").html(data.responseText);
				$("#response2").attr("href",data.responseText)
    		   }
    	});		
   
				
}

</script>
</body>
</html>