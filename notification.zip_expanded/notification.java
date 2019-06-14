package cm;
import java.util.Iterator;
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
	//private CMServerStub m_serverStub;
	//private CMWinClient m_client; 
    private CMClientStub m_clientStub;
    
 
    
    public void alarmOrder(String userId, int waitNum, String cardNum, int payPrice) { //결제 내역, 대기번호를 사용자에게 전해줌  
    	String strMessage = userId +"," + Integer.toString(waitNum) + cardNum + Integer.toString(payPrice); 
    	m_clientStub.chat(userId, strMessage);
	}
    
	public void alarmComplete(String user) throws InterruptedException{  //제품 제작 완료시 알림 전송(랜덤 시간 이후에 해줌)
		
		double dValue = Math.random();
		int tValue = (int)(dValue * 10); 
		Thread.sleep(tValue*1000); 
		m_clientStub.chat(user, "we are done!");
		
	}
	
	public void alarmEventall(){  //모든 사용자에게 할인 이벤트 알림 메세지를 전송 
		String strText = "%%% 30% discount for every sale !!! %%%";
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
