package com.allinpay.generator;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.allinpay.util.DateUtil;
import com.allinpay.util.FileUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author nilomiao
 * 
 */
public class AgentPaymentBatchFileGenerator {

	public File createEmptyGeneratedFile(String fileName) {
		File file = new File("E:/RPT_ORG_SUMMARY"
				+ DateUtil.formatCurrDateTime(DateUtil.DF_YMDHMS) + ".csv");
		return file;
	}

	public static void main(String[] args) {
		String templateFile = "ccb-c.ftl";
		String fileName = "E:/template/RPT_ORG_SUMMARY"
				+ DateUtil.formatCurrDateTime(DateUtil.DF_YMDHMS) + ".txt";
		
		Map model = new HashMap();
		String date = DateUtil.formatCurrDateTime(DateUtil.DF_YYYY_MM_DD);
		model.put("tlAccountNo", "100010001000");
		model.put("today", DateUtil.date2string(new Date()));
		List resultList = new ArrayList();
		AgentPaymentDetail paymentBatchDetail = new AgentPaymentDetail();
		paymentBatchDetail.setMerchantId("1000200912180001");
		paymentBatchDetail.setAccountName("name");
		paymentBatchDetail.setAccountNo("no");
		paymentBatchDetail.setAmount(10000L);
		paymentBatchDetail.setFee(888l);
		resultList.add(paymentBatchDetail);
		AgentPaymentDetail paymentBatchDetail1 = new AgentPaymentDetail();
		paymentBatchDetail.setMerchantId("1000200912180002");
		paymentBatchDetail1.setAccountName("name2");
		paymentBatchDetail1.setAccountNo("no2");
		paymentBatchDetail1.setAmount(8898l);
		paymentBatchDetail1.setFee(888l);
		resultList.add(paymentBatchDetail1);
		model.put("resultList", resultList);
		Writer writer = null;
		FileWriter fw = null;
		try {
			writer = new CharArrayWriter();
			Template template = getTemplate(templateFile);
			template.process(model, writer);
			writer.flush();
			File file = new File(fileName);
			file.delete();
			OutputStream os = new FileOutputStream(file);
			FileUtil.save(os, writer.toString().getBytes("utf-8"));
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Read template.
	 * @param templateFilePath
	 * @return
	 * @throws IOException
	 */
    protected static Template getTemplate(String templateFile) throws IOException  {
    	Configuration conf = Configuration.getDefaultConfiguration();
    	conf.setDirectoryForTemplateLoading(new File("E:\\dev-workspace\\ets\\CodeGenerator\\test\\src\\main\\java\\com\\allinpay\\generator"));
    	conf.setEncoding(Locale.CHINA, "utf-8");
    	conf.setOutputEncoding("gb2312");
        return conf.getTemplate(templateFile);
    }
}
