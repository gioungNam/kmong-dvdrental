package data;

public class DVD {

	 	private int id;
	    private String title;
	    private String genre;
	    // 주연배우
	    private String leadActor;
	    private boolean isRented;

	    // 기본 생성자
	    public DVD() {}

	    // 매개변수가 있는 생성자
	    public DVD(int id, String title, String genre, String leadActor, boolean isRented) {
	        this.id = id;
	        this.title = title;
	        this.genre = genre;
	        this.leadActor = leadActor;
	        this.isRented = isRented;
	    }

	    // Getter 및 Setter 메서드
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getGenre() {
	        return genre;
	    }

	    public void setGenre(String genre) {
	        this.genre = genre;
	    }

	    public String getLeadActor() {
	        return leadActor;
	    }

	    public void setLeadActor(String leadActor) {
	        this.leadActor = leadActor;
	    }

	    public boolean getIsRented() {
	        return isRented;
	    }

	    public void setRented(boolean rented) {
	        isRented = rented;
	    }

		@Override
		public String toString() {
			return "DVD [id=" + id + ", title=" + title + ", genre=" + genre + ", leadActor=" + leadActor
					+ ", isRented=" + isRented + "]";
		}
	    
	    
}
