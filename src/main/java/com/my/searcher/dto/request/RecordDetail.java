package com.my.searcher.dto.request;

public class RecordDetail {

    private String location;
    private String category;
    private String eventType;
    private String eventTitle;
    private String eventTimestamp_yyyy_mm_dd_hh_MM_ss;
    private String tags;
    private String fav;
	
    public RecordDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public RecordDetail(String location, String category, String eventType, String eventTitle,
			String eventTimestamp_yyyy_mm_dd_hh_MM_ss, String tags, String fav) {
		super();
		this.location = location;
		this.category = category;
		this.eventType = eventType;
		this.eventTitle = eventTitle;
		this.eventTimestamp_yyyy_mm_dd_hh_MM_ss = eventTimestamp_yyyy_mm_dd_hh_MM_ss;
		this.tags = tags;
		this.fav = fav;
	}



	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventTimestamp_yyyy_mm_dd_hh_MM_ss() {
		return eventTimestamp_yyyy_mm_dd_hh_MM_ss;
	}
	public void setEventTimestamp_yyyy_mm_dd_hh_MM_ss(String eventTimestamp_yyyy_mm_dd_hh_MM_ss) {
		this.eventTimestamp_yyyy_mm_dd_hh_MM_ss = eventTimestamp_yyyy_mm_dd_hh_MM_ss;
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
	@Override
	public String toString() {
		return "RecordDetail [location=" + location + ", category=" + category + ", eventType=" + eventType
				+ ", eventTitle=" + eventTitle + ", eventTimestamp_yyyy_mm_dd_hh_MM_ss="
				+ eventTimestamp_yyyy_mm_dd_hh_MM_ss + ", tags=" + tags + ", fav=" + fav + "]";
	}
	
    
}
