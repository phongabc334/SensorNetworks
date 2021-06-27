/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.Config.TRANS_RANGE_SINK;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author ĐĐ
 */
public class SensorNetwork {

    private static final int RANDOM_TYPE = 1;
    private static final int REGULAR_TYPE = 2;
    private static final int PLAN_TYPE = 3;
    private int width;
    private int height;
    private List<Sensor> listSensors;
    private List<SinkNode> sinkSensors;
    private int type;

    public SensorNetwork(int width, int height, List<Sensor> listSensors, int type) {
        this.width = width;
        this.height = height;
        this.listSensors = listSensors;
        this.type = type;
    }

    //lấy ra những sensor có thể kết nối đc với sensor đang xét
    public void getConnectSensor() {
        for (int i = 0; i < listSensors.size(); i++) {
            List<Sensor> listConnect = new ArrayList<>();
            for (int j = 0; j < listSensors.size(); j++) {
                if (listSensors.get(i).isConnected(listSensors.get(j))) {
                    listConnect.add(listSensors.get(j));
                }
            }
            listSensors.get(i).setListConnect(listConnect);
            listConnect.clear();
        }
    }
    
    public List<SinkNode> getListSink() {
        List<SinkNode> listSensorSink = new ArrayList<>();//Giả sử các sensor đều là nút sink 
        for (Sensor sensor : listSensors) {
            SinkNode s = new SinkNode();
            s.setId(sensor.getId());
            s.setTransmissionRange(TRANS_RANGE_SINK);
            s.setPoint(sensor.getPoint());
            listSensorSink.add(s);
        }
        //Xét số lượng connect với từng sink
        for (int i = 0; i < listSensorSink.size(); i++) {//duyệt list sensor trong mạng
            List<Sensor> listConnect = new ArrayList<>();//tạo 1 list liên kết
            for (int j = 0; j < listSensors.size(); j++) {
                if (listSensorSink.get(i).isConnected(listSensors.get(j))) {//nếu connect đc thì add vào list
                    listConnect.add(listSensors.get(j));
                    //System.out.println(listSensors.get(j));
                }
            }
            listSensorSink.get(i).setListConnect(listConnect);
            //System.out.println(listSenserSink.get(i).getListConnect().size());
        }
        return listSensorSink;
    }
    //Lấy ra sink sensor có kết nối nhiều nhất
    public SinkNode getSinkSensor() {
        List<SinkNode> list = getListSink();
        int index = 0;
        int max = list.get(0).getListConnect().size();
        for (int i = 1; i < list.size(); i++) {
            System.out.println(list.get(i).getId() + "," + list.get(i).getListConnect().size());
            if (list.get(i).getListConnect().size() > max) {
                max = list.get(i).getListConnect().size();
                index = i;
            }
        }
        return list.get(index);

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Sensor> getListSensors() {
        return listSensors;
    }

    public void setListSensors(List<Sensor> listSensors) {
        this.listSensors = listSensors;
    }

    public List<SinkNode> getSinkSensors() {
        return sinkSensors;
    }

    public void setSinkSensors(List<SinkNode> sinkSensors) {
        this.sinkSensors = sinkSensors;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
