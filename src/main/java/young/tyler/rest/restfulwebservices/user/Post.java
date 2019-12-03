package young.tyler.rest.restfulwebservices.user;

import java.util.Date;

public class Post {
	private int id;
	private Date timestamp;
	private String content;
	private String author;
	
	public Post() {};
	
	public Post(String content, String author) {
		super();
		this.timestamp = new Date();
		this.content = content;
		this.author = author;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	

}
