package com.huitian.frame;

import com.huitian.constants.ConstantsURL;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.net.URI;

/**
 * 主面板的各种字段定义
 * Created by dingjianlei on 2017/9/21.
 */
public class MainForm {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable workingtable;
    private JPanel toppanel;
    private JScrollPane jscrollpanel;
    private JPanel bottompanel;
    private JPanel workeringpanel;
    private JPanel noworkingpanel;
    private JPanel waitsendgoodspanel;
    private JPanel historyindentpanel;
    private JPanel systemsettingpanel;
    private JPanel pagepanel;
    private JButton startButton;
    private JButton stopButton;
    private JPanel noworkingtoppanel;
    private JButton shouyeButton;
    private JButton gotobutton;
    private JButton lastButton;
    private JButton beforeButton;
    private JButton afterButton;
    private JTextField notextField;
    private JTable noworkingtable;
    private JLabel historylabel;
    private JButton hshouyeButton;
    private JButton hafterButton;
    private JButton hlastButton;
    private JButton hbeforeButton;
    private JButton gotoButton;
    private JTextField historytextField;
    private JLabel htotallabel;
    private JLabel nototallabel;
    private JTable table1;
    private JLabel settinglabel;
    private JTextField orgnalpasswordtext;
    private JTextField newpasswordtext;
    private JButton saveuserButton;
    private JTextField confirmpasswordtext;
    private JTextField motifytext;
    private JButton savefileButton;
    private JLabel filepathlabel;
    private JLabel orignalpasswordlabel;
    private JLabel newpasswordlabel;
    private JLabel confirmpasseordlabel;
    private JLabel motify;
    private JPanel morenlabel;
    private JTabbedPane foreightindenttab;
    private JTable finishtable;
    private JTable sendtable;
    private JPanel finishnosend;
    private JPanel sendprintindent;
    private JPanel finishjpanel;
    private JPanel finishbottom;
    private JScrollPane finishscrollpanel;
    private JPanel printpanel;
    private JLabel sendprintlabel;
    private JPanel sendbottompanel;
    private JScrollPane sendscrollpane;
    private JButton finishshouyebutton;
    private JButton finishgotoButton;
    private JButton finishlastButton;
    private JButton finishbeforeButton;
    private JButton finishafterButton;
    private JTextField finishtext;
    private JButton sendshouyeButton;
    private JButton sendafterButton;
    private JButton sendlastButton;
    private JButton sendbeforeButton;
    private JButton sendgotoButton;
    private JTextField aftertext;
    private JLabel finishlabelall;
    private JLabel finishtotalindent;
    private JLabel sendtotallabel;
    private JLabel sendtotalindent;
    private WebSocketClient webSocketClient;

