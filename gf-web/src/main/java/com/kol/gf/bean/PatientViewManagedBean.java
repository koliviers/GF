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


}
