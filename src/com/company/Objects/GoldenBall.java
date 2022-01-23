package com.company.Objects;

import com.company.gui.AnimationPanel;
import com.company.math.Vector2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class GoldenBall {
    private Vector2D position;
    private Color color;
    private static float diameter = 100f;
    private Shape shape;
    private final AnimationPanel panel;
    private Image image;

    public GoldenBall(Vector2D position, Color color, AnimationPanel panel, Image image) {
        this.position = position;
        this.color = color;
        this.shape = new Ellipse2D.Float(position.y, position.x, diameter, diameter);
        this.panel = panel;
        this.image = image;
    }

    public static GoldenBall setUpBall(AnimationPanel panel, Color color, Image image) {
//        return new GoldenBall(new Vector2D(((float)panel.getHeight())/2f-diameter/2, ((float)panel.getWidth())/2f-diameter/2), color, panel);
        return new GoldenBall(new Vector2D(250 - diameter/2, 500 - diameter/2), color, panel, image);
    }

    public void render(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fill(shape);
        g2d.drawImage(image,(int)position.getYPosition(),(int)position.getXPosition(), null);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.x;
    }

    public Vector2D getInvertedPosition() {
        return new Vector2D(500 - diameter/4, 250 - diameter/4);
//        return new Vector2D(position.y, position.x);
    }

    public float getDiameter() {
        return diameter;
    }
}
