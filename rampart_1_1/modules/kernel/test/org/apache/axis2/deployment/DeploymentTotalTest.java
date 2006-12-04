/*
 * Copyright 2004,2005 The Apache Software Foundation.
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

package org.apache.axis2.deployment;

import junit.framework.TestCase;
import org.apache.axis2.AxisFault;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.phaseresolver.PhaseException;

import javax.xml.stream.XMLStreamException;

public class DeploymentTotalTest extends TestCase {
    AxisConfiguration er;

    public void testparseService1() throws PhaseException,
            DeploymentException,
            AxisFault,
            XMLStreamException {
        String filename = System.getProperty("basedir")+"/target/test-resources/deployment";
        er = ConfigurationContextFactory.createConfigurationContextFromFileSystem(filename, filename + "/axis2.xml")
                        .getAxisConfiguration();

        // OK, no exceptions.  Now make sure we read the correct file...
        Parameter param = er.getParameter("FavoriteColor");
        assertNotNull("No FavoriteColor parameter in axis2.xml!", param);

        assertEquals("purple", param.getValue());
    }

}
