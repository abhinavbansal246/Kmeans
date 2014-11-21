package com.project.two;


public class PojoTrain {

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	private String id;
	private String title;
	private String body;
	private String tags;
	
	@Override
	public String toString()
	{
		return(id+""+title+""+body+""+tags);
	}
	
}
