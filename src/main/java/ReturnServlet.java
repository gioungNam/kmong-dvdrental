

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.RentalDAO;

/**
 * Servlet implementation class ReturnServlet
 */
@WebServlet("/return")
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] selectedRentalItems = request.getParameterValues("rentalItemCheckbox");
		
		// 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		if (selectedRentalItems == null || selectedRentalItems.length == 0) {
            response.getWriter().println("<script>alert('返却するDVDを選択してください。'); history.back();</script>");
            return;
        }
		
		RentalDAO rentalDao = new RentalDAO();
        for (String rentalItemId : selectedRentalItems) {
            rentalDao.returnRentalItem(Integer.parseInt(rentalItemId));  // 반납 처리
        }

        // 반납 완료 후 다시 렌탈 관리 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() +"/rent");
	}

}
