package com.stream.video.Utils;

public class Utils {
	/**
     * @param fileName - name of video file with file extension.
     * @return only file name, without file extension.
     * Take video name to save JPEG-preview file with similar file name.
     */
    public static String getFileNameWithoutExt(String fileName) {
        int extIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, extIndex);
    }

}
