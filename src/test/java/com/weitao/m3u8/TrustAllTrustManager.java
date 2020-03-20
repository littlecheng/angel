package com.weitao.m3u8;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @title: TrustAllTrustManager
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/2021:46
 */
public class TrustAllTrustManager implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager
{
    @Override public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
        throws CertificateException
    {
        return;
    }

    @Override public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
        throws CertificateException
    {
        return;
    }

    @Override public X509Certificate[] getAcceptedIssuers()
    {
          return null;
    }
}
