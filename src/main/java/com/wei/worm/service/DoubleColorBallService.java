package com.wei.worm.service;

import com.wei.worm.dao.DoubleColorBallDao;
import com.wei.worm.entity.DoubleColorBall;
import com.wei.worm.utils.ImportExcel;
import com.wei.worm.utils.UploadFileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DoubleColorBallService{
    @Autowired
    private DoubleColorBallDao doubleColorBallDao;
    @Autowired
    private ImportExcel importExcel;
    @Autowired
    private UploadFileUtil uploadFileUtil;

    public void save(DoubleColorBall doubleColorBall){
        doubleColorBallDao.save(doubleColorBall);
    }





    public void doUpload(HttpServletRequest request) {
        String filePath=uploadFileUtil.uploadFile(request);
        Workbook workbook=importExcel.getWorkBook(filePath);
        List<Map<String, Object>> content = importExcel.readExcelContent(workbook);
        for (int i=0;i<content.size();i++){
            DoubleColorBall doubleColorBall=new DoubleColorBall();

            doubleColorBall.setMonthNum((int)content.get(i).get("期号"));
            doubleColorBall.setRed1((int)content.get(i).get("红1"));
            doubleColorBall.setRed2((int)content.get(i).get("红2"));
            doubleColorBall.setRed3((int)content.get(i).get("红3"));
            doubleColorBall.setRed4((int)content.get(i).get("红4"));
            doubleColorBall.setRed5((int)content.get(i).get("红5"));
            doubleColorBall.setRed6((int)content.get(i).get("红6"));
            doubleColorBall.setBlue((int)content.get(i).get("蓝球"));
            doubleColorBall.setPoolAmount((int)content.get(i).get("奖池奖金(元)"));
            doubleColorBall.setPrize1Count((int)content.get(i).get("一等奖注数"));
            doubleColorBall.setPrize1Amount((int)content.get(i).get("一等奖奖金(元)"));
            doubleColorBall.setPrize2Count((int)content.get(i).get("二等奖注数"));
            doubleColorBall.setPrize2Amount((int)content.get(i).get("二等奖奖金(元)"));
            doubleColorBall.setPutAmount((int)content.get(i).get("总投注额(元)"));
            doubleColorBall.setNoticeDate((Date) content.get(i).get("开奖日期"));
            doubleColorBallDao.save(doubleColorBall);
        }
    }
}