/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 *
 * @author ĐĐ
 */

public class Circle implements Serializable{//Serializable chuyển đối tượng sang dạng nhị phân để ghi vào file
    protected int id;//id
    protected Point2D.Double point;//Điểm ảnh gồm 2 thuộc tính x,y
    
    public Circle() {
    }
    
    public Circle(int id, float x,float y) {//constrctor
        this.id = id;
        point = new Point2D.Double(x, y);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point2D.Double getPoint() {
        return point;
    }

    public void setPoint(Point2D.Double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Circle{" + "id=" + id + ", point=" + point+ '}';
    }
    
    
}
