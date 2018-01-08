package com.cpst.news.event;

/**
 * Created by wy on 2017/12/3.
 *
 */

public class KeyDownEvent {
    public static int KEYCODE_BACK = 4;
    private int keyCode;

    public KeyDownEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
