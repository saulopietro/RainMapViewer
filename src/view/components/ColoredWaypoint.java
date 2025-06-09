/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import java.awt.Color;

public class ColoredWaypoint extends DefaultWaypoint {
    private final Color color;

    public ColoredWaypoint(GeoPosition position, Color color) {
        super(position);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
