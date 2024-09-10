package data;

import java.sql.Date;

public class Rental {
    private int rentalId;
    private int memberId;
    private Date rentalDate;

    // 생성자 및 getter, setter
    public Rental() {}

    public Rental(int rentalId, int memberId, Date rentalDate) {
        this.rentalId = rentalId;
        this.memberId = memberId;
        this.rentalDate = rentalDate;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }
}
