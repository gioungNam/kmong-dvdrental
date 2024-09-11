

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DVD;
import data.DVDDAO;
import data.RentalDAO;

/**
 * Servlet implementation class DVDManagementServlet
 */
@WebServlet("/dvd")
public class DVDManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DVDManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DVDDAO dvdDao = new DVDDAO();
        List<DVD> dvdList = dvdDao.getAllDVDs();
        
        request.setAttribute("dvdList", dvdList);
        request.getRequestDispatcher("dvd-management.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] selectedDVDs = request.getParameterValues("dvdCheckbox");
		
		// 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

		// selectedDVDs가 null이거나 빈 배열인 경우 경고 메시지 표시
	    if (selectedDVDs == null || selectedDVDs.length == 0) {
	        response.getWriter().println("<script>alert('削除するDVDを選択してください。'); history.back();</script>");
	        return;
	    }
		
        
            DVDDAO dvdDao = new DVDDAO();
            RentalDAO rentalDao = new RentalDAO();
            
            for (String dvdIdStr : selectedDVDs) {
            	int dvdId = Integer.parseInt(dvdIdStr);
            	
            	// DVD가 렌탈 중인지 확인
                if (rentalDao.isDvdCurrentlyRented(dvdId)) {
                    response.getWriter().println("<script>alert('現在レンタル中のDVDは削除できません。'); history.back();</script>");
                    return;
                }
            	
                dvdDao.deleteDVD(dvdId);  // DVD 삭제
            }
        

        response.sendRedirect(request.getContextPath() + "/dvd");
	}

}
