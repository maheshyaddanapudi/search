package com.my.searcher.data.entity.es;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = "record_indexed_es_data")
public class RecordIndexedData {

		@Id
		private int recordId;
	    private String location;
	    private String category;
	    private String eventType;
	    private String eventTitle;
	    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	    private Date eventTimestamp;
	    private String tags;
	    private String fav;
	    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	    private Date insertTimestamp;
	    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		private Date updateTimestamp;
		
		public RecordIndexedData() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public RecordIndexedData(int recordId, String location, String category, String eventType, String eventTitle,
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


		public String getFav() {
			return fav;
		}

		public void setFav(String fav) {
			this.fav = fav;
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

		@Override
		public String toString() {
			return "RecordIndexedData [recordId=" + recordId + ", location=" + location + ", category=" + category
					+ ", eventType=" + eventType + ", eventTitle=" + eventTitle + ", eventTimestamp=" + eventTimestamp
					+ ", tags=" + tags + ", fav=" + fav + ", insertTimestamp=" + insertTimestamp + ", updateTimestamp="
					+ updateTimestamp + "]";
		}
		
}
