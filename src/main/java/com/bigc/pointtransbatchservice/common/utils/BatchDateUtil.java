package com.bigc.pointtransbatchservice.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class BatchDateUtil {

    public Date getDateNow() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter.format(new Date());
        Date parsedDate = formatter.parse(dateStr);
        log.info("##Create date param: {}",parsedDate);
        return parsedDate;
    }

    public String getDateNowStr() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter.format(new Date());
        log.info("##Create date param: {}",dateStr);
        return dateStr;
    }
}
