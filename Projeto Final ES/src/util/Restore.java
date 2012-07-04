/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */

public class Restore {
    public void RestaurarBackup (){
        JFileChooser fileChooser = new JFileChooser();
	fileChooser.setDialogTitle("Selecione o arquivo que deseja usar para restaurar o Bakcup");
        
        int variableChooser = fileChooser.showOpenDialog(null);
        if(variableChooser == JFileChooser.APPROVE_OPTION){
                String cmd = null;
                String location = fileChooser.getSelectedFile().getAbsolutePath()+"";
                if(System.getProperty("os.name").toUpperCase().contains("LINUX")){
                    cmd = "/bin/sh -c mysql --user=root --password=marcela --host=localhost ProjES < \"" + location + "\"";
                }else{
                    if(System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
                        cmd = "cmd.exe /c \"C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\mysql\" -hlocalhost -uroot -pmarcela ProjES < "+location;
                }
                try {
                    Runtime.getRuntime().exec(cmd);
                    JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
        }
    }
}