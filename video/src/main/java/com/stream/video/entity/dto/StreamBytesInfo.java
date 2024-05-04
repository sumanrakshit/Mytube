package com.stream.video.entity.dto;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StreamBytesInfo {
	  private  StreamingResponseBody responseBody;
	    private  long fileSize;
	    private  long rangeStart;
	    private  long rangeEnd;
	    private  String contentType;
		public StreamingResponseBody getResponseBody() {
			return responseBody;
		}
		public long getFileSize() {
			return fileSize;
		}
		public long getRangeStart() {
			return rangeStart;
		}
		public long getRangeEnd() {
			return rangeEnd;
		}
		public String getContentType() {
			return contentType;
		}
		public StreamBytesInfo(StreamingResponseBody responseBody, long fileSize, long rangeStart, long rangeEnd,
				String contentType) {
			super();
			this.responseBody = responseBody;
			this.fileSize = fileSize;
			this.rangeStart = rangeStart;
			this.rangeEnd = rangeEnd;
			this.contentType = contentType;
		}
		public StreamBytesInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
	    

}
