package com.ft.pda;

public class PDAServicesProxy implements com.ft.pda.PDAServices {
  private String _endpoint = null;
  private com.ft.pda.PDAServices pDAServices = null;
  
  public PDAServicesProxy() {
    _initPDAServicesProxy();
  }
  
  public PDAServicesProxy(String endpoint) {
    _endpoint = endpoint;
    _initPDAServicesProxy();
  }
  
  private void _initPDAServicesProxy() {
    try {
      pDAServices = (new com.ft.pda.PDAServicesServiceLocator()).getPDAServices();
      if (pDAServices != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pDAServices)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pDAServices)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (pDAServices != null)
      ((javax.xml.rpc.Stub)pDAServices)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ft.pda.PDAServices getPDAServices() {
    if (pDAServices == null)
      _initPDAServicesProxy();
    return pDAServices;
  }
  
  public java.lang.String PDACall(java.lang.String function, java.lang.String args, java.lang.String dbstring) throws java.rmi.RemoteException{
    if (pDAServices == null)
      _initPDAServicesProxy();
    return pDAServices.PDACall(function, args, dbstring);
  }
  
  
}