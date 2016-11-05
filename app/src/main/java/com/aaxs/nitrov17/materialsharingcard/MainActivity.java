package com.aaxs.nitrov17.materialsharingcard;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ImageView imageView;
    LinearLayout layoutButtons ,revelLayout;

    Animation alphaAnimation;
    int x,y;
    float pixelDensity;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);
        revelLayout = (LinearLayout) findViewById(R.id.linearView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View myView = view;
                launchTwitter(myView);
            }
        });

    }

    void launchTwitter(View view){

        x = imageView.getRight();
        y = imageView.getBottom();
        x -= ((28 * pixelDensity) + (16 * pixelDensity));

        int hypo = (int) Math.hypot(imageView.getMaxHeight(), imageView.getMaxWidth());

        if(flag){

            floatingActionButton.setImageResource(R.mipmap.image_cancel);
            floatingActionButton.setBackgroundResource(R.drawable.rounded_cancel_button);

            FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) revelLayout.getLayoutParams();
            layoutParams1.height=imageView.getHeight();
            revelLayout.setLayoutParams(layoutParams1);

            Animator animator = ViewAnimationUtils.createCircularReveal(revelLayout,x,y,0,hypo);
            animator.setDuration(700);

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    layoutButtons.setVisibility(View.VISIBLE);
                    layoutButtons.setAnimation(alphaAnimation);

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            revelLayout.setVisibility(View.VISIBLE);
            animator.start();
            flag=false;

        }

        else{

            floatingActionButton.setImageResource(R.mipmap.twitter_logo);
            floatingActionButton.setBackgroundResource(R.drawable.rounded_button);

            Animator animator  =ViewAnimationUtils.createCircularReveal(view,x,y,hypo,0);
            animator.setDuration(400);

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    revelLayout.setVisibility(View.GONE);
                    layoutButtons.setVisibility(View.GONE);

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            animator.start();
            flag=true;

        }

    }
}
