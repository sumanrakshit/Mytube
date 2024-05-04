package com.stream.video.sevice;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpRange;
import org.springframework.stereotype.Component;

import com.stream.video.entity.dto.NewVideoRepresentation;
import com.stream.video.entity.dto.StreamBytesInfo;
import com.stream.video.entity.dto.VideoMetadataRepresentation;

@Component
public interface VideoService {
	 public List<VideoMetadataRepresentation> findAllRepresentation() ;
	 public Optional<VideoMetadataRepresentation> findRepresentationById(Long id);
	 public void saveNewVideo(NewVideoRepresentation newVideoRepr);
	 public Optional<InputStream> getPreviewInputStream(Long id);
	 public Optional<StreamBytesInfo> getStreamBytes(Long id, HttpRange range);
	 
	 

}
