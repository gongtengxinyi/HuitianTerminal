package com.huitian.frame;

import com.huitian.constants.ConstantsUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zlyj on 2017/9/21.
 */
public class WaitFrame {
    private JButton logincencelButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel panel1jlabel;
    private JLabel panel2label;
    private JLabel labelforpanel4;
    private JPanel waitpanel;
    private JButton testButton;


    public WaitFrame() {

    }


    public JButton getTestButton() {
        return testButton;
    }

    public void setTestButton(JButton testButton) {
        this.testButton = testButton;
    }

    public JButton getLogincencelButton() {
        return logincencelButton;
    }

    public void setLogincencelButton(JButton logincencelButton) {
        this.logincencelButton = logincencelButton;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public void setPanel3(JPanel panel3) {
        this.panel3 = panel3;
    }

    public JPanel getPanel4() {
        return panel4;
    }

    public void setPanel4(JPanel panel4) {
        this.panel4 = panel4;
    }

    public JLabel getPanel1jlabel() {
        return panel1jlabel;
    }

    public void setPanel1jlabel(JLabel panel1jlabel) {
        this.panel1jlabel = panel1jlabel;
    }

    public JLabel getPanel2label() {
        return panel2label;
    }

    public void setPanel2label(JLabel panel2label) {
        this.panel2label = panel2label;
    }

    public JLabel getLabelforpanel4() {
        return labelforpanel4;
    }

    public void setLabelforpanel4(JLabel labelforpanel4) {
        this.labelforpanel4 = labelforpanel4;
    }

    public JPanel getWaitpanel() {
        return waitpanel;
    }

    public void setWaitpanel(JPanel waitpanel) {
        this.waitpanel = waitpanel;
    }
}
