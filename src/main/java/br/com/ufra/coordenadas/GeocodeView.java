/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.coordenadas;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Jairo
 */
@ManagedBean
public class GeocodeView {

    private MapModel geoModel;

    private String centerGeoMap = "-1.2965754,-48.4652863";

    private LatLng center;

    private Coordenada coordenada;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        coordenada = new Coordenada();
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            this.center = results.get(0).getLatLng();
            centerGeoMap = this.center.getLat() + "," + this.center.getLng();

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }

        }

        JOptionPane.showMessageDialog(null, "Latitude: " + String.valueOf(center.getLat())
                + "\nLongitude: " + String.valueOf(center.getLng()));

        System.out.println("Latitude: " + String.valueOf(center.getLat()));
        System.out.println("Longitude: " + String.valueOf(center.getLng()));
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

}
