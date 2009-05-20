/*
 * JDesktop.java
 *
 * Created on 27 de Novembro de 2006, 23:51
 */

package visual;

import framework.agent.Agent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.GerenciadorFluxos;


/**
 *
 * @author  Heliomar, Roberto
 *
 */

public class JDesktop extends javax.swing.JFrame {
    
    
    public static Map<Agent, Principal> telagentes = new HashMap<Agent, Principal>();
    public static Principal saidas;
    
    private javax.swing.JMenu jMArquivo;
    private javax.swing.JMenuItem jMIniciar;
    private javax.swing.JMenuItem jMISair;
    private javax.swing.JMenuItem jMISobre;
    private javax.swing.JMenu jMSobre;
    private javax.swing.JMenuBar jMenuBar1;
    
    public JDesktop() {
        initComponents();
    }
    
    public static Principal getTela(Agent agente){
        return telagentes.get(agente);
    }
    
    private void addComponentes(){
        saidas.setVisible(true);        
        add(saidas);
    }
    
    
    private void initComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMArquivo = new javax.swing.JMenu();
        jMIniciar = new javax.swing.JMenuItem();
        jMISair = new javax.swing.JMenuItem();
        jMSobre = new javax.swing.JMenu();
        jMISobre = new javax.swing.JMenuItem();
        
        saidas = new Principal();
        saidas.setTitle("Outras saídas");
        saidas.setBounds(0, 0, 600, 300);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        jMArquivo.setText("Agente");
        jMIniciar.setText("Iniciar Simulação");
        jMIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIArqCriacaoActionPerformed(evt);
            }
        });

        jMArquivo.add(jMIniciar);

        jMISair.setText("Sair");
        jMISair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISairActionPerformed(evt);
            }
        });

        jMArquivo.add(jMISair);

        jMenuBar1.add(jMArquivo);


        jMSobre.setText("Sobre");
        jMISobre.setText("Sobre");
        jMISobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISobreActionPerformed(evt);
            }
        });

        jMSobre.add(jMISobre);

        jMenuBar1.add(jMSobre);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-300)/2, 400, 300);
    }

        
    private void jMISobreActionPerformed(java.awt.event.ActionEvent evt) {
        ImageIcon icone = new ImageIcon("computador1.gif");
        JOptionPane.showMessageDialog(null, "Universidade Federal Fluminense \n\n" +
                "DISCIPLINA:: Sistema Multi-Agentes \n\n" +
                "CURSO: " +
                "Mestrado em Ciência da Computação 1º/2009\n\n" +
                "Simulação de uma Residência com integrantes e faxineira\n\n"+
                "INTEGRANTES DO GRUPO :\n\n" +
                "Heliomar Kann da Rocha Santos \n\n" +
                "Roberto Weidmann Menezes\n\n\n" +
                "Maio 2009", "Sistema Multi-Agentes", JOptionPane.INFORMATION_MESSAGE,
                icone);
    }
    
    private void jMISairActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(1);
    }
    
    private void jMIArqCriacaoActionPerformed(java.awt.event.ActionEvent evt) {
        addComponentes();
        GerenciadorFluxos.iniciarFluxo();
        jMIniciar.setEnabled(false);
    }
    
    
    
    
}
