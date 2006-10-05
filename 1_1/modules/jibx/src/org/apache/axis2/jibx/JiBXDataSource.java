/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.axis2.jibx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMDataSource;
import org.apache.axiom.om.OMOutputFormat;
import org.apache.axiom.om.util.StAXUtils;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IXMLWriter;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.StAXWriter;

/**
 * Data source for OM element backed by JiBX data bound object.
 */
public class JiBXDataSource implements OMDataSource
{
    /** Marshaller index (only needed if object does not have a top-level
     mapping definition in the binding, <code>-1</code> if not used). */
    private final int marshallerIndex;
    
    /** Data object for output. */
    private final Object dataObject;
    
    /** Binding factory for creating marshaller. */
    private final IBindingFactory bindingFactory;
    
    /**
     * Constructor from marshallable object and binding factory.
     * 
     * @param obj
     * @param factory
     */
    public JiBXDataSource(IMarshallable obj, IBindingFactory factory) {
        marshallerIndex = -1;
        dataObject = obj;
        bindingFactory = factory;
    }
    
    /**
     * Constructor from object with mapping index and binding factory.
     * 
     * @param obj
     * @param index
     * @param factory
     */
    public JiBXDataSource(Object obj, int index, IBindingFactory factory) {
        marshallerIndex = index;
        dataObject = obj;
        bindingFactory = factory;
    }

    /**
     * Internal method to handle the actual marshalling.
     * 
     * @param ctx
     * @throws JiBXException
     */
    private void marshal(IMarshallingContext ctx) throws JiBXException {
        if (dataObject instanceof IMarshallable) {
            ((IMarshallable)dataObject).marshal(ctx);
        } else {
            IMarshaller mrsh = ctx.getMarshaller(marshallerIndex,
                bindingFactory.getMappedClasses()[marshallerIndex]);
            mrsh.marshal(dataObject, ctx);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.axiom.om.OMDataSource#serialize(java.io.OutputStream, org.apache.axiom.om.OMOutputFormat)
     */
    public void serialize(OutputStream output, OMOutputFormat format) throws XMLStreamException {
        try {
            IMarshallingContext ctx = bindingFactory.createMarshallingContext();
            ctx.setOutput(output, format.getCharSetEncoding());
            marshal(ctx);
        } catch (JiBXException e) {
            throw new XMLStreamException("Error in JiBX marshalling", e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.axiom.om.OMDataSource#serialize(java.io.Writer, org.apache.axiom.om.OMOutputFormat)
     */
    public void serialize(Writer writer, OMOutputFormat format) throws XMLStreamException {
        try {
            IMarshallingContext ctx = bindingFactory.createMarshallingContext();
            ctx.setOutput(writer);
            marshal(ctx);
        } catch (JiBXException e) {
            throw new XMLStreamException("Error in JiBX marshalling", e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.axiom.om.OMDataSource#serialize(javax.xml.stream.XMLStreamWriter)
     */
    public void serialize(XMLStreamWriter xmlWriter) throws XMLStreamException {
        try {
            IXMLWriter writer = new StAXWriter(bindingFactory.getNamespaces(),
                xmlWriter);
            IMarshallingContext ctx = bindingFactory.createMarshallingContext();
            ctx.setXmlWriter(writer);
            marshal(ctx);
        } catch (JiBXException e) {
            throw new XMLStreamException("Error in JiBX marshalling", e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.axiom.om.OMDataSource#getReader()
     */
    public XMLStreamReader getReader() throws XMLStreamException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        serialize(bos, null);
        return StAXUtils.createXMLStreamReader(new ByteArrayInputStream(bos.toByteArray()));
    }
}