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
package org.apache.axis2.addressing;

import junit.framework.TestCase;
import org.apache.axis2.context.MessageContext;

public class AddressingHelperTest extends TestCase {

    public void testIsReplyRedirectedNoReplyTo() {
        MessageContext mc = new MessageContext();
        assertFalse(AddressingHelper.isReplyRedirected(mc));
    }

    public void testIsReplyRedirectedAnonReplyTo() {
        MessageContext mc = new MessageContext();
        mc.setReplyTo(new EndpointReference(AddressingConstants.Final.WSA_ANONYMOUS_URL));
        assertFalse(AddressingHelper.isReplyRedirected(mc));
    }

    public void testIsReplyRedirectedNonAnonReplyTo() {
        MessageContext mc = new MessageContext();
        mc.setReplyTo(new EndpointReference("http://ws.apache.org/axis2"));
        assertTrue(AddressingHelper.isReplyRedirected(mc));
    }

    public void testIsFaultRedirectedNoFaultToOrReplyTo() {
        MessageContext mc = new MessageContext();
        assertFalse(AddressingHelper.isFaultRedirected(mc));
    }

    public void testIsFaultRedirectedAnonFaultTo() {
        MessageContext mc = new MessageContext();
        mc.setFaultTo(new EndpointReference(AddressingConstants.Final.WSA_ANONYMOUS_URL));
        assertFalse(AddressingHelper.isFaultRedirected(mc));
    }

    public void testIsFaultRedirectedNonAnonFaultTo() {
        MessageContext mc = new MessageContext();
        mc.setFaultTo(new EndpointReference("http://ws.apache.org/axis2"));
        assertTrue(AddressingHelper.isFaultRedirected(mc));
    }
}
