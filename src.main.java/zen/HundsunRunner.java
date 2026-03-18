package zen;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class HundsunRunner {
	public static void main(String[] args) {
		// 1. 生成 SM4 密钥（128位，16字节）
//        byte[] key = SmUtil.generateSm4Key();
//        System.out.println("SM4 密钥（16进制）：" + cn.hutool.core.codec.HexUtil.encodeHexStr(key));
        
		
		byte[] key = "|5f3Q4-#\"Kf&h[E%".getBytes();
        // 2. 初始化 SM4 加密对象（ECB 模式，PKCS5Padding 填充方式）
        SymmetricCrypto sm4 = SmUtil.sm4(key);
        
        // 3. 待加密内容
        String content = "15221010425";
        // 4. 加密（返回 Base64 编码字符串）
        String hex = sm4.encryptHex(content);
        System.out.println("加密后（Hex）：" + hex);
//        String encryptBase64 = sm4.encryptBase64(content);
//        System.out.println("加密后（Base64）：" + hex);
        
        // 5. 解密
        String decryptStr = sm4.decryptStr(hex);
        System.out.println("解密后：" + decryptStr);
        
        // ------------------------------
        // 模式2：使用 CBC 模式（需要初始化向量IV）
        // ------------------------------
        // 生成初始化向量（16字节）
//        byte[] iv = SmUtil.generateSm4Iv();
//        System.out.println("\nCBC模式初始化向量（16进制）：" + cn.hutool.core.codec.HexUtil.encodeHexStr(iv));
        
        
//        byte[] iv = "".getBytes();
//        // 初始化 CBC 模式的 SM4 对象
//        SM4 sm4Cbc = new SM4(Mode.CBC, Padding.PKCS5Padding, key, iv);
//        
//        // 加密
//        String encryptCbcBase64 = sm4Cbc.encryptBase64(content);
//        System.out.println("CBC模式加密后：" + encryptCbcBase64);
//        
//        // 解密
//        String decryptCbcStr = sm4Cbc.decryptStr(encryptCbcBase64);
//        System.out.println("CBC模式解密后：" + decryptCbcStr);
	}
}
