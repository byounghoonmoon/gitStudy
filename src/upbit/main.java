package upbit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class main {

	// 화폐종류 
	static String [] list = {"ADA","OMG","BTG","XRP","LTC","DASH","BTC","BCC","XMR","ETH","ZEC","NEO","ETC"};
	// 비교 원화 (한국/UDSDT)
	static String [] nation ={"KRW","USDT"}; 
	// 달러 원화 
	static double dollar = 1080.97;
	static String urlStr = "https://crix-api-endpoint.upbit.com/v1/crix/candles/ticks/60?code=CRIX.UPBIT.";
	
	static List<DataVO> result = new ArrayList<DataVO>();
	
	public static void main(String[] args) {
		
		main m = new main();
		System.out.println( " 암호화폐 정보 ");
		m.getPrice();
		System.out.println( " 암호화폐 정보 끝 ");
		
		
	}
	
	public void getPrice(){
		
		for(int i=0; i<list.length; i++)	{	// 화폐별
			DataVO temp = new DataVO();  
			temp.code = list[i];
			temp.a = getHttpsConnection(nation[0],list[i]);
			temp.b = getHttpsConnection(nation[1],list[i]);
			temp.diff = (temp.a / temp.b) - 1;
			result.add(temp);
		}

		Collections.sort(result, new Comparator<DataVO>(){
			@Override
			public int compare(DataVO o1, DataVO o2) {
				// TODO Auto-generated method stub
				
				if(o1.diff > o2.diff)
					return 1;
				else
					return -1;
			}
			
		});
		
		for( DataVO data : result)
		{
			System.out.print(data.code + " : ");  
			System.out.printf("%f ", data.a);
			System.out.print(" / ");
			System.out.printf("%.4f ", data.b); 
			System.out.print(" / 프리미엄 :  ");
			System.out.printf("%.4f \n", data.diff);  
		}
	}

	public Double getHttpsConnection(String nation, String code) {

		String result ="";
		Double price = 0.0;
		ObjectMapper mapper = new ObjectMapper();
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URL url = new URL(urlStr+nation+"-"+code);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
			
			BufferedReader br = new BufferedReader(in);
			result = br.readLine().replace("\"", "'");
			result = result.replace("'", "\"");
//			System.out.println(result);
			br.close();
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		List<DataVO> myObjects;
		try {
			myObjects = mapper.readValue(result,	new TypeReference<List<DataVO>>() {});
			
			if(nation.equals("USDT"))
				price =myObjects.get(0).tradePrice * dollar;
			else
				price =myObjects.get(0).tradePrice;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return price;

	}

}
