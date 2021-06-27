/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.Config.BAND_WIDTH;
import static data.Config.SELF_INTERFERENCE_INDEX;

/**
 *
 * @author ĐĐ
 */
public class SinkNode extends Sensor{//node sink
    private boolean isEnabled;
    private double dataReceive;

    public SinkNode() {
    }

    public SinkNode(int transmissionRange, double speed, int capacityOfPackage, int startUpEnergy, double transmitPower, double receivePower) {
        super(transmissionRange, speed, capacityOfPackage, startUpEnergy, transmitPower, receivePower);
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public double getDataReceive() {
        return dataReceive;
    }

    public void setDataReceive(double dataReceive) {
        this.dataReceive = dataReceive;
    }
    public boolean checkEnabled(){//check xem sink có thỏa mãn để kích hoạt hay không
        if (this.dataReceive <= SELF_INTERFERENCE_INDEX*BAND_WIDTH) {
            return true;
        }
        return false;
    }
    //Lấy khoảng cách với 1 sensor bất kì 
    @Override
    public double getDistance(Sensor other){
        return Math.sqrt((other.point.getX()-this.point.getX())*(other.point.getX()-this.point.getX())+(other.point.getY()-this.point.getY())*(other.point.getY()-this.point.getY()));
    }
    //Kiểm tra tính kết nối với 1 sensor bất kì 
    @Override
    public boolean isConnected(Sensor other){
        if((this.transmissionRange + other.transmissionRange)>=getDistance(other)){
            return true;
        }
        return false;
    }
    //Kiểm tra tính tiếp xúc với 1 sensor bất kì
    @Override
    public boolean isContact(Sensor other){
        if((this.transmissionRange + other.transmissionRange)==getDistance(other)){
            return true;
        }
        return false;
    }
}
