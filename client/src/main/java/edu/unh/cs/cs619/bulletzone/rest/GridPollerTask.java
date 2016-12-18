package edu.unh.cs.cs619.bulletzone.rest;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import edu.unh.cs.cs619.bulletzone.events.BusProvider;
import edu.unh.cs.cs619.bulletzone.util.GridWrapper;

/**
 * Created by simon on 10/3/14.
 */
@EBean
public class GridPollerTask {
    private static final String TAG = "PollServer";

    BusProvider busProvider;// = BusProvider.getInstance();

    @RestService
    BulletZoneRestClient restClient;

    public GridPollerTask(){
        busProvider = BusProvider.getInstance();
    }

    public void doPoll() {
        new Thread(new Runnable() {
            public void run(){
                while (true) {
                    onGridUpdate(restClient.grid());
                    // poll server every 100ms
                    SystemClock.sleep(100);
                }
            }
        }).start();
    }

    public void onGridUpdate(final GridWrapper gw) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                busProvider.getEventBus().post(new GridUpdateEvent(gw));
            }
        });
    }
}
