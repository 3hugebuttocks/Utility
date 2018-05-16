package com.example.utility.retrofit;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by caoyouqiang on 18-4-28.
 */

public enum  OKHttpFactory {
    INSTANCE;
    private final OkHttpClient mOKHttpClient;
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 20;
    private static final String TAG = "OKHttpFactory";
    private static boolean isEncrypted = true;

    OKHttpFactory(){
        mOKHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .hostnameVerifier(getHostnameVerifier())
                .addInterceptor(requestInterceptor).build();
    }


    public OkHttpClient getOKHttpClient(){
        return mOKHttpClient;
    }

    private final Interceptor requestInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (!isEncrypted) {
                Request newRequest = originalRequest.newBuilder()
                        .addHeader("Accept", "*/*")
                        .addHeader("User-Agent", "Mozilla" + "//" + "5.0(X11;Ubuntu;Linux x86_64;rv:48.0)")
                        .build();
                Response response = chain.proceed(newRequest);
                return response;
            }

            String date = /*TimeUtil.getISO8601Timestamp()*/"";
            String canonical_request = originalRequest.method() + originalRequest.url() + date;
            try {
                String signature = /*HAMCHelper.get().encodeHMAC(canonical_request)*/ "";
                String authorization = ""/*String.format("HHMAC;key=%s;signature=%s;date=%s"
                        , HAMCHelper.api_key_id, signature, date)*/;
                Request newRequest = originalRequest.newBuilder()
                        .addHeader("Authorization", authorization)
                        .addHeader("_V", "3.0.1")
                        .addHeader("version", "v2")
                        .addHeader("Accept", "*/*")
                        .addHeader("User-Agent", "Mozilla" + "//" + "5.0(X11;Ubuntu;Linux x86_64;rv:48.0)")
                        .build();
                Response response = chain.proceed(newRequest);

                //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
                ResponseBody responseBody = response.body();
                //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                //获得返回的数据
                Buffer buffer = source.buffer();
                //使用前clone()下，避免直接消耗
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                Response response = chain.proceed(originalRequest);
                return response;
            }
        }
    };

    private HostnameVerifier getHostnameVerifier() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            HostnameVerifier hostnameVerifier = new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            return hostnameVerifier;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
