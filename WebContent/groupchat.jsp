<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% String WsUrl = getServletContext().getInitParameter("WsUrl");
    String current_username = session.getAttribute("UserName").toString();
    String id = request.getParameter("id");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Whatsapp Web</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<link
			rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
			integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
			crossorigin="anonymous"
		/>
    <link rel="stylesheet" href="css/whatsapp-web.css">
    
    <style>
			.btn-file {
    position: relative;
    overflow: hidden;
}
.btn-file input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
}
		</style>
</head>
<body class="container-fluid py-1">
    
        <div class="row">
					<div class="col-md-4">
						<div class="card-group mb-0 p-0">
							<div class="shadow border-0 card" style="height: 600px">
								<div class="card-body p-0">
								
								 <div style="background: #EBEDEF" class=" d-flex justify-content-between align-items-center p-2">
									 <img width="10%" class="rounded-circle" alt="100x100" src="https://mdbootstrap.com/img/Photos/Avatars/img%20(30).jpg"
          data-holder-rendered="true">
									<div class="d-flex justify-content-end align-items-center">
										<a href="#" title="New Group">
                   <i onclick="openNewGroup()" class="mx-3 text-dark fas fa-users"></i>
                </a>
                <a href="#" title="New Chat">
                    <i class="mx-3 text-dark fas fa-comment-dots text-secondary"></i>
                </a>
                <a href="#" title="Menu">
                    <i class="mx-3 text-dark fas fa-ellipsis-v text-secondary"></i>
                </a>
									</div>
								</div>
           
								
									
									<c:forEach var="user" items="${listUser}">

										<a style="text-decoration: none;" href="onechat?id=<c:out value='${user.username}' />">
										<div class="mx-2">
			<div class="d-flex justify-content-start align-items-center p-1">
			<img width="10%" class="rounded-circle" alt="100x100" src="https://mdbootstrap.com/img/Photos/Avatars/img%20(30).jpg"
          data-holder-rendered="true">
                        <div class="d-flex flex-column">
          <p class="mx-2 my-0 p-0"><b><c:out value="${user.username}" /></b></p>
          <p style="font-size: 12px" class="mx-2 my-0 p-0">last message of user</p>
          </div>
			</div>
		</div>
		                                	
	                                    </a>
                            		</c:forEach>
                            		
                            		
                            		<c:forEach var="group" items="${listGroup}">

										<a style="text-decoration: none;" href="groupchat?id=<c:out value='${group.groupId}' />&name=<c:out value='${group.groupName}' />">
										<div class="mx-2">
			<div class="d-flex justify-content-start align-items-center p-1">
			<img width="10%" class="rounded-circle" alt="100x100" src="https://mdbootstrap.com/img/Photos/Avatars/img%20(30).jpg"
          data-holder-rendered="true">
                        <div class="d-flex flex-column">
          <p class="mx-2 my-0 p-0"><b><c:out value="${group.groupName}" /></b></p>
          <p style="font-size: 12px" class="mx-2 my-0 p-0">last message of group</p>
          </div>
			</div>
		</div>
		                                	
		                                	
	                                    </a>
                            		</c:forEach>
		
									
									
								</div>
							</div>
						</div>
					</div>
					
			
			<div class="col-md-8">
						<div class="card-group border-0 m-0">
							<div class="shadow border-0 card p-0" style="height: 600px">
								<div style="background: url(https://raw.githubusercontent.com/erdoganbavas/web-practices/master/whatsapp-web/img/bg.png)" class="card-body p-0" >
								<div style="background: #EBEDEF" class=" d-flex justify-content-start align-items-center p-2">
									 <img width="5%" class="rounded-circle" alt="100x100" src="https://mdbootstrap.com/img/Photos/Avatars/img%20(30).jpg"
          data-holder-rendered="true">
          <div class="d-flex flex-column">
          <p class="mx-2 my-0 p-0"><b><%= id %></b></p>
          <p style="font-size: 12px" class="mx-2 my-0 p-0">typing...</p>
          </div>
          
									
								</div>
								<img id="show_image"></<img>

								<div id="msgContainer"  class="d-flex flex-column justify-content-start mx-2 mb-2 mt-2" style="overflow-x: auto; height: 470px;">
									
									<c:forEach var="mes" items="${listMessages}">
									
									
									<c:if test="${mes.placement == 'left'}">
									
									<c:if test="${mes.type == 'IMAGE' }">	
									<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-white
						text-dark
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <i style="font-size:12px;" class="far fa-file-image"></i>
					  <a href="UploadImgFile?filename=<c:out value='${mes.message}' />" >
				<div><c:out value="${mes.message}" /></div>
				</a>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				</div>
			</div>
			<br />
		</div>				
				</c:if>
				
				<c:if test="${mes.type == 'DOC' }">	
									<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-white
						text-dark
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <i style="font-size:12px;" class="far fa-file"></i>
					  <a href="UploadDocFile?filename=<c:out value='${mes.message}' />" >
				<div><c:out value="${mes.message}" /></div>
				</a>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				</div>
			</div>
			<br />
		</div>				
				</c:if>
				
				<c:if test="${mes.type == 'AUDIO' }">	
									<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-white
						text-dark
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <i style="font-size:12px;" class="fas fa-microphone-alt"></i>
					   <a href="UploadAudFile?filename=<c:out value='${mes.message}' />" >
				<div><c:out value="${mes.message}" /></div>
				</a>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				</div>
			</div>
			<br />
		</div>				
				</c:if>
				<c:if test="${mes.type == 'MESSAGE' }">
				<div class="d-flex justify-content-start">
									<div class="
				d-flex
				flex-column
				bg-white
				text-dark
				py-2
				px-2
				my-3
			  " style="border-radius: 15px">
			  <div><c:out value="${mes.message}" /></div>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				</div>
			</div>
			<br />
		</div>
		</c:if>
		
									</c:if>
			
			
			
		<c:if test="${mes.placement == 'right'}">
		
		<c:if test="${mes.type == 'IMAGE' }">
		<div class="d-flex justify-content-end">
			<div style="background-color: #DAF7A6; border-radius: 15px" class="
				d-flex
				flex-column
				text-dark
				py-2
				px-2
				my-3
			  " >
			  <i style="font-size:12px;" class="far fa-file-image"></i>
			  <a href="UploadImgFile?filename=<c:out value='${mes.message}' />" >
				<div><c:out value="${mes.message}" /></div>
				</a>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				<div>
				<c:if test="${mes.status == '0'}">
					<i style="font-size:12px;" class="fas fa-check text-muted"></i>
				</c:if>
				<c:if test="${mes.status == '1'}">
					<i style="font-size:12px;" class="fas fa-check-double text-primary"></i>
				</c:if>
				</div>
				</div>
				
			</div>
			<br />
		</div>
		</c:if>
		
		<c:if test="${mes.type == 'DOC' }">
		<div class="d-flex justify-content-end">
			<div style="background-color: #DAF7A6; border-radius: 15px" class="
				d-flex
				flex-column
				text-dark
				py-2
				px-2
				my-3
			  " >
			  <i style="font-size:12px;" class="far fa-file"></i>
			  <a href="UploadDocFile?filename=<c:out value='${mes.message}' />" >
				<div><c:out value="${mes.message}" /></div>
				</a>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				<div>
				<c:if test="${mes.status == '0'}">
					<i style="font-size:12px;" class="fas fa-check text-muted"></i>
				</c:if>
				<c:if test="${mes.status == '1'}">
					<i style="font-size:12px;" class="fas fa-check-double text-primary"></i>
				</c:if>
				</div>
				</div>
				
			</div>
			<br />
		</div>
		</c:if>
		
		
		<c:if test="${mes.type == 'AUDIO' }">
		<div class="d-flex justify-content-end">
			<div style="background-color: #DAF7A6; border-radius: 15px" class="
				d-flex
				flex-column
				text-dark
				py-2
				px-2
				my-3
			  " >
			  <i style="font-size:12px;" class="fas fa-microphone-alt"></i>
				<a href="UploadAudFile?filename=<c:out value='${mes.message}' />" >
				<div><c:out value="${mes.message}" /></div>
				</a>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				<div>
				<c:if test="${mes.status == '0'}">
					<i style="font-size:12px;" class="fas fa-check text-muted"></i>
				</c:if>
				<c:if test="${mes.status == '1'}">
					<i style="font-size:12px;" class="fas fa-check-double text-primary"></i>
				</c:if>
				</div>
				</div>
				
			</div>
			<br />
		</div>
		</c:if>
		
		<c:if test="${mes.type == 'MESSAGE' }">
		<div class="d-flex justify-content-end">
			<div style="background-color: #DAF7A6; border-radius: 15px" class="
				d-flex
				flex-column
				text-dark
				py-2
				px-2
				my-3
			  " >
				<div><c:out value="${mes.message}" /></div>
			  
				<div class="d-flex justify-content-end">
				<div class="mx-1"><p style="font-size:12px; margin:0px"><i><c:out value="${mes.time}" /></i></p></div>
				<div>
				<c:if test="${mes.status == '0'}">
					<i style="font-size:12px;" class="fas fa-check text-muted"></i>
				</c:if>
				<c:if test="${mes.status == '1'}">
					<i style="font-size:12px;" class="fas fa-check-double text-primary"></i>
				</c:if>
				</div>
				</div>
				
			</div>
			<br />
		</div>
		</c:if>
		
		</c:if>

										
		
                            		</c:forEach>
                            		
                            		</div>
									<div id="msgContainer" style="overflow: auto;"></div>
		<div id="msgController">
		
		
		<div style="background: #EBEDEF" class="d-flex  justify-content-start align-items-center p-2">
											
											<input
											id="message" 
												type="text"
												name="message"
												class="shadow border-0 form-control" placeholder="write message.." />
											
											
											<span
												class="
													rounded-left
													border-0
													input-group-text
													
												"
												id="basic-addon1"
												
												
											>
											
											
											<button id="stopButton" style="display: none" disabled class=" border-0 rounded-0 btn btn-danger px-3" ><i class="far fa-stop-circle"></i></button>
			
			<button id="recordButton" class=" border-0 rounded-0 btn btn-default px-3" ><i class="fas fa-microphone"></i></button>
			
																		
											
												<span class="btn btn-default btn-file"> <i class="far fa-file"></i> <input id="doc" type="file" accept=".xlsx,.xls,.doc, .docx,.ppt, .pptx,.txt,.pdf"></span>
											
												<span class="btn btn-default btn-file"><i class="far fa-file-image"></i> <input id="image" type="file" accept="image/*"></span>
											
												<button class=" border-0 rounded-0 btn btn-primary px-3" onclick="sendMessage('<%= id %>', '<%= current_username %>')" type="button"><i class="fas fa-paper-plane"></i></button>
											</span>
										</div>
			
			
								</div>
							</div>
						</div>
				</div>
			</div>
			</div>
			
		
		</body>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script src="https://cdn.rawgit.com/mattdiamond/Recorderjs/08e7abd9/dist/recorder.js"></script>
	<script src="scripts/audio.js"></script>
	
	<script>
	
	
		
	var messageContainer = document.getElementById("msgContainer");
	messageContainer.scrollTop = messageContainer.scrollHeight;
	
	function close() {
		alert('close')
	}
	
		var websocket = new WebSocket("ws://localhost:8080/websocket-app/chat");
		console.log(websocket);
		websocket.binaryType = "arraybuffer";
		
    	
    	websocket.onopen = function() {
    		var message = { messageType: 'LOGIN', message: '<%= id %>', to: '<%= id %>', from : '<%= current_username %>', type: 'one'};
    		
    		websocket.send(JSON.stringify(message));
        };
        websocket.onmessage = function(e) {
        	
        	console.log(e.data)
        	if (typeof(e.data) == "object") {
        		var bytes = new Uint8Array(e.data);
            	console.log(bytes)
                var data = "";
                var len = bytes.byteLength;
                for (var i = 0; i < len; ++i) {
                	data += String.fromCharCode(bytes[i]);
                }
                //var img = document.getElementById("show_image");
                //img.src = "data:image/png;base64,"+window.btoa(data);
                displayMessage("data:image/png;base64,"+window.btoa(data),'<%= current_username %>','time','IMAGE');
        	}else {
        		var msg = e.data.split("$");
            	displayMessage(msg[0], msg[1], msg[2], msg[3]);
            	console.log(msg[0], msg[1], msg[2], msg[3])
            	
            	if ('<%= current_username %>' != msg[1]) {
            		notifyMe(msg[0], msg[1]);
            	}	
        	}
        	
        	
            
            
        };
        websocket.onerror = function(e) {};
        websocket.onclose = function(e) {
        	websocket = null;
        	
        };
	
        
	document.addEventListener("DOMContentLoaded", function() {
		
	
	});
	
	function sendMessage(username, current_username) {
		
		var message = document.getElementById("message");
	
		if (websocket != null && websocket.readyState == 1) {
			
			if (message.value == '') { 
				return; 
			}
		
			var messageItem = { messageType: 'MESSAGE', message: message.value, to: username, from: current_username, type: 'one' };
			console.log(messageItem)
			
			message.value = '';
			
			websocket.send(JSON.stringify(messageItem));
			
		}
	}
	
	
	
	
	var displayMessage = function(msg, from, time, type) {
		
		
		if (from == '<%= current_username %>') {
			if (type == 'IMAGE') {
				messageContainer.innerHTML += `<div class="d-flex justify-content-end">
					<div style="background-color: #DAF7A6; border-radius: 15px" class="
						d-flex
						flex-column
						text-dark
						py-1
						px-1
						my-2
					  " >
						<i style="font-size:12px;" class="far fa-file-image"></i>
						<a href="UploadImgFile?filename=`+msg+`">
					
						<div>`+msg+`</div> </a>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						<div>
						<i style="font-size:12px;" class="fas fa-check text-muted"></i>
						</div>
						</div>
						
					</div>
					<br />
				</div>`;
			}else if (type == 'DOC') {
				messageContainer.innerHTML += `<div class="d-flex justify-content-end">
					<div style="background-color: #DAF7A6; border-radius: 15px" class="
						d-flex
						flex-column
						text-dark
						py-1
						px-1
						my-2
					  " >
						<i style="font-size:12px;" class="far fa-file"></i>
						<a href="UploadDocFile?filename=`+msg+`">
					
						<div>`+msg+`</div> </a>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						<div>
						<i style="font-size:12px;" class="fas fa-check text-muted"></i>
						</div>
						</div>
						
					</div>
					<br />
				</div>`;
			} else if (type == 'AUDIO') {
				messageContainer.innerHTML += `<div class="d-flex justify-content-end">
					<div style="background-color: #DAF7A6; border-radius: 15px" class="
						d-flex
						flex-column
						text-dark
						py-1
						px-1
						my-2
					  " >
						<i style="font-size:12px;" class="fas fa-microphone-alt"></i>
						<a href="UploadAudFile?filename=`+msg+`">
							
							<div>`+msg+`</div> </a>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						<div>
						<i style="font-size:12px;" class="fas fa-check text-muted"></i>
						</div>
						</div>
						
					</div>
					<br />
				</div>`;
			} else {
				messageContainer.innerHTML += `<div class="d-flex justify-content-end">
					<div style="background-color: #DAF7A6; border-radius: 15px" class="
						d-flex
						flex-column
						text-dark
						py-1
						px-1
						my-2
					  " >
						<div>`+msg+`</div>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						<div>
						<i style="font-size:12px;" class="fas fa-check text-muted"></i>
						</div>
						</div>
						
					</div>
					<br />
				</div>`;
			}
			
		}else {
			
			if (type == 'IMAGE') {
				messageContainer.innerHTML += `<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-info
						text-white
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <i style="font-size:12px;" class="far fa-file-image"></i>
					  <a href="UploadImgFile?filename=`+msg+`">
							
							<div>`+msg+`</div> </a>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						</div>
					</div>
					<br />
					</div>`;
			}else if (type == 'DOC') {
				messageContainer.innerHTML += `<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-info
						text-white
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <i style="font-size:12px;" class="far fa-file"></i>
					  <a href="UploadDocFile?filename=`+msg+`">
							
							<div>`+msg+`</div> </a>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						</div>
					</div>
					<br />
					</div>`;
			} else if (type == 'AUDIO') {
				messageContainer.innerHTML += `<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-info
						text-white
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <i style="font-size:12px;" class="fas fa-microphone-alt"></i>
					  <a href="UploadAudFile?filename=`+msg+`">
							
							<div>`+msg+`</div> </a>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						</div>
					</div>
					<br />
					</div>`;
			}else {
				messageContainer.innerHTML += `<div class="d-flex justify-content-start">
					<div class="
						d-flex
						flex-column
						bg-info
						text-white
						py-1
						px-1
						my-2
					  " style="border-radius: 10px">
					  <div>`+msg+`</div>
					  
						<div class="d-flex justify-content-end">
						<div class="mx-1"><p style="font-size:12px; margin:0px"><i>`+time+`</i></p></div>
						</div>
					</div>
					<br />
					</div>`;
			}
			
		}
		
		messageContainer.scrollTop = messageContainer.scrollHeight;
	};
	
	function askNotificationPermission() {
		  // function to actually ask the permissions
		  
		  var notificationBtn = document.getElementById("enable");
		  
		  function handlePermission(permission) {
		    // set the button to shown or hidden, depending on what the user answers
		    if(Notification.permission === 'denied' || Notification.permission === 'default') {
		      notificationBtn.style.display = 'block';
		    } else {
		      notificationBtn.style.display = 'none';
		    }
		  }

		  // Let's check if the browser supports notifications
		  if (!('Notification' in window)) {
		    console.log("This browser does not support notifications.");
		  } else {
		    if(checkNotificationPromise()) {
		      Notification.requestPermission()
		      .then((permission) => {
		        handlePermission(permission);
		      })
		    } else {
		      Notification.requestPermission(function(permission) {
		        handlePermission(permission);
		      });
		    }
		  }
		}
	

	function checkNotificationPromise() {
	    try {
	      Notification.requestPermission().then();
	    } catch(e) {
	      return false;
	    }

	    return true;
	  }
	
	
	function notifyMe(msg, from) {
		  if (!("Notification" in window)) {
		    alert("This browser does not support desktop notification");
		  }
		  else if (Notification.permission === "granted") {
		    
		    var options = {
		    	      body: "Message for you, "+msg,
		    	  }
		   	var notification = new Notification("Notification from "+from, options);
		    
		  }
		  else if (Notification.permission !== "denied") {
		    Notification.requestPermission().then(function (permission) {
		      if (permission === "granted") {
		        var notification = new Notification("Hi there!");
		      }
		    });
		  }
		}
	
	var fileByteArray = [];
	var imageUploadButton = document.getElementById("image");
	var documentUploadButton = document.getElementById("doc");
	var audioUploadButton = document.getElementById("aud");
	
	imageUploadButton.onchange = function(event) {
		var file = imageUploadButton.files[0];
		uploadImgFile(file);
	
	}
	
	documentUploadButton.onchange = function(event) {
		var file = documentUploadButton.files[0];
		uploadDocFile(file);
		
	}
	
	
	
	
	
	function uploadDocFile(file) {
	    let formData = new FormData(); 
	    formData.append("file",file);
	    
	    fetch('UploadDocFile', {
	    	
		      method: "POST", 
		      
		      body: formData
		    })
		    
	    .then(response => {
	    	if (response.status == 200) {
	    		var message = { messageType: 'DOC', message: file.name, to: '<%= id %>', from : '<%= current_username %>', type: 'one'};
	    		
				websocket.send(JSON.stringify(message));
	    	}
	    	
	    })
	    .catch(error => {
	      console.error('Error:', error);
	    });
	  }
	

	
	async function uploadImgFile(file) {
		
		
	    let formData = new FormData(); 
	    formData.append("file", file);
	    
	    fetch('UploadImgFile', {
		      method: "POST", 
		      body: formData
		    })
	    .then(response => {
	    	if (response.status == 200) {
	    		var message = { messageType: 'IMAGE', message: file.name, to: '<%= id %>', from : '<%= current_username %>', type: 'one'};
	    		
				websocket.send(JSON.stringify(message));
	    	}
	    	
	    })
	    .catch(error => {
	      console.error('Error:', error);
	    });
	  }
	
	
	
	
	
	
	async function getByteArray(myFile) {
	    let byteArray = await fileToByteArray(myFile);

	    console.log(byteArray);
	    
			if (websocket != null && websocket.readyState == 1) {
  	    	
				
	      		var messageItem = { messageType: 'IMAGE', message: myFile.name, file: byteArray, to: '<%= id %>', from : '<%= current_username %>', type: 'one' };
	      		console.log(messageItem);
      		
      		websocket.send(byteArray);
      		
      		}
	}
	
	function fileToByteArray(file) {
	    return new Promise((resolve, reject) => {
	        try {
	            let reader = new FileReader();
	            let fileByteArray = [];
	            reader.readAsArrayBuffer(file);
	            reader.onloadend = (evt) => {
	                if (evt.target.readyState == FileReader.DONE) {
	                    let arrayBuffer = evt.target.result,
	                        array = new Uint8Array(arrayBuffer);
	                    for (byte of array) {
	                        fileByteArray.push(byte);
	                    }
	                }
	                resolve(fileByteArray);
	            }
	        } catch (e) {
	            reject(e);
	        } 
	    })
	}
	</script>
	
	<script>
	
	URL = window.URL || window.webkitURL;
	var gumStream;
	//stream from getUserMedia()
	var rec;
	//Recorder.js object
	var input;

	var recordButton = document.getElementById("recordButton");
	var stopButton = document.getElementById("stopButton");
	//var pauseButton = document.getElementById("pauseButton");

	var audioContext;

	recordButton.addEventListener("click", startRecording);
	stopButton.addEventListener("click", stopRecording);
	//pauseButton.addEventListener("click", pauseRecording);

	function startRecording() {
		var AudioContext = window.AudioContext || window.webkitAudioContext;
		audioContext = new AudioContext();
		var constraints = {
			audio: true,
			video: false,
		};

		recordButton.disabled = true;
		stopButton.disabled = false;
		//pauseButton.disabled = false;
		
		stopButton.style.display = "block";
		//pauseButton.style.display = "block";
		

		navigator.mediaDevices
			.getUserMedia(constraints)
			.then(function (stream) {
				console.log(
					"getUserMedia() success, stream created, initializing Recorder.js ..."
				);
				/* assign to gumStream for later use */
				gumStream = stream;
				/* use the stream */
				input = audioContext.createMediaStreamSource(stream);
				/* Create the Recorder object and configure to record mono sound (1 channel) Recording 2 channels will double the file size */
				rec = new Recorder(input, {
					numChannels: 1,
				});
				//start the recording process
				rec.record();
				console.log("Recording started");
			})
			.catch(function (err) {
				//enable the record button if getUserMedia() fails
				recordButton.disabled = false;
				stopButton.disabled = true;
				//pauseButton.disabled = true;
			});
	}

	function pauseRecording() {
		console.log("pauseButton clicked rec.recording=", rec.recording);
		if (rec.recording) {
			//pause
			rec.stop();
			pauseButton.innerHTML = "Resume";
		} else {
			//resume
			rec.record();
			pauseButton.innerHTML = "Pause";
		}
	}

	function stopRecording() {
		console.log("stopButton clicked");
		//disable the stop button, enable the record too allow for new recordings
		stopButton.disabled = true;
		recordButton.disabled = false;
		//pauseButton.disabled = true;
		//reset button just in case the recording is stopped while paused
		//pauseButton.innerHTML = "Pause";
		//tell the recorder to stop the recording
		rec.stop(); //stop microphone access
		gumStream.getAudioTracks()[0].stop();
		//create the wav blob and pass it on to createDownloadLink
		rec.exportWAV(createDownloadLink);
	}

	function createDownloadLink(blob) {
		uploadAudFile(blob)
		
	}
	
	async function uploadAudFile(blob) {
	    
		let formData = new FormData(); 
		var filename = new Date().toISOString() + ".wav"
		
		const file = new File([blob], filename, {type: blob.type})
		
		formData.append("file", file);
		formData.append('filename', filename);
		
		
		//formData.append('url', url);
		//formData.append('filename', filename);
		
		fetch('UploadAudFile', {
		      method: "POST", 
		      body: formData,
		    })
	    .then(response => {
	    	if (response.status == 200) {
	    		stopButton.style.display = "none";
	    		var message = { messageType: 'AUDIO', message: filename, to: '<%= id %>', from : '<%= current_username %>', type: 'one'};
	    		
	    		websocket.send(JSON.stringify(message));
	    	}
	    	
	    })
	    .catch(error => {
	      console.error('Error:', error);
	    });
		
		
		
	    
	  }
	</script>
</html>