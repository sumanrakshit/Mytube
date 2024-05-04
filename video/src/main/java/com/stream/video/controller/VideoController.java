package com.stream.video.controller;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.bytedeco.javacv.FrameGrabber;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.stream.video.entity.dto.NewVideoRepresentation;
import com.stream.video.entity.dto.StreamBytesInfo;
import com.stream.video.entity.dto.VideoMetadataRepresentation;
import com.stream.video.sevice.VideoService;
import com.stream.video.exception.NotFoundException;

import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
//	@Autowired
//	private FrameGrabber frameGrabber;
	
	 @GetMapping("/all")
	    private List<VideoMetadataRepresentation> findAllVideoMetadata() {
	        return videoService.findAllRepresentation();
	    }

	    @GetMapping("/{id}")
	    public VideoMetadataRepresentation findVideoMetadataById(@PathVariable("id") Long id) {
	        return videoService.findRepresentationById(id).orElseThrow(NotFoundException::new);
	    }

	    @GetMapping(value = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	    public ResponseEntity<StreamingResponseBody> getPreviewPicture(@PathVariable("id") Long id) {
	        InputStream inputStream = videoService.getPreviewInputStream(id)
	                .orElseThrow(NotFoundException::new);

	        return ResponseEntity.ok(inputStream::transferTo);
	    }
	    
	    
	    
	    @GetMapping("/stream/{id}")
	    public ResponseEntity<StreamingResponseBody> streamVideo(
	            @RequestHeader(value = "Range", required = false) String httpRangeHeader,
	            @PathVariable("id") Long id
	    ) {
//	        log.info("Requested range [{}] for file `{}`", httpRangeHeader, id);
	        System.out.println("Requested range"+httpRangeHeader +"for file "+   id);

	        List<HttpRange> httpRangeList = HttpRange.parseRanges(httpRangeHeader);

	        StreamBytesInfo streamBytesInfo = videoService
	                .getStreamBytes(id, httpRangeList.size() > 0 ? httpRangeList.get(0) : null)
	                .orElseThrow(NotFoundException::new);
	        
	        System.out.println(streamBytesInfo.getFileSize());

	        long byteLength = streamBytesInfo.getRangeEnd() - streamBytesInfo.getRangeStart() + 1;

	        ResponseEntity.BodyBuilder builder = ResponseEntity
	                .status(httpRangeList.size() > 0 ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
	                .header("Content-Type", streamBytesInfo.getContentType())
	                .header("Accept-Ranges", "bytes")
	                .header("Content-Length", Long.toString(byteLength));

	        if (httpRangeList.size() > 0) {
	            builder.header(
	                    "Content-Range",
	                    "bytes " + streamBytesInfo.getRangeStart() +
	                            "-" + streamBytesInfo.getRangeEnd() +
	                            "/" + streamBytesInfo.getFileSize());
	        }
//	        log.info("Providing bytes from {} to {}. We are at {}% of overall video.",
//	                streamBytesInfo.getRangeStart(),
//	                streamBytesInfo.getRangeEnd(),
//	                new DecimalFormat("###.##")
//	                        .format(100.0 * streamBytesInfo.getRangeStart() / streamBytesInfo.getFileSize()));
	        System.out.println("Providing bytes from "+streamBytesInfo.getRangeStart()+ "to"+ streamBytesInfo.getRangeEnd() + " We are at"+ new DecimalFormat("###.##")
                    .format(100.0 * streamBytesInfo.getRangeStart() / streamBytesInfo.getFileSize())+ "% of overall video." );

	        return builder.body(streamBytesInfo.getResponseBody());
	    }

	 
	 @CrossOrigin("**")   
	    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> uploadVideo(NewVideoRepresentation newVideoRepresentation) {
//	        log.info(newVideoRepresentation.getDescription());
	        System.out.println(newVideoRepresentation.getDescription());
	        try {
	            videoService.saveNewVideo(newVideoRepresentation);
	        } catch (Exception ex) {
//	            log.error(ex.getMessage());
	            System.out.println(ex.getMessage());
	            return ResponseEntity.internalServerError().build();
	        }
//	        return ResponseEntity.status(HttpStatus.OK).body("Sucessfully upload video");
	        return ResponseEntity.ok().build();
	    }
	

}
