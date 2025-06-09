/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 * Componente de mapa reutilizável usando JXMapViewer e OpenStreetMap.
 */
public class MapComponent extends JPanel {
    private final JXMapViewer map;

    public MapComponent() {
        setLayout(new BorderLayout());

        map = new JXMapViewer();

        // Define a tile factory com tiles do OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        map.setTileFactory(tileFactory);

        // Define uma posição inicial padrão (Teresina - PI)
        GeoPosition geo = new GeoPosition(-5.0278769, -42.8102525);
        map.setAddressLocation(geo);
        map.setZoom(5);

        // Habilita interações com o mapa: pan (arrastar) e zoom com scroll
        MouseInputListener mouseListener = new PanMouseInputListener(map);
        map.addMouseListener(mouseListener);
        map.addMouseMotionListener(mouseListener);
        map.addMouseWheelListener(new ZoomMouseWheelListenerCenter(map));

        // Adiciona o mapa ao painel
        add(map, BorderLayout.CENTER);
    }

    public JXMapViewer getMap() {
        return map;
    }
}
