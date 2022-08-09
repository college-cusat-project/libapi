package com.api.service;

import com.api.entity.ExcelObject;
import com.api.helper.Helper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    private static final String GET_URL = "http://localhost:8080/download";

    public String save(MultipartFile file) {
        String jsonString = new String();
        try {
            List<ExcelObject> excelObjectList = Helper.convertExcelToListOfProduct(file.getInputStream());
            jsonString  = Helper.convertExcelListToOutputJSON(excelObjectList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

/*
for(Pss pss : pssList) {
                System.out.println("PSS Id: " + pss.getPssId());
                System.out.println("PSS name: " + pss.getPssName());
                System.out.println("circle name: " + pss.getCircleName());
                System.out.println("circle Id: " + pss.getCircleId());
                System.out.println("divison Id: " + pss.getDivisonId());
                System.out.println("divison name: " + pss.getDivisonName());
                System.out.println("subdivison Id: " + pss.getSubDivisonId());
                System.out.println("subdivison name: " + pss.getSubDivisonName());

            }
 */

}
