package com.api.helper;

import com.api.entity.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Helper {


    //check that file is of Excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    }


    //convert excel to list of products

    public static List<ExcelObject> convertExcelToListOfProduct(InputStream is) {
        List<ExcelObject> excelObjectList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0;

            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.cellIterator();
                int cid = 0;
                ExcelObject obj = new ExcelObject();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0 -> obj.setCompanyId(cell.getStringCellValue());
                        case 1 -> obj.setCompanyName(cell.getStringCellValue());
                        case 2 -> obj.setCircleId(cell.getStringCellValue());
                        case 3 -> obj.setCircleName(cell.getStringCellValue());
                        case 4 -> obj.setDivisionId(cell.getStringCellValue());
                        case 5 -> obj.setDivisionName(cell.getStringCellValue());
                        case 6 -> obj.setSubDivisionId(cell.getStringCellValue());
                        case 7 -> obj.setSubDivisionName(cell.getStringCellValue());
                        case 8 -> obj.setPssId(cell.getStringCellValue());
                        case 9 -> obj.setPssName(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cid++;
                }
                excelObjectList.add(obj);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelObjectList;

    }

    public static String  convertExcelListToOutputJSON(List<ExcelObject> excelObjectList) {
        Set<String> companyIdSet = new HashSet<>();
        for (ExcelObject excel : excelObjectList) {
            companyIdSet.add(excel.getCompanyId());
        }
        return getCompanyJSON(companyIdSet, excelObjectList);

    }

    public static String getCompanyJSON(Set<String> companyIdSet, List<ExcelObject> excelObjectList) {
        List<Company> companyListForJSON = new ArrayList<>();
        for (String companyId : companyIdSet) {
            List<ExcelObject> companyTempList = excelObjectList.stream()
                    .filter(x -> x.getCompanyId().equals(companyId))
                    .collect(Collectors.toList());
            ExcelObject compNameObject = excelObjectList.stream()
                    .filter(x -> x.getCompanyId().equals(companyId))
                    .findAny()
                    .orElse(null);
            List<Circle> circleListForACompany = getCircleListForACompany(companyTempList);
            Company companyObject = new Company();
            companyObject.setCompanyId(companyId);
            companyObject.setCompanyName(compNameObject.getCompanyName());
            companyObject.setCircleList(circleListForACompany);
            companyListForJSON.add(companyObject);
        }

        JSONArray companyJsonArray = new JSONArray();
        for (Company company : companyListForJSON) {
            JSONObject companyJsonObject = new JSONObject();
            companyJsonObject.put("comp_id", company.getCompanyId());
            companyJsonObject.put("comp_name", company.getCompanyName());
            companyJsonObject.put("circles", company.getCircleList());
            companyJsonArray.put(companyJsonObject);
        }
        System.out.println("Json Array: " + companyJsonArray);
        return  companyJsonArray.toString();
    }

    public static List<Circle> getCircleListForACompany(List<ExcelObject> excelObjectList) {
        List<Circle> returnList = new ArrayList<>();
        Set<String> uniqueCircles = new HashSet<>();
        for (ExcelObject obj : excelObjectList) {
            uniqueCircles.add(obj.getCircleId());
        }
        for (String circleId : uniqueCircles) {
            List<ExcelObject> uniqueCircleObjectList = excelObjectList.stream()
                    .filter(x -> x.getCircleId().equals(circleId))
                    .collect(Collectors.toList());
            ExcelObject circleNameObject = excelObjectList.stream()
                    .filter(x -> x.getCircleId().equals(circleId))
                    .findAny()
                    .orElse(null);
            Circle circleObject = new Circle();
            circleObject.setCircleId(circleId);
            circleObject.setCircleName(circleNameObject.getCircleName());
            circleObject.setDivisionList(getDivisionListForACircle(uniqueCircleObjectList));
            returnList.add(circleObject);
        }
        return returnList;
    }

    public static List<Division> getDivisionListForACircle(List<ExcelObject> excelObjectList) {
        List<Division> returnList = new ArrayList<>();
        Set<String> uniqueDivisions = new HashSet<>();
        for (ExcelObject obj : excelObjectList) {
            uniqueDivisions.add(obj.getDivisionId());
        }
        for (String divId : uniqueDivisions) {
            List<ExcelObject> uniqueDivObjectList = excelObjectList.stream()
                    .filter(x -> x.getDivisionId().equals(divId))
                    .collect(Collectors.toList());
            ExcelObject divisionNameObject = excelObjectList.stream()
                    .filter(x -> x.getDivisionId().equals(divId))
                    .findAny()
                    .orElse(null);
            Division divisionObject = new Division();
            divisionObject.setDivisionId(divId);
            divisionObject.setDivisionName(divisionNameObject.getDivisionName());
            divisionObject.setSubDivisionList(getSubDivisionListForADivision(uniqueDivObjectList));
            returnList.add(divisionObject);
        }
        return returnList;
    }

    public static List<SubDivision> getSubDivisionListForADivision(List<ExcelObject> excelObjectList) {
        List<SubDivision> returnList = new ArrayList<>();
        Set<String> uniqueSubDivisions = new HashSet<>();
        for (ExcelObject obj : excelObjectList) {
            uniqueSubDivisions.add(obj.getSubDivisionId());
        }
        for (String subDivId : uniqueSubDivisions) {
            List<ExcelObject> uniqueSubDivObjectList = excelObjectList.stream()
                    .filter(x -> x.getSubDivisionId().equals(subDivId))
                    .collect(Collectors.toList());
            ExcelObject subDivisionNameObject = excelObjectList.stream()
                    .filter(x -> x.getSubDivisionId().equals(subDivId))
                    .findAny()
                    .orElse(null);
            SubDivision subDivisionObject = new SubDivision();
            subDivisionObject.setSubDivisionId(subDivId);
            subDivisionObject.setSubDivisionName(subDivisionNameObject.getSubDivisionName());
            subDivisionObject.setPssList(getPssListForASubDivision(uniqueSubDivObjectList));
            returnList.add(subDivisionObject);
        }
        return returnList;
    }

    public static List<Pss> getPssListForASubDivision(List<ExcelObject> excelObjectList) {
        List<Pss> returnList = new ArrayList<>();
        for (ExcelObject obj : excelObjectList) {
            Pss pssObject = new Pss();
            pssObject.setPssName(obj.getPssName());
            pssObject.setPssId(obj.getPssId());
            returnList.add(pssObject);
        }
        return returnList;
    }
}
