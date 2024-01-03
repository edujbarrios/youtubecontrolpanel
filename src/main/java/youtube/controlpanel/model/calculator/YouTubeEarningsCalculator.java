package youtube.controlpanel.model.calculator;

import com.google.api.services.youtube.model.Video;

public class YouTubeEarningsCalculator {

    private static final double AVERAGE_CPM = 3.0; // This can be adjusted as needed - 3€ per 1000 streams

    private static double calculateEarningsByViews(long views) {
        return (views / 1000.0) * AVERAGE_CPM;
    }

    private static double adjustEarningsForEngagement(double earnings, long likes, long comments) {
        double engagementFactor = (likes / 1000.0) * 0.005 + (comments / 100.0) * 0.003;
        return earnings * (1 + engagementFactor);
    }

    public static double calculateAdjustedEarnings(Video video) {
        long views = video.getStatistics().getViewCount().longValue();
        long likes = video.getStatistics().getLikeCount().longValue();
        long comments = video.getStatistics().getCommentCount().longValue();

        double earnings = calculateEarningsByViews(views);
        return adjustEarningsForEngagement(earnings, likes, comments);
    }
}
