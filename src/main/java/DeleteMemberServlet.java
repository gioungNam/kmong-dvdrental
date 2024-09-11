

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MemberDAO;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
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
		String[] selectedMembers = request.getParameterValues("memberCheckbox");
		
		// 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		// 선택된 회원이 없는 경우 경고 메시지 표시
        if (selectedMembers == null || selectedMembers.length == 0) {
            response.getWriter().println("<script>alert('削除する会員を選択してください。'); history.back();</script>");
            return;
        }
		
		
            MemberDAO memberDao = new MemberDAO();
            for (String memberId : selectedMembers) {
                memberDao.deleteMember(memberId);
            }
        
		
		response.sendRedirect(request.getContextPath() + "/member");
	}

}
