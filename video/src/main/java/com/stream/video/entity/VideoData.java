package com.stream.video.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="video_metadata")
public class VideoData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName;
	private String contentType;
	private String description;
	private Long fileSize;
	private Long videoLength;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Long getVideoLength() {
		return videoLength;
	}
	public void setVideoLength(Long videoLength) {
		this.videoLength = videoLength;
	}
	public VideoData(Long id, String fileName, String contentType, String description, Long fileSize,
			Long videoLength) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.contentType = contentType;
		this.description = description;
		this.fileSize = fileSize;
		this.videoLength = videoLength;
	}
	
	
	
	
	public VideoData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "VideoData [id=" + id + ", fileName=" + fileName + ", contentType=" + contentType + ", description="
				+ description + ", fileSize=" + fileSize + ", videoLength=" + videoLength + "]";
	}
	
	
	

}
