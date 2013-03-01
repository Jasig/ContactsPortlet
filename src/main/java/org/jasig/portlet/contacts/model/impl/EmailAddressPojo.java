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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasig.portlet.contacts.model.impl;

import org.apache.commons.lang.StringUtils;
import org.jasig.portlet.contacts.model.EmailAddress;

/**
 *
 * @author mfgsscw2
 */
public class EmailAddressPojo implements EmailAddress {

    private String label = null;
    private String type = null;
    private String address = null;
    
    public String toString() {
        return "EMAIL:"+type + ":" + label + ":"+address;
    }
    
    public String getLabel() {
        return label;
    }

    public String getEmailAddress() {
        return address;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setEmailAddress(String email) {
        this.address = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDisplayType() {
        return label != null ? label : type.toString();
    }

    /**
     * Usable entries must have something for an address plus a type.
     *
     * @return True if populated.
     */
    @Override
    public boolean isPopulated() {
        return StringUtils.isNotBlank(address) && EmailAddressType.getType(type) != null;
    }
}
