package com.company.Objects;

import com.company.gui.AnimationPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimationObjects {
    private final AnimationPanel panel;
    private final ArrayList<Flag> objects = new ArrayList<>();
    private ExecutorService executorService;

    public AnimationObjects(AnimationPanel panel){this.panel = panel;}

    public void prepareObjects() {
        Flag.setUpFlag(panel, objects, Color.RED);
        Flag.setUpFlag(panel, objects, Color.PINK);
        Flag.setUpFlag(panel, objects, Color.CYAN);
        Flag.setUpFlag(panel, objects, Color.GREEN);
        Flag.setUpFlag(panel, objects, Color.MAGENTA);
        Flag.setUpFlag(panel, objects, Color.BLACK);
        Flag.setUpFlag(panel, objects, Color.BLUE);
        Flag.setUpFlag(panel, objects, Color.GRAY);
        executorService = Executors.newFixedThreadPool(objects.size());
    }
    public void updateParallel()
    {
        for (int i = 0; i < objects.size();i++){
            int finalI = i;
            executorService.submit(()->objects.get(finalI).update(objects));
        }
    }

    public ArrayList<Flag> getObjects() {
        return objects;
    }
}
