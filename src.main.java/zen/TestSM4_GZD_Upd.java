package zen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.hutool.Hutool;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.LineHandler;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;

public class TestSM4_GZD_Upd {

	public static void main(String[] args) {
        
        // 128位密钥 (16字节) - 实际项目中应从安全渠道获取
        //String key = "|5f3Q4-#\"Kf&h[E%"; // 16个字符 = 16字节 = 128位
        String key = "|5f3Q4-#\"Kf&h[E%";
        
        // 初始化SM4实例 (默认使用ECB模式/PKCS5Padding填充)
        SM4 sm4 = SmUtil.sm4(key.getBytes());

        String filePath = "D:\\idno\\0206idno.csv";
        //-- 点石成金 犇牛战法 量价趋势擒龙 成林俱乐部 私颜社 主力掘金 万维龙头战法
        
        File file = FileUtil.file(filePath);
        
        try {
        	Set<String> set = new HashSet<String>(100000);
        	final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\idno\\chg\\0206idno.csv"), StandardCharsets.UTF_8));
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
				        	if(parts.length >= 4 && StringUtils.isNotBlank(parts[0]) && StringUtils.isNotBlank(parts[3])) {
				        		if(set.add(parts[0])) {
					        		writer.write("update we_substitute_customer_order set id_no = '" + parts[3] +"' where order_sn = '" + parts[0] + "';");
									writer.newLine();
									writer.flush();
				        		}
				        	}
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
