

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DVDDAO;

/**
 * Servlet implementation class RegisterDVDServlet
 */
@WebServlet("/registerDVD")
public class RegisterDVDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterDVDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("register-dvd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String leadActor = request.getParameter("leadActor");
        
        DVDDAO dvdDao = new DVDDAO();
        
     // 중복된 제목이 있는지 체크
        if (dvdDao.isTitleDuplicated(title)) {
            response.getWriter().println("<script>alert('すでに登録されたタイトルです。別のタイトルを入力してください。'); history.back();</script>");
            return;
        }
        
     // 입력 값 길이 검증
        if (title.length() > 30) {
            response.getWriter().println("<script>alert('タイトルは30文字以内で入力してください。'); history.back();</script>");
            return;
        }
        
        if (genre.length() > 20) {
            response.getWriter().println("<script>alert('ジャンルは20文字以内で入力してください。'); history.back();</script>");
            return;
        }

        if (leadActor.length() > 100) {
            response.getWriter().println("<script>alert('主演俳優は100文字以内で入力してください。'); history.back();</script>");
            return;
        }

       
        dvdDao.insertDVD(title, genre, leadActor);

        // 등록 후 DVD 관리 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/dvd");
	}

}
