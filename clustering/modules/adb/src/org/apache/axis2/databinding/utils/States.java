package org.apache.axis2.databinding.utils;
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

public interface States {

    static int INIT_STATE = -1;
    static int STARTED_STATE = 0;
    static int START_ELEMENT_FOUND_STATE = 1;
    static int TEXT_FOUND_STATE = 2;
    static int END_ELEMENT_FOUND_STATE = 3;
    static int FINISHED_STATE = 4;
    static int ILLEGAL_STATE = 5;
    static int CONTENT_FOUND_STATE = 6;
    static int NULLED_STATE = 7;

}
