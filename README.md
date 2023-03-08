# Certifcate access from keyvault

## Prerequisities
1. Az login to your subscription.
2. Create a user assigned managed identity.
3. Keyvault with role "Keyvault Administrator" to the created managed identity.
4. ![Create a private endpoint in the keyvault](https://learn.microsoft.com/en-us/azure/key-vault/general/private-link-service?tabs=portal)
5. Keyvault - Access configuration - has to be changed to role based access.
6. If you are using VM in the same virtual network to deploy this code, Add identity in the virtual machine to access user assigned managed identity.
7. DNS name(SAN) should match the CN host name of the cert in keyvault.

