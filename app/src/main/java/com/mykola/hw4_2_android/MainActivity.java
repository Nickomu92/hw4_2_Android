/**
 *      Завдання на використання Frame Animation. Знайдіть де-небудь в мережі Інтернет спрайт з персонажем, що рухається
 * (чоловічком, твариною, птицею і т.д.). Наприклад, як показано на малюнку.
 *      Напишіть макет ігрової програми, в якій
 * персонаж рухався б, у відповідь на команди користувача, як показано на малюнку. Командами користувача є натискання
 * області екрана, які показані на малюнку блакитним кольором.
 *      Звертаємо вашу увагу, що ігровий персонаж завжди знаходиться в центрі екрана (дивись маланок). При натисканні на
 * праву блакитну область екрана (див. малюнок), ігровий персонаж рухається вправо доти, доки користувач не припинить
 * утримувати палець на екрані або користувач не натисне на ліву область екрана.
 *      При натисканні на ліву блакитну область екрана (дивись малюнок), ігровий персонаж рухається вліво доти,
 * поки користувач не припинить утримувати палець на екрані або користувач не натисне праву область екрана. При
 * припиненні натискань з боку користувача ігровий персонаж зупиняється.
 *      Програма має бути зафіксована в альбомній орієнтації (програмно). Як це зробити, розповідалося в одному із наших
 * попередніх уроків цього курсу.
 *      Підібрати зображення для піктограми програми з різною деталізацією та розмістити їх у каталозі /res/mipmap.
 * Призначити у Маніфесті програми піктограму для цієї програми.
 */

package com.mykola.hw4_2_android;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ImageView ivAction;
    private Button btnLeft;
    private Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOrientationLandscape();
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void setOrientationLandscape() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void initViews() {
        ivAction = findViewById(R.id.ivAction);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListeners() {
        btnLeft.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                animation(R.drawable.leo_go_right_animation, true);
                animation(R.drawable.leo_go_left_animation, false);
            } else {
                animation(R.drawable.leo_go_left_animation, true);
            }
            return false;
        });

        btnRight.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                animation(R.drawable.leo_go_left_animation, true);
                animation(R.drawable.leo_go_right_animation, false);
            } else {
                animation(R.drawable.leo_go_right_animation, true);
            }
            return false;
        });
    }

    public void animation(int animationId, boolean isStart) {
        ivAction.setBackgroundResource(animationId);

        AnimationDrawable animation = (AnimationDrawable) ivAction.getBackground();

        if (!isStart && animation.isRunning()) {
            animation.stop();
        } else if (isStart) {
            animation.start();
        }
    }
}