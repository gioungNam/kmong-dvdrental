

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Member;
import data.MemberDAO;
import data.RentalDAO;

/**
 * Servlet implementation class RentalServlet
 */
@WebServlet("/rent")
public class RentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalServlet() {
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
		String[] selectedDVDs = request.getParameterValues("dvdCheckbox");
        String memberIdStr = request.getParameter("memberId");

        if (selectedDVDs == null || memberIdStr == null || memberIdStr.isEmpty()) {
            response.getWriter().println("<script>alert('대여할 DVD 및 회원 ID를 입력해주세요.'); history.back();</script>");
            return;
        }

        int memberId = Integer.parseInt(memberIdStr);
        MemberDAO memberDao = new MemberDAO();
        Member member = memberDao.getMemberById(memberId);

        if (member == null) {
            response.getWriter().println("<script>alert('해당 ID는 비회원입니다.'); history.back();</script>");
            return;
        }

        RentalDAO rentalDao = new RentalDAO();
        int rentalId = rentalDao.createRental(memberId);

        if (rentalId != -1) {
            for (String dvdId : selectedDVDs) {
                rentalDao.addRentalItem(rentalId, Integer.parseInt(dvdId));
            }
            response.getWriter().println("<script>alert('대여가 완료되었습니다.'); window.location = '/';</script>");
        } else {
            response.getWriter().println("<script>alert('대여 처리 중 오류가 발생했습니다.'); history.back();</script>");
        }
    }
}


