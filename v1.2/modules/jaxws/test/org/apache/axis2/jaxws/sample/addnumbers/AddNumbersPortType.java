/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.axis2.jaxws.sample.addnumbers;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;




/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_01-b15-fcs
 * Generated source version: 2.0
 * 
 */
@WebService(name = "AddNumbersPortType", targetNamespace = "http://org/test/addnumbers")
public interface AddNumbersPortType {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     * @throws AddNumbersFault_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://org/test/addnumbers")
    @RequestWrapper(localName = "addNumbers", targetNamespace = "http://org/test/addnumbers", className = "org.test.addnumbers.AddNumbers")
    @ResponseWrapper(localName = "addNumbersResponse", targetNamespace = "http://org/test/addnumbers", className = "org.test.addnumbers.AddNumbersResponse")
    public int addNumbers(
        @WebParam(name = "arg0", targetNamespace = "http://org/test/addnumbers")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "http://org/test/addnumbers")
        int arg1)
        throws AddNumbersFault_Exception
    ;

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "oneWayInt", targetNamespace = "http://org/test/addnumbers", className = "org.test.addnumbers.OneWayInt")
    public void oneWayInt(
        @WebParam(name = "arg0", targetNamespace = "http://org/test/addnumbers")
        int arg0);

}
