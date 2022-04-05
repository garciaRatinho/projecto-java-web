<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title> Curso JSP </title>

<style type="text/css">
 form {
	position: absolute;
	top: 40%; 
	right:33%;
	left: 33%;
	
    }

h5 {
	position: absolute;
	top: 20%; 
	left: 33%
}

h6 {
	position: absolute;
	top: 90%; 
	left: 33%;
	color: red;
}
</style>>
</head>
<body>
 <h5>BEM - VINDO AO SISTEMA WEB-JSP</h5>
 
 <form action="<%=request.getContextPath()%>/Loginservlet" method="post" class="row g-3 needs-validation" novalidate>
 
 <input type="hidden" value="<%= request.getParameter("url")%>" name = "url">
 <div class="mb-3">
 <label class="form-label">Login</label> 
 <input class="form-control" name="login" type="text"  required>
    <div class="invalid-feedback">
      Informe o nome do usuário!
    </div>
    <div class="valid-feedback">
      Ok!
    </div>
 </div>
 
 <div class="mb-3">
  <label class="form-label">Senha</label>
  
 <input class="form-control" name="senha" type="password" required>
    <div class="invalid-feedback">
      Informe a senha do usuário!
    </div>
    <div class="valid-feedback">
      Ok!
    </div>
 </div>
 
 
 <input type="submit" value="Entrar" class="btn btn-primary">
 
 </form>
  <h6>${msg}</h6>
  
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
   <script type="text/javascript">
  
	(function () {
	  'use strict'
	
	 
	  var forms = document.querySelectorAll('.needs-validation')
	
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }
	
	        form.classList.add('was-validated')
	      }, false)
	    })
	  })()
   </script>
</body>
</html>