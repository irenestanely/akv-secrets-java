package com.keyvault.secrets.quickstart;

import com.azure.identity.ManagedIdentityCredential;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.security.keyvault.certificates.CertificateClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.models.KeyVaultCertificateWithPolicy;
import com.azure.security.keyvault.certificates.models.KeyVaultCertificate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        try {
            ManagedIdentityCredential managedIdentityCredential = new ManagedIdentityCredentialBuilder()
                    .build();

            CertificateClient certificateClient = new CertificateClientBuilder()
                    .vaultUrl("https://dps-lite-vault.vault.azure.net/")
                    .credential(managedIdentityCredential).buildClient();

            System.out.printf("Cert   %s", certificateClient.getVaultUrl());

            KeyVaultCertificateWithPolicy certificateClientCertificate = certificateClient.getCertificate("pks-test");

            System.out.printf("keyVaultCertificate   %s", certificateClientCertificate.getKeyId());

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStream = new ByteArrayInputStream(certificateClientCertificate.getCer());
            Certificate certificate = certificateFactory.generateCertificate(inputStream);

            System.out.printf("Received certificate  %s", certificate.getEncoded());
            System.out.printf("Cert details  %s", certificate.getPublicKey());
        }
        catch(Exception e) {
            System.out.println("Errored out");
            e.printStackTrace();
        }
    }
}
