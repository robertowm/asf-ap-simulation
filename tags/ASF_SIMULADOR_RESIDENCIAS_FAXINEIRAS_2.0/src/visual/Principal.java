/*
 * Principal.java
 *
 * Created on 29 de Novembro de 2006, 00:47
 */

package visual;

/**
 *
 * @author  Heliomar kann
 */
public class Principal extends javax.swing.JInternalFrame {
    
    public Principal() {
        initComponents();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        
        setClosable(true);
        
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Saidas");
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Lucida Console", 0, 13));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(380, 10, 500, 300);
    }
    
    public void apendTexto(String linha){
        jTextArea1.append(linha+"\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
        
        this.repaint();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
}
