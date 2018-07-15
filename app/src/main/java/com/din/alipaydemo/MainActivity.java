package com.din.alipaydemo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.din.alipaydemo.alipyay.OrderInfoUtil2_0;
import com.din.alipaydemo.alipyay.PayResult;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018071460672067";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDXZeWmll/OIoLw\n" +
            "CceeQ9e8mtCbKsq1S/2v7nNfXQucSonH1LCFlwH3sNCIJFrVBiKN5cI0vafjI0O5\n" +
            "ZsXovpWbgzyVVlFNQxTUDeQ398+zMDCI7Mi2lEba5zhw9n6Yl76jdR/3H/G4oYJO\n" +
            "s8N0qJ4dpi3wJd0DqD1fWo9c05AMxg6oCQgqcZUAMjgHj1ip58MOfJRONTWnsyaS\n" +
            "MlCRrkWJk73/1BRadWsMUHngD7jsxWN/XrFmYjZ7wXP3K4WrUPp5FPYeUi3oUTBH\n" +
            "MGBp5LXIKC1O6FuHseG7ACmqIhyUdy59MoMqiNx4axJtft1b2TW9dZ+EqAsluOAj\n" +
            "utmEjhzrAgMBAAECggEAGBTVUwWW2nXzS6DcR25wTS7FycND/HbteuDe1zeQ171h\n" +
            "oAzAW38hgi6rcb0WxqAz9sdMKhdWEMfdh3ndAINmOIe4mz2WQ1wPlRGkhTlYaEEn\n" +
            "+/b1DayQK2MUZVS5nJfCgYXTuA5DORAdgG0Kmh1L6CavFh01k3YYvnNiec9RWIwL\n" +
            "ex1yHD9p+tR/XenSJg4QMXNfUZpPZ7B+x4FS2Kdw0e0bIIPE7J6akOjs/jpm0kW3\n" +
            "rvsX1wUQQSxEpWjhQJH15ww6d7uXdmfFPYZSAmxR6gJvo9N4cxwAQhhud8Pi6kqn\n" +
            "cbfizk69GbYXULrVMS+onjIJzZLYk2WXMMf30hE8wQKBgQD4KohMiJv/WzPtnfmt\n" +
            "mYM+aYl2xyXzSXdPHmElu7i9xR338R/vVqpW6uy77+xc+dc4qFesoeYtp57spmYg\n" +
            "WSCAuFEhXZLxKN4/NaqQATweC+u/X821OZwHt46yN4wkVWu2yHoqQEio6KqvqyL+\n" +
            "pvG19NsHoGTAibezO541m/sYiwKBgQDeMo+KEGYV3PMIiZ4NLYZLtdcsBzrUB6hh\n" +
            "rBDQe85G6EZ/vOocRDPsxVeoBpccecbunX2BAfIwguryT5CUVWV2Qj7MnQl59ife\n" +
            "nDZGDuxLESlCkLHAmBIrju5+mnBr0bMvcEM2bFh4TmiMKyL05HY7n0hhnA4/mFSF\n" +
            "HYdJ8Es5IQKBgQCqySqP8Rvaerub9bIUhKdL1nmh4p/9dDvFmVSmETCsePGM5sZh\n" +
            "gjUsJ/TPyefSfKZe+YuM5MZx+P9oQYPbEztJGo/K9Ijhqox418gP3qmuy+lsBNSe\n" +
            "uYzlyq9LeurZPmgonINmfGMEy0pcJtyof9+sp1vtPbbYwm5HzOmuXzBIzQKBgH1q\n" +
            "LmiwTt6iQq8b9hDcFEQhlBzuy76nv9u5Yhg6+Tg+nKQ2/jEMMf6/eHWHmr1g4u8G\n" +
            "B7PMZGYLTw+L50BCXy17wLR/Ir0rOl/E7kFm5OqN+irvPtVFQGRDncvp4fCJgUkS\n" +
            "ZW2Pg/QL1ajgJC/zz+RhSFqH2eb9VR95hkYuklbhAoGBAN51bVN4Q1Xe87NM0O5m\n" +
            "Sc/J3yDQJObbS3KJgtR0cox5iv/S1nzntFg87F0IVAAIt66g5aqi5SRITCnZ3e5w\n" +
            "Em/l1CHcTPire1ZRdsSMTSlktAKGyU3ECQ1xeHX4mifcQt+M/gmjoeGtc9x8wnze\n" +
            "vWclUQrVttFBj4ZdfKfgFU/n";
    public static final String RSA_PRIVATE = "" +
            "MIIEpAIBAAKCAQEA12XlppZfziKC8AnHnkPXvJrQmyrKtUv9r+5zX10LnEqJx9Sw\n" +
            "hZcB97DQiCRa1QYijeXCNL2n4yNDuWbF6L6Vm4M8lVZRTUMU1A3kN/fPszAwiOzI\n" +
            "tpRG2uc4cPZ+mJe+o3Uf9x/xuKGCTrPDdKieHaYt8CXdA6g9X1qPXNOQDMYOqAkI\n" +
            "KnGVADI4B49YqefDDnyUTjU1p7MmkjJQka5FiZO9/9QUWnVrDFB54A+47MVjf16x\n" +
            "ZmI2e8Fz9yuFq1D6eRT2HlIt6FEwRzBgaeS1yCgtTuhbh7HhuwApqiIclHcufTKD\n" +
            "KojceGsSbX7dW9k1vXWfhKgLJbjgI7rZhI4c6wIDAQABAoIBABgU1VMFltp180ug\n" +
            "3EducE0uxcnDQ/x27Xrg3tc3kNe9YaAMwFt/IYIuq3G9FsagM/bHTCoXVhDH3Yd5\n" +
            "3QCDZjiHuJs9lkNcD5URpIU5WGhBJ/v29Q2skCtjFGVUuZyXwoGF07gOQzkQHYBt\n" +
            "CpodS+gmrxYdNZN2GL5zYnnPUViMC3sdchw/afrUf13p0iYOEDFzX1GaT2ewfseB\n" +
            "UtincNHtGyCDxOyempDo7P46ZtJFt677F9cFEEEsRKVo4UCR9ecMOne7l3ZnxT2G\n" +
            "UgJsUeoCb6PTeHMcAEIYbnfD4upKp3G34s5OvRm2F1C61TEvqJ4yCc2S2JNllzDH\n" +
            "99IRPMECgYEA+CqITIib/1sz7Z35rZmDPmmJdscl80l3Tx5hJbu4vcUd9/Ef71aq\n" +
            "Vursu+/sXPnXOKhXrKHmLaee7KZmIFkggLhRIV2S8SjePzWqkAE8Hgvrv1/NtTmc\n" +
            "B7eOsjeMJFVrtsh6KkBIqOiqr6si/qbxtfTbB6BkwIm3szueNZv7GIsCgYEA3jKP\n" +
            "ihBmFdzzCImeDS2GS7XXLAc61AeoYawQ0HvORuhGf7zqHEQz7MVXqAaXHHnG7p19\n" +
            "gQHyMILq8k+QlFVldkI+zJ0JefYn3pw2Rg7sSxEpQpCxwJgSK47ufppwa9GzL3BD\n" +
            "NmxYeE5ojCsi9OR2O59IYZwOP5hUhR2HSfBLOSECgYEAqskqj/Eb2nq7m/WyFISn\n" +
            "S9Z5oeKf/XQ7xZlUphEwrHjxjObGYYI1LCf0z8nn0nymXvmLjOTGcfj/aEGD2xM7\n" +
            "SRqPyvSI4aqMeNfID96prsvpbATUnrmM5cqvS3rq2T5oKJyDZnxjBMtKXCbcqH/f\n" +
            "rKdb7T222MJuR8zprl8wSM0CgYB9ai5osE7eokKvG/YQ3BREIZQc7su+p7/buWIY\n" +
            "Ovk4PpykNv4xDDH+v3h1h5q9YOLvBgezzGRmC08Pi+dAQl8te8C0fyK9KzpfxO5B\n" +
            "ZuTqjfoq7z7VRUBkQ53L6eHwiYFJEmVtj4P0C9Wo4CQv88/kYUhah9nm/VUfeYZG\n" +
            "LpJW4QKBgQDedW1TeENV3vOzTNDuZknPyd8g0CTm20tyiYLUdHKMeYr/0tZ857RY\n" +
            "POxdCFQACLeuoOWqouUkSEwp2d3ucBJv5dQh3Ez4q3tWUXbEjE0pZLQChslNxAkN\n" +
            "cXh1+Jon3ELfjP4Jo6HhrXPcfMJ83r1nJVEK1bbRQY+GXXyn4BVP5w==\n";

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            if (msg.what == SDK_PAY_FLAG) {
                @SuppressWarnings("unchecked")
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            finish();
                        }
                    }).show();
            return;
        }
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}