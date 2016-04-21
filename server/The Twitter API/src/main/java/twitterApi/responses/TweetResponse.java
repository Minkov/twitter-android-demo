package twitterApi.responses;

public class TweetResponse {
	private int id;
	private String text;
	
	public TweetResponse() {
		this(0, null);
	}
	
	public TweetResponse(int id, String text) {
		this.setId(id);
		this.setText(text);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}	
}
