package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.ConexãoBancoDados;


@WebFilter("/principal/*")
public class FilterAutenticar implements Filter {

    private static Connection connection;
    public FilterAutenticar() {
        
    }
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlParAutenticar = req.getServletPath();
		
		if (usuarioLogado == null && 
				!urlParAutenticar.equalsIgnoreCase("/principal/Loginservlet")) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp/url=?" + urlParAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redirecionar.forward(request, response);	
		}else {
			chain.doFilter(request, response);
		}
		connection.commit();
	}catch (Exception e) {
		e.printStackTrace();
		RequestDispatcher redirecionar = request.getRequestDispatcher("PaginaDeErro.jsp");
		request.setAttribute("msg", e.getMessage());
		redirecionar.forward(request, response);
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		connection = ConexãoBancoDados.getConnection();
		
	}

}
