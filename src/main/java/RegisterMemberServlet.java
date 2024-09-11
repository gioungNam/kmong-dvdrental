

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MemberDAO;

/**
 * Servlet implementation class RegisterMemberServlet
 */
@WebServlet("/registerMember")
public class RegisterMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("register-member.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        // ID 길이 검증 (30자 이하)
        if (id.length() > 30) {
            response.getWriter().println("<script>alert('会員IDは30文字以下にしてください。'); history.back();</script>");
            return;
        }

        // 이메일 형식 검증 ('@'가 포함되어 있는지)
        if (!email.contains("@")) {
            response.getWriter().println("<script>alert('有効なEメールアドレスを入力してください。'); history.back();</script>");
            return;
        }
        
        MemberDAO memberDao = new MemberDAO();
        
        // 중복 회원 체크 로직 (선택 사항)
        if (memberDao.getMemberById(id) != null) {
            response.getWriter().println("<script>alert('既に存在する会員IDです。'); history.back();</script>");
            return;
        }
        

        memberDao.registerMember(id, name, email);

        response.sendRedirect(request.getContextPath() + "/member");
	}

}