    public MainForm() {
        try {
            String id = "";
            webSocketClient = this.connectWebsocket(id);
            webSocketClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebSocketClient connectWebsocket(final String centerAccountId) {
        WebSocketClient WebSocketClient = null;
        try {
            String websockerUrl = String.format("%s?USERID=%s&TERMINALTYPE=PC", ConstantsURL.WEBSOCKET_ADDRESS,
                    centerAccountId);
            URI uri = new URI(websockerUrl);
            WebSocketClient = new WebSocketClient(uri) {
                public void onMessage(String message) {
                    if (message != null) {
                        System.out.println(message);
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    try {
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }


                public void onOpen(ServerHandshake handshake) {
                    System.out.println("onopen");
                }


                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("onclose");
                }


                public void onError(Exception ex) {
                    System.out.println("onerror");
                }
            };
            System.out.println("窗体初始化，websocket连接成功");
            return WebSocketClient;
            // MainFrame.indentTalk(id,indent,userName,websocketSession);
        } catch (Exception e2) {
            // TODO: handle exception
            return WebSocketClient;
        }

    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public void setTabbedPane1(JTabbedPane tabbedPane1) {
        this.tabbedPane1 = tabbedPane1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTable getWorkingtable() {
        return workingtable;
    }

    public void setWorkingtable(JTable workingtable) {
        this.workingtable = workingtable;
    }

    public JPanel getToppanel() {
        return toppanel;
    }

    public void setToppanel(JPanel toppanel) {
        this.toppanel = toppanel;
    }

    public JScrollPane getJscrollpanel() {
        return jscrollpanel;
    }

    public void setJscrollpanel(JScrollPane jscrollpanel) {
        this.jscrollpanel = jscrollpanel;
    }

    public JPanel getBottompanel() {
        return bottompanel;
    }

    public void setBottompanel(JPanel bottompanel) {
        this.bottompanel = bottompanel;
    }

    public JPanel getWorkeringpanel() {
        return workeringpanel;
    }

    public void setWorkeringpanel(JPanel workeringpanel) {
        this.workeringpanel = workeringpanel;
    }

    public JPanel getNoworkingpanel() {
        return noworkingpanel;
    }

    public void setNoworkingpanel(JPanel noworkingpanel) {
        this.noworkingpanel = noworkingpanel;
    }

    public JPanel getWaitsendgoodspanel() {
        return waitsendgoodspanel;
    }

    public void setWaitsendgoodspanel(JPanel waitsendgoodspanel) {
        this.waitsendgoodspanel = waitsendgoodspanel;
    }

    public JPanel getHistoryindentpanel() {
        return historyindentpanel;
    }

    public void setHistoryindentpanel(JPanel historyindentpanel) {
        this.historyindentpanel = historyindentpanel;
    }

    public JPanel getSystemsettingpanel() {
        return systemsettingpanel;
    }

    public void setSystemsettingpanel(JPanel systemsettingpanel) {
        this.systemsettingpanel = systemsettingpanel;
    }

    public JPanel getPagepanel() {
        return pagepanel;
    }

    public void setPagepanel(JPanel pagepanel) {
        this.pagepanel = pagepanel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JPanel getNoworkingtoppanel() {
        return noworkingtoppanel;
    }

    public void setNoworkingtoppanel(JPanel noworkingtoppanel) {
        this.noworkingtoppanel = noworkingtoppanel;
    }

    public JButton getShouyeButton() {
        return shouyeButton;
    }

    public void setShouyeButton(JButton shouyeButton) {
        this.shouyeButton = shouyeButton;
    }

    public JButton getGotobutton() {
        return gotobutton;
    }

    public void setGotobutton(JButton gotobutton) {
        this.gotobutton = gotobutton;
    }

    public JButton getLastButton() {
        return lastButton;
    }

    public void setLastButton(JButton lastButton) {
        this.lastButton = lastButton;
    }

    public JButton getBeforeButton() {
        return beforeButton;
    }

    public void setBeforeButton(JButton beforeButton) {
        this.beforeButton = beforeButton;
    }

    public JButton getAfterButton() {
        return afterButton;
    }

    public void setAfterButton(JButton afterButton) {
        this.afterButton = afterButton;
    }

    public JTextField getNotextField() {
        return notextField;
    }

    public void setNotextField(JTextField notextField) {
        this.notextField = notextField;
    }

    public JTable getNoworkingtable() {
        return noworkingtable;
    }

    public void setNoworkingtable(JTable noworkingtable) {
        this.noworkingtable = noworkingtable;
    }

    public JLabel getHistorylabel() {
        return historylabel;
    }

    public void setHistorylabel(JLabel historylabel) {
        this.historylabel = historylabel;
    }

    public JButton getHshouyeButton() {
        return hshouyeButton;
    }

    public void setHshouyeButton(JButton hshouyeButton) {
        this.hshouyeButton = hshouyeButton;
    }

    public JButton getHafterButton() {
        return hafterButton;
    }

    public void setHafterButton(JButton hafterButton) {
        this.hafterButton = hafterButton;
    }

    public JButton getHlastButton() {
        return hlastButton;
    }

    public void setHlastButton(JButton hlastButton) {
        this.hlastButton = hlastButton;
    }

    public JButton getHbeforeButton() {
        return hbeforeButton;
    }

    public void setHbeforeButton(JButton hbeforeButton) {
        this.hbeforeButton = hbeforeButton;
    }

    public JButton getGotoButton() {
        return gotoButton;
    }

    public void setGotoButton(JButton gotoButton) {
        this.gotoButton = gotoButton;
    }

    public JTextField getHistorytextField() {
        return historytextField;
    }

    public void setHistorytextField(JTextField historytextField) {
        this.historytextField = historytextField;
    }

    public JLabel getHtotallabel() {
        return htotallabel;
    }

    public void setHtotallabel(JLabel htotallabel) {
        this.htotallabel = htotallabel;
    }

    public JLabel getNototallabel() {
        return nototallabel;
    }

    public void setNototallabel(JLabel nototallabel) {
        this.nototallabel = nototallabel;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JLabel getSettinglabel() {
        return settinglabel;
    }

    public void setSettinglabel(JLabel settinglabel) {
        this.settinglabel = settinglabel;
    }

    public JTextField getOrgnalpasswordtext() {
        return orgnalpasswordtext;
    }

    public void setOrgnalpasswordtext(JTextField orgnalpasswordtext) {
        this.orgnalpasswordtext = orgnalpasswordtext;
    }

    public JTextField getNewpasswordtext() {
        return newpasswordtext;
    }

    public void setNewpasswordtext(JTextField newpasswordtext) {
        this.newpasswordtext = newpasswordtext;
    }

    public JButton getSaveuserButton() {
        return saveuserButton;
    }

    public void setSaveuserButton(JButton saveuserButton) {
        this.saveuserButton = saveuserButton;
    }

    public JTextField getConfirmpasswordtext() {
        return confirmpasswordtext;
    }

    public void setConfirmpasswordtext(JTextField confirmpasswordtext) {
        this.confirmpasswordtext = confirmpasswordtext;
    }

    public JTextField getMotifytext() {
        return motifytext;
    }

    public void setMotifytext(JTextField motifytext) {
        this.motifytext = motifytext;
    }

    public JButton getSavefileButton() {
        return savefileButton;
    }

    public void setSavefileButton(JButton savefileButton) {
        this.savefileButton = savefileButton;
    }

    public JLabel getFilepathlabel() {
        return filepathlabel;
    }

    public void setFilepathlabel(JLabel filepathlabel) {
        this.filepathlabel = filepathlabel;
    }

    public JLabel getOrignalpasswordlabel() {
        return orignalpasswordlabel;
    }

    public void setOrignalpasswordlabel(JLabel orignalpasswordlabel) {
        this.orignalpasswordlabel = orignalpasswordlabel;
    }

    public JLabel getNewpasswordlabel() {
        return newpasswordlabel;
    }

    public void setNewpasswordlabel(JLabel newpasswordlabel) {
        this.newpasswordlabel = newpasswordlabel;
    }

    public JLabel getConfirmpasseordlabel() {
        return confirmpasseordlabel;
    }

    public void setConfirmpasseordlabel(JLabel confirmpasseordlabel) {
        this.confirmpasseordlabel = confirmpasseordlabel;
    }

    public JLabel getMotify() {
        return motify;
    }

    public void setMotify(JLabel motify) {
        this.motify = motify;
    }

    public JPanel getMorenlabel() {
        return morenlabel;
    }

    public void setMorenlabel(JPanel morenlabel) {
        this.morenlabel = morenlabel;
    }

    public JTabbedPane getForeightindenttab() {
        return foreightindenttab;
    }

    public void setForeightindenttab(JTabbedPane foreightindenttab) {
        this.foreightindenttab = foreightindenttab;
    }

    public JTable getFinishtable() {
        return finishtable;
    }

    public void setFinishtable(JTable finishtable) {
        this.finishtable = finishtable;
    }

    public JTable getSendtable() {
        return sendtable;
    }

    public void setSendtable(JTable sendtable) {
        this.sendtable = sendtable;
    }

    public JPanel getFinishnosend() {
        return finishnosend;
    }

    public void setFinishnosend(JPanel finishnosend) {
        this.finishnosend = finishnosend;
    }

    public JPanel getSendprintindent() {
        return sendprintindent;
    }

    public void setSendprintindent(JPanel sendprintindent) {
        this.sendprintindent = sendprintindent;
    }

    public JPanel getFinishjpanel() {
        return finishjpanel;
    }

    public void setFinishjpanel(JPanel finishjpanel) {
        this.finishjpanel = finishjpanel;
    }

    public JPanel getFinishbottom() {
        return finishbottom;
    }

    public void setFinishbottom(JPanel finishbottom) {
        this.finishbottom = finishbottom;
    }

    public JScrollPane getFinishscrollpanel() {
        return finishscrollpanel;
    }

    public void setFinishscrollpanel(JScrollPane finishscrollpanel) {
        this.finishscrollpanel = finishscrollpanel;
    }

    public JPanel getPrintpanel() {
        return printpanel;
    }

    public void setPrintpanel(JPanel printpanel) {
        this.printpanel = printpanel;
    }

    public JLabel getSendprintlabel() {
        return sendprintlabel;
    }

    public void setSendprintlabel(JLabel sendprintlabel) {
        this.sendprintlabel = sendprintlabel;
    }

    public JPanel getSendbottompanel() {
        return sendbottompanel;
    }

    public void setSendbottompanel(JPanel sendbottompanel) {
        this.sendbottompanel = sendbottompanel;
    }

    public JScrollPane getSendscrollpane() {
        return sendscrollpane;
    }

    public void setSendscrollpane(JScrollPane sendscrollpane) {
        this.sendscrollpane = sendscrollpane;
    }

    public JButton getFinishshouyebutton() {
        return finishshouyebutton;
    }

    public void setFinishshouyebutton(JButton finishshouyebutton) {
        this.finishshouyebutton = finishshouyebutton;
    }

    public JButton getFinishgotoButton() {
        return finishgotoButton;
    }

    public void setFinishgotoButton(JButton finishgotoButton) {
        this.finishgotoButton = finishgotoButton;
    }

    public JButton getFinishlastButton() {
        return finishlastButton;
    }

    public void setFinishlastButton(JButton finishlastButton) {
        this.finishlastButton = finishlastButton;
    }

    public JButton getFinishbeforeButton() {
        return finishbeforeButton;
    }

    public void setFinishbeforeButton(JButton finishbeforeButton) {
        this.finishbeforeButton = finishbeforeButton;
    }

    public JButton getFinishafterButton() {
        return finishafterButton;
    }

    public void setFinishafterButton(JButton finishafterButton) {
        this.finishafterButton = finishafterButton;
    }

    public JTextField getFinishtext() {
        return finishtext;
    }

    public void setFinishtext(JTextField finishtext) {
        this.finishtext = finishtext;
    }

    public JButton getSendshouyeButton() {
        return sendshouyeButton;
    }

    public void setSendshouyeButton(JButton sendshouyeButton) {
        this.sendshouyeButton = sendshouyeButton;
    }

    public JButton getSendafterButton() {
        return sendafterButton;
    }

    public void setSendafterButton(JButton sendafterButton) {
        this.sendafterButton = sendafterButton;
    }

    public JButton getSendlastButton() {
        return sendlastButton;
    }

    public void setSendlastButton(JButton sendlastButton) {
        this.sendlastButton = sendlastButton;
    }

    public JButton getSendbeforeButton() {
        return sendbeforeButton;
    }

    public void setSendbeforeButton(JButton sendbeforeButton) {
        this.sendbeforeButton = sendbeforeButton;
    }

    public JButton getSendgotoButton() {
        return sendgotoButton;
    }

    public void setSendgotoButton(JButton sendgotoButton) {
        this.sendgotoButton = sendgotoButton;
    }

    public JTextField getAftertext() {
        return aftertext;
    }

    public void setAftertext(JTextField aftertext) {
        this.aftertext = aftertext;
    }

    public JLabel getFinishlabelall() {
        return finishlabelall;
    }

    public void setFinishlabelall(JLabel finishlabelall) {
        this.finishlabelall = finishlabelall;
    }

    public JLabel getFinishtotalindent() {
        return finishtotalindent;
    }

    public void setFinishtotalindent(JLabel finishtotalindent) {
        this.finishtotalindent = finishtotalindent;
    }

    public JLabel getSendtotallabel() {
        return sendtotallabel;
    }

    public void setSendtotallabel(JLabel sendtotallabel) {
        this.sendtotallabel = sendtotallabel;
    }

    public JLabel getSendtotalindent() {
        return sendtotalindent;
    }

    public void setSendtotalindent(JLabel sendtotalindent) {
        this.sendtotalindent = sendtotalindent;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
