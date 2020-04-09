package com.gomai.order.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    private static String neturl = "http://api.gomai.com/api/order";
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101800718676";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCN8q1i8vxuHE4JVNqC4XQsvnxrD0Tvo1S1pd1O1xMwmC8w8GpyANv0RI3XMzAU+IFtTHlfMhVfoeGLKZ5P/v+OkRQ58JlFqsLjCdBLURtPsAj9kJw+86yhaqpYbcieuoBMjsNqilTJD8JBNgwLKZnWG3QZueRvXLlY3g+juoTjCLM9ojcL/SFHFLXaZkA4HiLWZV6uKVoR6ECEk0N1xf3jL2UHwiP4/AQrYp/SR6bFDQ28kfuJbnWt+FJvLheqOaXgBGpcwG83HHDefKayaiBFv8mNvsaPVZ0H1H4ln0RUoXqW+xrHHojxSNiHuSmp4nPnvEsYv1E7zgZYZ3wSDhlzAgMBAAECggEAD/rsDiGwzWyeVGqbFvTusULYmuzjI99NYA3GNmP8IxJe7bGDKkZyfLNjDIEUvqejWnyrhU4MHfIJhDMxQWxBtPkcP9GYT8GDoy+tpW0CGByHRpSLIoMoQhvSBZaGaWgxnWzNDyd3mBMQGWtOO8t5KCN7FJJa5zaXHywQsqwC3dQmsGshMVjqeh/KGuCBX1fzLCFGCShd9zV7qzDe6PE399VlMDGmFRvct9M13BMfDPVZMDZoQKINbcnkinRg4CWSIkCJX+Xh0vG4VtWrLstJPdSsLbSZ0ASdI6vQRwfLjhdughUznqI2CnnVle0IALvcuszIx+uMa09S5lY8upnqkQKBgQDzuHoc3EttsT7W14PnLFvjOiVtIUSS5quEcxXQUW1WiArlFu1gWNK3ASZHy3Thpg/MLHEVmcSu/UzZbayTWFxbYaYs5/ctq3G7Dkz7yLH3sxKWJwz5589ii58vOqW07RbdwrqRQqUiyVTJo88/DQVjGhA5SoU/DPaQ0jayDoHejwKBgQCVGYfP6qRQc9KIPmeCV2xjamiIv4nUtzoi9V27hyk5mvBq7ZwgumUOlMKilQzQo1AKM1xan5H4qQ9SRWmHfmadbWmQge19eYBD2Li5H3wPjURGQ3vlVnShmNXkKIawt94BVKTS8ZHfS/lUgJZd4wbuA2awR8vb9I7u+mN68XuI3QKBgQCs5FIkBRaxIwqb59jXdn1czVPdfbsipuZHmEiCNjFlMC7iYwhTz87zKaqZRbiNlnsgcOpr02BWbrREjevdvtINtVDoDEopqLSOaM+t0aqGOA9YWy12Xr6cfsWXAHiqx4HwIQKup2N0MPSn8j/Dz53htoyJx9aMasrC440PdQEl+wKBgFpjOdQv2SD0tiLmQfXgbdLvBSQxiass0M+0k/hFfhZv+WO5XDbzCKSIRl4tobEx8Cne7RP7PJDUXHdxoQIE/MxfMenq+kBg0gNPdNy1W45KA6Pc8VUczmPQtYo1j0G+lZWU7L78t4P5GNPDiGSuZtsPsMcZH1rerW/u1zQHBO75AoGAcDQkP/IZESbxNBqn9DypM1zOV3z4hAfUvWjfhJi9yMbZKdltduO2vvODfqbEbXZ/Fl0+HJ0rcBOg199DunBqJTwNjyQBkgaKohYZ8K1uyOkk4emYpnQYHV+H9DAkqZOGeJW5kyhLeVyaGmPrDriGQ85fqEsHAC7BdcelGUfrOVg=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk6c645DuWirXfuRqdjJgScm7BVh9Vc2kNbo7Juin4O3mZ6JBlKVdeqWJJAk2c/GU7g2GJ9TFCJTWz97YDBGG2uoXej4p11wYhJyfkFLOZGduypA71WesnD+k35+TR9mUl3R9bMzPDZ/whrEc0r+yHMyYNNT4u3taghjBM0SZ7DN4SFLRas7ncVxw8L78IcUdDFObghvK4s3iRFMjT7WDqJWbm1+F8YUC2uyBOUoIqXdWZFg1QLAVkV0IiwhqxE+vGmmzitYQ2NcXUwKdO7DKj61omwcDkJ34wj0KU8n0IvLfu/jMdZ6mnGhE0OjeYUeorAElLgBm4MljH4LcYNjXiwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url  = neturl+"/alipay/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = neturl+"/alipay/return_url";
//      public static String return_url ="http://www.gomai.com/buyOrder" ;

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
