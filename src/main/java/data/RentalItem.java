package data;

public class RentalItem {
    private int rentalItemId;
    private int rentalId;
    private int dvdId;
    private boolean isReturned;

    // 생성자 및 getter, setter
    public RentalItem() {}

    public RentalItem(int rentalItemId, int rentalId, int dvdId, boolean isReturned) {
        this.rentalItemId = rentalItemId;
        this.rentalId = rentalId;
        this.dvdId = dvdId;
        this.isReturned = isReturned;
    }

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

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
