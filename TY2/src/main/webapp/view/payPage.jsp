
<%@ page language="java" pageEncoding="GBK"%>
<html>
<body>
<form action="https://www.yeepay.com/app-merchant-proxy/node" method="POST">
    <table align="center" width="600" border="6" cellspacing="0" cellpadding="2">
        <tr>
            <td align="center" colspan="4" bgcolor="#FFD2E9">
                <b>�����ţ�</b><input type="text" name="orderID">
                <b>Ӧ������</b><input type="text" name="amount" size="6"><b>Ԫ</b>
            </td>
        </tr>
        <tr>
            <td colspan="4"> </td>
        </tr>
        <tr>
            <td colspan="4" bgcolor="#C0C0C0">��ѡ������֧������</td>
        </tr>
        <tr>
            <td height="25" width="24%"><input type="radio" name="pd_FrpId" value="ICBC-NET">��������</td>
            <td height="25" width="24%"><input type="radio" name="pd_FrpId" value="CMBCHINA-NET">��������</td>
            <td height="25" width="24%"><input type="radio" name="pd_FrpId" value="ABC-NET">ũҵ����</td>
            <td height="25" width="28%"><input type="radio" name="pd_FrpId" value="CCB-NET">��������</td>
        </tr>
        <tr>
            <td height="25"><input type="radio" name="pd_FrpId" value="CEB-NET" >�������</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="BOCO-NET">��ͨ����</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="CMBC-NET">��������</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="SDB-NET">���ڷ�չ����</td>
        </tr>
        <tr>
            <td height="25"><input type="radio" name="pd_FrpId" value="BCCB-NET">��������</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="CIB-NET">��ҵ����</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="ECITIC-NET">��������</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="SPDB-NET">�ֶ���չ����</td>
        </tr>
        <tr>
            <td colspan="4"> </td>
        </tr>
        <tr>
            <td colspan="4" align="center" bgcolor="#FFDAB5">
                <input type="submit" value="  ȷ��֧��  "/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
