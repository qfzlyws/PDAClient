/**
 * PDAServicesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ft.pda;

public class PDAServicesServiceLocator extends org.apache.axis.client.Service implements com.ft.pda.PDAServicesService {

    public PDAServicesServiceLocator() {
    }


    public PDAServicesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PDAServicesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PDAServices
    private java.lang.String PDAServices_address = "http://localhost:8080/PDAWebServices/services/PDAServices";

    public java.lang.String getPDAServicesAddress() {
        return PDAServices_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PDAServicesWSDDServiceName = "PDAServices";

    public java.lang.String getPDAServicesWSDDServiceName() {
        return PDAServicesWSDDServiceName;
    }

    public void setPDAServicesWSDDServiceName(java.lang.String name) {
        PDAServicesWSDDServiceName = name;
    }

    public com.ft.pda.PDAServices getPDAServices() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PDAServices_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPDAServices(endpoint);
    }

    public com.ft.pda.PDAServices getPDAServices(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ft.pda.PDAServicesSoapBindingStub _stub = new com.ft.pda.PDAServicesSoapBindingStub(portAddress, this);
            _stub.setPortName(getPDAServicesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPDAServicesEndpointAddress(java.lang.String address) {
        PDAServices_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ft.pda.PDAServices.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ft.pda.PDAServicesSoapBindingStub _stub = new com.ft.pda.PDAServicesSoapBindingStub(new java.net.URL(PDAServices_address), this);
                _stub.setPortName(getPDAServicesWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PDAServices".equals(inputPortName)) {
            return getPDAServices();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://pda.ft.com", "PDAServicesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://pda.ft.com", "PDAServices"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PDAServices".equals(portName)) {
            setPDAServicesEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
