package com.example.se2_group4_project;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.se2_group4_project.cheating.CheatPopUpActivity;
import com.example.se2_group4_project.databinding.ActivityCheatPopupBinding;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CheatPopUpTest {

    Context appContext;
    CheatPopUpActivity cheatPopUpActivity;
    ActivityCheatPopupBinding activityCheatPopupBinding;

    @Test
    public void integrationTest(){
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        activityCheatPopupBinding = ActivityCheatPopupBinding.inflate(LayoutInflater.from(appContext));
        cheatPopUpActivity = new CheatPopUpActivity(appContext,activityCheatPopupBinding );

        cheatPopUpActivity.setCheatingPlayer(1);
        assertTrue(cheatPopUpActivity.cheatingPlayer(1));
        assertFalse(cheatPopUpActivity.cheatingPlayer(2));
        assertEquals(1, cheatPopUpActivity.getCheatingPlayer());
    }
}
