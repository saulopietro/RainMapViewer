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

        // Inicializa o mapa
        map = new JXMapViewer();

        // Usa tiles do OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        map.setTileFactory(tileFactory);

        // Define a posição inicial: Teresina - PI
        GeoPosition teresina = new GeoPosition(-5.0892, -42.8016); // Coordenadas mais precisas
        map.setAddressLocation(teresina);
        map.setZoom(12); // Zoom mais próximo

        // Ativa interações: mover e zoom
        MouseInputListener panListener = new PanMouseInputListener(map);
        map.addMouseListener(panListener);
        map.addMouseMotionListener(panListener);
        map.addMouseWheelListener(new ZoomMouseWheelListenerCenter(map));

        // Adiciona o mapa ao painel
        add(map, BorderLayout.CENTER);
    }

    /**
     * Retorna o componente JXMapViewer interno, útil para adicionar pins ou interações extras.
     * @return 
     */
    public JXMapViewer getMap() {
        return map;
    }
}
