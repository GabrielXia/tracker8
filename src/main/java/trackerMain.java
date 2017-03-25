import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.events.Event;
import com.snowplowanalytics.snowplow.tracker.events.PageView;
import com.snowplowanalytics.snowplow.tracker.events.ScreenView;

/**
 */
public class trackerMain {

    public static void main(String[] args) {

        // for now the collectorUrl runs on AWS
        String collectorUrl = "http://localhost:8000";

        Event osEvent = EventsUtils.getOsEvent();

        Event pageView = PageView.builder()
                .referrer("1")
                .pageTitle("2")
                .pageUrl("3")
                .build();

        Event screenEvent = ScreenView.builder()
                .id("id")
                .name("name")
                .build();

        ReportMetric.reportMetric(collectorUrl, "terasology", DevicePlatform.Desktop, pageView);
    }
}
