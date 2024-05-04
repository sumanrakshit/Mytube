package com.stream.video.entity.dto;

import org.springframework.web.multipart.MultipartFile;

public class NewVideoRepresentation {
	 private String description;
	 private MultipartFile file;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "NewVideoRepresentation [description=" + description + ", file=" + file + "]";
	}
	 
	 
	 

}
