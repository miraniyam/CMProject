package cm;

import java.io.UnsupportedEncodingException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

//import com.mysql.cj.xdevapi.Statement;
//import com.mysql.cj.jdbc.*;
import java.sql.*;

import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

public class Pay {

   private int WaitNum = 0;
   private CMClientStub payMessenger;

   private String cardNum;

   // ������
   public void Card(String cardNum) {
      this.cardNum = cardNum;
   }

   // ī�尪 ��ȯ �Լ�
   public String getcardNum() {
      return this.cardNum;
   }

   // ī�尪 ��ȣȭ �Լ�
   public void cardNumEncryption(String cardNum) throws NoSuchAlgorithmException, NoSuchPaddingException,
         InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
      String encryptionKey = "happyprogrammer!";
      Cipher cipher = Cipher.getInstance("AES");

      SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

      // ��ȣȭ
      byte[] encryptBytes = cipher.doFinal(cardNum.getBytes("UTF-8"));
      // System.out.println(new String(encryptBytes));

      // ��ȣȭ�� �̷���
      // cipher.init(cipher.DECRYPT_MODE, secretKeySpec);
      // byte[] decryptBytes = cipher.doFinal(encryptBytes);
      // System.out.println(new String(decryptBytes, "UTF-8"));

      // ��ȣȭ �� ������ �� �����
      this.cardNum = encryptBytes.toString();
   }

   // �̰͵� ī�尪 ��ȯ �Լ��ε� getcardNum�̶� �̰� �� �ϳ��� ������ �� �� ���׿�
   public String sendPayment() {
      // ȣ��Ʈ���� �� �Լ��� ȣ���ϸ� ���� ���� �� �ֱ� ������ chat ����� ������� �ʾҽ��ϴ�.
      return this.cardNum;
   }

   // ���� ���� Ŭ����
   class Payment {
      private String cardNum;
      private int price;

      // ������
      public void Payment(String cardNum, int price) {
         this.cardNum = cardNum;
         this.price = price;
      }

      // ���� ��ȯ �Լ�
      public int getPrice() {
         return this.price;
      }

      public String getcardNum() {
         return this.cardNum;
      }

      // ���� �Լ��� ī���� �����ؼ� �ϴºκ� �ε� ī�带 ������ ������ �ȴٰ� ����
      // target�� username �Դϴ�. CMWinClient 1292�� ����
      public void completeOrder(String target) {
         WaitNum++;
         payMessenger.chat(target, "�ֹ��� �Ϸ�Ǿ����ϴ�.\n" + "����ȣ(�ֹ���ȣ)�� " + WaitNum + "�Դϴ�.\n");
         savePayInfo(this.cardNum, this.price);
      }
   }

   public int getWaitNum() {
      return WaitNum;
   }

   // username�� �˾Ƽ� �ű⿡ ���̷�Ʈ�� �޽��� ���ִ� ����� server�� �ƴϰ� client�� �ִ��󱸿�
   // username�� �˸� �ٷ� �޽��� �� �� �ֽ��ϴ�.
   public void makeMessenger() {
      this.payMessenger = new CMClientStub();
   }

   /*
    * public void connectDB() { Connection connection = null; Statement st = null;
    * 
    * try { Class.forName("com.mysql.jdbc.Driver");
    * 
    * try { connection = DriverManager.getConnection(
    * "jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=UTC", "root", "rlarl123");
    * st = (Statement) connection.createStatement(); } catch (SQLException e) { //
    * TODO Auto-generated catch block e.printStackTrace(); }
    * 
    * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
    * e.printStackTrace(); } }
    */

   public void savePayInfo(String cardNum, int payPrice) {
      String sql;

      // ����ð� ����
      LocalDateTime localDateTime = LocalDateTime.now();

      sql = "INSERT INTO pay_history_table" + "VALUES(" + WaitNum + localDateTime + cardNum + payPrice + ")";
   }


}