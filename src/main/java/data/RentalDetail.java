package data;

import java.util.Date;

public class RentalDetail {
	   private int rentalItemId;
	    private int rentalId;
	    private Date rentalDate;
	    private String dvdTitle;
	    private String memberId;
	    private boolean isReturned;
	    private Date returnDate;  // 반납일자 추가

	    // Getter 및 Setter
	    public int getRentalItemId() {
	        return rentalItemId;
	    }

	    public void setRentalItemId(int rentalItemId) {
	        this.rentalItemId = rentalItemId;
	    }

	    public int getRentalId() {
	        return rentalId;
	    }

	    public void setRentalId(int rentalId) {
	        this.rentalId = rentalId;
	    }

	    public Date getRentalDate() {
	        return rentalDate;
	    }

	    public void setRentalDate(Date rentalDate) {
	        this.rentalDate = rentalDate;
	    }

	    public String getDvdTitle() {
	        return dvdTitle;
	    }

	    public void setDvdTitle(String dvdTitle) {
	        this.dvdTitle = dvdTitle;
	    }

	    public String getMemberId() {
	        return memberId;
	    }

	    public void setMemberId(String memberId) {
	        this.memberId = memberId;
	    }

	    public boolean getIsReturned() {
	        return isReturned;
	    }

	    public void setReturned(boolean isReturned) {
	        this.isReturned = isReturned;
	    }
	    
	    public Date getReturnDate() {
	        return returnDate;
	    }

	    public void setReturnDate(Date returnDate) {
	        this.returnDate = returnDate;
	    }
}
