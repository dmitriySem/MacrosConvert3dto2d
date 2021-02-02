/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macro;

import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dsemenov
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        System.out.println("Path:"+System.getProperty("user.dir")+File.separator+"Plane_z=0.071.csv");
        Methods methods = new Methods(364.2);
        try {
            File dir = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"macro"+File.separator);
            File[] files = dir.listFiles((d, name) -> name.endsWith(".csv"));
            for (int i = 0; i < files.length; i++) {
//                System.out.println(files[i].toString());
              
                methods.parsingCSVFile(files[i].toString());
        }
//  methods.parsingCSVFile(System.getProperty("user.dir")+File.separator+"src"+File.separator+"macro"+File.separator+"Plane_z=0.071.csv");
        } catch (CsvException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
