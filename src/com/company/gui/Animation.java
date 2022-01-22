package com.company.gui;

import com.company.Objects.AnimationObjects;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Animation {
    private AnimationPanel panel;
    public static double frameTime;
    private int fps;
    private final int TIME_SCALE = 1_000_000_000;
    private AnimationObjects objects;
    public static volatile boolean isEndOfProgram = false;

    public Animation() {
    }

    public void start(AnimationPanel panel){
        this.panel = panel;
        objects = new AnimationObjects(panel);
        new SwingWorker<Void,Void>(){
            @Override
            protected Void doInBackground(){
                objects.prepareObjects();
                unlimitedLoop();
                System.exit(0);
                return null;
            }
        }.execute();
    }

    private void unlimitedLoop() {
        long currentTime;
        long previousTime = System.nanoTime();
        long time = 0;
        long frameTimeNanos;
        int current_fps = 0;
        while (!isEndOfProgram){
            currentTime = System.nanoTime();
            objects.updateParallel();
            render();
            frameTimeNanos = currentTime - previousTime;
            previousTime = currentTime;
            time += frameTimeNanos;
            frameTime = (double) frameTimeNanos / TIME_SCALE;
            current_fps++;
            if (time >= TIME_SCALE) {
                time = 0;
                fps = current_fps;
                current_fps = 0;
            }
        }
    }
    private void render(){
        try {
            SwingUtilities.invokeAndWait(() -> panel.paintImmediately(0,0, panel.getWidth(),panel.getHeight()));
        } catch (InterruptedException | InvocationTargetException e){
            e.printStackTrace();
        }
    }


    public void renderFps(Graphics2D g2d) {
        objects.getObjects().stream().filter(Objects::nonNull).forEach(o -> o.render(g2d));
        objects.getBall().render(g2d);
        g2d.setColor(Color.GREEN);
        g2d.drawString("FPS: " + fps + "", 5, 15);
    }
    public Rectangle getDimensions() {
        return panel.getBounds();
    }

    public static void setIsEndOfProgram(boolean isEndOfProgram) {
        Animation.isEndOfProgram = isEndOfProgram;
    }
}
