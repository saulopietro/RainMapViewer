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
 *
 * @author saulo
 */
public class MapComponent extends JPanel {
    private JXMapViewer map;

    public MapComponent() {
        setLayout(new BorderLayout());
        map = new JXMapViewer();

        // Tile factory com OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        map.setTileFactory(tileFactory);

        // Localização inicial
        GeoPosition geo = new GeoPosition(-5.0278769, -42.8102525);
        map.setAddressLocation(geo);
        map.setZoom(5);

        // Interações
        MouseInputListener mouseListener = new PanMouseInputListener(map);
        map.addMouseListener(mouseListener);
        map.addMouseMotionListener(mouseListener);
        map.addMouseWheelListener(new ZoomMouseWheelListenerCenter(map));
        
    }

    public JXMapViewer getMap() {
        return map;
    }
}
