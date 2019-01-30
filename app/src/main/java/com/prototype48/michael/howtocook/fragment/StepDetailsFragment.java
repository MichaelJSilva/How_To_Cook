package com.prototype48.michael.howtocook.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.prototype48.michael.howtocook.R;
import com.prototype48.michael.howtocook.model.Step;
import com.prototype48.michael.howtocook.utils.GlobalTagUtils;

public class StepDetailsFragment extends Fragment {

    // views
    PlayerView mPlayerView;
    TextView mStepTitle;
    TextView mStepDescription;
    SimpleExoPlayer playerView;

    // step selected
    Step step;

    //context
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        GlobalTagUtils tagUtils = new GlobalTagUtils();

        View view = inflater.inflate(R.layout.fragment_recipe_step_details,container,false);

        if (bundle != null) {
            this.step = bundle.getParcelable(tagUtils.STEP_DETAILS_TAG);
        }else{
            this.step = null;
        }

        // load details into the views
        if (step != null){
            loadStepDetails(view, step);
        }

        return view;
    }

    public void loadStepDetails(View view,Step step){

        // views
        this.mPlayerView         = view.findViewById(R.id.stepVideoPlayer);
        this.mStepDescription    = view.findViewById(R.id.txvStepDescription);
        this.mStepTitle          = view.findViewById(R.id.txvStepTitle);

        // views values
        mStepDescription.setText(step.getDescription());
        mStepTitle.setText(step.getShortDescription());

        // context
        mContext = this.getActivity().getBaseContext();

        playerView = ExoPlayerFactory.newSimpleInstance(mContext);

        mPlayerView.setPlayer(playerView);

        Log.d("TESTE",step.getVideoURL());

        DataSource.Factory dataSource = new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, "How_To_Cook"));

        if (step.getVideoURL() != "") {

            Uri uri = Uri.parse(step.getVideoURL());

            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSource)
                    .createMediaSource(uri);

            playerView.prepare(videoSource);
        }

    }

    public Step getStep() {
        return step;
    }

    @Override
    public void onDestroy() {
        if (playerView != null)
            playerView.release();
        super.onDestroy();
    }
}
