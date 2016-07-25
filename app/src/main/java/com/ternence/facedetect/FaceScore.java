package com.ternence.facedetect;

/**
 * Created by ternence on 16/7/23.
 */
public class FaceScore {
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.abs(Math.pow(p2.x - p1.x, 2)) + Math.abs(Math.pow(p2.y - p1.y, 2)));
    }

    public static double culFaceScore(FaceMark face) {

        if (face.smile < 20) {
            face.smile = -10;
        } else {
            face.smile = face.smile / 10;
        }

        //计算两眉头间的距离
        //double c1 = distance(face.left_eyebrow_right_corner,face.right_eyebrow_left_corner);
        //console.log('计算两眉头间的距离 = ' + c1);

        //眉毛之间的中点坐标;
        Point eyebrowMid = new Point(0, 0);
        eyebrowMid.x = (face.right_eyebrow_left_corner.x + face.left_eyebrow_right_corner.y) / 2;
        eyebrowMid.y = (face.right_eyebrow_left_corner.y + face.left_eyebrow_right_corner.y) / 2;

        //眉毛中点到鼻子最低处的距离
        double d1 = distance(face.nose_contour_lower_middle, eyebrowMid);

        //眼角之间的距离
        //console.log('眼角之间的距离 = ' + c3);
        double d2 = distance(face.left_eye_right_corner, face.right_eye_left_corner);

        //鼻子的宽度
        double d3 = distance(face.nose_left, face.nose_right);

        //脸的宽度
        double d4 = distance(face.contour_left1, face.contour_right1);

        //下巴到鼻子下方的高度
        double d5 = distance(face.contour_chin, face.nose_contour_lower_middle);

        //眼睛的大小
        double d6_left = distance(face.left_eye_left_corner, face.left_eye_right_corner);
        double d6_right = distance(face.right_eye_left_corner, face.right_eye_right_corner);

        //嘴巴的大小
        double d7 = distance(face.mouth_left_corner, face.mouth_right_corner);

        //嘴巴处的face大小
        double d8 = distance(face.contour_left6, face.contour_right6);


        /* 开始计算步骤 */
        double yourmark = 100, mustm = 0;

        //眼角距离为脸宽的1/5，
        mustm += Math.abs((d2 / d4) * 100 - 25);

        //鼻子宽度为脸宽的1/5
        mustm += Math.abs((d3 / d4) * 100 - 25);

        //眼睛的宽度，应为同一水平脸部宽度的1/5
        double eyepj = (d6_left + d6_right) / 2;
        mustm += Math.abs(eyepj / d4 * 100 - 25);

        //理想嘴巴宽度应为同一脸部宽度的1/2
        mustm += Math.abs((d7 / d8) * 100 - 50);

        //下巴到鼻子下方的高度 == 眉毛中点到鼻子最低处的距离
        mustm += Math.abs(d5 - d1);

        return yourmark - mustm + face.smile;
    }

}
