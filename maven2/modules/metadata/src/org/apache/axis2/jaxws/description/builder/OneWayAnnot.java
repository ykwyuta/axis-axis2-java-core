/* Copyright 2004,2005 The Apache Software Foundation.
 * Copyright 2006 International Business Machines Corp.
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

package org.apache.axis2.jaxws.description.builder;

import java.lang.annotation.Annotation;

public class OneWayAnnot implements javax.jws.Oneway {

		/**
	     * A OneWayAnnot cannot be instantiated.
	     */
		private	OneWayAnnot()
		{
		}
		
	    public static OneWayAnnot createOneWayAnnotImpl() {
	        return new OneWayAnnot();
	    }

	    public Class<Annotation> annotationType(){
			return Annotation.class;
		}
}