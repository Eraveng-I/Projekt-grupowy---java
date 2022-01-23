package com.company.Objects;

import com.company.gui.AnimationPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.company.math.Vector2D;


public class AnimationObjects {
    private final AnimationPanel panel;
    private final ArrayList<Flag> objects = new ArrayList<>();
    private GoldenBall ball;
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
        ball = GoldenBall.setUpBall(panel, Color.decode("#D8C841"));
        executorService = Executors.newFixedThreadPool(objects.size());
    }
    public void updateParallel()
    {
        for (int i = 0; i < objects.size();i++){
            int finalI = i;
            executorService.submit(()->objects.get(finalI).update(objects, ball));
        }
    }

    public ArrayList<Flag> getObjects() {
        return objects;
    }

    public Flag getBallByColor(Color _color){
        return objects.stream().filter(f -> _color.equals(f.getColor())).findFirst().orElse(null);
    }

    public GoldenBall getBall() {
        return ball;
    }
}
