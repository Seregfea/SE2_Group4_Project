package com.example.se2_group4_project;

import android.content.Context;
import android.hardware.SensorEvent;
import android.os.Handler;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.cheating.CheatFunction;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CheatFunctionTest {
    CheatFunction cheatFunction;
    Context appContext;
    Handler clientHandler;
    ClientCallbacks clientCallbacks;
    SensorEvent event;

    @Test
    public void integrationTest(){
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        cheatFunction = new CheatFunction(appContext, clientHandler, clientCallbacks);
        assertTrue(cheatFunction.isSensorAvailable());
        cheatFunction.registerSensor();
        assertFalse(cheatFunction.isNotFirstTime());
        assertThrows(NullPointerException.class, ()->{
            cheatFunction.onSensorChanged(event);
        });
        cheatFunction.setCurrentX(2.0f);
        cheatFunction.setCurrentY(2.0f);
        cheatFunction.setCurrentZ(2.0f);

        cheatFunction.setLastX(2.0f);
        cheatFunction.setLastY(2.0f);
        cheatFunction.setLastZ(2.0f);

        cheatFunction.calculateSensorValues(cheatFunction.getCurrentX(), cheatFunction.getCurrentY(), cheatFunction.getCurrentZ(),
                cheatFunction.getLastX(), cheatFunction.getLastY(), cheatFunction.getLastZ());
        assertEquals(2, cheatFunction.getTestValue());

        cheatFunction.setCurrentX(6.0f);
        cheatFunction.calculateSensorValues(cheatFunction.getCurrentX(), cheatFunction.getCurrentY(), cheatFunction.getCurrentZ(),
                cheatFunction.getLastX(), cheatFunction.getLastY(), cheatFunction.getLastZ());
        assertEquals(2, cheatFunction.getTestValue());

        cheatFunction.setCurrentX(6.0f);
        cheatFunction.setCurrentY(6.0f);
        assertThrows(NullPointerException.class, ()->{
            cheatFunction.calculateSensorValues(cheatFunction.getCurrentX(), cheatFunction.getCurrentY(), cheatFunction.getCurrentZ(),
                    cheatFunction.getLastX(), cheatFunction.getLastY(), cheatFunction.getLastZ());
        });
        assertEquals(1, cheatFunction.getTestValue());
        assertEquals(4.0f, cheatFunction.getxDifference(), 0.002f);
        assertEquals(4.0f, cheatFunction.getyDifference(), 0.002f);
        assertEquals(0.0f, cheatFunction.getzDifference(), 0.002f);
        cheatFunction.setShakeThreshhold(3.5f);
        assertEquals(3.5f, cheatFunction.getShakeThreshhold(), 0.002f);

        cheatFunction.setSensorAvailable(false);
        cheatFunction.registerSensor();
        assertEquals(3, cheatFunction.getTestValue());
        cheatFunction.unRegisterSensor();
        assertEquals(4, cheatFunction.getTestValue());
    }
}
