 
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
 
public class WsChatServlet extends WebSocketServlet{
	private static final long serialVersionUID = 1L;
    private static ArrayList<MyMessageInbound> mmiList = new ArrayList<MyMessageInbound>();
    private MeetingUtil  meetingUtil = new MeetingUtil();
    
    public StreamInbound createWebSocketInbound(String protocol){
        return new MyMessageInbound();
    }
 
    private class MyMessageInbound extends MessageInbound{
        WsOutbound myoutbound;
 
        @Override
        public void onOpen(WsOutbound outbound){
            try {
                System.out.println("Open Client.");
                this.myoutbound = outbound;
                mmiList.add(this);
                
                //outbound.writeTextMessage(CharBuffer.wrap("Hello!"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        @Override
        public void onClose(int status){
            System.out.println("Close Client.");
            mmiList.remove(this);
        }
 
        @Override
        public void onTextMessage(CharBuffer cb) throws IOException{
            System.out.println("Accept Message : "+ cb);
            for(MyMessageInbound mmib: mmiList){
            	String m = meetingUtil.returnTable();
                //CharBuffer buffer = CharBuffer.wrap(meetingUtil.returnTable(cb.toString()).toCharArray());
                CharBuffer buffer = CharBuffer.wrap(m.toCharArray()); //
                mmib.myoutbound.writeTextMessage(buffer);
                mmib.myoutbound.flush();
                System.out.println(buffer.toString());
            }
        }
 
        @Override
        public void onBinaryMessage(ByteBuffer bb) throws IOException{
        	System.out.println("Accept Message onBinaryMessage ");
        }
    }
}