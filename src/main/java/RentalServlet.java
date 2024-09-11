

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DVDDAO;
import data.Member;
import data.MemberDAO;
import data.RentalDAO;
import data.RentalDetail;

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
		RentalDAO rentalDao = new RentalDAO();
        List<RentalDetail> rentalHistory = rentalDao.getAllRentals();
        
        request.setAttribute("rentalHistory", rentalHistory);
        request.getRequestDispatcher("rental-management.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] selectedDVDs = request.getParameterValues("dvdCheckbox");
        String memberIdStr = request.getParameter("memberId");
        
        // 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (selectedDVDs == null || memberIdStr == null || memberIdStr.isEmpty()) {
            response.getWriter().println("<script>alert('レンタルするDVDと会員IDを入力してください。'); history.back();</script>");
            return;
        }

        String memberId = memberIdStr;
        MemberDAO memberDao = new MemberDAO();
        Member member = memberDao.getMemberById(memberId);

        if (member == null) {
            response.getWriter().println("<script>alert('該当IDは非会員です。'); history.back();</script>");
            return;
        }

        RentalDAO rentalDao = new RentalDAO();
        DVDDAO dvdDao = new DVDDAO();  // DVDDAO 인스턴스 생성
        int rentalId = rentalDao.createRental(memberId);

        if (rentalId != -1) {
            for (String dvdId : selectedDVDs) {
            	int id = Integer.parseInt(dvdId);
                rentalDao.addRentalItem(rentalId, id);
             // DVD 대출 상태를 업데이트하는 메서드 호출
                dvdDao.rentDVD(id);  // dvd23 테이블에서 is_rented를 true로 설정
            }
            response.getWriter().println("<script>alert('レンタルが完了しました。'); window.location = './rent';</script>");
        } else {
            response.getWriter().println("<script>alert('レンタル処理中にエラーが発生しました。'); history.back();</script>");
        }
    }
}


