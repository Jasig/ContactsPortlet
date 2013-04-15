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
package org.jasig.portlet.contacts.adapters.impl.ldap;

import java.util.Map;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portlet.contacts.model.Address;
import org.jasig.portlet.contacts.model.Contact;
import org.jasig.portlet.contacts.model.EmailAddress;
import org.jasig.portlet.contacts.model.ModelObjectFactory;
import org.jasig.portlet.contacts.model.PhoneNumber;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.util.StringUtils;

/**
 *
 * @author mfgsscw2
 */
public class ConfigurableContactAttributesMapper implements AttributesMapper {

    final static Log logger =  LogFactory.getLog(ConfigurableContactAttributesMapper.class);
    // Prefix to identify the LDAP attribute name value is instead a default value to apply.
    
    Map<String, Object> config;
    ModelObjectFactory factory;
    
    public ConfigurableContactAttributesMapper(Map<String, Object> config, ModelObjectFactory factory) {
        this.config = config;
        this.factory = factory;
    }
    
    
    
    @Override
    public Object mapFromAttributes(Attributes attrs) throws NamingException {
        Contact contact = factory.getObjectOfType(Contact.class);
        
        for(String key : config.keySet()) {

            if (key.equalsIgnoreCase("address")) {
                Address addr = populate(Address.class, (Map<String,String>)config.get(key), attrs);
                if (addr != null)
                    contact.getAddresses().add(addr);
            } else if (key.equalsIgnoreCase("phone")) {
                PhoneNumber phone = populate(PhoneNumber.class, (Map<String,String>)config.get(key), attrs);
                if (phone != null)
                    contact.getPhoneNumbers().add(phone);
            } else if (key.equalsIgnoreCase("email")) {
                EmailAddress email = populate(EmailAddress.class, (Map<String,String>)config.get(key), attrs);
                if (email != null)
                    contact.getEmailAddresses().add(email);
            } else {
                try {
                    String method = "set" + StringUtils.capitalize(key);
                    Contact.class.getMethod(method, String.class).invoke(contact, attrs.get((String)config.get(key)));
                } catch (Exception ex) {
                    logger.fatal(ex);
                }
            }
        }
        
        return contact;        
    }
    
    private <T> T populate(Class<T> clazz, Map<String,String> conf, Attributes attrs) {
        T obj = factory.getObjectOfType(clazz);
        for (String key : conf.keySet()) {
            try {
                String method = "set" + StringUtils.capitalize(key);
                obj.getClass().getMethod(key, String.class).invoke(obj, getValue(attrs.get((String)config.get(key))));
            } catch (Exception ex) {
                logger.fatal(ex);
            }
        }
        
        return obj;
    }
    
    
    private String getValue(Attribute attribute) throws javax.naming.NamingException {        
        if(attribute != null) {
            String value = (String)attribute.get();
            if(value != null && !value.equalsIgnoreCase("empty")) {
                return value;
            }
        }
        return "";
    }
}
