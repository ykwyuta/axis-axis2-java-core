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

package org.apache.axis2.util.threadpool;

import org.apache.axis2.AbstractTestCase;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;

public class TestThreadPool extends AbstractTestCase {
    /**
     * @param testName
     */
    public TestThreadPool(String testName) {
        super(testName);
    }

    class TestWorker implements Runnable {
        private boolean workDone;

        public void run() {
            workDone = true;
        }

        public boolean isWorkDone() {
            return workDone;
        }
    }


    public void testPool() throws AxisFault {
        ThreadPool tPool = new ThreadPool();
        List workerList = new ArrayList();

        for (int i = 0; i < 5; i++) {
            TestWorker worker = new TestWorker();
            workerList.add(worker);
            tPool.execute(worker);
        }

        tPool.safeShutDown();

        for (int i = 0; i < 5; i++) {
            assertEquals(true, ((TestWorker) workerList.get(i)).isWorkDone());
        }

    }

}
