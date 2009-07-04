/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visual.relatorio;

/**
 *
 * @author heliokann
 */

import agente.papel.Papel;
import framework.agentRole.AgentRole;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Arquivo;
import util.GeradorRelatorio;

/**
 * A simple demonstration of the crosshairs that can be displayed in an {@link XYPlot}.
 *
 * @author Heliokann
 */
public class Grafico extends javax.swing.JInternalFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    
    private Arquivo arquivo = null;
   
    public Grafico(AgentRole a) throws Exception{
        super("Gráfico de probabilidade de execução de ações");
        arquivo = GeradorRelatorio.getArquivoAprendizado((Papel)a);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(a.getAgentPlayingRole().toString(), dataset);    
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYDataset createDataset() throws Exception{
        
        List<Double> tempo = new ArrayList(100);
        List<Double> arruma = new ArrayList(100);
        List<Double> desArruma = new ArrayList(100);
        List<Double> limpa = new ArrayList(100);
        List<Double> suja = new ArrayList(100);
        
        String linha = arquivo.leLinha();
        
        while( (linha = arquivo.leLinha()) != null) {
//            System.out.println(linha);
            String[] split = linha.replaceAll(",", ".").split(";");
            tempo.add(Double.parseDouble(split[0]));
            arruma.add(Double.parseDouble(split[1]));
            desArruma.add(Double.parseDouble(split[2]));
            limpa.add(Double.parseDouble(split[3]));
            suja.add(Double.parseDouble(split[4]));
        }
        
        if(tempo.size()<5){
            throw new Exception("O agente ainda não possui dados necessários para gerar Gráfico");
        }
        
        XYSeries series1 = new XYSeries("Arrumar");
        
        for (int i = 0; i < tempo.size(); i++) {
            series1.add(tempo.get(i), arruma.get(i));
        }

        XYSeries series2 = new XYSeries("Desarrumar");
        for (int i = 0; i < tempo.size(); i++) {
            series2.add(tempo.get(i), desArruma.get(i));
        }

        XYSeries series3 = new XYSeries("Limpar");
        for (int i = 0; i < tempo.size(); i++) {
            series3.add(tempo.get(i), limpa.get(i));
        }
        
        XYSeries series4 = new XYSeries("Sujar");
        for (int i = 0; i < tempo.size(); i++) {
            series4.add(tempo.get(i), suja.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
                
        return dataset;
        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(String titulo, XYDataset dataset) {
        
        JFreeChart chart = ChartFactory.createXYLineChart(
            titulo,       // chart title
            "Tempo em milisegundos",                      // x axis label
            "Porcentagem",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(Color.white);

//        LegendTitle legend =  chart.getLegend();
//        legend.setDisplaySeriesShapes(true);
        
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
//        plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);
//        plot.setOutlineStroke(Stroke)
        
//        StandardXYItemRenderer renderer = (StandardXYItemRenderer) plot.getRenderer();
////        renderer.setPlotShapes(true);
//        renderer.setShapesFilled(true);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
//    public static void main(String[] args) {
//
//        Grafico demo = new Grafico("Crosshair Demo 1", null);
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//
//    }

}

