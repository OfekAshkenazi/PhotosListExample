package com.ofek.photosexam.objects.guruapiobjects;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class PhotoDto{

	@SerializedName("member_id")
	private String memberId;

	@SerializedName("event_id")
	private long eventId;

	@SerializedName("width")
	private int width;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("adult")
	private boolean adult;

	@SerializedName("views")
	private int views;

	@SerializedName("height")
	private int height;

	@SerializedName("upload_date")
	private int uploadDate;

	@SerializedName("labels")
	private List<String> labels;

	@SerializedName("ratio")
	private double ratio;

	@SerializedName("likes")
	private int likes;

	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getMemberId(){
		return memberId;
	}

	public void setEventId(long eventId){
		this.eventId = eventId;
	}

	public long getEventId(){
		return eventId;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setViews(int views){
		this.views = views;
	}

	public int getViews(){
		return views;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	public void setUploadDate(int uploadDate){
		this.uploadDate = uploadDate;
	}

	public int getUploadDate(){
		return uploadDate;
	}

	public void setLabels(List<String> labels){
		this.labels = labels;
	}

	public List<String> getLabels(){
		return labels;
	}

	public void setRatio(double ratio){
		this.ratio = ratio;
	}

	public double getRatio(){
		return ratio;
	}

	public void setLikes(int likes){
		this.likes = likes;
	}

	public int getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"PhotoDto{" + 
			"member_id = '" + memberId + '\'' + 
			",event_id = '" + eventId + '\'' + 
			",width = '" + width + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",adult = '" + adult + '\'' + 
			",views = '" + views + '\'' + 
			",height = '" + height + '\'' + 
			",upload_date = '" + uploadDate + '\'' + 
			",labels = '" + labels + '\'' + 
			",ratio = '" + ratio + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}