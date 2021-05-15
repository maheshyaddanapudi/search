package com.my.searcher.dto.response.buffer;

import java.util.Date;

public class SearchResultBuffer {

	
	private int recordId;
    private String eventTitle;
    private String location;
    private Date insertTimestamp;
    private String _class;
    private String eventType;
    private String category;
    private String fav;
    private Date updateTimestamp;
    private Date eventTimestamp;
    private String tags;
	public SearchResultBuffer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchResultBuffer(int recordId, String eventTitle, String location, Date insertTimestamp, String _class,
			String eventType, String category, String fav, Date updateTimestamp, Date eventTimestamp, String tags) {
		super();
		this.recordId = recordId;
		this.eventTitle = eventTitle;
		this.location = location;
		this.insertTimestamp = insertTimestamp;
		this._class = _class;
		this.eventType = eventType;
		this.category = category;
		this.fav = fav;
		this.updateTimestamp = updateTimestamp;
		this.eventTimestamp = eventTimestamp;
		this.tags = tags;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getInsertTimestamp() {
		return insertTimestamp;
	}
	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	public Date getEventTimestamp() {
		return eventTimestamp;
	}
	public void setEventTimestamp(Date eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFav() {
		return fav;
	}
	public void setFav(String fav) {
		this.fav = fav;
	}
	
}
