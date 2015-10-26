/*package com.firstdata.firstapi.client;

import javax.net.ssl.HttpsURLConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
//import org.apache.http.params.HttpParams;

@SuppressWarnings("deprecation")
public class SystemSSLDefaultHttpClient  {
    
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;

    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;

    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (60 * 1000);
    
    private HttpClient httpClient;

    
    public SystemSSLDefaultHttpClient() {
//        @SuppressWarnings("deprecation")
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        PlainSocketFactory plainSocketFactory = PlainSocketFactory.getSocketFactory();
        //Scheme currentScheme =  new org.apache.http.conn.scheme.Scheme("http", 80, socketFactory);
        Scheme plainScheme =  new org.apache.http.conn.scheme.Scheme("http", plainSocketFactory, 80);
        schemeRegistry.register(plainScheme);
        
        //SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSystemSocketFactory();
        SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        Scheme sslScheme =  new org.apache.http.conn.scheme.Scheme("http", sslSocketFactory, 80);
        schemeRegistry.register(sslScheme);

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

        //this.httpClient = new DefaultHttpClient(connectionManager);
        
        this.httpClient = new DefaultHttpClient(connectionManager, null);
//        setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);

    }

    public SystemSSLDefaultHttpClient(Boolean allAllHostnameVerifier) {
//      @SuppressWarnings("deprecation")
        System.out.println("allAllHostnameVerifier:"+allAllHostnameVerifier);
      SchemeRegistry schemeRegistry = new SchemeRegistry();
      //schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
      schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      org.apache.http.conn.ssl.SSLSocketFactory socketFactory;
      if(allAllHostnameVerifier) {
        //  @SuppressWarnings("unused")
		//javax.net.ssl.SSLSocketFactory wasSslFactory=(javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault();
          //socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(wasSslFactory, org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
          //socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(wasSslFactory, org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
          socketFactory = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
          HttpsURLConnection.setDefaultHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      }
      else {
          //socketFactory=org.apache.http.conn.ssl.SSLSocketFactory.getSystemSocketFactory();
    	  socketFactory=org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
      }
      //schemeRegistry.register(new Scheme("https", 443, socketFactory));
      schemeRegistry.register(new Scheme("https", socketFactory, 443));

      PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
      connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
      connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

      //this.httpClient = new DefaultHttpClient(connectionManager);
      this.httpClient = new DefaultHttpClient(connectionManager, null);
//      setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);

  }
    
    public static int getDefaultReadTimeoutMilliseconds() {
        return DEFAULT_READ_TIMEOUT_MILLISECONDS;
    }

    
    public HttpClient getHttpClient() {
        return httpClient;
    }
    
    
    
}
*/