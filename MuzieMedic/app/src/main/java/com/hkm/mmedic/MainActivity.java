package com.hkm.mmedic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;


import android.support.v7.widget.Toolbar;

import com.hkm.mmedic.contents.AboutFragment;
import com.hkm.mmedic.contents.BassBoostFragment;
import com.hkm.mmedic.contents.EnvironmentalReverbFragment;
import com.hkm.mmedic.contents.EqualizerFragment;
import com.hkm.mmedic.contents.HQEqualizerFragment;
import com.hkm.mmedic.contents.HQVisualizerFragment;
import com.hkm.mmedic.contents.LoudnessEnhancerFragment;
import com.hkm.mmedic.contents.PlayerControlFragment;
import com.hkm.mmedic.contents.PresetReverbFragment;
import com.hkm.mmedic.contents.VirtualizerFragment;
import com.hkm.mmedic.contents.VisualizerFragment;
import com.hkm.mmedic.framework.AppEvent;
import com.hkm.mmedic.framework.AppEventBus;
import com.hkm.mmedic.model.EventDefs;
import com.hkm.mmedic.model.GlobalAppController;
import com.hkm.mmedic.utils.GlobalAppControllerAccessor;

import static com.hkm.mmedic.model.EventDefs.NavigationDrawerReqEvents.*;
import static com.hkm.mmedic.model.EventDefs.Category.*;
import static com.hkm.mmedic.model.EventDefs.PlayerControlNotifyEvents.*;
import static com.hkm.mmedic.model.EventDefs.*;
public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_NAVIGATION_DRAWER = "NavigationDrawer";

    // fields
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private AppEventReceiver mAppEventReceiver;
    private Toast mToast;
    private Runnable mDecorViewInitialized;

    // internal classes
    private static class AppEventReceiver extends AppEventBus.Receiver<MainActivity> {
        private static final int[] FILTER = new int[] {
                EventDefs.Category.NAVIGATION_DRAWER,
                EventDefs.Category.NOTIFY_PLAYER_CONTROL,
        };

        public AppEventReceiver(MainActivity holder) {
            super(holder, FILTER);
        }

        @Override
        protected void onReceiveAppEvent(MainActivity holder, AppEvent event) {
            holder.onReceiveAppEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup event bus
        mAppEventReceiver = new AppEventReceiver(this);
        eventBus().register(mAppEventReceiver);

        // set content view
        setContentView(R.layout.activity_main);

        // set ToolBar as a ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_toolbar_title_text));
        setSupportActionBar(toolbar);

        // instantiate NavigationDrawerFragment
        FragmentManager fm = getFragmentManager();

        NavigationDrawerFragment fragment = (NavigationDrawerFragment)
                fm.findFragmentByTag(FRAGMENT_TAG_NAVIGATION_DRAWER);

        if (fragment == null) {
            fragment = (NavigationDrawerFragment)
                    Fragment.instantiate(this, NavigationDrawerFragment.class.getName());
            fm.beginTransaction()
                    .replace(R.id.drawer_container, fragment, FRAGMENT_TAG_NAVIGATION_DRAWER)
                    .commit();
        } else {
            fm.beginTransaction().attach(fragment).commit();
        }

        mNavigationDrawerFragment = fragment;

        // set initial contents
        if (savedInstanceState == null) {
            switchContents(SECTION_INDEX_PLAYER_CONTROL);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // schedule onDecorViewInitialized() event
        mDecorViewInitialized = new Runnable() {
            @Override
            public void run() {
                onDecorViewInitialized();
            }
        };

        getWindow().getDecorView().post(mDecorViewInitialized);
    }

    @Override
    protected void onPause() {
        if (mDecorViewInitialized != null) {
            getWindow().getDecorView().removeCallbacks(mDecorViewInitialized);
            mDecorViewInitialized = null;
        }

        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        eventBus().unregister(mAppEventReceiver);
        mAppEventReceiver = null;
        super.onDestroy();
    }

    private void onDecorViewInitialized() {
        // measure status bar & navigation bar height
        final ViewGroup contents = (ViewGroup) findViewById(R.id.activity_contents);
        final int statusBarOffset = contents.getPaddingTop();
        final int navBarOffset = contents.getPaddingBottom();

        // apply to navigation drawer
        mNavigationDrawerFragment.setSystemBarsOffset(statusBarOffset, navBarOffset);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /* package */void onReceiveAppEvent(AppEvent event) {
        switch (event.category) {
            case NAVIGATION_DRAWER:
                onNavigationDrawerEvent(event);
                break;
            case NOTIFY_PLAYER_CONTROL:
                onNotifyPlayerControlEvent(event);
                break;
        }
    }

    private void onNavigationDrawerEvent(AppEvent event) {
        switch (event.event) {
            case SELECT_PAGE:
                switchContents(event.arg1);
                break;
        }
    }

    private void onNotifyPlayerControlEvent(AppEvent event) {
        switch (event.event) {
            case EventDefs.PlayerControlNotifyEvents.PLAYER_STATE_CHANGED: {
                int state0 = getAppController().getPlayerState(0);
                int state1 = getAppController().getPlayerState(1);
                int active = getAppController().getActivePlayerIndex();

                StringBuilder sb = new StringBuilder();

                sb.append((active == 0) ? "* " : "  ");
                sb.append("Player[0] state: ");
                sb.append(formatPlayerState(state0));
                sb.append("\n");

                sb.append((active == 1) ? "* " : "  ");
                sb.append("Player[1] state: ");
                sb.append(formatPlayerState(state1));

                showToastMessage(sb.toString());
            }
            break;
            case EventDefs.PlayerControlNotifyEvents.NOTIFY_PLAYER_INFO: {
                int playerIndex = event.arg1;
                int what = event.extras.getInt(EventDefs.PlayerControlNotifyEvents.EXTRA_ERROR_INFO_WHAT);
                int extra = event.extras.getInt(EventDefs.PlayerControlNotifyEvents.EXTRA_ERROR_INFO_EXTRA);

                showToastMessage("Player[" + playerIndex + "] INFO: \n"
                        + "what = " + what + ", extra = " + extra);
            }
            break;
            case EventDefs.PlayerControlNotifyEvents.NOTIFY_PLAYER_ERROR: {
                int playerIndex = event.arg1;
                int what = event.extras.getInt(EventDefs.PlayerControlNotifyEvents.EXTRA_ERROR_INFO_WHAT);
                int extra = event.extras.getInt(EventDefs.PlayerControlNotifyEvents.EXTRA_ERROR_INFO_EXTRA);

                showToastMessage("Player[" + playerIndex + "] ERROR: \n"
                        + "what = " + what + ", extra = " + extra);
            }
            break;
            case EventDefs.PlayerControlNotifyEvents.NOTIFY_EXCEPTION_OCCURRED: {
                int playerIndex = event.arg1;
                String method = event.extras.getString(
                        EventDefs.PlayerControlNotifyEvents.EXTRA_EXEC_OPERATION_NAME);
                String exception = event.extras.getString(
                        EventDefs.PlayerControlNotifyEvents.EXTRA_EXCEPTION_NAME);

                showToastMessage("Player[" + playerIndex + "] Exception: \n" +
                        "method = " + method + ", exception = " + exception);
            }
            break;
        }
    }

    private void switchContents(int section) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment contents;

        switch (section) {
            case SECTION_INDEX_PLAYER_CONTROL:
                contents = new PlayerControlFragment();
                break;
            case SECTION_INDEX_BASSBOOST:
                contents = new BassBoostFragment();
                break;
            case SECTION_INDEX_VIRTUALIZER:
                contents = new VirtualizerFragment();
                break;
            case SECTION_INDEX_EQUALIZER:
                contents = new EqualizerFragment();
                break;
            case SECTION_INDEX_LOUDNESS_ENHANCER:
                contents = new LoudnessEnhancerFragment();
                break;
            case SECTION_INDEX_PRESET_REVERB:
                contents = new PresetReverbFragment();
                break;
            case SECTION_INDEX_ENVIRONMENTAL_REVERB:
                contents = new EnvironmentalReverbFragment();
                break;
            case SECTION_INDEX_VISUALIZER:
                contents = new VisualizerFragment();
                break;
            case SECTION_INDEX_HQ_EQUALIZER:
                contents = new HQEqualizerFragment();
                break;
            case SECTION_INDEX_HQ_VISUALIZER:
                contents = new HQVisualizerFragment();
                break;
            case SECTION_INDEX_ABOUT:
                contents = new AboutFragment();
                break;
            default:
                throw new IllegalStateException();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, contents)
                .commitAllowingStateLoss();
    }

    private void showToastMessage(String text) {
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public boolean isNavigationDrawerOpen() {
        return mNavigationDrawerFragment.isDrawerOpen();
    }

    public GlobalAppController getAppController() {
        return GlobalAppControllerAccessor.getInstance(this);
    }

    public AppEventBus eventBus() {
        return getAppController().eventBus();
    }

    private static String formatPlayerState(int state) {
        switch (state) {
            case PlayerControlNotifyEvents.STATE_IDLE:
                return "IDLE";
            case PlayerControlNotifyEvents.STATE_INITIALIZED:
                return "INITIALIZED";
            case PlayerControlNotifyEvents.STATE_PREPARING:
                return "PREPARING";
            case PlayerControlNotifyEvents.STATE_PREPARED:
                return "PREPARED";
            case PlayerControlNotifyEvents.STATE_STARTED:
                return "STARTED";
            case PlayerControlNotifyEvents.STATE_PAUSED:
                return "PAUSED";
            case PlayerControlNotifyEvents.STATE_STOPPED:
                return "STOPPED";
            case PlayerControlNotifyEvents.STATE_PLAYBACK_COMPLETED:
                return "PLAYBACK_COMPLETED";
            case PlayerControlNotifyEvents.STATE_END:
                return "END";
            case PlayerControlNotifyEvents.STATE_ERROR:
                return "ERROR";
            default:
                return "Unknown (" + state + ")";
        }
    }
}
