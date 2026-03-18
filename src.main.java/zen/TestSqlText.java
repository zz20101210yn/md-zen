package zen;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;

public class TestSqlText {
	static String key = "|5f3Q4-#\"Kf&h[E%";
    // 初始化SM4实例 (默认使用ECB模式/PKCS5Padding填充)
    static SM4 sm4 = SmUtil.sm4(key.getBytes());
	
	public static void main(String[] args) {
        // 文件路径
//        String filePath = "E:\\sql\\before.sql";
		//String filePath = "E:\\sql\\export.csv";
		String filePath = "D:\\1028合规数据\\csv\\启明星.csv";
        // 创建FileReader对象，指定文件路径
        FileReader fileReader = new FileReader(filePath);
        // 按行读取所有内容，返回List<String>
        List<String> lines = fileReader.readLines();
        System.out.println("读取完毕......" + lines.size());
        //加解密
        StringBuilder builder = new StringBuilder();
        List<String> processedLines = new ArrayList<String>();
        
        for (int i = 0; i < lines.size(); i ++) {
        	builder.delete(0, builder.length());
        	builder.append(lines.get(i));
        	processedSql(builder);
        	System.out.println("line " + i + ":" + builder.toString());
        	processedLines.add(builder.toString());
        }
        System.out.println("加密完毕......");
//        filePath = "E:\\sql\\after.sql";
        filePath = "D:\\1028合规数据\\csv\\启明星_export.csv";
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.writeLines(processedLines);
        
        System.out.println("写入完毕......");
    }
	
	private static void processedSql(StringBuilder sql) {

        int sIdx = sql.indexOf("^^^") + 3;
        int eIdx = sql.indexOf("^~^");
        if(sIdx == 2 && eIdx == -1) {
        	return;
        }
        String mobile = sql.substring(sIdx, eIdx);
        System.out.println(mobile);
//        String encryptMobile = sm4.encryptHex(mobile);
        String encryptMobile = sm4.decryptStr(mobile);
        System.out.println(encryptMobile);
        sql.replace(sIdx - 3, eIdx + 3, encryptMobile);
	}
}
