package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import modelos.ModeloLogin;

@MultipartConfig
@WebServlet(urlPatterns = {"/servletControlUsuario"})
public class servletControlUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private DAOUsuarioRepository daoUsuarioRepository= new DAOUsuarioRepository();
    
    public servletControlUsuario() {
        super();
        
     }

	
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			 if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.excluirUsuario(idUser);
				
				request.setAttribute("msg", "Usuário excluído com sucesso!");
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
				
		}
			else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.excluirUsuario(idUser);
				
				response.getWriter().write("Usuário excluído com sucesso por ajax!");
				
				
		} 	
			
		    else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuario_ajax")) {
			
				String nomeBusca  = request.getParameter("nomeBusca");
				
		 
				List<ModeloLogin> dadosJSonUser = daoUsuarioRepository.listaDeConsultarUsuario(nomeBusca);
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJSonUser);
				
				response.getWriter().write(json);
	      }	
		    else {
			
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
		   }
		
			
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("PaginaDeErro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		
			String msg = "Usuário cadastrado com sucesso!";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String login = request.getParameter("login");
			
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id): null);
			modeloLogin.setNome(nome);
			modeloLogin.setEmail(email);
			modeloLogin.setLogin(login);
			modeloLogin.setSenha(senha);
			
			if(daoUsuarioRepository.validarLogin(modeloLogin.getLogin()) && modeloLogin.getId() == null) {
				msg = "Já existe usuário com mesmo login, informe outro login";
			}else {
				
				if(modeloLogin.isNOVO()) {
					msg = "Usuário gravado com sucesso!";
				}else {
					
					msg = "Usuário atualizado com sucesso!";
				}
				
			    modeloLogin = daoUsuarioRepository.gravarUsuario(modeloLogin); 
			     
			   }
			
			request.setAttribute("msg", msg);
			request.setAttribute("modeloLogin", modeloLogin);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("PaginaDeErro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
