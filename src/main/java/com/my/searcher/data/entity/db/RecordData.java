package com.my.searcher.data.entity.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table (name = "record_data")
public class RecordData {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id", updatable = false, nullable = false)
    private int recordId;
    
    @Column(name = "location", nullable = false, length = 150)
    private String location;
    
    @Column(name = "category", length = 150)
    private String category;
    
    @Column(name = "event_type", length = 150)
    private String eventType;
    
    @Column(name = "event_title", length = 150)
    private String eventTitle;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_timestamp", nullable = true)
    private Date eventTimestamp;    
    
    @Column(name = "tags", length = 1500)
    private String tags;
    
    @Column(name = "fav", nullable = true, length = 1)
    private String fav;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_timestamp", nullable = false)
	private Date insertTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_timestamp", nullable = false)
	private Date updateTimestamp;
    
	@PrePersist
    protected void onCreate() {
        updateTimestamp = insertTimestamp = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTimestamp = new Date();
    }

	public RecordData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecordData(int recordId, String location, String category, String eventType, String eventTitle,
			Date eventTimestamp, String tags, String fav, Date insertTimestamp, Date updateTimestamp) {
		super();
		this.recordId = recordId;
		this.location = location;
		this.category = category;
		this.eventType = eventType;
		this.eventTitle = eventTitle;
		this.eventTimestamp = eventTimestamp;
		this.tags = tags;
		this.fav = fav;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
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

	public Date getInsertTimestamp() {
		return insertTimestamp;
	}

	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getFav() {
		return fav;
	}

	public void setFav(String fav) {
		this.fav = fav;
	}

	@Override
	public String toString() {
		return "RecordData [recordId=" + recordId + ", location=" + location + ", category=" + category + ", eventType="
				+ eventType + ", eventTitle=" + eventTitle + ", eventTimestamp=" + eventTimestamp + ", tags=" + tags
				+ ", fav=" + fav + ", insertTimestamp=" + insertTimestamp + ", updateTimestamp=" + updateTimestamp
				+ "]";
	}
    
}