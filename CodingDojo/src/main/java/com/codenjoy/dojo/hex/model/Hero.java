package com.codenjoy.dojo.hex.model;

import com.codenjoy.dojo.services.*;

public class Hero extends PointImpl implements Joystick, Tickable, State<Elements, Player> {

    private Field field;

    private Direction direction;
    private boolean jump;
    private Elements element;

    public Hero(Point xy, Elements element) {
        super(xy);
        this.element = element;
    }

    public Hero(int x, int y, Elements element ) {
        super(x, y);
        this.element = element;
    }

    public void init(Field field) {
        this.field = field;
    }

    @Override
    public void down() {
        direction = Direction.DOWN;
    }

    @Override
    public void up() {
        direction = Direction.UP;
    }

    @Override
    public void left() {
        direction = Direction.LEFT;
    }

    @Override
    public void right() {
        direction = Direction.RIGHT;
    }

    @Override
    public void act(int... p) {
        // do nothing
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void tick() {
        if (direction != null) {
            int newX = direction.changeX(x);
            int newY = direction.changeY(y);

            if (jump) {
                newX = direction.changeX(newX);
                newY = direction.changeY(newY);
            }

            if (!field.isBarrier(newX, newY)) {
                if (jump) {
                    field.jumpHero(newX, newY, this);
                } else {
                    field.addHero(newX, newY, this);
                }
            }
        }
        direction = null;
        jump = false;
    }

    public void isJump(boolean jump) {
        this.jump = jump;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return element;
    }

    public Elements getElement() {
        return element;
    }

    public void newOwner(Player player) {
        element = player.getElement();
    }
}

