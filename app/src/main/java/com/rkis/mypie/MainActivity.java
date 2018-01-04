package com.rkis.mypie;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    PieChart pieChartFS;
    PieChart pieChartSalt;
    PieChart pieChartFibe;
    float protT = 0f, carbT = 0f, fattT = 0f;
    float protP = 15F , carbP = 55f, fattP= 30f;
    float protPt = 15f, carbPt = 55f, fattPt = 30f;
    float fatsPie = 0f, fibePie = 0f, saltPie = 0f;
    float fatsPieR = 100f, fibePieR = 100f, saltPieR = 100f;
    boolean pieInit = false, pieFSInit = false, pieSaltInit = false, pieFibeInit = false;
    ArrayList<Entry> yvalues = new ArrayList<Entry>();
    //ArrayList<String> xVals = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // big pie
        updatePie();


    }

    public void PupCdn(View v) {
        protT += 1f;
        protP -= 1f;
        updatePie();
    }
    public void PdnCup(View v) {
        protT -= 1f;
        protP += 1f;

        updatePie();
    }
    public void FupCdn(View v) {
        protT += 1f;
        protP -= 1f;
        carbT += 4f;
        carbP -= 4f;
        fattT += 2f;
        fattP -= 2f;
        fatsPie += 4;
        fatsPieR -= 4;
        fibePie += 4;
        fibePieR -= 4;
        saltPie += 4;
        saltPieR -= 4;
        updatePie();
    }
    public void FdnCup(View v) {
        protT -= 1f;
        protP += 1f;
        carbT -= 4f;
        carbP += 4f;
        fattT -= 2f;
        fattP += 2f;
        fatsPie -= 4;
        fatsPieR += 4;
        fibePie -= 4;
        fibePieR += 4;
        saltPie -= 4;
        saltPieR += 4;
        updatePie();
    }

    public void updatePie(){


        //Toast.makeText(getApplicationContext(),  "updatePie", Toast.LENGTH_SHORT).show();
        pieChart = (PieChart) findViewById(R.id.piechart);
        if ( pieInit == true){
            pieChart.invalidate();
        }else {
            pieInit = true;

        }

        pieChart.setUsePercentValues(true);
        ArrayList<Entry> vvalues = new ArrayList<Entry>();
        vvalues.add(new Entry(protT, 0));
        vvalues.add(new Entry(protP, 1));
        vvalues.add(new Entry(carbT, 2));
        vvalues.add(new Entry(carbP, 3));
        vvalues.add(new Entry(fattT, 4));
        vvalues.add(new Entry(fattP, 5));
        PieDataSet dataSet = new PieDataSet(vvalues, "");

        ArrayList<String> xVals = new ArrayList<String>();

        //dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        int lowC = 151;
        int highC = 200;
        final int[] MY_COLORS = {getResources().getColor(R.color.prot),Color.rgb(highC,lowC,lowC), getResources().getColor(R.color.carb),Color.rgb(lowC,highC,lowC), getResources().getColor(R.color.fats),Color.rgb(lowC,lowC,highC)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);

        dataSet.setColors(colors);

        xVals.add("PROTEIN " + String.valueOf((int)protT));
        xVals.add("");
        xVals.add("CARBS " + String.valueOf((int)carbT));
        xVals.add("");
        xVals.add("FATS " + String.valueOf((int)fattT));
        xVals.add("");

        PieData data = new PieData(xVals, dataSet);
        pieChart.setDrawHoleEnabled(false);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.BLACK);
        Legend l = pieChart.getLegend();
        l.setTextColor(Color.WHITE);
        l.setTextSize(12f);
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);

        dataSet.setDrawValues(false);
        data.setValueFormatter(new PercentFormatter());

        pieChart.setData(data);

        //  FS PIE
        pieChartFS = (PieChart) findViewById(R.id.piechartFS);
        if ( pieFSInit == true){
            pieChartFS.invalidate();
        }else {
            pieFSInit = true;

        }

        pieChartFS.setUsePercentValues(true);
        ArrayList<Entry> fsvalues = new ArrayList<Entry>();
        fsvalues.add(new Entry(fatsPie, 0));
        fsvalues.add(new Entry(fatsPieR, 1));
        PieDataSet dataSetFS = new PieDataSet(fsvalues, "");
        //ArrayList<String> xVals = new ArrayList<String>();
        final int[] MY_COLORSFS = {getResources().getColor(R.color.prot), getResources().getColor(R.color.salt)};
        ArrayList<Integer> colorsFS = new ArrayList<Integer>();
        for(int c: MY_COLORSFS) colorsFS.add(c);
        pieChartFS.setDescription("");
        dataSetFS.setColors(colorsFS);
        ArrayList<String> fsVals = new ArrayList<String>();
        //fsVals.add("PROTEIN " + String.valueOf((int)fatsPie));
        fsVals.add("Sat.fats " + String.valueOf((int)fatsPie));
        fsVals.add("");
        dataSetFS.setDrawValues(false);
        pieChartFS.setDrawHoleEnabled(false);
        pieChartFS.setHighlightPerTapEnabled(false);
        pieChartFS.setDrawSliceText(false);
        PieData dataFS = new PieData(fsVals, dataSetFS);
        Legend lfs = pieChartFS.getLegend();
        lfs.setTextColor(Color.WHITE);
        lfs.setTextSize(12f);
        pieChartFS.setRotationAngle(180);
        pieChartFS.setData(dataFS);
        //FS pie end

        //salt pie start
        pieChartSalt = (PieChart) findViewById(R.id.piechartSalt);
        if ( pieSaltInit == true){
            pieChartSalt.invalidate();
        }else {
            pieSaltInit = true;
        }
        pieChartSalt.setUsePercentValues(true);
        ArrayList<Entry> saltvalues = new ArrayList<Entry>();
        saltvalues.add(new Entry(saltPie, 0));
        saltvalues.add(new Entry(saltPieR, 1));
        PieDataSet dataSetSalt = new PieDataSet(saltvalues, "");
        //ArrayList<String> xVals = new ArrayList<String>();
        final int[] MY_COLORSSALT = {getResources().getColor(R.color.salt), getResources().getColor(R.color.secondaryColor)};
        ArrayList<Integer> colorsSalt = new ArrayList<Integer>();
        for(int c: MY_COLORSSALT) colorsSalt.add(c);
        pieChartSalt.setDescription("");
        dataSetSalt.setColors(colorsSalt);
        ArrayList<String> saltVals = new ArrayList<String>();
        //fsVals.add("PROTEIN " + String.valueOf((int)protT));
        saltVals.add("Salt " + String.valueOf((int)saltPie));
        saltVals.add("");
        dataSetSalt.setDrawValues(false);
        pieChartSalt.setDrawHoleEnabled(false);
        pieChartSalt.setHighlightPerTapEnabled(false);
        pieChartSalt.setDrawSliceText(false);
        PieData dataSalt = new PieData(saltVals, dataSetSalt);
        Legend lsalt = pieChartSalt.getLegend();
        lsalt.setTextColor(Color.WHITE);
        lsalt.setTextSize(12f);
        lsalt.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        pieChartSalt.setRotationAngle(180);
        pieChartSalt.setData(dataSalt);
        // end salt pie

        // fiber pie
        pieChartFibe = (PieChart) findViewById(R.id.piechartFibe);
        if ( pieFibeInit == true){
            pieChartFibe.invalidate();
        }else {
            pieFibeInit = true;

        }
        pieChartFibe.setUsePercentValues(true);
        ArrayList<Entry> fibevalues = new ArrayList<Entry>();
        fibevalues.add(new Entry(fibePie, 0));
        fibevalues.add(new Entry(fibePieR, 1));
        PieDataSet dataSetFibe = new PieDataSet(saltvalues, "");
        //ArrayList<String> xVals = new ArrayList<String>();
        final int[] MY_COLORSFIBE = {getResources().getColor(R.color.salt), getResources().getColor(R.color.secondaryColor)};
        ArrayList<Integer> colorsFibe = new ArrayList<Integer>();
        for(int c: MY_COLORSFIBE) colorsFibe.add(c);
        pieChartFibe.setDescription("");
        dataSetFibe.setColors(colorsFibe);
        ArrayList<String> fibeVals = new ArrayList<String>();
        //fsVals.add("PROTEIN " + String.valueOf((int)protT));
        fibeVals.add("Sugar " + String.valueOf((int)saltPie));
        fibeVals.add("");
        dataSetFibe.setDrawValues(false);
        pieChartFibe.setDrawHoleEnabled(false);
        pieChartFibe.setHighlightPerTapEnabled(false);
        pieChartFibe.setDrawSliceText(false);
        PieData dataFibe = new PieData(fibeVals, dataSetFibe);
        Legend lfibe = pieChartFibe.getLegend();
        lfibe.setTextColor(Color.WHITE);
        lfibe.setTextSize(12f);
        lfibe.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        pieChartFibe.setRotationAngle(180);
        pieChartFibe.setData(dataFibe);
        // end fiber
    }


}
