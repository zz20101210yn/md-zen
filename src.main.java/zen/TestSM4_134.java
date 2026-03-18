package zen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.LineHandler;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;

public class TestSM4_134 {
	public static void main(String[] args) {
        
        // 128位密钥 (16字节) - 实际项目中应从安全渠道获取
        //String key = "|5f3Q4-#\"Kf&h[E%"; // 16个字符 = 16字节 = 128位
        String key = "|5f3Q4-#\"Kf&h[E%";
        
        // 初始化SM4实例 (默认使用ECB模式/PKCS5Padding填充)
        SM4 sm4 = SmUtil.sm4(key.getBytes());

        //String filePath = "D:\\mobile\\chg\\1205refund.csv";
        //String filePath = "D:\\mobile\\chg\\12081600.csv";
        //String filePath = "D:\\mobile\\chg\\12091400.csv";
        String filePath = "E:\\mobile\\chg\\134.csv";
        //-- 点石成金 犇牛战法 量价趋势擒龙 成林俱乐部 私颜社 主力掘金 万维龙头战法
        
        File file = FileUtil.file(filePath);
        
        try {
        	final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\mobile\\chg\\out\\134.csv"), StandardCharsets.UTF_8));
			FileUtil.readLines(file, Charset.forName("utf-8"), new LineHandler() {
			    private int lineNum = 0;
			    @Override
			    public void handle(String line) {
			        lineNum++;
			        if (line.trim().isEmpty()) {
			            System.out.println("忽略空行");
			            return;
			        }
			        String lineString =  line.replace("\"", "");
			        String[] parts = lineString.split(",");
			        if(lineNum != 1) {
			        	try {
				        	System.out.println(parts[2]);
				        	if(parts.length == 3) {
				        		String mobile = sm4.decryptStr(parts[2]);
				        		if(mobile.startsWith("134") && mobile.endsWith("73")) {
				        			writer.write(lineString + "," + mobile + "," + "134**73");
									writer.newLine();
									writer.flush();
				        		}
				        		//writer.write(lineString + "," + sm4.decryptStr(parts[2]));
				        	}else {
				        		//writer.write(lineString);
				        	}
			        		
//				        	writer.write(line + "," + sm4.decryptStr(parts[0]));

						} catch (IOException e) {
						}
			        }
			        
			        System.out.println("row: " + lineNum);
			    }
			});
		} catch (IORuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("文件处理完成");
    }

}
