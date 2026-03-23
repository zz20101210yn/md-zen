package zen;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;

public class TestSM4 {
        public static void main(String[] args) {
                String plainText = "13726584806DEL";
                String key = "|5f3Q4-#\"Kf&h[E%";

                SM4 sm4 = SmUtil.sm4(key.getBytes());

                String encryptedHex = encrypt(sm4, plainText);
                System.out.println("加密结果(16进制): " + encryptedHex);

                String decrypted = decrypt(sm4, encryptedHex);
                System.out.println("解密结果: " + decrypted);

                List<String> encryptedList = java.util.Arrays.asList("4c0b0608756aed7d725f95eadbf17153");
                printDecryptedList(sm4, encryptedList);

                System.out.println(DateUtil.format(new Date(), "yyyy.MM.dd HH:mm:ss"));

                // ================= 解密 ================= 哲学基本理论
                // String decryptedText = sm4.decryptStr("9b4565aeb5797d81fe31c01afc40c503");
                // System.out.println("解密结果: " + decryptedText);

                // StringBuilder st = new StringBuilder("INSERT INTO `<table_name>` (`source`,
                // `order_sn`, `product_name`, `order_time`, `returned_date`, `returned_money`,
                // `order_status`, `product_unit_price`, `amount`, `total_price`, `status`,
                // `create_time`, `create_by`, `create_by_id`, `del_flag`, `payer`, `phone`)
                // VALUES ('启明星', '545606071545368576', '资金为王体验版', '2022-02-14 14:03:44',
                // '2022-02-14 14:03:44', 0.00, '已支付', 0.00, 1, 0.00, 1, '2025/09/03 00:00:00',
                // 'sync', 2155, 0, '用户1zz2', '^13916399540~');");
                // int sIdx = st.indexOf("^") + 1;
                // int eIdx = st.indexOf("~");
                // String mobile = st.substring(sIdx, eIdx);
                //
                // String encryptMobile = sm4.encryptHex(mobile);
                // System.out.println("手机号加密结果：" + encryptMobile);
                // st.replace(sIdx - 1, eIdx + 1, encryptMobile);
                //
                // System.out.println(st.toString());
                //
                // StringBuilder st1 = new StringBuilder();
                // st1.append("哈哈哈哈一二三四五");
                // System.out.println("清楚后字符串：" + st1.delete(0, st1.length()));

                // // =============== 使用CBC模式 ===============
                // // 需要初始向量IV (16字节)
                // String iv = "1234567890abcdef"; // 16字节IV
                // SM4 sm4Cbc = SmUtil.sm4(key.getBytes(), iv.getBytes());
                //
                // // CBC模式加密
                // String cbcEncrypted = sm4Cbc.encryptHex(plainText);
                // System.out.println("\nCBC模式加密结果: " + cbcEncrypted);
                //
                // // CBC模式解密
                // String cbcDecrypted = sm4Cbc.decryptStr(cbcEncrypted);
                // System.out.println("CBC模式解密结果: " + cbcDecrypted);

        }

        private static String encrypt(SM4 sm4, String text) {
                return sm4.encryptHex(text);
        }

        private static String decrypt(SM4 sm4, String hexText) {
                return sm4.decryptStr(hexText);
        }

        private static void printDecryptedList(SM4 sm4, List<String> encryptedValues) {
                if (encryptedValues == null || encryptedValues.isEmpty()) {
                        return;
                }
                for (String encrypted : encryptedValues) {
                        System.out.println(decrypt(sm4, encrypted));
                }
        }
}
