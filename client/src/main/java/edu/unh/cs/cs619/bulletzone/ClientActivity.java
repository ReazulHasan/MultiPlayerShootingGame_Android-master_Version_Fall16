package edu.unh.cs.cs619.bulletzone;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.BackgroundExecutor;

import edu.unh.cs.cs619.bulletzone.events.BusProvider;
import edu.unh.cs.cs619.bulletzone.rest.BZRestErrorhandler;
import edu.unh.cs.cs619.bulletzone.rest.BulletZoneRestClient;
import edu.unh.cs.cs619.bulletzone.rest.GridPollerTask;
import edu.unh.cs.cs619.bulletzone.rest.GridUpdateEvent;
import edu.unh.cs.cs619.bulletzone.ui.GridAdapter;
import edu.unh.cs.cs619.bulletzone.util.GridWrapper;

@EActivity(R.layout.activity_client)
public class ClientActivity extends Activity {

    private static final String TAG = "ClientActivity";

    protected GridAdapter mGridAdapter;

    @ViewById
    protected GridView gridView;

    BusProvider busProvider = BusProvider.getInstance();;

    @NonConfigurationInstance
    @Bean
    GridPollerTask gridPollTask;// = new GridPollerTask();;

    @RestService
    BulletZoneRestClient restClient;

    BZRestErrorhandler bzRestErrorhandler;

    /**
     * Remote tank identifier
     */
    private long tankId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGridAdapter = new GridAdapter(this);
        bzRestErrorhandler = new BZRestErrorhandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        busProvider.getEventBus().unregister(gridEventHandler);
    }

    /**
     * Otto has a limitation (as per design) that it will only find
     * methods on the immediate class type. As a result, if at runtime this instance
     * actually points to a subclass implementation, the methods registered in this class will
     * not be found. This immediately becomes a problem when using the AndroidAnnotations
     * framework as it always produces a subclass of annotated classes.
     *
     * To get around the class hierarchy limitation, one can use a separate anonymous class to
     * handle the events.
     */
    private Object gridEventHandler = new Object()
    {
        @Subscribe
        public void onUpdateGrid(GridUpdateEvent event) {
            updateGrid(event.gw);
        }
    };

    @AfterViews
    protected void afterViewInjection() {
        joinAsync();
        SystemClock.sleep(500);
        gridView.setAdapter(mGridAdapter);
    }

    @AfterInject
    void afterInject() {
        restClient.setRestErrorHandler(bzRestErrorhandler);
        busProvider.getEventBus().register(gridEventHandler);
    }

    void joinAsync() {
        new Thread(new Runnable() {
            public void run(){
                try {
                    tankId = restClient.join().getResult();
                    gridPollTask.doPoll();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void updateGrid(GridWrapper gw) {
        mGridAdapter.updateList(gw.getGrid());
    }

    @Click({R.id.buttonUp, R.id.buttonDown, R.id.buttonLeft, R.id.buttonRight})
    protected void onButtonMove(View view) {
        final int viewId = view.getId();
        byte direction = 0;

        switch (viewId) {
            case R.id.buttonUp:
                direction = 0;
                break;
            case R.id.buttonDown:
                direction = 4;
                break;
            case R.id.buttonLeft:
                direction = 6;
                break;
            case R.id.buttonRight:
                direction = 2;
                break;
            default:
                Log.e(TAG, "Unknown movement button id: " + viewId);
                break;
        }
        this.moveAsync(tankId, direction);
    }

    void moveAsync(final long tankId, final byte direction) {
        new Thread(new Runnable() {
            public void run(){
                restClient.move(tankId, direction);
            }
        }).start();
    }

    @Click({R.id.btnTurnUp, R.id.btnTurnDown, R.id.btnTurnLeft, R.id.btnTurnRight})
    protected void onButtonTurn(View view) {
        final int viewId = view.getId();
        byte direction = 0;
        System.out.println("button turn called!");
        switch (viewId) {
            case R.id.btnTurnUp:
                direction = 0;
                break;
            case R.id.btnTurnDown:
                direction = 4;
                break;
            case R.id.btnTurnLeft:
                direction = 6;
                break;
            case R.id.btnTurnRight:
                direction = 2;
                break;
            default:
                Log.e(TAG, "Unknown movement button id: " + viewId);
                break;
        }
        this.turnAsync(tankId, direction);
    }

    void turnAsync(final long tankId, final byte direction) {
        new Thread(new Runnable() {
            public void run(){
                restClient.turn(tankId, direction);
            }
        }).start();
    }

    @Click(R.id.buttonFire)
    protected void onButtonFire() {
        new Thread(new Runnable() {
            public void run(){
                restClient.fire(tankId, 1);
            }
        }).start();
    }

    @Click(R.id.buttonLeave)
    void leaveGame() {
        new Thread(new Runnable() {
            public void run(){
                restClient.leave(tankId);
            }
        }).start();
    }

    @Click(R.id.buttonEjectFeature)
    void ejectFeature() {
        new Thread(new Runnable() {
            public void run(){
                restClient.ejectFeature(tankId);
            }
        }).start();
    }

    @Click(R.id.buttonEjectPerson)
    void ejectPerson() {
        new Thread(new Runnable() {
            public void run(){
                restClient.ejectPerson(tankId);
            }
        }).start();
    }


    void leaveAsync(final long tankId) {
        new Thread(new Runnable() {
            public void run(){
                restClient.leave(tankId);
            }
        }).start();
    }
}
