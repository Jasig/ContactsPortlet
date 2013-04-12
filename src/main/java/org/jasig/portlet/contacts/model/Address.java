/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.portlet.contacts.model;

import java.io.Serializable;
import org.jasig.portlet.contacts.model.util.ContactAttributeType;


/**
 *
 * @author mfgsscw2
 */
public interface Address extends Serializable, TestableContactDependency {
            
    public String getLabel();
    public String getType();
    
    public String getInternal();
    public String getBuilding();
    public String getStreet();
    public String getLocality();
    public String getRegion();
    public String getPostCode();
    public String getCountry();
    
    
    public void setLabel(String label);
    public void setType(ContactAttributeType type);
    
    public void setInternal(String internal);
    public void setBuilding(String building);
    public void setStreet(String street);
    public void setLocality(String locality);
    public void setRegion(String region);
    public void setPostCode(String code);
    public void setCountry(String country);

    public String getDisplayType();
}
