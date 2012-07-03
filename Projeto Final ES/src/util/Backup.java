/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Victor
 */
public class Backup {
    public void CriarBackup() {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setDialogTitle("Selecione o caminho para realizar o backup");
        
	int variableChooser = fileChooser.showSaveDialog(null);
        
	if (variableChooser != JFileChooser.APPROVE_OPTION) {
		JOptionPane.showMessageDialog(null, "Backup não realizado", "Janela finalizada", JOptionPane.CANCEL_OPTION);
	} else {
            String commandDump = "";
            if (System.getProperty("os.name").toUpperCase().compareTo("LINUX") == 0) {
                commandDump = "/usr/bin/mysqldump";
            } else {				
		commandDump = "C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\mysqldump.exe";
            }
            String location = fileChooser.getSelectedFile() + ".sql";
            ProcessBuilder pb = new ProcessBuilder(commandDump, "--user=root","--password=marcela", "ProjES", "--result-file=" + location);
            try {
                pb.start();
                JOptionPane.showMessageDialog(null, "Backup efetuado com sucesso");
            } catch (IOException e) {
                // TODO Auto-generated catch block		
                JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
	}
   }
    
}
