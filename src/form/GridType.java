/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import static data.Config.BAND_WIDTH;
import static data.Config.SELF_INTERFERENCE_INDEX;
import static data.Config.TRANS_RANGE_SINK;
import data.Sensor;
import data.SensorNetwork;
import data.SinkNode;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ĐĐ
 */
public class GridType extends javax.swing.JFrame {

    Graphics graphic;//khởi tạo thư viện graphics
    Sensor sensor;//sensor trung gian để lấy dữ liệu từ form RunMain
    List<Sensor> listSensor = new ArrayList<>();//danh sách các sensor trong mạng
    List<SinkNode> listSink = new ArrayList<>();//danh sách các sink sensor
    int transmissionRange;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Creates new form GridType
     */
    public GridType() {//constructor
        initComponents();
        initGraphics();

        addWindowListener(new WindowAdapter() {//form load
            @Override
            public void windowOpened(WindowEvent e) {
                transmissionRange = sensor.getTransmissionRange();
                drawGridSensor(transmissionRange);
                drawGridSinkSensor(TRANS_RANGE_SINK);
                showInforSink();
            }
        });

    }

    public void initGraphics() { //khởi tạo constructor thư viện
        graphic = this.getGraphics();
        graphic.setPaintMode();
    }

    public void drawCircle(int x, int y) {//hàm vẽ hình và đường tròn
        graphic.setColor(Color.blue);
        //vẽ đường tròn với các tham số x,y,chiều cao,chiều rộng,từ 0-360 độ
        graphic.drawArc(x - transmissionRange, y - transmissionRange, 2 * transmissionRange, 2 * transmissionRange, 0, 360);
        graphic.setColor(Color.yellow);
        //vẽ đường tròn với các tham số x,y,chiều cao,chiều rộng,từ 0-360 độ
        graphic.fillArc(x + 1 - transmissionRange, y + 1 - transmissionRange, 2 * transmissionRange - 2, 2 * transmissionRange - 2, 0, 360);
    }

    public void drawCircleSink(SinkNode s) {// vẽ đường tròn của sink
        if (s.checkEnabled()) {
            graphic.setColor(Color.RED);
        }else{
            graphic.setColor(Color.GRAY);
        }
        graphic.drawArc((int)s.getPoint().x - s.getTransmissionRange(), (int)s.getPoint().y - s.getTransmissionRange(), 2 * s.getTransmissionRange(), 2 * s.getTransmissionRange(), 0, 360);
    }

    public void drawGridSensor(int trantransmissionRange) {//vẽ grid các sensor
        int x = transmissionRange + 9;
        int y = transmissionRange + 23;
        int index = 1;
        for (int i = 0; i < this.getHeight() / (2 * transmissionRange); i++) {
            for (int j = 0; j < this.getWidth() / (2 * transmissionRange); j++) {
                Sensor s = new Sensor(sensor.getTransmissionRange(), sensor.getSpeed(), sensor.getCapacityOfPackage(), sensor.getStartUpEnergy(), sensor.getTransmitPower(), sensor.getReceivePower());
                s.setId(i);
                s.setPoint(new Point2D.Double(x, y));
                listSensor.add(s);
                drawCircle(x, y);
                graphic.setColor(Color.black);
                graphic.setFont(new Font("Arial", Font.PLAIN, 8));
                graphic.drawString(index + "", x, y);
                x += 2 * transmissionRange;
                index++;
            }
            x = transmissionRange + 9;
            y += 2 * transmissionRange;
        }
    }

    public void drawGridSinkSensor(int trans) {//vẽ grid sink

        int x = trans;
        int y = trans + 10;
        int index = 1;
        for (int i = y; i < this.getHeight() + (2 * trans) + (2 * trans) / (2 * trans); i += 2 * trans) {
            for (int j = x; j < this.getWidth() + (2 * trans) / (2 * trans); j += 2 * trans) {
                SinkNode s = new SinkNode();
                s.setId(index);
                s.setTransmissionRange(TRANS_RANGE_SINK);
                s.setPoint(new Point2D.Double(i, j));
                listSink.add(s);
                //x += 2 * trans;
                index++;
            }
            x = trans;
            //y += 2 * trans;
        }
        totalPackageOfEachSink();
        for(SinkNode sinkNode : listSink){
            drawCircleSink(sinkNode);
        }

        //System.out.println(listSenSor.size() + "," + listSink.size());
    }

    public void totalPackageOfEachSink() {//get data mà các sink nhận được từ các sensor nó kết nối đc
        for (int i = 0; i < listSink.size(); i++) {
            double data = 0;
            for (int j = 0; j < listSensor.size(); j++) {
                if (listSink.get(i).isConnected(listSensor.get(j))) {
                    data += listSensor.get(j).getSpeed() * listSensor.get(j).getCapacityOfPackage();
                }
            }
            listSink.get(i).setDataReceive(data);
        }
    }
    public void showInforSink() {//hiển thị thông tin để so sánh
        String message = "";
        for (SinkNode sinkNode: listSink) {
            message += "Sink " + sinkNode.getId() + ", Data: " + sinkNode.getDataReceive() + "bps, Enabled: " + sinkNode.checkEnabled()+"\n";
            //System.out.println(key.toString()+value);
        }
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
        // System.out.println("Infor:"+message);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGridType = new javax.swing.JPanel();

        setTitle("GridType");
        setBackground(new java.awt.Color(255, 204, 255));

        panelGridType.setBackground(new java.awt.Color(255, 204, 204));
        panelGridType.setAlignmentX(0.0F);
        panelGridType.setAlignmentY(0.0F);

        javax.swing.GroupLayout panelGridTypeLayout = new javax.swing.GroupLayout(panelGridType);
        panelGridType.setLayout(panelGridTypeLayout);
        panelGridTypeLayout.setHorizontalGroup(
            panelGridTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        panelGridTypeLayout.setVerticalGroup(
            panelGridTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGridType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGridType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GridType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GridType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GridType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GridType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GridType().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelGridType;
    // End of variables declaration//GEN-END:variables
}
