/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

/**
 *
 * @author saulo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideMenuComponent extends JPanel {

    private JPanel jPanel1;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public SideMenuComponent() {
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 600)); // largura padrão
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        jPanel1 = criarItemMenu("/img/CfCqj5bgJAGChpNqi7Ii4AYBEmPXtUjQ6JqQHfRQgKDRQ2wOhQACCCCAgNcECBpW1DhPbFaosk8EEEAAAQcKWBM0EPrwKZAkRFwlwC3IXfVJ2fjXAFrgoZzPSg5AggggAACCJgo8FyYtMDl4b6IQAAAABJRU5ErkJggg.png", "Mapa", jLabel3 = new JLabel());
        jPanel4 = criarItemMenu("/img/w9FOGLn501GZgAAAABJRU5ErkJggg.png", "Alertas", jLabel5 = new JLabel());
        jPanel5 = criarItemMenu("/img/wdffTCL6rzidQAAAABJRU5ErkJggg.png", "Perfil", jLabel2 = new JLabel());

        add(Box.createVerticalStrut(175));
        add(jPanel1);
        add(Box.createVerticalStrut(6));
        add(jPanel4);
        add(Box.createVerticalStrut(6));
        add(jPanel5);
        add(Box.createVerticalGlue());
    }

    private JPanel criarItemMenu(String iconPath, String labelText, JLabel labelRef) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 74));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null); // Layout absoluto

        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        iconLabel.setBounds(30, 20, 32, 32);
        panel.add(iconLabel);

        labelRef.setText(labelText);
        labelRef.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        labelRef.setBounds(90, 20, 100, 30);
        panel.add(labelRef);

        // Hover effects
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                panel.setBackground(new Color(230, 230, 230));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                panel.setBackground(Color.WHITE);
            }
        });

        return panel;
    }

    // Getter para ajustar visibilidade/textos se necessário
    public JLabel getMapaLabel() {
        return jLabel3;
    }

    public JLabel getAlertasLabel() {
        return jLabel5;
    }

    public JLabel getPerfilLabel() {
        return jLabel2;
    }

    public void setLabelsVisible(boolean visible) {
        jLabel2.setVisible(visible);
        jLabel3.setVisible(visible);
        jLabel5.setVisible(visible);
    }
}

