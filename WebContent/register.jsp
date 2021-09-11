<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Whatsapp Web | Register</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<link
			rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
			integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
			crossorigin="anonymous"
		/>
		<style>
    body {background-color: #d6dbd9; position: relative;}
body::before {content: ""; background-color: #128C7E; height: 163px; width: 100%; position: absolute; z-index: -1; top: 0}
    
    </style>
</head>
<body class="h-100 py-5">
     <div>
     <div class="d-flex justify-content-start align-items-center container">
				<img width="4%" src="https://raw.githubusercontent.com/anandhamurthy/Whatsapp-Web/main/whatsapp.png" alt="">
				<h5 class="m-2 text-white" >WHATSAPP WEB</h5>
			</div>
			
			<div class="my-2 container">
				<div class="row justify-content-center">
					<div class="col-md-6">
						<div class="card-group mb-0">
							<div class="shadow border-0 card p-4">
								<div class="card-body">
									<h1 class="text-dark">Register</h1>
									<p class="text-dark">
										Sign Up to your account
									</p>

									<form
										action="<%=request.getContextPath()%>/register"
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
												<i class="fa fa-user"></i>
											</span>
											<input
												type="text"
												name="username"
												class="
													shadow
													rounded-right
													border-0
													form-control
												"
												placeholder="User Name"
											/>
										</div>
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
												<i class="fa fa-lock"></i>
											</span>
											<input
												type="password"
												name="password"
												class="
													shadow
													rounded-right
													border-0
													form-control
												"
												placeholder="Password"
											/>
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
													Register
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

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>