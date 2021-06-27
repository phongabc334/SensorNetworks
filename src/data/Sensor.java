package data;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Sensor extends Circle implements Serializable{
    protected int transmissionRange;//bán kính nhận/truyền
    protected double speed;//tốc độ truyền
    protected int capacityOfPackage;//dung lương của package
    protected int startUpEnergy;//năng lượng khởi đầu
    protected double transmitPower;//nằng lượng truyền
    protected double receivePower;//năng lượng nhận
    protected List<Sensor> listConnect;//danh sách các sensor kết nối được
    
    //các constrctor
    public Sensor() {
      
    }

    public Sensor(int transmissionRange, double speed, int capacityOfPackage, int startUpEnergy, double transmitPower, double receivePower, int id, float x, float y) {
        super(id, x, y);
        this.transmissionRange = transmissionRange;
        this.speed = speed;
        this.capacityOfPackage = capacityOfPackage;
        this.startUpEnergy = startUpEnergy;
        this.transmitPower = transmitPower;
        this.receivePower = receivePower;
    }

    public Sensor(int transmissionRange, double speed, int capacityOfPackage, int startUpEnergy, double transmitPower, double receivePower) {
        this.transmissionRange = transmissionRange;
        this.speed = speed;
        this.capacityOfPackage = capacityOfPackage;
        this.startUpEnergy = startUpEnergy;
        this.transmitPower = transmitPower;
        this.receivePower = receivePower;
    }
    
    //getter và setter
    public List<Sensor> getListConnect() {
        return listConnect;
    }

    public void setListConnect(List<Sensor> listConnect) {
        this.listConnect = listConnect;
    }
   
    public int getTransmissionRange() {
        return transmissionRange;
    }

    public void setTransmissionRange(int transmissionRange) {
        this.transmissionRange = transmissionRange;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCapacityOfPackage() {
        return capacityOfPackage;
    }

    public void setCapacityOfPackage(int capacityOfPackage) {
        this.capacityOfPackage = capacityOfPackage;
    }

    public int getStartUpEnergy() {
        return startUpEnergy;
    }

    public void setStartUpEnergy(int startUpEnergy) {
        this.startUpEnergy = startUpEnergy;
    }

    public double getTransmitPower() {
        return transmitPower;
    }

    public void setTransmitPower(double transmitPower) {
        this.transmitPower = transmitPower;
    }

    public double getReceivePower() {
        return receivePower;
    }

    public void setReceivePower(double receivePower) {
        this.receivePower = receivePower;
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

    //Lấy khoảng cách với 1 sensor bất kì 
    public double getDistance(Sensor other){
        return Math.sqrt((other.point.getX()-this.point.getX())*(other.point.getX()-this.point.getX())+(other.point.getY()-this.point.getY())*(other.point.getY()-this.point.getY()));
    }
    //Kiểm tra tính kết nối với 1 sensor bất kì 
    public boolean isConnected(Sensor other){
        if((this.transmissionRange + other.transmissionRange)>=getDistance(other)){
            return true;
        }
        return false;
    }
    //Kiểm tra tính tiếp xúc với 1 sensor bất kì
    public boolean isContact(Sensor other){
        if((this.transmissionRange + other.transmissionRange)==getDistance(other)){
            return true;
        }
        return false;
    }
    
    //Kiểm tra khoảng cách với 1 điểm bất kì 
    public double getDistance(int x, int y){
        return Math.sqrt((x-this.point.getX())*(x-this.point.getX())+(y-this.point.getY())*(y-this.point.getY()));
    }
    //Kiểm tra tính kết nối với 1 điểm bất kì 
    public boolean isConnected(int x, int y, int trans){
        if((this.transmissionRange + trans)>=getDistance(x,y)){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString()+ ", Sensor{" + "transmissionRange=" + transmissionRange + ", speed=" + speed + ", capacityOfPackage=" + capacityOfPackage + ", startUpEnergy=" + startUpEnergy + ", transmitPower=" + transmitPower + ", receivePower=" + receivePower + '}';
    }

    
    
}
