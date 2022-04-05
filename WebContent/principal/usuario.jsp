<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <jsp:include page="theme-loader.jsp"></jsp:include>

  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
           
          <jsp:include page="navbar.jsp"></jsp:include> 
          
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <jsp:include page="Nav-menu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                  
                  <jsp:include page="page-header.jsp"></jsp:include>
                      
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                           
                                           
                                          <div class="row">
                                            <div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                <div class="card">
                                                
	         <div class="card-block">
	         <h4 class="sub-title">Cadastro do usuário</h4>
                                          
		<form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath()%>/servletControlUsuario" method="post" id = "formUser">
			
			  <input type="hidden" name ="acao" id="acao" value="">
			
			  <div class="form-group form-default form-static-label">
			      <input type="text" name="id" id = "id" class="form-control" readonly="readonly" value="${modeloLogin.id}">
			      <span class="form-bar"></span>
			      <label class="float-label">ID:</label>
			  </div>
			  
			  <div class="form-group form-default form-static-label">
			     <input type="text" name="nome" id="nome" class="form-control"  placeholder="Nome" required="required" value="${modeloLogin.nome}">
			     <span class="form-bar"></span>
			     <label class="float-label">Nome:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="email" name="email" id="email" class ="form-control" placeholder="E-mail" required="required" autocomplete="off" value="${modeloLogin.email}">
			     <span class="form-bar"></span>
			     <label class="float-label">Email:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="text" name="login" id="login" placeholder="login" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.login}">
			     <span class="form-bar"></span>
			     <label class="float-label">Login:</label>
			 </div>
			 
			 <div class="form-group form-default form-static-label">
			     <input type="password" name="senha" id="senha" placeholder="Senha" class="form-control"  required="required" autocomplete="off" value="${modeloLogin.senha}">
			     <span class="form-bar"></span>
			     <label class="float-label">Senha:</label>
			 </div>
			                                   
			<button type ="button" class="btn btn-primary waves-effect waves-light"  onclick="limparForm()">Novo</button>
			<button class="btn btn-success waves-effect waves-light">Salvar</button>
			<button type ="button" class="btn btn-info waves-effect waves-light"  onclick="criarExcluir()">Excluir</button>
			<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#ModalUsuario">Pesquisar</button>

		    </form>  
      	 </div>
      		 
      		  <span id ="msg">${msg}</span>
      		  
                  </div>
                  </div>
                  </div>
                  </div>
                  </div>
                  
    		</div>
                                    <!-- corpo da página -->
                                    
            </div>
            
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
    <jsp:include page="javascriptArquivos.jsp"></jsp:include>
   
   
   <!-- Modal -->
<div class="modal fade" id="ModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisar usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
	 <div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="pesquisa pelo nome"  aria-label="nome" id ="nomeBusca" aria-describedby="basic-addon2">
		  <div class="input-group-append">
		    <button class="btn btn-success" type="button"  onclick="buscarUsuario()">Buscar</button>
		  </div>
	 </div>
	
	<table class="table" id = "tabelaResultados">
	
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nome</th>
      <th scope="col">Ver</th>
     
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
     
      </div>
    </div>
  </div>
</div>

 <script type="text/javascript">

     function criarExcluir(){
		 
	       if(confirm("Deseja realmente excluir os dados?")){
		       
			   document.getElementById("formUser").method = 'get',
			   document.getElementById("acao").value = 'deletar',
			   document.getElementById("formUser").submit();

			   }
	        
		  }
	   
		 function limparForm(){
		        
		    var elementos = document.getElementById("formUser").elements;
		
		    for (p = 0; p<elementos.length; p++){
		
		    	 elementos[p].value = '';
		        }
		    }
	
	     function criarDeletarajax(){

		     if(confirm('Deseja realmente excluir dados_ajax?')){
		
		         var urlAction = document.getElementById("formUser").action;
		         var idUser = document.getElementById('id').value;
		
		         $.ajax({
		
		             method: "get",
		             url: urlAction,
		             data: "id = " + idUser + '&acao = deletarajax',
		             
		             success: function(response)
		             {
		
			             limparForm();
			             
			             alert(response);
		                 }
		             
		             }).fail(function(xhr,status, errorthrown){
		
		                 alert('Erro ao deletar usuário por id: ' + xhr.responseText);
		
		                 });
		         }
		     }

		 function buscarUsuario(){

		      var nomeBusca = document.getElementById('nomeBusca').value;
		     
		      if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){


		        var urlAction = document.getElementById("formUser").action;
		         

		    	 $.ajax({
		    			
		             method: 'get',
		             url: urlAction,
		             data:  "nomeBusca = " + nomeBusca + '&acao = buscarUsuario_ajax',
		             success: function(response)
		             
		           var json = JSON.parse(response);

		              
		              
					$('#tabelaResultados > tbody > tr').remove();

			          for(var p = 0; p < json.length; p++){
			
			           $('#tabelaResultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td></tr>');
			                      
			                    }
			                 
			                 } 
		         
		            }).fail(function(xhr,status, errorthrown){

		                 alert('Erro ao buscar usuário por nome: ' + xhr.responseText);

		              });
		         
		         }
				  
	    
    </script>
    
</body>

</html>
