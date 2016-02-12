/*******************************************************************************
 *  Copyright FUJITSU LIMITED 2016 
 *******************************************************************************/

package org.oscm.reportingservice.business;

public class Formatting {
    public static String nameAndId(String name, String id) {
        return (null != name && !name.trim().isEmpty()) ? name + " (" + id
                + ")" : id;
    }
}
