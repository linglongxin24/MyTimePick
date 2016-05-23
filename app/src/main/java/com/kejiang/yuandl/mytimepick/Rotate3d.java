package com.kejiang.yuandl.mytimepick;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by yuandl on 2016/4/22 0022.
 */
public class Rotate3d extends Animation {
//    private float mCenterX = 0;
//    private float mCenterY = 0;
//
//    public void setCenter(float centerX, float centerY) {
//        mCenterX = centerX;
//        CenterY = centerY;
//    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        Camera camera = new Camera();
        camera.save();
        camera.rotateY(180 * interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();
//        matrix.preTranslate(-mCenterX, -mCenterY);
//        matrix.postTranslate(mCenterX, mCenterY);
    }
}
