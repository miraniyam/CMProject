package cm;
import java.io.*;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;

import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.event.CMEvent;
import kr.ac.konkuk.ccslab.cm.event.CMEventHandler;
import kr.ac.konkuk.ccslab.cm.event.CMFileEvent;
import kr.ac.konkuk.ccslab.cm.event.CMInterestEvent;
import kr.ac.konkuk.ccslab.cm.event.CMMultiServerEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSNSEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.event.CMUserEvent;
import kr.ac.konkuk.ccslab.cm.event.CMUserEventField;
import kr.ac.konkuk.ccslab.cm.info.CMConfigurationInfo;
import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;
import kr.ac.konkuk.ccslab.cm.manager.CMFileTransferManager;
import kr.ac.konkuk.ccslab.cm.manager.CMInteractionManager;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;
import kr.ac.konkuk.ccslab.cm.stub.CMServerStub;

public class notification {
	private CMWinServer m_server;
	private CMServerStub m_serverStub;
	//private CMWinClient m_client; 
    private CMClientStub m_clientStub;
    
 
    
    public String alarmOrder(String userId, int waitNum, String cardNum, int payPrice) { //寃곗젣 �궡�뿭, ��湲곕쾲�샇瑜� �궗�슜�옄�뿉寃� �쟾�빐以�  
    	String strMessage = userId +"의 주문 :" + Integer.toString(waitNum) + " / 카드 번호 : "+ cardNum + " / 결제금액 : "+ Integer.toString(payPrice); 
    	return strMessage;
	}
    
	public String alarmComplete(String user) throws InterruptedException{  //�젣�뭹 �젣�옉 �셿猷뚯떆 �븣由� �쟾�넚(�옖�뜡 �떆媛� �씠�썑�뿉 �빐以�)
		
		double dValue = Math.random();
		int tValue = (int)(dValue * 10); 
		Thread.sleep(tValue*1000);
		return "주문이 완료됐습니다 ! 음식을 받아가세요 !";
		
	}
	
	public void alarmEventall(){  //紐⑤뱺 �궗�슜�옄�뿉寃� �븷�씤 �씠踰ㅽ듃 �븣由� 硫붿꽭吏�瑜� �쟾�넚 
		String strText = "%%% [회원 전체 메세지] 30% 할인행사 시작 !!! 우리 매장에 찾아오세요 !! %%%";
		//m_serverStub.broadcast()
		//m_clientStub.chat(strTarget, strMessage);
		m_server.printMessage(strText);
		
	}

	public void processEvent(CMEvent cme) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	private void setMessage(String strText)
	{
		m_outTextArea.setText(strText);
		m_outTextArea.setCaretPosition(m_outTextArea.getDocument().getLength());
	}
	*/

}
