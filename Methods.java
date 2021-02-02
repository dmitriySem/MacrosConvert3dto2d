/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macro;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dsemenov
 */
public class Methods  {
    
      private double const_x;

    public Methods(double const_x) {
        this.const_x = const_x;
    }
    
      
      public void parsingCSVFile (String PathAndNameFiles) throws IOException, CsvException {
            
      try (CSVReader reader = new CSVReader(new FileReader(PathAndNameFiles))) {
          
            List<String[]> r = reader.readAll();
            r.remove(0);
            
//            List<double[]> listArraysTXYZ = new ArrayList<>();
//            for (int i = 0; i < r.size()-1; i++) {
//                double [] arrayTXYZ = Arrays.stream(r.get(i)).mapToDouble(Double::parseDouble).toArray(); //Разберись что это
//                listArraysTXYZ.add(arrayTXYZ);
//            }

            double[][] TableTXYZ =new double[r.size()][4];
//            double[][] TableTXYZ =new double[10][4];
            for (int i = 0; i < r.size(); i++) {
//            for (int i = 0; i < 10; i++) {
                double [] arrayTXYZ = Arrays.stream(r.get(i)).mapToDouble(Double::parseDouble).toArray(); //Разберись что это
                for (int j = 0; j < 4; j++) {
                    TableTXYZ[i][j] = arrayTXYZ[j];
                }
            }
            
                
//            System.out.println("Not Sorted Table:\n");
//            for (int i = 0; i < TableTXYZ.length; i++) {
//           
//                System.out.println(Arrays.toString(TableTXYZ[i]));
//            }
            
            
            
            for (int i = 0; i < TableTXYZ.length; i++) {
                for (int j = 0; j < TableTXYZ.length; j++) {
                    
                    if (TableTXYZ[i][1]>TableTXYZ[j][1]) {
                        double [] buf = TableTXYZ[i];
                        TableTXYZ[i] = TableTXYZ[j];
                        TableTXYZ[j] = buf;
                    }
                }
            }
//            System.out.println("Sorted Table:\n");
//            for (int i = 0; i < TableTXYZ.length; i++) {
//           
//                System.out.println(Arrays.toString(TableTXYZ[i]));
//            }

            File outputFile = new File("out.txt");
            FileWriter fr = new FileWriter(outputFile, true);
//            fr.append("T(K), X(m), Y(m)\n");
            
            List<double[]> row = new ArrayList<>();
            for (int i = 0; i < TableTXYZ.length-1; i++) {
                if ((TableTXYZ[i][1]-TableTXYZ[i+1][1])<0.0001) {
                    row.add(TableTXYZ[i]);
                } else {
                    row.add(TableTXYZ[i]);
                    double sumT = 0, sumX = 0, AveT = 0, AveX= 0;
                    for (double[] element : row) {
                        sumT = sumT + element[0];
                        sumX = sumX + element[1];
                        
                    }
                    AveT = (sumT/Double.valueOf(row.size()))-273.15;
                    AveX = (sumX/Double.valueOf(row.size()))*1000+const_x;
//                    AveT = (sumT/Double.valueOf(row.size()));
//                    AveX = (sumX/Double.valueOf(row.size()));
                    fr.append(Double.toString(AveX)+","+Double.toString(TableTXYZ[i][2]*1000)+",0,"+Double.toString(AveT)+"\n");
//                    System.out.printf("AveT=%f; AveX=%f; Y=%f\n",AveT,AveX,TableTXYZ[i][2]);
                    row.clear();
                }
            }
           fr.close();

            
    }
       
    }
}