package com.util;

public class PaymentUtil {
    public static String buildHmac(String p0_Cmd, String p1_MerId,
                                   String p2_Order, String p3_Amt, String p4_Cur, String p5_Pid,
                                   String p6_Pcat, String p7_Pdesc, String p8_Url, String p9_SAF,
                                   String pa_MP, String pd_FrpId, String pr_NeedResponse,
                                   String keyValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(p0_Cmd);
        sb.append(p1_MerId);
        sb.append(p2_Order);
        sb.append(p3_Amt);
        sb.append(p4_Cur);
        sb.append(p5_Pid);
        sb.append(p6_Pcat);
        sb.append(p7_Pdesc);
        sb.append(p8_Url);
        sb.append(p9_SAF);
        sb.append(pa_MP);
        sb.append(pd_FrpId);
        sb.append(pr_NeedResponse);

        return DigestUtil.hmacSign(sb.toString(), keyValue);
    }
    public static boolean verifyCallback(String hmac, String p1_MerId,
                                         String r0_Cmd, String r1_Code, String r2_TrxId, String r3_Amt,
                                         String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid,
                                         String r8_MP, String r9_BType, String keyValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(p1_MerId);
        sb.append(r0_Cmd);
        sb.append(r1_Code);
        sb.append(r2_TrxId);
        sb.append(r3_Amt);
        sb.append(r4_Cur);
        sb.append(r5_Pid);
        sb.append(r6_Order);
        sb.append(r7_Uid);
        sb.append(r8_MP);
        sb.append(r9_BType);
        return hmac.equals(DigestUtil.hmacSign(sb.toString(), keyValue));
    }

}
