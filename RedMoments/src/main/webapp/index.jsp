<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Red Moments</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"rel="stylesheet"integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"crossorigin="anonymous"/>

		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"
		></script>
	</head>
                    <body class="" bgcolor="#EEEEEE">

                    <div
			class="container-fluid"
			style="width: 1100px;height: 600px;margin: auto;margin-top: 40px;
             background-color: #b7b7b7;" align = "center"
		>

			<form action="user/login" method="post">
                </br>
                <div
                    style="width: 500px;height: 300px;margin: auto;margin-top: 40px;
                             background-color: #000000;" align = "center">

				<h2 class="col-sm-12 mb-3" style="color:white">Red Moments</h2>
				<div class="col-sm-12 text-center mb-3">
					<input
						name="username"
						size="30"
						required="required"
						placeholder="Username"
					/>
				</div>

				<div class="col-sm-12 text-center mb-3">
					<input
						type="password"
						name="password"
						size="30"
						required="required"
						placeholder="Password"
					/>
				</div>
					<div class="col-sm-12 mb-3">
						<button type="submit" value="Login" class="btn btn-danger btn-lg" style = "width: 250px;" >
						      	Login
						</button>
					</div>
                                        </div>
                                            <h2 class="col-sm-12 mb-3" style="color:white">Don't have a account ? </h2>
					<div class="col-sm-12 mb-3">

						<a href="user/register.htm">
							<button type="button" class="btn btn-danger btn-lg">Sign Up</button>

						</a>

                                        </div>
			</form>
		</div>
	</body>
</html>
