package com.example.se2_group4_project.cheating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.se2_group4_project.Gameboard;
import com.example.se2_group4_project.R;

public class CheatFunction extends PopupWindow implements SensorEventListener {
    private final Context cheatContext;
    private float currentX, currentY, currentZ, lastX, lastY, lastZ;
    private float  xDifference, yDifference, zDifference;
    private float shakeThreshhold = 3f;
    private boolean notFirstTime = false;
    private Sensor accelerometerSensor;
    private SensorManager sensorManager;
    private boolean isSensorAvailable;
    private View cheatPopUpView;

    public CheatFunction(Context context){
        this.cheatContext = context;
        cheatShake();
    }

    public void cheatShake(){
        sensorManager = (SensorManager) cheatContext.getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensorAvailable = true;
            Log.d("Sensor", "Sensor available!");
        }else{
            Log.d("Sensor", "Sensor is not available!");
            isSensorAvailable = false;
        }
    }

    public void registerSensor(){
        if (isSensorAvailable){
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void unRegisterSensor(){
        if (isSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("Sensor", "x-Sensor: "+sensorEvent.values[0]+"m/s2");
        Log.d("Sensor", "y-Sensor: "+sensorEvent.values[1]+"m/s2");
        Log.d("Sensor", "z-Sensor: "+sensorEvent.values[2]+"m/s2");

        currentX = sensorEvent.values[0];
        currentY = sensorEvent.values[1];
        currentZ = sensorEvent.values[2];

        if(notFirstTime)
        {
            xDifference = Math.abs(lastX - currentX);
            yDifference = Math.abs(lastY - currentY);
            zDifference = Math.abs(lastZ - currentZ);

            if((xDifference > shakeThreshhold && yDifference > shakeThreshhold) ||
                    (xDifference > shakeThreshhold && zDifference > shakeThreshhold) ||
                    (yDifference > shakeThreshhold && zDifference > shakeThreshhold))
            {
                Log.d("Sensor", "You shook the phone");
                unRegisterSensor();
            }
        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;
        notFirstTime = true;
    }





    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public Context getCheatContext() {
        return cheatContext;
    }

    public float getCurrentX() {
        return currentX;
    }

    public void setCurrentX(float currentX) {
        this.currentX = currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    public void setCurrentY(float currentY) {
        this.currentY = currentY;
    }

    public float getCurrentZ() {
        return currentZ;
    }

    public void setCurrentZ(float currentZ) {
        this.currentZ = currentZ;
    }

    public float getLastX() {
        return lastX;
    }

    public void setLastX(float lastX) {
        this.lastX = lastX;
    }

    public float getLastY() {
        return lastY;
    }

    public void setLastY(float lastY) {
        this.lastY = lastY;
    }

    public float getLastZ() {
        return lastZ;
    }

    public void setLastZ(float lastZ) {
        this.lastZ = lastZ;
    }

    public float getxDifference() {
        return xDifference;
    }

    public void setxDifference(float xDifference) {
        this.xDifference = xDifference;
    }

    public float getyDifference() {
        return yDifference;
    }

    public void setyDifference(float yDifference) {
        this.yDifference = yDifference;
    }

    public float getzDifference() {
        return zDifference;
    }

    public void setzDifference(float zDifference) {
        this.zDifference = zDifference;
    }

    public float getShakeThreshhold() {
        return shakeThreshhold;
    }

    public void setShakeThreshhold(float shakeThreshhold) {
        this.shakeThreshhold = shakeThreshhold;
    }

    public boolean isNotFirstTime() {
        return notFirstTime;
    }

    public void setNotFirstTime(boolean notFirstTime) {
        this.notFirstTime = notFirstTime;
    }

    public boolean isSensorAvailable() {
        return isSensorAvailable;
    }

    public void setSensorAvailable(boolean sensorAvailable) {
        isSensorAvailable = sensorAvailable;
    }
}
