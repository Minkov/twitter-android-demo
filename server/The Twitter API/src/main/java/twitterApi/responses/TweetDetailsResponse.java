package twitterApi.responses;

public class TweetDetailsResponse {
	private int id;
	private String text;
	private AuthorResponse author;
	
	public TweetDetailsResponse(int id, String text, AuthorResponse author) {
		this.setId(id);
		this.setText(text);
		this.setAuthor(author);
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
	public AuthorResponse getAuthor() {
		return author;
	}
	public void setAuthor(AuthorResponse author) {
		this.author = author;
	}
}
