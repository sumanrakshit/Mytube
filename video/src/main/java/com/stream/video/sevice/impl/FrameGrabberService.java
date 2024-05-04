package com.stream.video.sevice.impl;
import  static com.stream.video.Utils.Utils.getFileNameWithoutExt;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.stereotype.Service;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class FrameGrabberService {
	 /**
     * @param filePath - video file path.
     * @return video time length.
     * Using openCV library, take video preview and save into .JPEG file.
     */
    public long generatePreviewPictures(Path filePath) throws IOException {
        try (FFmpegFrameGrabber g = new FFmpegFrameGrabber(filePath.toString())) {
            Java2DFrameConverter converter = new Java2DFrameConverter();

            g.start();
            BufferedImage image;
            for (int i = 0; i < 50; i++) {
                image = converter.convert(g.grabKeyFrame());
                if (image != null) {
                    File file = Path.of( getFileNameWithoutExt(filePath.toString()) + ".jpeg").toFile();
                    ImageIO.write(image, "jpeg", file);
                    break;
                }
            }
            long lengthInTime = g.getLengthInTime();
            g.stop();
            return lengthInTime;
        }
    }
}
