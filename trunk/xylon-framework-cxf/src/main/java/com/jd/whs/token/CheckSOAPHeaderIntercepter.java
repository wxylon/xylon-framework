package com.jd.whs.token;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

import javax.xml.namespace.QName;

/**
 * **************************************************
 *
 * @author sunbingwei@360buy.com
 *         date: Feb 23, 2011
 *         <p/>
 *         ***************************************************
 */
public class CheckSOAPHeaderIntercepter extends AbstractPhaseInterceptor<SoapMessage> {

    Log logger = LogFactory.getLog(CheckSOAPHeaderIntercepter.class);
    private AuthHeader authHeader;

    public CheckSOAPHeaderIntercepter() {
        super(Phase.PRE_PROTOCOL);
        getAfter().add(SAAJInInterceptor.class.getName());
    }


    public void handleMessage(SoapMessage message) throws Fault {
 
        org.apache.cxf.binding.soap.SoapHeader soapHeader = (org.apache.cxf.binding.soap.SoapHeader) message.getHeader(new QName(authHeader.getqName(), authHeader.getKey()));
        if (soapHeader == null) {
            throw new IllegalArgumentException("Token null! ");
        }
        ElementNSImpl ei = (ElementNSImpl) soapHeader.getObject();
        String token;
        try {
             org.w3c.dom.Node node= ei.getFirstChild().getFirstChild() ;
            if(node!=null){
               token=node .getTextContent();
            }
            else{
                 token="";
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Token wrong! ");
        }
        logger.info("token:" + token);
        boolean b = authHeader.checkTokenValue(token);
        if (!b) {
            throw new IllegalArgumentException("Token wrong! ");
        }

    }

    public void setAuthHeader(AuthHeader authHeader) {
        this.authHeader = authHeader;
    }


}
