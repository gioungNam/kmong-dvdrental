

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DVD;
import data.DVDDAO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DVD 리스트를 DAO를 통해 가져옴
        DVDDAO dvdDao = new DVDDAO();
        List<DVD> dvdList = dvdDao.getAllDVDs();
        
        System.out.println(dvdList);
        
        // DVD 리스트를 JSP로 전달
        request.setAttribute("dvdList", dvdList);
        request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	/**W
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 대출 기능 처리 로직
        String[] selectedDVDs = request.getParameterValues("dvdCheckbox");
        
        if (selectedDVDs != null) {
            DVDDAO dvdDao = new DVDDAO();
            for (String dvdId : selectedDVDs) {
                dvdDao.rentDVD(Integer.parseInt(dvdId));
            }
        }
        response.sendRedirect("main");
	}

}
