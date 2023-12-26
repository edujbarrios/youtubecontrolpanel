package youtube.controlpanel.model.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkParser {

    /**
     * Extracts the video ID from a YouTube URL.
     *
     * @param youtubeUrl The full YouTube URL.
     * @return The extracted video ID or null if not found.
     */
    public static String extractVideoIdFromUrl(String youtubeUrl) {
        Pattern videoPattern = Pattern.compile(
                "^https?://www\\.youtube\\.com/watch\\?v=([^&]*)", Pattern.CASE_INSENSITIVE);
        Matcher videoMatcher = videoPattern.matcher(youtubeUrl);

        if (videoMatcher.find()) {
            return videoMatcher.group(1);
        }
        return null;
    }

    /**
     * Extracts the channel ID from a YouTube URL.
     *
     * @param youtubeUrl The full YouTube URL.
     * @return The extracted channel ID or null if not found.
     */
    public static String extractChannelIdFromUrl(String youtubeUrl) {
        Pattern channelPattern = Pattern.compile(
                "^https?://www\\.youtube\\.com/channel/([^&/?]*)", Pattern.CASE_INSENSITIVE);
        Matcher channelMatcher = channelPattern.matcher(youtubeUrl);

        if (channelMatcher.find()) {
            return channelMatcher.group(1);
        }
        return null;
    }
}

