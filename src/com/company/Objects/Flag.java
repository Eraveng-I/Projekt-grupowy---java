package com.company.Objects;

import com.company.gui.Animation;
import com.company.gui.AnimationPanel;
import com.company.math.Vector2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Flag{
    protected static final ArrayList<Integer> flagsIndices = new ArrayList<>();
    private Vector2D position;
    private Color color;
    private static float diameter = 50f;
    private float desiredSeparation = diameter * 1.4f;
    private Shape shape;
    private Vector2D velocity;
    private Vector2D acceleration;
    private final AnimationPanel panel;
    private int index;
    private float maxSpeed = 200f; // zmienione z 80 na 200
    private float maxAcceleration = 10f;
    private float separationWeight = 10f;
    private boolean isWinner = false;


        public Flag(Vector2D position, Color color, float diameter,AnimationPanel panel) {
            super();
            this.position = position;
            this.color = color;
            this.diameter = diameter;
            this.shape = new Ellipse2D.Float(0, 0,diameter,diameter);
            this.panel = panel;
        }


    public static void setUpFlag(AnimationPanel panel, ArrayList<Flag> objects, Color color){
            Flag flag = new Flag(new Vector2D(
                    Vector2D.randomFloat(0f, (float) panel.getBounds().getMaxX()),
                    Vector2D.randomFloat(0f, (float) panel.getBounds().getMaxY())
            ),
                    color, diameter, panel);
            flag.velocity = new Vector2D(
                    Vector2D.randomFloat(0f,100f),
                    Vector2D.randomFloat(0f,100f));
            int i;
            for (i=0; i<objects.size(); i++) {
                if (objects.get(i) == null) {
                    objects.set(i,flag);
                    break;
                }
            }
            if (i == objects.size()) {
                objects.add(flag);
            }
            flagsIndices.add(i);
            flag.setIndex(i);
            if ( i%2 == 1 ){
                flag.velocity = new Vector2D(-flag.velocity.x,-flag.velocity.y);
            }
        }

        public void moveTo(Vector2D newPosition){
            position = new Vector2D(newPosition);
            shape = new Ellipse2D.Float(position.x, position.y,diameter,diameter);
        }

    private void remainOnScreen(Rectangle frame) {
        Vector2D newPosition = new Vector2D(position);
        if (newPosition.x > frame.getMaxX()) {
            newPosition.x = (float) frame.getMinX();
        } else if (newPosition.x < frame.getMinX()) {
            newPosition.x = (float) frame.getMaxX();
        }
        if (newPosition.y > frame.getMaxY()) {
            newPosition.y = (float) frame.getMinY();
        } else if (newPosition.y < frame.getMinY()) {
            newPosition.y = (float) frame.getMaxY();
        }
        if (!newPosition.equals(position)) {
            moveTo(newPosition);
        }
    }
    private Vector2D separation(ArrayList<Flag> objects) {
        Vector2D steer = Vector2D.ZERO;
        int count = 0;
        for (var i : flagsIndices) {
            if (i == index) {
                continue;
            }
            Flag flag = objects.get(i);
            if (flag.position.subtract(position).magnitude() < desiredSeparation) {
                float distance = Vector2D.distance(position, flag.position);
                Vector2D diff = this.position.subtract(flag.position);
                if (distance * distance > 0) {
                    diff = diff.divide(distance * distance);
                }
                steer = steer.add(diff);
                count++;
            }
        }
        if (count > 0) {
            steer = steer.divide(count);
            if (steer.magnitude() > 0) {
                steer.normalize();
                steer = steer.multiply(maxSpeed);
            }
            steer = steer.subtract(this.velocity);
            steer.limit(maxAcceleration);
        }
        steer = steer.multiply(separationWeight);
        return steer;
    }

    private Vector2D ballContact(GoldenBall ball) {
        Vector2D steer = Vector2D.ZERO;
        if (ball.getInvertedPosition().subtract(position).magnitude() < ball.getDiameter() && !isWinner) {
            float distance = Vector2D.distance(position, ball.getInvertedPosition());
            Vector2D diff = position.subtract(ball.getInvertedPosition());
            if (distance * distance > 0) {
                diff = diff.divide(distance * distance);
            }
            steer = steer.add(diff);

            if (steer.magnitude() > 0) {
                steer.normalize();
                steer = steer.multiply(maxSpeed);
            }
            steer = steer.subtract(velocity);
            steer.limit(maxAcceleration);
            steer = steer.multiply(separationWeight * 2);
        }
        return steer;
    }

    public void update(ArrayList<Flag> objects, GoldenBall ball, Flag obj){
        acceleration = Vector2D.ZERO;
        acceleration = acceleration.add(ballContact(ball));
        acceleration = acceleration.add(separation(objects));
        if (acceleration == Vector2D.ZERO) acceleration = velocity.multiply(0.1);
        velocity = velocity.add(acceleration.multiply(Animation.frameTime));
        if (velocity.magnitude() > 0) {
            velocity.limit(maxSpeed);
        }

        toGoldenBall(obj, velocity);

        Vector2D newPosition = position.add(velocity.multiply(Animation.frameTime));
        moveTo(newPosition);
        remainOnScreen(panel.getBounds());
    }

    public void render(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fill(shape);
    }

    public void toGoldenBall(Flag obj, Vector2D velocity){
        if(isWinner){

            velocity.x = (450 - obj.position.getXPosition());
            velocity.y = (200 - obj.position.getYPosition());

            Vector2D newPosition = position.add(velocity.multiply(Animation.frameTime));
            moveTo(newPosition);

            if(velocity.x < 0.1 && velocity.y < 0.1){
                isWinner = false;
            }
        }
    }

    public void setIsWinner(boolean _isWinner){
        isWinner = _isWinner;
    }

    public Color getColor(){
        return this.color;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
