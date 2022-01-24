package com.company.Objects;

import com.company.gui.AnimationPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.company.math.Vector2D;

import javax.swing.*;


public class AnimationObjects {
    private final AnimationPanel panel;
    private final ArrayList<Flag> objects = new ArrayList<>();
    private GoldenBall ball;
    private ExecutorService executorService;

    public AnimationObjects(AnimationPanel panel){this.panel = panel;}

    public void prepareObjects() {
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag0.png").getImage(),Color.cyan);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag1.png").getImage(),Color.GREEN);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag2.png").getImage(),Color.GREEN);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag3.png").getImage(),Color.GREEN);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag4.png").getImage(),Color.RED);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag5.png").getImage(),Color.GREEN);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag6.png").getImage(),Color.GREEN);
        Flag.setUpFlag(panel, objects, new ImageIcon("src/main/resources/flag7.png").getImage(),Color.GREEN);
        ball = GoldenBall.setUpBall(panel, Color.decode("#D8C841"), new ImageIcon("src/main/resources/ballondor.png").getImage());
        executorService = Executors.newFixedThreadPool(objects.size());
    }
    public void updateParallel()
    {
        for (int i = 0; i < objects.size();i++){
            int finalI = i;
            executorService.submit(()->objects.get(finalI).update(objects, ball, objects.get(finalI)));
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
