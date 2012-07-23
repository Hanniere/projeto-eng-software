package interfaces;

import java.awt.Container;
import javax.swing.*;

public class PanelRelatorio extends JFrame {
    
    public PanelRelatorio () {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setLayout(null);
        this.setTitle("Relatorio");
        Container frame = this.getContentPane();
        this.setSize(300, 200);        
        JLabel selecioneData = new JLabel("Selecione a data que deseja gerar relatorio:");
        JComboBox dia = new JComboBox(new String[]{"-"});
        JComboBox mes = new JComboBox(new String[]{"-"});
        JComboBox ano = new JComboBox(new String[]{"-"});
        JButton gerar = new JButton("Gerar Relatorio");
        JButton fechar = new JButton("Cancelar");
        
        selecioneData.setBounds(20,5,290,30);
        dia.setBounds(30,50,50,30);
        mes.setBounds(100,50,50,30);
        ano.setBounds(170,50,70,30);
        gerar.setBounds(15,100,130,40);
        fechar.setBounds(160,100,110,40);
        
        frame.add(selecioneData);
        frame.add(dia);
        frame.add(mes);
        frame.add(ano);
        frame.add(gerar);
        frame.add(fechar);
        
        for(int i=1;i<=31;i++){
            dia.addItem(i);
        }
        for(int i=1;i<=12;i++){
            mes.addItem(i);
        }
        for(int i=2000;i<=2012;i++){
            ano.addItem(i);
        }
    }
}
