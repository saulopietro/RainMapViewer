/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import api.ApiClient;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import view.components.MapComponent;
import view.components.SideMenuComponent;

/**
 *
 * @author alway
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainView() {
        
    initComponents(); // primeiro cria todos os componentes

    // Cria o componente de mapa
    MapComponent mapComponent = new MapComponent();
    mapComponent.centralizarNaLocalizacaoAtual();
    JXMapViewer map = mapComponent.getMap();
        
    carregarPinsNoMapa(map);

    
    map.setPreferredSize(new Dimension(800, 600));
    
    // Cria o componente do menu lateral
    SideMenuComponent sideMenu = new SideMenuComponent();

    // Define layout manual para o Background
    Background.setLayout(new BorderLayout());
    
    // Adiciona o menu lateral à esquerda
    Background.add(sideMenu, BorderLayout.WEST);

    // Adiciona o mapa no centro (resto da tela)
    Background.add(map, BorderLayout.CENTER);

    pack();
    setLocationRelativeTo(null); // centraliza na tela
    setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flood Viewer");

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setToolTipText("");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carregarPinsNoMapa(JXMapViewer map) {
    try {
        String response = ApiClient.getAll("alert");
        JSONArray alertas = new JSONArray(response.trim());

        Set<Waypoint> waypoints = new HashSet<>();
        Map<Waypoint, JSONObject> waypointToAlertMap = new HashMap<>();

        for (int i = 0; i < alertas.length(); i++) {
            JSONObject alerta = alertas.getJSONObject(i);
            JSONObject endereco = alerta.getJSONObject("address");

            double latitude = endereco.getDouble("latitude");
            double longitude = endereco.getDouble("longitude");

            GeoPosition pos = new GeoPosition(latitude, longitude);
            DefaultWaypoint waypoint = new DefaultWaypoint(pos);
            waypoints.add(waypoint);
            waypointToAlertMap.put(waypoint, alerta);
        }

        WaypointPainter<Waypoint> painter = new WaypointPainter<>();
        painter.setWaypoints(waypoints);

        // Adiciona o listener de clique
        map.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Converte coordenada de tela para posição geográfica
            GeoPosition geoPosition = map.convertPointToGeoPosition(e.getPoint());
            
            // Encontra o waypoint mais próximo
            Waypoint clickedWaypoint = null;
            double minDistance = Double.MAX_VALUE;
            final int TOLERANCE_PIXELS = 20; // Tolerância de 20 pixels
            
            for (Waypoint wp : waypoints) {
                Point2D wpPoint = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
                double distance = e.getPoint().distance(wpPoint);
                
                if (distance < TOLERANCE_PIXELS && distance < minDistance) {
                    minDistance = distance;
                    clickedWaypoint = wp;
                }
            }
            
            if (clickedWaypoint != null) {
                JSONObject alerta = waypointToAlertMap.get(clickedWaypoint);
                mostrarDetalhesAlerta(alerta);
                
                // Opcional: centralizar no waypoint clicado
                map.setCenterPosition(clickedWaypoint.getPosition());
                map.setZoom(map.getZoom() + 1); // Dá um zoom in
                map.setZoom(map.getZoom() - 1); // E volta (efeito de destaque)
            }
        }
    }
});

        map.setOverlayPainter(painter);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar pins: " + e.getMessage(), 
            "Erro", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    
    private void mostrarDetalhesAlerta(JSONObject alerta) {
    try {
        // Extrai os dados do alerta
        String titulo = alerta.getString("title");
        String descricao = alerta.getString("description");
        String severidade = alerta.getString("severity");
        String data = alerta.getString("date");
        
        JSONObject endereco = alerta.getJSONObject("address");
        String rua = endereco.getString("street");
        String cidade = endereco.getString("city");
        
        // Formata a mensagem
        String mensagem = String.format(
            "<html><b>Título:</b> %s<br>" +
            "<b>Descrição:</b> %s<br>" +
            "<b>Severidade:</b> %s<br>" +
            "<b>Data:</b> %s<br>" +
            "<b>Local:</b> %s, %s</html>",
            titulo, descricao, severidade, data, rua, cidade);
        
        // Cria um painel personalizado para melhor formatação
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(mensagem), BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 200));
        
        // Mostra o diálogo
        JOptionPane.showMessageDialog(this, panel, "Detalhes do Alerta", 
            JOptionPane.INFORMATION_MESSAGE);
            
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao exibir detalhes: " + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    // End of variables declaration//GEN-END:variables
}
