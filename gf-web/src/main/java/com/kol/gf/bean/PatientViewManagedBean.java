/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Patient;
import com.kol.gf.entities.Suivie;
import com.kol.gf.service.SuiviSessionBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@SessionScoped
public class PatientViewManagedBean implements Serializable {

    private Patient patient;

    private List<Suivie> suivieListePatient;

    private LineChartModel lineModel1;

    private BarChartModel barModel;

    @EJB
    private SuiviSessionBeanLocal suivieServices;

    public PatientViewManagedBean() {
        patient = new Patient();

        suivieListePatient = new ArrayList<>();
    }

    @PostConstruct
    public void init() {

    }

    public String informationPatient(Patient pat) {
        this.annulerSuivie();

        String page = null;
        if (pat != null) {
            patient = pat;
            this.createLineModels();
            this.createBarModel();
            suivieListePatient = suivieServices.getBy("patient", pat).stream()
                    .sorted(Comparator.comparing(Suivie::getDate_suivie).reversed())
                    .limit(3)
                    .collect(Collectors.toList());

            page = "/gf/patient/InformationPatient.xhtml";
        }
        return page;
    }

    public void annulerSuivie() {
        patient = new Patient();

        suivieListePatient = new ArrayList<>();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Tension");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("0 = Diastolique, 1 = Normal, 2 = Systolique");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("");
        yAxis.setMin(-1);
        yAxis.setMax(3);

        barModel.setExtender("skinBar");
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries tg = new ChartSeries();
        tg.setLabel("T.G");

        if (patient.getId() == null) {
            tg.set(1, -1);
            tg.set(2, -1);
            tg.set(3, -1);
            tg.set(4, -1);
            tg.set(5, -1);
            tg.set(6, -1);
            tg.set(7, -1);
            tg.set(8, -1);
            tg.set(9, -1);
            tg.set(10, -1);
        } else {
            
            int[] moisTableau2 = new int[10];
            LinkedHashMap<Integer, Double> resultat2 = new LinkedHashMap<>();

            List<String> suivieListeChart2 = suivieServices.getBy("patient", patient).stream()
                    .sorted(Comparator.comparing(Suivie::getDate_suivie).reversed())
                    .map(sc -> sc.getTensiongauche())
                    .collect(Collectors.toList());
            
            for (int i = 0; i < moisTableau2.length; i++) {
                int test = 1 + i;
                moisTableau2[i] = test;
            }
            
            for (int i22 = 0; i22 < moisTableau2.length; i22++) {
                int test2 = moisTableau2[i22];
                int test3 = 10 - i22;

                if (test3 > suivieListeChart2.size()) {
                    resultat2.put(test2, Double.parseDouble(String.valueOf(-1)));
                } else {
                    resultat2.put(test2, this.conversionTension(suivieListeChart2.get(test3 - 1)));
                }

            }

            for (Map.Entry<Integer, Double> svMp2 : resultat2.entrySet()) {
                tg.set(svMp2.getKey(), svMp2.getValue());
            }
            
            
        }


        ChartSeries td = new ChartSeries();
        td.setLabel("T.D");

        if (patient.getId() == null) {
            td.set(1, -1);
            td.set(2, -1);
            td.set(3, -1);
            td.set(4, -1);
            td.set(5, -1);
            td.set(6, -1);
            td.set(7, -1);
            td.set(8, -1);
            td.set(9, -1);
            td.set(10, -1);
        } else {
            
            int[] moisTableau22 = new int[10];
            LinkedHashMap<Integer, Double> resultat22 = new LinkedHashMap<>();

            List<String> suivieListeChart22 = suivieServices.getBy("patient", patient).stream()
                    .sorted(Comparator.comparing(Suivie::getDate_suivie).reversed())
                    .map(sc -> sc.getTensiondroit())
                    .collect(Collectors.toList());
            
            for (int i = 0; i < moisTableau22.length; i++) {
                int test = 1 + i;
                moisTableau22[i] = test;
            }
            
            for (int i222 = 0; i222 < moisTableau22.length; i222++) {
                int test2 = moisTableau22[i222];
                int test3 = 10 - i222;

                if (test3 > suivieListeChart22.size()) {
                    resultat22.put(test2, Double.parseDouble(String.valueOf(-1)));
                } else {
                    resultat22.put(test2, this.conversionTension(suivieListeChart22.get(test3 - 1)));
                }

            }

            for (Map.Entry<Integer, Double> svMp22 : resultat22.entrySet()) {
                td.set(svMp22.getKey(), svMp22.getValue());
            }

        }

        model.addSeries(tg);
        model.addSeries(td);

        return model;
    }
    
    public Double conversionTension(String cv){
        Double rslt = Double.parseDouble(String.valueOf(-1));
        
        switch(cv){
            case "Diastolique" :
                rslt = Double.parseDouble(String.valueOf(0));
                break;
            
            case "Normal" :
                rslt = Double.parseDouble(String.valueOf(1));
                break;
                
            case "Systolique" :
                rslt = Double.parseDouble(String.valueOf(2));
                break;
                
                
            default :
                rslt = Double.parseDouble(String.valueOf(-1));
        }
        
        return rslt;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Masse corporelle");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        lineModel1.setExtender("skinChart");

    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("MC");

        if (patient.getId() == null) {
            series1.set(1, 0);
            series1.set(2, 0);
            series1.set(3, 0);
            series1.set(4, 0);
            series1.set(5, 0);
            series1.set(6, 0);
            series1.set(7, 0);
            series1.set(8, 0);
            series1.set(9, 0);
            series1.set(10, 0);
        } else {

            int[] moisTableau = new int[10];
            LinkedHashMap<Integer, Double> resultat = new LinkedHashMap<>();

            List<Double> suivieListeChart = suivieServices.getBy("patient", patient).stream()
                    .sorted(Comparator.comparing(Suivie::getDate_suivie).reversed())
                    .map(sc -> sc.calculMasse())
                    .collect(Collectors.toList());

            for (int i = 0; i < moisTableau.length; i++) {
                int test = 1 + i;
                moisTableau[i] = test;
            }

            int taille = suivieListeChart.size();

            List<Double> suivieListeChartRangement = new ArrayList<>();

            for (int i3 = 0; i3 < suivieListeChart.size(); i3++) {
                suivieListeChartRangement.add(suivieListeChart.get(taille - (1 + i3)));
            }

            for (int i2 = 0; i2 < moisTableau.length; i2++) {
                int test2 = moisTableau[i2];
                int test3 = 10 - i2;

                if (test3 > suivieListeChart.size()) {
                    resultat.put(test2, Double.parseDouble(String.valueOf(0)));
                } else {
                    resultat.put(test2, suivieListeChart.get(test3 - 1));
                }

            }

            for (Map.Entry<Integer, Double> svMp : resultat.entrySet()) {
                series1.set(svMp.getKey(), svMp.getValue());
            }

        }

        model.addSeries(series1);

        return model;
    }

    public String retour() {
        return "/gf/patient/gestionPat.xhtml?faces-redirect=true";
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Suivie> getSuivieListePatient() {
        return suivieListePatient;
    }

    public void setSuivieListePatient(List<Suivie> suivieListePatient) {
        this.suivieListePatient = suivieListePatient;
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public SuiviSessionBeanLocal getSuivieServices() {
        return suivieServices;
    }

    public void setSuivieServices(SuiviSessionBeanLocal suivieServices) {
        this.suivieServices = suivieServices;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    

}
