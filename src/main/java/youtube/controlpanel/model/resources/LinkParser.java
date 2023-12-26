package youtube.controlpanel.model.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class to parse YouTube URLs and extract relevant information like video ID and channel ID.
 */
public class LinkParser {

    /**
     * Extracts the video ID from a YouTube URL.
     *
     * @param youtubeUrl The full YouTube URL.
     * @return The extracted video ID, or null if not found.
     */
    public static String extractVideoIdFromUrl(String youtubeUrl) {
        Pattern videoPattern = Pattern.compile(
                "^https?://(?:www\\.)?youtube\\.com/watch\\?v=([^&]+)", Pattern.CASE_INSENSITIVE);
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
     * @return The extracted channel ID, or null if not found.
     */
    public static String extractChannelIdFromUrl(String youtubeUrl) {
        Pattern channelPattern = Pattern.compile(
                "^https?://(?:www\\.)?youtube\\.com/channel/([^&/?]+)", Pattern.CASE_INSENSITIVE);
        Matcher channelMatcher = channelPattern.matcher(youtubeUrl);

        if (channelMatcher.find()) {
            return channelMatcher.group(1);
        }
        return null;
    }

    /**
     * Determines if the URL is a YouTube video URL.
     *
     * @param url The URL to check.
     * @return True if it is a video URL, false otherwise.
     */
    public static boolean isVideoUrl(String url) {
        return url.matches("^https?://(?:www\\.)?youtube\\.com/watch\\?v=([^&]+)");
    }

    /**
     * Determines if the URL is a YouTube channel URL.
     *
     * @param url The URL to check.
     * @return True if it is a channel URL, false otherwise.
     */
    public static boolean isChannelUrl(String url) {
        return url.matches("^https?://(?:www\\.)?youtube\\.com/channel/([^&/?]+)");
    }
}


