    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
package view.components;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 * Componente de mapa reutilizável usando JXMapViewer e OpenStreetMap.
 */
public class MapComponent extends JPanel {
    private final JXMapViewer map;
    private final Set<Waypoint> waypoints = new HashSet<>();
    private final WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();

    public MapComponent() {
        setLayout(new BorderLayout());

        // Inicializa o mapa
        map = new JXMapViewer();

        // Usa tiles do OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        map.setTileFactory(tileFactory);

        // Ativa interações: mover e zoom
        MouseInputListener panListener = new PanMouseInputListener(map);
        map.addMouseListener(panListener);
        map.addMouseMotionListener(panListener);
        map.addMouseWheelListener(new ZoomMouseWheelListenerCenter(map));

        // Configura o painter de pinos
        waypointPainter.setWaypoints(waypoints);
        map.setOverlayPainter(waypointPainter);

        // Adiciona o mapa ao painel
        add(map, BorderLayout.CENTER);
    }

    public void centralizarNaLocalizacaoAtual() {
        try {
            URL url = new URL("http://ip-api.com/json");
            String json = new Scanner(url.openStream()).useDelimiter("\\A").next();
            JSONObject obj = new JSONObject(json);

            double lat = obj.getDouble("lat");
            double lon = obj.getDouble("lon");

            GeoPosition localAtual = new GeoPosition(lat, lon);
            map.setAddressLocation(localAtual);
            map.setZoom(7); // Zoom desejado

            System.out.println("Localização detectada: " + lat + ", " + lon);
        } catch (IOException e) {
            System.err.println("Erro ao obter localização: " + e.getMessage());
        }
    }

    public void adicionarWaypoint(GeoPosition posicao) {
        waypoints.add(new DefaultWaypoint(posicao));
        waypointPainter.setWaypoints(waypoints);
        map.repaint();
    }

    public void limparWaypoints() {
        waypoints.clear();
        waypointPainter.setWaypoints(waypoints);
        map.repaint();
    }

    public JXMapViewer getMap() {
        return map;
    }
}
