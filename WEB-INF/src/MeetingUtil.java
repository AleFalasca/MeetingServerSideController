import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MeetingUtil {
	
	HashMap<String, String> couple = new HashMap<String, String>();
	
	
	public MeetingUtil() {
		initMap();
		startList();
	}
	public String returnTable () throws IOException {
		HashMap<String, String> tabs = new HashMap<String, String>();
		for (String chiave: couple.keySet()){
			System.out.println(chiave);
			String s = couple.get(chiave);
			//System.out.println(s);
			//System.out.println(chiave);
			Document doc2;			
				doc2 = Jsoup.connect(s).get();
				Elements table = doc2.select("#datatableframe");
				table.select("#buttontable").remove();
				String valore = table.html();
				//System.out.println(valore);
				tabs.put(chiave, valore);
		}
		Gson gson = new Gson();
		String json = gson.toJson(tabs);
		return json;
    }

	private void initMap() {
		// Do required initialization
	    //  message = "Hello World";
		  
	        Document doc;
			try {
				doc = Jsoup
				        .connect(
				                "http://www.rietimeeting.com/results/timetable.html")
				        .get();
				Elements tables = doc.select("#timetabledata");
				String gender="Women";
				
				for (Element t : tables) {
					if (couple.get(gender +"-"+t.text())!=null){
						gender="Man";
					}
					
					couple.put(gender +"-"+t.text(), t.childNode(0).attr("abs:href"));

				}
	        couple.remove("Man-Overall Result Summary (PDF)");
	    
	        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
	}
	private void startList(){
		HashMap<String, String> coupleTmp = new HashMap<String, String>();
		HashMap<String, String> couple2 = couple;
		for (String s: couple.keySet()){
		String url = couple2.get(s);
		System.out.println(url.length());
		char [] urlC = url.toCharArray();
		urlC[36] = 's';
		urlC[37] = 'h';
		String urlS = new String(urlC);
		System.out.println(urlS);
		//System.out.println(urlS);
		coupleTmp.put("Start-"+s, urlS);
		
		System.out.println(coupleTmp);
		}
		couple.putAll(coupleTmp);
		couple.remove("Start-Women-Women Hammer-Qualification");
		couple.remove("Start-Women-Men Hammer-Qualification");
		}
		}


