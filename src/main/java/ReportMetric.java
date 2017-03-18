import com.snowplowanalytics.snowplow.tracker.DevicePlatform;
import com.snowplowanalytics.snowplow.tracker.Tracker;
import com.snowplowanalytics.snowplow.tracker.emitter.BatchEmitter;
import com.snowplowanalytics.snowplow.tracker.emitter.Emitter;
import com.snowplowanalytics.snowplow.tracker.emitter.RequestCallback;
import com.snowplowanalytics.snowplow.tracker.emitter.SimpleEmitter;
import com.snowplowanalytics.snowplow.tracker.events.Event;
import com.snowplowanalytics.snowplow.tracker.http.HttpClientAdapter;
import com.snowplowanalytics.snowplow.tracker.http.OkHttpClientAdapter;
import com.snowplowanalytics.snowplow.tracker.payload.TrackerPayload;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 */
public class ReportMetric {

    /**
     * report the metric to the collector, by HTTP
     * @param url       HTTP url
     * @param appId     app name
     * @param platform  the device platform
     * @param event     the event to send
     */
    public static void reportMetric(String url, String appId, DevicePlatform platform, Event event) {
        // Make a new client
        OkHttpClient client = new OkHttpClient();

        // Optionally configure the client
        client.setConnectTimeout(5, TimeUnit.SECONDS);
        client.setReadTimeout(5, TimeUnit.SECONDS);
        client.setWriteTimeout(5, TimeUnit.SECONDS);

        // Build the adapter
        HttpClientAdapter adapter = OkHttpClientAdapter.builder()
                .url(url)
                .httpClient(client)
                .build();

        // Make a RequestCallback
        RequestCallback callback = new RequestCallback() {

            public void onSuccess(int successCount) {
                System.out.println("Success sent, successCount: " + successCount);
            }

            public void onFailure(int successCount, List<TrackerPayload> failedEvents) {
                System.out.println("Failure, successCount: " + successCount + "\nfailedEvent:\n" + failedEvents.toString());
            }
        };

        Emitter batchEmitter = BatchEmitter.builder()
                .httpClientAdapter(adapter) // Required
                .threadCount(20) // Default is 50
                .requestCallback(callback)
                .bufferSize(1)
                .build();

        Tracker tracker = new Tracker.TrackerBuilder(batchEmitter, "tracker", appId)
                .platform(platform)
                .build();

        tracker.track(event);
    }
}
