<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% String WsUrl = getServletContext().getInitParameter("WsUrl");
    String current_username = session.getAttribute("UserName").toString();
    %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>New Group</title>

		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
			crossorigin="anonymous"
		/>

		<link
			rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
			integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
			crossorigin="anonymous"
		/>
	</head>
	<body>
		<div>
			<div class="my-5 container">
				<div class="row justify-content-center">
					<div class="col-md-6">
						<div class="card-group mb-0">
							<div class="shadow border-0 card p-4">
								<div class="card-body">
									<h1 class="text-dark">New Group</h1>
									<p class="text-dark">
										Add Users
									</p>

									<form
										action="<%=request.getContextPath()%>/createGroup"
										method="post"
									>
										<div class="input-group mb-3">
											<span
												class="
													shadow
													rounded-left
													border-0
													input-group-text
													bg-white
												"
												id="basic-addon1"
											>
												<i class="fas fa-users"></i>
											</span>
											<input
												type="text"
												name="name"
												class="
													shadow
													rounded-right
													border-0
													form-control
												"
												placeholder="Group Name"
											/>
										</div>
										
										<div class="card-body">
								
									
									
		
									<c:forEach var="user" items="${listUser}">

										<div>
			<div id="card-2" class="visitor-card m-2">
				<div class="shadow border-0 p-4">
					<div class="d-flex justify-content-between align-items-center">
						<div class="d-flex justify-content-start align-items-center">
						<input
								type="checkbox"
								class="mx-2 check_box"
								name="users"
								value="${user.username}"
								onclick="getName('${user.username}')"
								
							>
							<i class="far fa-user"></i>
							<div>
							
								<p class="mx-2 my-0">
									<b><c:out value="${user.username}" /></b>
								</p>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		                                	
	                                   
                            		</c:forEach>
									
								</div>
										
										<div class="row">
											<div class="col-6">
												<button
													type="submit"
													value="Submit"
													class="
														shadow
														border-0
														rounded-0
														btn btn-primary
														px-4
													"
												>
													Create Group
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	
	<script>
	
		var groupUsers = [];
		
		function getName(name) {
			groupUsers.push(name)
		}
		
		console.log(groupUsers);
	</script>
</html>
