package net.github.rtc.micro.user.job;


import com.google.inject.Inject;
import net.github.rtc.micro.user.annotations.ForReport;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by ivan on 15.05.14.
 */
public class UserExportJob implements Job {

    @Inject
    private UserDao userDao;

    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        List<User> userList = userDao.findAll();
        //Export logic here
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("users");
        CreationHelper createHelper = wb.getCreationHelper();
        Field[] fields = User.class.getDeclaredFields();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yy"));

        int i = 0;
        Row row = sheet.createRow((short)0);
        for(Field f : fields){
            if(f.isAnnotationPresent(ForReport.class)){
                row.createCell(i).setCellValue(createHelper.createRichTextString(f.getName()));
                i++;
            }
        }

        i=1;
        for(User user : userList){
            sheet.createRow(i);
            for(int j =0; j < fields.length; j++){
                if(fields[j].isAnnotationPresent(ForReport.class)){
                    try {
                        fields[j].setAccessible(true);
                        if((fields[j].getType().equals(String.class))){
                            sheet.getRow(i).createCell(j).setCellValue(createHelper.createRichTextString((String)fields[j].get(user)));
                        }else if((fields[j].getType().equals(Date.class))){
                            sheet.getRow(i).createCell(j).setCellValue((Date) fields[j].get(user));
                            sheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        }else if((fields[j].getType().equals(Integer.class))){
                            sheet.getRow(i).createCell(j).setCellValue((Integer)fields[j].get(user));
                        }
                        fields[j].setAccessible(false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            i++;
        }

        File file;
        FileOutputStream fileOut = null;
        try {
            file = new File("/var/log/rtc-user-webservices/users.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
