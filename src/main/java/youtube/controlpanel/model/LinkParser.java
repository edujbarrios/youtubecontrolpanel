package youtube.controlpanel.model;

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
        Pattern pattern = Pattern.compile(
                "^https?://www\\.youtube\\.com/.*v=([^&]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(youtubeUrl);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
