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

   // 생성자
   public void Card(String cardNum) {
      this.cardNum = cardNum;
   }

   // 카드값 반환 함수
   public String getcardNum() {
      return this.cardNum;
   }

   // 카드값 암호화 함수
   public void cardNumEncryption(String cardNum) throws NoSuchAlgorithmException, NoSuchPaddingException,
         InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
      String encryptionKey = "happyprogrammer!";
      Cipher cipher = Cipher.getInstance("AES");

      SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

      // 암호화
      byte[] encryptBytes = cipher.doFinal(cardNum.getBytes("UTF-8"));
      // System.out.println(new String(encryptBytes));

      // 복호화는 이렇게
      // cipher.init(cipher.DECRYPT_MODE, secretKeySpec);
      // byte[] decryptBytes = cipher.doFinal(encryptBytes);
      // System.out.println(new String(decryptBytes, "UTF-8"));

      // 암호화 된 값으로 덧 씌우기
      this.cardNum = encryptBytes.toString();
   }

   // 이것도 카드값 반환 함수인데 getcardNum이랑 이것 중 하나는 지워야 할 것 같네요
   public String sendPayment() {
      // 호스트에서 이 함수를 호출하면 값을 받을 수 있기 때문에 chat 기능을 사용하지 않았습니다.
      return this.cardNum;
   }

   // 결제 정보 클래스
   class Payment {
      private String cardNum;
      private int price;

      // 생성자
      public void Payment(String cardNum, int price) {
         this.cardNum = cardNum;
         this.price = price;
      }

      // 가격 반환 함수
      public int getPrice() {
         return this.price;
      }

      public String getcardNum() {
         return this.cardNum;
      }

      // 결제 함수는 카드사랑 연계해서 하는부분 인데 카드를 긁으면 결제가 된다고 가정
      // target은 username 입니다. CMWinClient 1292줄 참조
      public void completeOrder(String target) {
         WaitNum++;
         payMessenger.chat(target, "주문이 완료되었습니다.\n" + "대기번호(주문번호)는 " + WaitNum + "입니다.\n");
         savePayInfo(this.cardNum, this.price);
      }
   }

   public int getWaitNum() {
      return WaitNum;
   }

   // username을 알아서 거기에 다이렉트로 메시지 쏴주는 기능이 server가 아니고 client에 있더라구요
   // username만 알면 바로 메시지 쏠 수 있습니다.
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

      // 현재시간 삽입
      LocalDateTime localDateTime = LocalDateTime.now();

      sql = "INSERT INTO pay_history_table" + "VALUES(" + WaitNum + localDateTime + cardNum + payPrice + ")";
   }


}