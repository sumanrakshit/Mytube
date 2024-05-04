package com.stream.video.entity.dto;

import com.stream.video.entity.VideoData;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class VideoMetadataRepresentation {
	
	 private Long id;
	    private String description;
	    private String contentType;
	    private String previewUrl;
	    private String streamUrl;
	    
	    
	    
	    

	    public Long getId() {
			return id;
		}





		public void setId(Long id) {
			this.id = id;
		}





		public String getDescription() {
			return description;
		}





		public void setDescription(String description) {
			this.description = description;
		}





		public String getContentType() {
			return contentType;
		}





		public void setContentType(String contentType) {
			this.contentType = contentType;
		}





		public String getPreviewUrl() {
			return previewUrl;
		}





		public void setPreviewUrl(String previewUrl) {
			this.previewUrl = previewUrl;
		}





		public String getStreamUrl() {
			return streamUrl;
		}





		public void setStreamUrl(String streamUrl) {
			this.streamUrl = streamUrl;
		}





		@Override
		public String toString() {
			return "VideoMetadataRepresentation [id=" + id + ", description=" + description + ", contentType="
					+ contentType + ", previewUrl=" + previewUrl + ", streamUrl=" + streamUrl + "]";
		}





		public VideoMetadataRepresentation(Long id, String description, String contentType, String previewUrl,
				String streamUrl) {
			super();
			this.id = id;
			this.description = description;
			this.contentType = contentType;
			this.previewUrl = previewUrl;
			this.streamUrl = streamUrl;
		}





		public VideoMetadataRepresentation (VideoData vmd) {
	        this.id = vmd.getId();
	        this.description = vmd.getDescription();
	        this.contentType = vmd.getContentType();
	        this.setPreviewUrl("/api/v1/video/preview/" + vmd.getId());
	        this.setStreamUrl("/api/v1/video/stream/" + vmd.getId());
	    }

}
