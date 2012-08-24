package com.xylon.framework.poi.jxls;

import java.util.*;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 
 * @author wxylon@gmail.com
 * @date 2012-8-24
 */
public class JxlsUtils {

    public static void generateExcel(String templateFile, Map data, String destFile) {

        try {
            XLSTransformer transformer = new XLSTransformer();
            transformer.transformXLS(templateFile, data, destFile);
        } catch (Exception e) {
            throw new RuntimeException("µ¼³öExcel³ö´í!", e);
        }
    }

}
